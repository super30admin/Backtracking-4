//TC: O(n)
//SC: O(1)
class Solution {
public:
unordered_map<int,int> map={{0,0},{1,1},{6,9},{8,8},{9,6}};
int count=0;
    int confusingNumberII(int n) {
        
         dfs(0, n );
         return count;
    }
    void dfs(long  val, long  n){
        //base case:
        if(val>n)
            return;
        if(validConfusingNumber(val))
            count++;
        for(auto [key,value]: map)
        {
            long newnum=val*10+key;
            if(newnum!=val)
                dfs(newnum, n);
        }
    }
    bool validConfusingNumber(long  val)
    {
        long  newNum=0, temp=val;
        while(temp)
        {
            long rem=temp%10;
            newNum=newNum*10+map[rem];
            temp/=10;
        }
        return newNum!=val;
    }
};