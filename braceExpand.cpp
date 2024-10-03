/*
**Time Complexity (TC):** O(B^N)

**Space Complexity (SC):** O(B^N)

Where:
- `B` is the average number of characters in each block.
- `N` is the number of blocks.
*/
class Solution {
public:
vector<string> ans;
vector<vector<char>> blocks;
    vector<string> expand(string s) {
        if(s.size()==0)
            return {};
        int i=0;
        while(i<s.size())
        {
            vector<char> block;
            if(s[i]=='{') 
            {
                i++;
                while(s[i]!='}') 
                {
                    if(s[i]!=',')
                        block.push_back(s[i]);
                    i++;
                }
            }
            else
            {
                block.push_back(s[i]); 
            }
            sort(block.begin(), block.end()); 
            blocks.push_back(block);
            i++;
        }
        backtrack(0,"");
        return ans;
    }

    void backtrack(int index, string temp)
    {
        if(index==blocks.size()) 
        {
            ans.push_back(temp);    
            return;
        }
        vector<char> block=blocks[index]; 
        for(auto c: block)
        {
            temp+=c;
            backtrack(index+1, temp); 
            temp.pop_back();
        }

    }

};