/*
**Time Complexity (TC):** O(4^N)

**Space Complexity (SC):** O(N)

Where:
- `N` is the number of matchsticks.
*/

class Solution {
public:
    bool makesquare(vector<int>& matchsticks) {
        if(matchsticks.size()<4)
            return false;
        int sum=0;
        for(int i=0;i<matchsticks.size();i++)
        {
            sum+=matchsticks[i];
        }
        if(sum%4!=0)
            return false;
        int side=sum/4;
        sort(matchsticks.begin(), matchsticks.end(),greater<>());
        vector<int> ans(4);
        return backtrack(matchsticks, side, 0, ans);
    }
    bool backtrack(vector<int>& matchsticks, int side, int index, vector<int>& ans)
    {
        if(index==matchsticks.size())
        {
            if(ans[0]==ans[1] and ans[1]==ans[2] and ans[2]==ans[3])
                return true;
            return false;
        }
        for(int i=0;i<4;i++)
        {
            
               if(ans[i]+matchsticks[index]<=side)
               {
                   ans[i]=ans[i]+matchsticks[index];
                 if(backtrack(matchsticks, side, index+1, ans))
                    return true;
                    ans[i]=ans[i]-matchsticks[index];
               } 

        }
         return false;
    }
};