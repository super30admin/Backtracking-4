

/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/




import java.io.*;
import java.util.*;

public class Main {
    static int min = Integer.MAX_VALUE;
    public static void main (String[] args) {
        System.out.print(findMinDistance(5, 1, 1));
    }
    public static int  findMinDistance(int H, int W, int n){
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
        Queue <int []> q = new LinkedList<>();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(grid[i][j] == 0) q.offer(new int[]{i,j});
                else grid[i][j] = Integer.MAX_VALUE;
            }
        }
        int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        while(!q.isEmpty()){
            int [] curr = q.poll();
            for(int [] dir: dirs){
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                if(r < 0 || r >= H || c < 0 || c >= W || grid[r][c] <= grid[curr[0]][curr[1]] + 1) continue;
                q.offer(new int[] {r,c});
                grid[r][c]= grid[curr[0]][curr[1]] + 1;
            }
        }
        int max = Integer.MIN_VALUE;
         for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                max = Math.max(max, grid[i][j]);
            }
         }
         return max;
    }
    public static int backtrack(int[][] grid, int r, int c, int n, int H, int W){
        // Base Case
        if(n == 0) return bfs(grid,H, W);
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
