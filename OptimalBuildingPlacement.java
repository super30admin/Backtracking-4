import java.util.LinkedList;
import java.util.Queue;
//tc - HWCn*HW
//sc-H*W
public class OptimalBuildingPlacement {
    public static void main(String args[])
    {
     BuildingPlacement buildingPlacement = new BuildingPlacement();
     System.out.println(buildingPlacement.findMinDistance(4,4,3));
    }
    public static class BuildingPlacement{
      int minDistance = Integer.MAX_VALUE;
      public int findMinDistance(int H, int W, int n)
      {
        int[][]grid = new int[H][W];
        for(int i=0;i<H;i++)
        {
            for(int j=0;j<W;j++)
            {
                grid[i][j] = -1;
            }
        }
        backtrack(grid,0,0,H,W,n);
        return minDistance;

      }

      private void bfs(int[][] grid,int H,int W)
      {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        for(int i=0;i<H;i++)
        {
            for(int j=0;j<W;j++)
            {
                if(grid[i][j] == 0)
                {
                    q.add(new int[]{i,j});
                }
            }
        }
        //now process neighbours
        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        int dist = 0;
        while(!q.isEmpty())
        {
          int size = q.size();
          for(int k=0;k<size;k++)
          {
            int[] curr = q.poll();
            for(int[]dir : dirs)
            {
                int r = curr[0]+dir[0];
                int c = curr[1]+dir[1];
                if(r>=0 && c>=0 && r<H && c<W && !visited[r][c])
                {
                    q.add(new int[]{r,c});
                    visited[r][c] = true;
                }
            }
            
          }
          dist++;

        }
        minDistance = Math.min(minDistance,dist-1);
      }

      private void backtrack(int[][] grid,int r,int c, int H, int W, int n)
      {
        //basecase
        if(n==0)
        {
            bfs(grid,H,W);
            return;
        }
        if(c>=W)//separate building
        {
            r++;
            c=0;
        }
        //logic
        for(int i=r;i<H;i++)
        {
            for(int j=c;j<W;j++)
            {
                //action
                grid[i][j] = 0;
                //recurse
                backtrack(grid, i, j+1, H, W, n-1);
                //backtrack
                grid[i][j] = -1;
            }
            c=0; //same building
        }

      }
    }
}
