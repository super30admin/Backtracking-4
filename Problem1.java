//Time Complexity: O((H*W)P(n) * (H*W))
//Space Complexity: O(H*W)
//Code run successfully on LeetCode.

public class Problem1 {

public class Main {
    
    public static class buildingPlacement{
        
        int[][] grid;
        int H, W;
        int n, minDistance;
        
        public buildingPlacement(int H, int W, int n)
        {
            this.H = H;
            this.W = W;
            this.n = n;
            
            grid = new int[H][W];
            minDistance = Integer.MAX_VALUE;
        }
        
        public int findMinDistance()
        {
            for(int i =0; i < H; i++)
            {
                for(int j =0; j < W; j++)
                {
                    grid[i][j] = -1;
                }
            }
            
            backtrack(0,0,n);
            
            return minDistance;
        }
        
        private void backtrack(int r, int c, int n)
        {
         
            if(n == 0)
            {
                bfs();
                return;
            }
            
            if(c == W)
            {
                r++;
                c = 0;
            }
            
            for(int i =r; i <H; i++)
            {
                for(int j =c; j < W; j++)
                {
                    grid[i][j] = 0;
                    backtrack(r, c+1, n-1);
                    grid[i][j] = -1;
                }
            }
        }
        
        private void bfs()
        {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            
            int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
            
            for(int i =0; i < H; i++)
            {
                for(int j =0; j < W; j++)
                {
                    if(grid[i][j] == 0)
                    {
                        q.add(new int[] {i,j});
                        visited[i][j] = true;
                    }      
                }
            }
            
            int distance =0;
            
            while(!q.isEmpty())
            {
                int size = q.size();
                
                for(int i =0; i < size; i++)
                {
                    int[] curr = q.poll();
                    
                    for(int[] dir : dirs)
                    {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];
                        
                        if(nr >=0 && nr < H && nc >=0 && nc < W && visited[nr][nc] != true)
                        {
                            q.add(new int[] {nr,nc});
                            visited[nr][nc] = true;
                        }
                    }  
                }
                distance++;
            }
            
            minDistance = Math.min(minDistance, distance-1);
        }
    }
    public static void main(String[] args) {
        
        buildingPlacement bp = new buildingPlacement(4,4,3);
        System.out.println(bp.findMinDistance());
    }
}
}
