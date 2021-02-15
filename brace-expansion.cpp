//Time - exponential
//Space - O(len of string)
class Solution {
public:
    void backtrack(vector<vector<char>>& blocks, int idx, string str, vector<string>& result){
        if(idx == blocks.size()){
            result.push_back(str);
            return;
        }
        
        for(int i = 0;i<blocks[idx].size();i++){
            str = str + blocks[idx][i];
            backtrack(blocks,idx+1,str,result);
            str.pop_back();
        }
    }
    
    vector<string> expandBraces(string str){
        vector<vector<char>> blocks;
        vector<string> result;
        for(int i = 0;i<str.size();i++){
            vector<char> block;
            if(str[i] == '{'){
                i++;
                while(str[i]!='}'){
                    if(str[i]!=',') block.push_back(str[i]);
                    i++;
                }
            }else{
                block.push_back(str[i]);
            }
            blocks.push_back(block);
        }
        backtrack(blocks,0,"",result);
        sort(result.begin(),result.end());
        return result;
    }    
    
};