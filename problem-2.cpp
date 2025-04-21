// https://leetcode.com/problems/brace-expansion/description/

// Time Complexity: O(n + m*k) n is the length of the string, k is no. of groups, and m is no. of combinations
// Space Complexity: O(m*k)


class Solution {
    public:
        void backtrack(vector<vector<char>> &groups, int idx, string path, vector<string> &result){
            //base
            if(idx == groups.size()){
                result.push_back(path);
                return;
            }
    
            for(char ch:groups[idx]){
                path.push_back(ch);
                backtrack(groups, idx+1, path, result);
                path.pop_back();
            }
        }
        vector<string> expand(string s) {
            vector<string> result;
            vector<vector<char>> groups;
            int n = s.size();
            int i=0;
            while(i<n){
                vector<char> grp;
                if(s[i] == '{'){
                    i++;
                    while(s[i] != '}'){
                        if(s[i] != ',') grp.push_back(s[i]);
                        i++;
                    }
                    i++;
                }
                else{
                    grp.push_back(s[i]);
                    i++;
                }
                sort(grp.begin(),grp.end());
                groups.push_back(grp);
            }
            backtrack(groups, 0, "", result);
    
           
            return result;
        }
    };