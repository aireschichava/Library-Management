#!/bin/bash
export PATH="/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"
set -uo pipefail

ROOT_DIR=$(cd "$(dirname "$0")" && pwd)

# Location of po-uilib.jar expected by Makefile and run.sh
# Adjust if your jar lives elsewhere.
PO_UILIB_JAR="${ROOT_DIR}/../po-uilib.jar"

if [[ ! -f "${PO_UILIB_JAR}" ]]; then
  echo "[ERROR] po-uilib.jar not found at: ${PO_UILIB_JAR}"
  echo "- Put po-uilib.jar in the parent folder of this repo or edit bci-app/Makefile (PO_UILIB_DIR)."
  exit 1
fi

echo "[1/3] Building bci-core …"
make -C "${ROOT_DIR}/bci-core" clean all

echo "[2/3] Building bci-app …"
make -C "${ROOT_DIR}/bci-app" clean all

BASE_CP="${ROOT_DIR}/bci-app/bci-app.jar:${ROOT_DIR}/bci-core/bci-core.jar:${PO_UILIB_JAR}"

TEST_DIR="${ROOT_DIR}/auto-tests"
EXP_DIR="${TEST_DIR}/expected"

if [[ ! -d "${TEST_DIR}" ]]; then
  echo "[WARN] No auto-tests directory found. Skipping."
  exit 0
fi

echo "[3/3] Running auto-tests …"
total=0
passed=0

shopt -s nullglob
for infile in "${TEST_DIR}"/*.in; do
  testname=$(basename "${infile}" .in)
  expfile="${EXP_DIR}/${testname}.out"
  # Extract group (e.g., A-03 from A-03-04-M-ok)
  group=$(echo "$testname" | cut -d'-' -f1-2)
  resultdir="${TEST_DIR}/results/${group}"
  mkdir -p "$resultdir"
  outfile="${resultdir}/output-${testname}.txt"
  difffile="${resultdir}/diff-${testname}.txt"
  (( total++ ))
  if [[ ! -f "${expfile}" ]]; then
    echo "- ${testname}: [SKIP] expected file missing: ${expfile}"
    continue
  fi
  # If there's a matching .import file, pass it via -Dimport
  impfile="${TEST_DIR}/${testname}.import"
  if [[ -f "${impfile}" ]]; then
    java -Dimport="${impfile}" -cp "${BASE_CP}" bci.app.App < "${infile}" > "${outfile}" 2>&1 || true
  else
    java -cp "${BASE_CP}" bci.app.App < "${infile}" > "${outfile}" 2>&1 || true
  fi
  # Compare outputs
  if [[ $? -eq 0 ]]; then
    :
  else
    echo "- ${testname}: [FAIL] application exited with non-zero status"
  fi
  if diff -u "${expfile}" "${outfile}" > /dev/null; then
    echo "- ${testname}: [PASS]"
    (( passed++ ))
    rm -f "${difffile}"
  else
    echo "- ${testname}: [FAIL] output differs"
    diff -u "${expfile}" "${outfile}" > "${difffile}" || true
    echo "  --- DIFF written to ${difffile} ---"
  fi
  # Keep output file for inspection
done

echo
echo "Summary: ${passed}/${total} passed"
exit $(( total - passed ))
