#!/bin/bash
time python3 a.py &
#time --quiet -o time.txt python3 a.py &
AllotedTime=5
pidOfTime=$!
pidOfProgram=$(pgrep -P "$pidOfTime")
cpuTime=$(ps -o time="$pidOfProgram")
IFS=: read -r hours mins secs <<< "$cpuTime"
echo "$hours " "$mins " "$secs "

seconds=$((10#$hours*3600+10#$mins*60+10#$secs))
#echo time in seconds is : "$seconds"

while [ $seconds -lt $AllotedTime ] && kill -0 $pidOfProgram 2>/dev/null; do
    cpuTime=$(ps -o time="$pidOfProgram")
    IFS=: read -r hours mins secs <<< "$cpuTime"
    # echo $hours
    sleep 1
    seconds=$((10#$hours*3600+10#$mins*60+10#$secs))
done
if [ $seconds -lt $AllotedTime ]; then
    echo the execution time of the program is
    cat time.txt
    echo program finished
else
    kill $pidOfProgram
    echo time limit exceeded
fi
#make time getter function and use this script to run programs on each test case.
#add to err file if tle, else chalega
