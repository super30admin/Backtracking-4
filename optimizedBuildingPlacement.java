

import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args) {
	    BuildingPlacement buildingPlacement = new BuildingPlacement();
	    System.out.println(buildingPlacement.findMinDistance(4,4,2));
	    
		
	}
	public static class BuildingPlacement{
	    int minDistance = Integer.MAX_VALUE;
	    public int findMinDistance(int H,int W,int n){
	        int[][] grid = new int[H][W];
	        for(int i = 0;i<H;i++){
	            for(int j = 0;j<W;j++){
	                grid[i][j] = -1;
	            }
	        }
	        backtrack(grid,0,0,n,H,W);
	        return minDistance;
	    }
	    public void bfs(int[][]grid,int H,int W){
	        //now we have to put all building in queue
	        Queue<int []> q = new LinkedList<>();
	        boolean [][] visited = new boolean[H][W];
	        for(int i =0;i<H;i++){
	            for(int j = 0;j<W;j++){
	                if(grid[i][j]==0){
	                    q.add(new int[] {i,j});
	                    visited[i][j] = true;
	                }
	                
	            }
	        }
	        int dist = 0;
	        int[][] dirs ={{0,1},{-1,0},{0,-1},{1,0}};
	        while(!q.isEmpty()){
	            int size = q.size();
	            for(int i = 0;i<size;i++){
	                int[] curr = q.poll();
	                for(int[] dir:dirs){
	                    int r = dir[0] + curr[0];
	                    int c = dir[1] + curr[1];
	                    if(r<H && r>=0 && c>=0 && c<W && grid[r][c] == -1 && !visited[r][c]){
	                        q.add(new int[] {r,c});
	                        visited[r][c] = true;
	                    }
	                }
	            }
	            dist++;
	        }
	        minDistance = Math.min(minDistance, dist-1);
	    }
	    private void backtrack(int[][] grid, int r,int c,int n, int H,int W){
	        //base 
	        if(n==0){
	            bfs(grid,H,W);
	            return;
	        }
	        if(c>=W){
	            r++;
	            c=0;
	        }
	        //logic
	        for(int i = r;i<H;i++){
	            for(int j = c;j<W;j++){
	                //action
	                grid[i][j] = 0;//Placing the building
	                //recursion // Increament the row if we are at last column, column has to be increanment by 1 =
	                backtrack(grid,i,j+1,n-1,H,W);
	                //backtrack
	                grid[i][j] = -1;
	                
	                
	            }
	            c = 0;
	        }
	    }
	}
