#!/bin/bash
inputFileCount=$(ls -l ./input | wc -l)

checkAndRemove(){
    if [ -f $1 ] || [ -d $1 ]; then
        rm -r ./$1
    fi
}
addToProgramOutput(){
    cat $1 > programOutput.txt
}
cleanUp(){
    # remove .txt and remove every file in here except programOutput.txt
    for file in ./*.txt
    do
        if [[ "$file" == "./programOutput.txt" ]] || [[ "$file" == "./allotedTime.txt" ]]; then
            continue
        else
            checkAndRemove $file
        fi
    done;
    # pass;
}

checkError(){
    errorWordCount=$(wc -c < error.txt)
    if  ! [ $errorWordCount == 0 ]; then
        addToProgramOutput error.txt
        cleanUp
        exit 0
    fi;
}

test(){
    # ./inputCode < $1.txt > output.txt 2>error.txt
    ./time.sh $1.txt output.txt error.txt
    timeWordCount=$(wc -l < time.txt)
    echo $timeWordCount
    if [ $timeWordCount -gt 1 ]; then
        echo TLE
        echo "Time Limit Exceeded" > programOutput.txt
        cleanUp
        exit 0
    fi
    echo not_tle

    checkError

    ./checker < $2.txt >> checkerOutput.txt 2>error.txt

    checkError
}

compile(){
    g++ inputCode.cpp -o inputCode 2>inputCompilationError.txt
    g++ checker.cpp -o checker 2>checkerCompilationError.txt
    if ! [ -f "inputCode" ]; then
        addToProgramOutput inputCompilationError.txt
        cleanUp
        exit 0
    fi

    if ! [ -f "checker" ]; then
        addToProgramOutput checkerCompilationError.txt
        cleanUp
        exit 0
    fi
}

compile

#upload zip of multiple files (all tc answer and input for a problem) to s3 and download and unzip the same
#storing it or not ka decision badme lete.

for ((i=1; i < $inputFileCount ; i++ ));
do
    test ./input/input$i ./output/output$i
done

addToProgramOutput ./checkerOutput.txt
cleanUp