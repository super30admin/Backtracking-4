//Time Complexity-O(k^n)---> considering that we have only choice making elements in our input string and so we will
//                           have k choices to make on an average at every point in the recursion tree. 
//Space Complexity-O(n) which is the temporary vector which I'm using to store all the characters between '{' & '}'
//                      If recursive stack space is also considered then it will be O(n+h)-->'h' is height of tree.
//Did the code execute on Leetcode? yes

class Solution {
public:
    void backtrack(string S, vector<string>&res,int index,string temp)
    {
        cout<<temp;
        if(index==S.length())
        {
            res.push_back(temp);
        }
        if(S[index]=='{')
        {
            vector<char>a;
            index++;
            while(S[index]!='}')
            {
                if(isalpha(S[index]))
                {
                    a.push_back(S[index]);
                }
                index++;
            }
            for(int i=0;i<a.size();i++)
            {
                backtrack(S,res,index+1,temp+a[i]);
            }
        }
        else if(isalpha(S[index]))
        {
            backtrack(S,res,index+1,temp+S[index]);
        }
    }
    
    vector<string> expand(string S) {
        vector<string>res;
        backtrack(S,res,0,"");
        sort(res.begin(),res.end());
        return res;
    }
};
