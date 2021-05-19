

import java.io.*;
import java.util.*;

// TC - O(m X n)
// SC - O(m X n)

// Initially place n buildings using backtracking and for each combination, traverse using bfs 
class GFG {
	public static void main (String[] args) {
	    BuildingPlacement bp = new BuildingPlacement();
	    System.out.println(bp.findMinDistnace(3, 3, 2));
	}
	
	public static class BuildingPlacement{
	    
	    int minDist = Integer.MAX_VALUE;
	    
	    public int findMinDistnace(int h, int w, int n){
	        int[][] grid = new int[h][w];
	        
	        for(int i = 0; i < h; i++){
	            for(int j = 0; j < w; j++){
	                grid[i][j] = -1;
	            }
	        }
	        
	        // int[][] grid, int r, int c, int n, int h, int w
	        backtracking(grid, 0, 0, n, h, w);
	        
	        return minDist;
	    }
	    
	    private void bfs(int[][] grid, int h, int w){
	        Queue<int[]> q = new LinkedList<>();
	        boolean[][] visited = new boolean[h][w];
	        
	        for(int i = 0; i < h; i++){
	            for(int j= 0; j < w; j++){
	                if(grid[i][j] == 0){
	                    q.add(new int[]{i, j});
	                    visited[i][j] = true;
	                }
	            }
	        }
	        
	        int level = 0;
	        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	        
	        while(!q.isEmpty()){
	            int size = q.size();
	            
	            for(int x = 0; x < size; x++){
	                int[] front = q.poll();
	                
	                int i = front[0];
	                int j = front[1];
	                for(int[] dir : dirs){
	                    
	                    int r = i + dir[0];
	                    int c = j + dir[1];
	                    
	                    if(r >= 0 && r < h && c >=0 && c < w && visited[r][c] == false){
	                        q.add(new int[]{r, c});
	                        visited[r][c] = true;
	                    }
	                }
	            }
	            level++;
	        }
	        minDist = Math.min(minDist, level - 1);
	    }
	    
	    private void backtracking(int[][] grid, int r, int c, int n, int h, int w){
	        if(n <= 0){
	            bfs(grid, h, w); // cal level
	            return;
	        }
	        if(c >= w){
	            r++;
	            c= 0;
	        }
	        
	        for(int i = r; i < h; i++){
	            for(int j = c; j < w; j++){
	                grid[i][j] = 0;
	                backtracking(grid, i, j, n - 1, h , w);
	                grid[i][j] = -1;
	            }
	            c = 0;
	        }
	    }
	}
}