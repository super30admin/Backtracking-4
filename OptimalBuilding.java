// Time Complexity : O(n * 2^H*W*H*W) 
// Space Complexity : O(HW) grid
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Next level hard problem
/* Your code here along with comments explaining your approach: We are trying each and every combination of building placement in backtracking
hence, we would try each and every row and columns to place te buildings in the grid and for each placement we would do BFS to get the min distance 
to the farthest parking lot. We would keep the minimum distance that is max distance of farthest parking lot from each of the buildings. */
class GFG{
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement, findMinDistance(4,4,3));
    }
    public static class BuildingPlacement{
        int minDistance = Integer.MAX_VALUE;
        public int findMinDistance(int H, int W, int n){
            int[][] grid = new int[H][W];
            for(int i =0; i < H; i++){
              for(int j  = 0; j < N; j++){
                    grid[i][j] =-1;                                                                     // Make the grid cells as -1
              }
            }
            backtrack(grid,0,0,n,H,W);                                                              // Start placing the buildings
            return minDistance;
        }
        private void bfs(int[][] grid, int H, int W){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new int[H][W];                                                                    // Mark visited
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){
                        visited[i][j] = true;
                        q.add(new int[]{i,j});                                                              // Add the parking lot to be processed
                    }
                }
            }
            int steps  = 0;
            int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};                                                  // Move in four directions
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){                                                          // calculate distance level by level
                int[] curr = q.poll();
                for(int[] dir : dirs){
                    int r = curr[0]  + dir[0];
                    int c  = curr[1] + dir[1];
                    if(r >=0 && c >=0 && r < H && c < W && !visited[r][c]){                                         // If not visited cell
                        q.add(new int[] {r,c});
                        visited[r][c]= true;                                                                // Visit it
                    }
                }
            }
            steps++;                                                                                // Calculate the distance
            }
            minDistance = Math.min(steps-1,minDistance);                                                        // Store the min distance
        }

        private void backtrack(int[][] grid, int r, int c, int n , int H, int W){
            if(n == 0){
                bfs(grid, H, W);                                                                        // All buildings placed, find distance
            } 

            if(c >= W){                                                                             // Column overshoot, goto next row and start again
                r++; c=0;
            }
            for(int i = r, i < H; i++){
                for(int j = c; j < W; j++){ 
                    grid[i][j] = 0;                                                                         // Place the buulding 0
                    backtrack(grid, i , j+1, n-1,  H, W);                                                   // Check for other cells by placing the remaining buildings
                    grid[i][j] = -1;                                                                            // Backtrack
                }
            }
            c = 0;                                                                                          // Start again in the next row
        }


    }

}