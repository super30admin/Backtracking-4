// Time Complexity : O(h*w for bfs, 2^hw for backtrack, so 2^hw) where h and w are height and width of the grid
// Space Complexity : O(h*w) where h and w are height and width of the grid

// Your code here along with comments explaining your approach
import java.io.*;
import java.util.*;

class optimalPlacementOfBuildings {
    static int min = Integer.MAX_VALUE;

    public static void main (String[] args) {
        System.out.print(findMinDistance(4, 4, 3));
    }

    public static int findMinDistance(int H, int W, int n){
        int [][] grid = new int[H][W];
        // form the grid using -1s
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                grid[i][j] = -1;
            }
        }
        return backtrack(grid, 0, 0, n, H, W);
    }

    public static int bfs(int[][] grid, int H, int W){
        Queue <int[]> q = new LinkedList<>();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(grid[i][j] == 0) q.offer(new int[]{i,j});
                else grid[i][j] = Integer.MAX_VALUE;
            }
        }
        int ans = Integer.MIN_VALUE;
        int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        while(!q.isEmpty()){
            int [] curr = q.poll();
            for(int [] dir: dirs){
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                if (r >= 0 && r < H && c >= 0 && c < W && grid[r][c] > grid[curr[0]][curr[1]] + 1) {
                    q.add(new int[] {r,c});
                    grid[r][c] = grid[curr[0]][curr[1]] + 1;
                    ans = Math.max(ans, grid[r][c]);
                }
            }
        }
        return ans;
    }

    public static int backtrack(int[][] grid, int r, int c, int n, int H, int W){
        // base case
        if(n == 0) return bfs(grid,H, W);
        // logic
        for(int i = r; i < H; i++){
            for(int j = c; j < W; j++){
                // place a building at a location
                grid[i][j] = 0;
                int val = 0;
                if(j < W){
                    val = backtrack(grid, i, j+1, n-1, H, W);
                } else {
                    val = backtrack(grid, i+1, 0, n-1, H, W);
                }
                min = Math.min(min, val);
                // backtrack and explore placement of other building combinations
                grid[i][j] = -1;
            }
        }
        return min;
    }
}