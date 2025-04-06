#include<iostream>
#include<stdlib.h>
#include <fstream>
#include <string>

using namespace std;

int main(){
	ifstream inputFile("output.txt");

    if(!inputFile){
        cerr<<"no output printed by code"<<endl;
        return 0;
    }

    string line;

    while(getline(inputFile,line)){
        string output;
        cin>>output;
        // cout<<output<<" "<<line<<endl;
        if(output!=line){
            cerr<<"wrong answer found or wrong output format"<<endl;
            return 0;
        }
    }
    cout<<"correct answer"<<endl;
    return 0;
}