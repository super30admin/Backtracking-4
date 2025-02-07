// Approach : Step 1 placement of buildings (place one building and call DFs and similarly place the second building and call the dfs) & step 2 to find the minimum distance
// similar to placement of N queens  + distance of nearest zero from all its neighbours (0's & 1's) problem
// Time : (H*w) * (H*w)Cn (combinations) => Similar to picking different combinations of two oranges from basket of three oranges 3C2
// Space : 
//Framework for Optimal Placement of Buildings
import java.util.LinkedList;
import java.util.Queue;

public class OptimalPlacementOfBuildings {
        public static void main(String[] args) {
            BuildingPlacement buildingPlacement = new BuildingPlacement();
            System.out.println(buildingPlacement.findMinDistance(4,5,3));
        }
        public static class BuildingPlacement{
            int H,W;
            int result;
            public int findMinDistance(int h, int w, int n){
              // Initialize variables and grid
              this.H = h;
              this.W = w;
              this.result = Integer.MAX_VALUE;

              int[][] grid = new int[H][W];
              for(int i =0;i<H;i++){
                for(int j=0;j<W;j++){
                    grid[i][j] = -1; // parking spots and buildings as zero
                }
              }
              // Call the dfs function
              dfs(grid,0,0,n); // row and column index & how many no of buildings
              // return min distance
              return result;
            }
        }

        private void dfs(in[][] grid,int i , int j, int n){
            if(n == 0){
                // After finishing the placement of buildings calculate the distance to the farthest parking spot.
                bfs(grid);
                return;
            }
            if(j == W){
                // out of bounds
                j=0;// reset the column position
                i++; // increment the row to start the next row
            }
            for(int r=i;r<H;r++){
                for(int c=j;c<W;c++){
                    grid[r][c] = 0; // place a building and call dfs
                    dfs(grid, r,c+1,n-1);
                    // backtrack
                    grid[r][c-1]= -1; // remove the last placed building and move to the next place (c+1)
                }
            }
        }

        private void bfs(int[][] grid){
            int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
            boolean[][] visited = new boolean[H][W];
            
            Queue<int[]> q = new LinkedList<>(); // position of row and column of a cell that has zero
            // 1. first take all the zeros into queue
            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    if(grid[i][j] == 0){
                        q.add(new int[]{i,j});
                    }
                }
            }

            int dist =0;
            while(!q.isEmpty()){
                int size = q.size();
                // 2. identify the first level of neighbours and second level of neighbours and so on..
                for(int k=0;k<size;k++){
                    int[] curr = q.poll();
                    for(int[] dir:dirs){
                        int r = curr[0] + dir[0];
                        int c = curr[0] + dir[1];
                        if(r>=0 && c>=0 && r<H && c<W && !visited[r][c] && grid[r][c] == -1){
                            q.add(new int[]{r,c});
                            visited[r][c] = true;
                        }
                    }
                }
                dist ++;
            }
            if(result > dist-1){
                result = dist -1;
                System.out.println(Arrays.deepToString(grid));
            }
            result = Math.min(result,dist-1);
        }
    }