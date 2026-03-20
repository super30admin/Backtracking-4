// Time Complexity : O(P(h*w,n) * h*w)
// Space Complexity : O(h*w)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Place all the n buildings in the h*w grid using backtracking
// Run bfs to find the find the mininum farthest distance of parking lot
// To avoid r,c index complexity - convert the 2d to 1d index using i/w and i%w

import java.util.LinkedList;
import java.util.Queue;

class OptimalPlacementOfBuilding {

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement();
        System.out.println(bp.findMinDist(7, 4, 3));
    }

    static class BuildingPlacement {
        int h, w, n;
        int min;

        public int findMinDist(int h, int w, int n) {
            this.h = h;
            this.w = w;
            this.n = n;
            this.min = Integer.MAX_VALUE;

            boolean[][] grid = new boolean[h][w];
            backtrack(grid, 0, n);
            return min;
        }

        private void backtrack(boolean[][] grid, int idx, int n) {
            if (n == 0) {
                bfs(grid);
                return;
            }

            for (int i = idx; i < h * w; i++) {
                int r = i / w;
                int c = i % w;
                grid[r][c] = true;
                backtrack(grid, i + 1, n - 1);
                grid[r][c] = false;
            }
        }

        private void bfs(boolean[][] grid) {
            int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            boolean[][] visited = new boolean[h][w];
            Queue<int[]> q = new LinkedList<>();

            for (int i = 0; i < h; i++)
                for (int j = 0; j < w; j++)
                    if (grid[i][j]) {
                        q.add(new int[] { i, j });
                        visited[i][j] = true;
                    }

            int dist = 0;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir : dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];

                        if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            q.add(new int[] { nr, nc });
                        }
                    }
                }
                dist++;
            }

            dist--;
            min = Math.min(min, dist);
        }

    }

}