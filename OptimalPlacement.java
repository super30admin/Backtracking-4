// Time Complexity :O(n)
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
import java.util.*;
public class OptimalPlacement {
    int minDistance = Integer.MAX_VALUE;
    public int findDistance(int height , int width, int n)
    {
        int[][] grid = new int[height][width];
        for(int i = 0; i <height;i++)
        {
            for(int j = 0; j <width;j++)
            {
                grid[i][j] = -1;
            }
        }
        backtrack(grid, 0, 0, n, height, width);
        return minDistance;
    }
    private void bfs(int[][] grid, int h, int w)
    {
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> q = new LinkedList<>();
        boolean [][] visited = new boolean[h][w];
        for(int i = 0 ; i <h;i++)
        {
            for(int j = 0; j <w;j++)
            {
                 if(grid[i][j]==0)
                 {
                    visited[i][j] = true;
                     q.add(new int[]{i,j});
                 }       
            }
        }
        int dist = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0 ; i <size;i++)
            {
                int [] poll_el = q.poll();
                int x = poll_el[0];
                int y = poll_el[1];
               
                for(int[] dir:dirs)
                {
                    int r = x+dir[0];
                    int c = y+dir[1];
                    if(r>=0&&r<h&&c>=0&&c<w&& !visited[r][c])
                    {
                        grid[r][c] = grid[x][y]+1;
                        visited[r][c] = true;
                        q.add(new int[]{r,c});
                    }
                }
            }
            dist++;
        }
        minDistance = Math.min(minDistance,dist-1);

    }
    private void backtrack(int [][] grid, int r, int c, int n, int h,int w)
    {
        //base case
        if(n==0)
        {
            bfs(grid, h, w);
            return;
        }

        //logic
        if(c>=w)
        {
            r++;
            c=0;
        }
        for(int i = r;i<h;i++)
        {
            for(int j = c;j<h;j++)
            {
                //action
                grid[i][j]=0;
                //recurse
                backtrack(grid, i, j+1, n-1, h, w);
                //backtrack
                grid[i][j] =-1;
            }
         
        }
    }
    public static void main(String [] args)
    {
        OptimalPlacement obj = new OptimalPlacement();
        System.out.println(obj.findDistance(4, 4, 3));
    }
}