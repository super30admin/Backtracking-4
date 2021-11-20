// Time Complexity : O( (mxn)^2 )
// Space Complexity : O( (mxn) )
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution{
    
    public static class BuildingPlacement{
        int minDistance = Integer.MAX_VALUE;
        
        public int findMinDistance(int H, int W, int n){
            int [][] grid = new int[H][W];
            
            for(int i=0 i<H; i++){
                for(int j=0 j<W; j++){
                    grid[i][j] = -1;
                }  
            }
            
            backtrack(grid, 0, 0, n, H, W);
            return minDistance;
        }
    }
    private void bfs(int[][] grid, int H, int W){
        Queue<int[]> q = new LinkedList<>();
        
        // grid with particular placement of buildings
        boolean[][] visited = new boolean[H][W];
        for(int i=0 i<H; i++){
            for(int j=0 j<W; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }  
        }
        
        // now we have all buildings in queue
        
        int[][] dirs = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        int dist = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0; k<size; k++){
                int[] curr = q.poll();
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr>=0 && nc>=0 && nr<H && nc<W && grid[nr][nc] == -1 && visited[nr][nc] == false){
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        
        // int the end distance is farthest distance
        minDistance = Math.min(minDistance, dist-1);
    }
    private void backtrack(int[][] grid, int r, int c, int n, int H, int W){
        // base
        if(n == 0){
            bfs(grid, H, W);
            return;
        }
        
        // logic
        if(c == W){
            r++;
            c = 0;
        }
        for(int i=r; i<H; i++){
            for(int j=c; j<W; j++){
                //action
                grid[i][j] = 0;
                // recurse
                backtrack(grid, i, j+1, n-1, H, W);
                // backtrack
                grid[i][j] = -1;
            }
        }
        
    }
    
}