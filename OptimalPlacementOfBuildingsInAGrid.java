// Time Complexity : O(H*W * C(H*W,N))
// Space Complexity : O(H*W)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We initially make the cells in the grid as -1 and then We place all the n buildings first by being
exhaustive and placing them in all H*W positions and adjusting the row, column boundaries. When we place all
the buildings, we do a bfs to calculate the minimum distance and maintain in the global result. We repeat
the process for all combinations and update the result whenever min value is found.
 */
import java.util.LinkedList;
import java.util.Queue;

public class OptimalPlacementOfBuildings {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDistance(7, 7, 3));
    }

    public static class BuildingPlacement {
        int H;
        int W;
        int result;

        public int findMinDistance(int h, int w, int n) {
            this.H = h;
            this.W = w;
            this.result = Integer.MAX_VALUE;
            int[][] grid = new int[H][W];

            for(int i = 0 ; i < H ; i++) {
                for(int j =0 ; j < W ; j++) {
                    grid[i][j] = -1;
                }
            }

            //place the buildings
            dfs(grid, 0, n);
            return result;
        }

        private void dfs(int[][] grid, int pivot, int n) {
            if(n == 0) {
                //calculate the min distance
                bfs(grid);
                return;
            }

            for(int i = pivot ; i < H*W ; i++) {
                int r = i / W;
                int c = i % W;

                grid[r][c] = 0;
                dfs(grid, i + 1, n - 1);
                grid[r][c] = -1;
            }
        }

        private void bfs(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            boolean[][] visited = new boolean[H][W];

            for(int i = 0 ; i < m ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(grid[i][j] == 0) {
                        q.add(new int[]{i, j});
                    }
                }
            }

            int dist = 0;

            while(!q.isEmpty()) {
                int size = q.size();
                for(int i = 0 ; i < size ; i++) {
                    int[] curr = q.poll();
                    for(int[] dir : dirs) {
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if(nr >= 0 && nr < m && nc >=0 && nc < n && grid[nr][nc] == -1 && !visited[nr][nc]) {
                            q.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }

            result = Math.min(result, dist - 1);
        }
    }
}