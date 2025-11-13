#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We go character by character and collect groups of letters, sorting each group.
Then we combine each character from the new group with all existing words built so far.
We iteratively repeat this till the end, building all possible strings step by step.
*/
class Solution{
public:
    vector<string> expand(string s){
        int i = 0;
        vector<string> expandedWords = {""};
        while(i < s.length()){
            vector<char> group;
            if(s[i] == '{'){
                i++;
                while(s[i] != '}'){
                    if(s[i] != ','){
                        group.push_back(s[i]);
                    }
                    i++;
                }
                i++;
            }else{
                group.push_back(s[i]);
                i++;
            }

            sort(group.begin(), group.end());
            vector<string> currWords;

            for(const string& word : expandedWords){
                for(char c : group){
                    currWords.push_back(word + c);
                }
            }
            expandedWords = currWords;
        }
        return expandedWords;
    }
};
