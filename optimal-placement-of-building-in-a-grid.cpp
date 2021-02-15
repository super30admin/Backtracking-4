//Time - exponential
//Space - O(HW)
class Solution {
  
public:
    int minDistance = INT_MAX;
    int findMinDistance(int H, int W, int n){
        
        vector<vector<int>> grid (H,vector<int>(W,0));
        
        backtrack(grid, 0, 0, H, W, n);
        return minDistance;
    }
    
    void dfs(vector<vector<int>> grid, int H, int W){
        vector<vector<bool>> visited (H,vector<bool>(W,false));
        
        queue<pair<int,int>> q;
        
        for(int i = 0;i<H;i++){
            for(int j = 0;j<W;j++){
                if(grid[i][j] == 1){
                    q.push(make_pair(i,j));
                }
            }
        }
        int dist = 0;
        vector<vector<int>> dir {{1,0},{0,1},{-1,0},{0,-1}};
        while(!q.empty()){
            int size = q.size();
            for(int i = 0;i<size;i++){
                pair<int,int> coord = q.front();q.pop();
                for(auto d:dir){
                    int r = coord.first + d[0];
                    int c = coord.second + d[1];
                    if(r>=0 && r<H && c>=0 && c<W && !visited[r][c]){
                        visited[r][c] = true;
                        q.push(make_pair(r,c));
                    }
                }
            }
            dist++;
        }
        
        minDistance = min(minDistance, dist-1);
        
    }
    
    void backtrack(vector<vector<int>> grid, int r, int c, int H, int W, int n){
        if(n == 0){
            dfs(grid, H, W);
        }
        
        if(c == W){
            c = 0;
            r++;
        }
        
        for(int i = r;i<H;i++){
            for(int j = c;j<W;j++){
                grid[i][j] = 1;
                backtrack(grid, i, j+1, H, W, n-1);
                grid[i][j] = 0;
            }
            c = 0;
        }
    }
    
    
};
