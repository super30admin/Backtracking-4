import java.util.*;
class OptimalPlacementsInAGrid {
    int min=Integer.MAX_VALUE;
    public int minDistance(int H, int W, int n)
    {
        int[][] grid = new int[H][W];
        for(int i=0; i < H; i++)
        {
            for(int j=0; j < W; j++)
            {
                grid[i][j]=-1;
            }
        }
        backtrack(grid, 0, 0, H, W, n);
        return min;
    }
    public void bfs(int[][]grid, int H, int W)
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        for(int i=0; i < H; i++)
        {
            for(int j=0; j<W; j++)
            {
                if(grid[i][j]==0)
                {
                    visited[i][j] = true; 
                    q.add(new int[]{i, j});
                }
            }
        }
        int dis = 0;
        int [][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i < size; i++)
            {
                int[] curr = q.poll();
                for(int [] dir: dirs)
                {
                    int r=curr[0]+dir[0];
                    int c=curr[1]+dir[1];
                    if(r >=0 && r < H && c>=0 && c < W && !visited[r][c])
                    {
                        visited[r][c]=true;
                        q.add(new int[]{r, c});
                    }
                }  
            }
            dis++;
        }
        min = Math.min(min, dis-1);
    }
    public void backtrack(int[][] grid, int r, int c, int H, int W, int n)
    {
        //base case
        if(n==0)
        {
            bfs(grid, H, W);
            return;
        }
        //logic
        for(int i=r; i < H; i++)
        {
            for(int j=c; j<W; j++)
            {
                grid[i][j]=0;
                if(j< W)
                    backtrack(grid, i, j+1, H, W, n-1);
                else
                    backtrack(grid, i+1, 0, H, W, n-1);
                grid[i][j]=-1;
            }
        }
    }
    public static void main(String args[])
    {
        OptimalPlacementsInAGrid o = new OptimalPlacementsInAGrid();
        System.out.println(o.minDistance(5, 1, 1));
    }
}

// Time Complexity: O(H^W) Not sure
// Space Complexity : O(H*W) .... Not sure