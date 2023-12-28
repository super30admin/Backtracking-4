/* Time Complexity : O((H*W)C n) */
/* Space Complexity : O(H*W) */
// Did this code successfully run on Leetcode : Yes, with below test on leetcode playground.
// Any problem you faced while coding this :

// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        BuildingPlacement obj = new BuildingPlacement();
        System.out.println(obj.findMinDist(5, 5, 1));
    }
    
    public static class BuildingPlacement{
        int H; int W; int min;
        public int findMinDist(int h, int  w, int n){
            this.H = h;
            this.W = w;
            this.min = Integer.MAX_VALUE;
            int[][] grid = new int[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    grid[i][j] = -1;
                }
            }
            helper(grid, n, 0);
            return min;
        }
        
        private void bfs(int[][] grid){
            Queue<int []> q = new LinkedList<>();
            int[][] dirs = new int[][]{{0,1}, {1, 0}, {0, -1}, {-1, 0}};
            boolean[][] visited = new boolean[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){
                        q.add(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            
            int dist = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i ++){
                    int[] curr = q.poll();
                    for(int[] dir: dirs){
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if(nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc]){
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
                dist++;
            }
            min = Math.min(min, dist - 1);
        }
        
        private void helper(int[][] grid, int n, int pivot){
            //base condn
            if(n == 0){
                bfs(grid);
                return;
            }
            
            //logic
            for(int i = pivot; i < H*W; i++){
                int r = i/W;
                int c = i%H;
                //action
                grid[r][c] = 0;
                //recurse
                helper(grid, n-1, i + 1);
                //backtrack;
                grid[r][c] = -1;
            }
        }
    
    }
}