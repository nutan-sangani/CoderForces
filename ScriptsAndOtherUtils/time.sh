#!/bin/bash

inputFile=$1
outputFile=$2
errorFile=$3

(/usr/bin/time -f %U -o time.txt ./inputCode <$inputFile >$outputFile 2>$errorFile) &
pidOfShell=$$
pidOfTime=$!

(inotifywait time.txt -e modify >/dev/null 2>&1) && (kill -9 $pidOfShell >/dev/null) &
pidOfNotify=$!

allotedTime=$(cat allotedTime.txt)
sleep $allotedTime

pkill --parent $pidOfTime
pkill --parent $pidOfShell