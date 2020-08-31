/*
Approach Optimal Placement of Building in grid
To place parking at minimum distance from the building
0 is building position rest all parking spaces
find the building postion wher from furthest distance of parking lot to it is minimum



*/
import java.io.*;
import java.util.*;

class GFG {
public static void main (String[] args) {
   BuildingOptimalHousing obj = new BuildingOptimalHousing();
   
System.out.println(obj.findMinDistance(3,2,1));
}

public static class  BuildingOptimalHousing{
    int minDistance = Integer.MAX_VALUE;

public int findMinDistance(int H,int W , int n ){
 int[][]grid = new int[H][W];
 for(int i= 0 ; i <H;i++){
 for(int j= 0 ; j <W;j++){
   grid[i][j] = -1;
}}

backtrack(grid,0,0,n,H,W);
return minDistance;
}

private void backtrack(int[][]grid,int r,int c ,int n,int H,int W){
   //base
   if(n==0) {
       bfs(grid,H,W);
       return;
   }
   
   //logic
   //placeholder
   if(c== W){
       r++;c=0; //go to next row and reset column when  after column is exhausted reset the row
   }
   
   for(int i = r; i < H ;i++){
     for(int j = c; j<W ;j++){
         //action
         grid[i][j] = 0;
         //recurse
        //next backtrack on next column
         backtrack(grid,r,c+1,n-1,H,W); //for next building hence n-1
         //backtrack
         grid[i][j] = -1;
     }
     c=0; // reset column for next row
   }
}
     
 private void bfs(int[][]grid,int H,int W){
     Queue<int[]> q = new LinkedList<>();
     boolean[][] visited = new boolean[H][W];
     
     for(int i = 0 ; i <H; i++)
      for(int j = 0 ; j <W;j++){
          if(grid[i][j] == 0 ){
          q.add(new int[]{i,j});
          visited[i][j] = true;
      }
      }
 int dist = 0;
 int[][] dirs = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
 while(!q.isEmpty()){
     int size = q.size();
     for(int i = 0 ; i < size ; i++){
         int[] curr = q.poll();
         for(int[] dir : dirs){
             int nr = curr[0] + dir[0];
             int nc = curr[1] + dir[1];
             if(nr >= 0 && nr < H && nc>=0 && nc <W && !visited[nr][nc]){
                 q.add(new int[]{nr,nc});
                 visited[nr][nc] = true;
             }
         }
     }
     dist++; //level finished incrment distance
 }
 //minDistance gives the distance between buildng and the farthest parking space
 minDistance = Math.min(minDistance,dist -1) ; //started with 1 so decrease 1
     return;
 }
}
}

/*
Time complexity : O(HW * HWCn)  at each position placing building to check if placement with farthest parking spot possible or not
Space complexity : O(HW)
*/