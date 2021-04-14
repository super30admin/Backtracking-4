// Time Complexity :O(n!)
// Space Complexity :O(n*n)
// Did this code successfully run on Leetcode :es
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
	public static void main (String[] args) {
		BuildingPlacement obj = new BuildingPlacement();
		System.out.println(obj.findDistance(3,3,2));
	}
}
    class BuildingPlacement{
    int minDistance= Integer.MAX_VALUE;
    
    public int findDistance(int h, int w, int b){
        int[][] grid= new int[h][w];
        
        backtrack(grid,0,0,b,h,w);
        return minDistance;
    }
    int[][] dirs= new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    public void dfs(int[][]grid, int h, int w){
        Queue<int[]> q= new LinkedList<>();
        boolean[][] visited= new boolean[h][w];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(grid[i][j]==1){
                    q.add(new int[]{i,j});//adding  all the placed buildings
                    visited[i][j]=true;
                }
            }
        }
        int level=0;//finding the paths which are farthest from building placed
        while(!q.isEmpty()){
            int size= q.size();
            for(int x=0;x<size;x++){
                int[] front = q.poll();
                int i=front[0]; int j= front[1];
                for(int[] dir: dirs){
                    int r=i+dir[0];
                    int c=j+dir[1];
                    if(r<h && c<w && r>=0 && c>=0 && !visited[r][c]){
                        q.add(new int[]{r,c});
                        visited[r][c]=true;;
                    }
                }
            }
            level++;
        }
        minDistance= Math.min(minDistance,level-1);
        
    }
    public void backtrack(int[][] grid, int r, int c, int b, int h, int w){//all combinations of building placed
        if(b<=0){
            dfs(grid,h,w);
            return;
        }
        if(c>=w){//we are moving in each from left to right and then increase row
            r++;
            c=0;
        }
        for(int i=r;i<h;i++){
            for(int j=c;j<w;j++){
                grid[i][j]=1;//placing the building at i,c
                backtrack(grid,i,j,b-1,h,w);
                grid[i][j]=0;//removing the building at i,c
            }
        }
        
        
    }
    
}