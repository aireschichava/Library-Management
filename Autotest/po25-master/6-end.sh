#!/bin/zsh

error_exit() {
    echo "Error: $1" >&2
    echo "Usage: $0 TIMESTAMP" >&2
    exit 1
}

[[ $# -ne 1 ]] && error_exit "argument expected"

echo                                                                        >> GLOBAL-REPORT.md
echo -n "Tests for $1 finished at " $(date)                                 >> GLOBAL-REPORT.md
echo                                                                        >> GLOBAL-REPORT.md

git commit -am "Updated global report for $1."

#rm -rf checked-out checked-out-ref 
#rm -rf logs

git push
