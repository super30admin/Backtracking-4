/*
Intuition: We first preprocess the string and then apply dfs with backtracking.
We will be exhaustive.
Time Complexity: O(N^N), N = Length of string.
Space Complexity: O(N^N), N = Length of string.

*/

class Solution {
public:
    vector<string> result;
    vector<string> expand(string s) {
        vector<vector<char>> blocks;
        int i = 0;
        while ( i < s.size()){
            vector<char> block;
            if ( s[i] == '{'){
                i++;
                while ( s[i] != '}'){
                    if ( s[i] != ','){
                        block.push_back(s[i]);
                    }
                    i++;
                }
            }
            else{
                block.push_back(s[i]);
            }
            i++;
            blocks.push_back(block);
            
        }
        string path;
        dfs(blocks, 0, path);
        sort(result.begin(), result.end());
        return result;
    }
    void dfs(vector<vector<char>>&blocks, int index, string& path){
        
        if ( index == blocks.size()){
            result.push_back(path);
            return; 
        }
        vector<char> block = blocks[index];
        for ( int i = 0; i < block.size(); i++){
            path += block[i];
            dfs(blocks, index + 1, path);
            path.pop_back();
        }
        
    }
};