// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        BuildingPlacement obj = new BuildingPlacement();
        System.out.println(obj.getOptimalPlacementOfBuildings(4,4,1));
    }
}
    class BuildingPlacement {
    int minDistance;
    int[][] dirs;    
    public int getOptimalPlacementOfBuildings(int H, int W, int n)
    {
        //create a grid
        int[][] grid = new int[H][W];
        // initialize all values of the grid to -1
         for (int i = 0; i < H; i++){
             for (int j = 0; j < W; j++){
                 grid[i][j] = -1;
           }    
         }
        // directions array
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        minDistance = Integer.MAX_VALUE;
        backtrack(grid, 0, 0, H, W, n);
        return minDistance;
    }
    private void backtrack(int[][] grid, int r, int c, int H, int W, int n)
    {
        //base
        if (n == 0){
            bfs(grid, H, W);
            return;
        }
        if (c >= W){
            r++;
            c=0;
        }
         
        //logic
        for ( int i = r; i < H; i++){
            for ( int j = c; j < W; j++){
                     //action
                     grid[i][j] = 0;
                    //recurse
                    backtrack(grid, i, j+1, H, W, n-1);
                    //backtrack
                   grid[i][j] = -1;
               }
            c = 0;
        } 
    }
    // BFS for finding minDistance of each combination
    private void bfs(int[][] grid, int H, int W)
    {
        Queue<int[]> q = new LinkedList();
        boolean[][] visited = new boolean[H][W];
        for(int i = 0; i < H; i++){
            for (int j = 0; j < W; j++){
                if (grid[i][j] == 0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int distance = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for ( int k = 0; k < size; k++){
              int[] curr = q.poll();
              for (int[] dir : dirs){
                int i = dir[0] + curr[0];
                int j = dir[1] + curr[1];
                if (i<H && i>=0 && j <W && j >=0 && !visited[i][j]){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }           
            }
        }
       distance++;
      }
     minDistance = Math.min(minDistance, distance-1);
   }
}
