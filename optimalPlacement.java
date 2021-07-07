//TC :  n^(H*W) n are number of buildings, h and w are height and width
//SC : O(HW) 
import java.util.*;
class optimalPlacement{
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args){
        System.out.println(findMinDist(4,4,3));
    }

    public static int findMinDist(int H,int W,int n){
        int[][] grid = new  int[H][W];

        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                grid[i][j] = -1;
            }
        }
        backTrack(grid,0,0,n,H,W);
        return min;
    }
    private static void bfs(int[][] grid,int H,int W){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited =  new boolean[H][W];
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
                    
            }
        }
        int dirs[][] = {{0,1},{0,-1},{1,0},{-1,0}};
        int dist =0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] curr = q.poll();
                for(int[] dir : dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r>=0 && r<H && c>=0 && c<W && !visited[r][c]){
                        
                        visited[r][c] = true;
                        q.add(new int[]{r,c});
                    }
                }
            }
            dist++;
        }

        min = Math.min(dist-1,min);

    }
    private static void backTrack(int[][] grid,int r,int c,int n,int H,int W){
        //Base case
        if(n == 0){
            bfs(grid,H,W);
            return;
        }
        //Logic
        for(int i=r;i<H;i++){
            for(int j=c;j<W;j++){
                grid[i][j] = 0; //action
                if(j<W){
                    backTrack(grid,i,j+1,n-1,H,W); //recurse
                }
                else{
                    backTrack(grid,i+1,0,n-1,H,W); //recurse
                }
                grid[i][j] = -1; //backtrack
            }
        }
        
    }
}