class Solution {
//    Approach
//    First, we try every way to place the buildings on the grid using backtracking. We convert 1d index to row and column index of 2d matrix to place the building
//    For each combination, we run a BFS to find the max distance from any empty cell to the nearest building.
//    Among all placements, we keep track of the one that gives the smallest such distance.
//    Combinations: C(h×w, n) ≈ (h×w)^n / n! in worst case
//    BFS per combination: O(h×w)
    // SC= O(h*w)
    public class OptimalPlacementOfBuildings {
        public static void main(String[] args) {
            BuildingPlacement buildingPlacement = new BuildingPlacement();
            System.out.println(buildingPlacement.findMinDistance(7, 7, 3));
        }

        public static class BuildingPlacement {
            int H, W;
            int result;

            public int findMinDistance(int h, int w, int n) {

                this.H = h;
                this.W = w;
                this.result = Integer.MAX_VALUE;
                int[][] grid = new int[H][W];
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        grid[i][j] = -1;
                    }
                }
                //place the buildings
                helper(grid, 0, n);
                return result;
            }

            private void helper(int[][] grid, int pivot, int n) {
                if (n==0)
                {
                    // calculate the distance from the empty parking lot to the building
                    bfs(grid);
                    return;
                }
                for (int i = pivot; i < H*W; i++) {
                    int r= i/W;
                    int c= i%W;

                    grid[r][c]=0; // action
                    helper(grid,i+1, n-1); //recurse
                    grid[r][c]=-1; // backtrack
                }
            }
            private void bfs(int[][] grid) {
                int m= grid.length;
                int n=grid[0].length;
                int[][] dirs= new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
                boolean[][] visited= new boolean[H][W];
                Queue<int[]> queue= new LinkedList<>();
                for (int i=0;i<m;i++)
                {
                    for (int j=0;j<n;j++)
                    {
                        if (grid[i][j]==0)
                        {
                            q.add(new int[]{i,j});
                            visited[i][j]=true;
                        }
                    }
                }
                int dist=0;
                while (!q.isEmpty())
                {
                    int size= q.size();
                    for (int i=0;i<size;i++)
                    {
                        int[] curr= q.poll();
                        for(int[] dir: dirs)
                        {
                            int r=  curr[0]+dir[0];
                            int c=  curr[1]+dir[1];

                            if (r>=0 && c>=0 && r<H && c<W && grid[r][c]==-1 && !visited[r][c]){
                                q.add(new int[]{r,c});
                                visited[r][c]=true;
                            }
                        }
                    }
                    dist++;
                }
                result = Math.min(result,dist-1);
            }
        }
    }
}