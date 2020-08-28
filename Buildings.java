// Time Complexity : exponential O (n (2 ^ HW))
// Space Complexity : 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


import java.util.LinkedList;
import java.util.Queue;

public class Buildings {

    public static class BuildingPlacement {
        int minDistance = Integer.MAX_VALUE;
        public int findMinDistance(int H, int W, int n) {
            int grid[][] = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    grid[i][j] = -1;
                }
            }
            backtrack(grid, 0, 0, n, H, W);
            return minDistance;
        }

        private void bfs(int [][] grid, int H, int W) {
            Queue<int[]> q = new LinkedList<>();
            boolean visited[][] = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }

            int dirs[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
            int dist = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int curr[] = q.poll();
                    for (int dir[] : dirs) {
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];

                        if (nr >= 0 && nr < H && nc >=0 && nc <W && !visited[nr][nc]) {
                            q.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(minDistance, dist - 1);
            return;
        }

        private void backtrack(int [][] grid, int r, int c, int n, int H, int W) {
            // base case
            if (n == 0) {
               bfs(grid, H, W);
               return;
            }

            // logic
            if (c == W) {
                r++; c = 0;
            }

            for (int i = r; i < H; i++) {
                for (int j = c; j < W; j++) {
                    // action
                    grid[i][j] = 0; // put the building

                    // recurse
                    backtrack(grid, r,c+1,n-1, H, W);

                    // backtrack
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
    }

    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDistance(4, 4, 3));
    }
}
