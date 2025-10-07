#!/bin/zsh

# DELIVERY is one of 'e0', 'ei', or 'ef'
DELIVERY=ei
TIMESTAMP=$(date +"%Y%m%d%H%M")

./0-begin.sh "$DELIVERY" "$TIMESTAMP"
./1-clone.sh "$DELIVERY"
./2-clean.sh checked-out-ref 2>/dev/null 1>/dev/null
./2-clean.sh checked-out     2>/dev/null 1>/dev/null
./3-prepare.sh               2>/dev/null 1>/dev/null
./4-test.sh "$DELIVERY"
./5-summarize.sh "$DELIVERY"
./6-end.sh "$TIMESTAMP"

