//Time : Exponential (Backtracking)
//Space : O(n) where n is the length of the string and the space taken by blocks vector
class Solution {    
public:
    vector<string> expand(string s) {
        vector<string> res;
        vector<vector<char>> blocks;
        int i = 0;
        while(i < s.length()){
            vector<char> block;
            if(s[i] == '{'){
                i++;
                while(s[i] != '}') {
                    if(s[i] != ',') block.push_back(s[i]);
                    i++;
                }
            }
            else{
                block.push_back(s[i]);
            }
            i++;
            blocks.push_back(block);
        }
        backtrack(blocks,0,"", res);
        sort(res.begin(),res.end());
        return res;
    }
    void backtrack(vector<vector<char>> blocks, int index,string path,vector<string>&res){
        //base
        if(path.size() == blocks.size()){
            res.push_back(path);
            return;
        }
        //logic
        vector<char> block = blocks[index];
        for(int i = 0; i < block.size();i++){
            //action
            path.push_back(block[i]);
            //recurse
            backtrack(blocks,index+1,path,res);
            //backtrack
            path.pop_back();
        }
    }
};