// Time Complexity : exponential    
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we solve this problem using backtracking and bfs
// we first create a grid and assign -1
// we then pass it to backtrack function for placing the buildings
// in the base case of this function we call the bfs to calculate the distance between the building and parkinglot
//we take min of all the distances and return as answer

public class Main {
    public static class BuildingPlacement {
        int min;

        public int findMinDistance(int H, int W, int N) {
            int[][] grid = new int[H][W];
            min = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    grid[i][j] = -1;

                }
            }

            backtrack(grid, 0, 0, N, H, W);
            return min;
        }

        public void bfs(int[][] grid, int H, int W) {
            boolean[][] visited = new boolean[H][W];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        q.add(new int[] { i, j });
                        visited[i][j] = true;
                    }

                }
            }
            int dist = 0;
            int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir : dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];
                        if (nr >= 0 && nc >= 0 && nr < H && nc < W && visited[nr][nc] == false) {
                            q.add(new int[] { nr, nc });
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
            min = Math.min(min, dist - 1);
        }

        public void backtrack(int[][] grid, int r, int c, int N, int H, int W) {
            if (N == 0) {
                bfs(grid, H, W);
                return;
            }

            if (c == W) {
                r++;
                c = 0;
            }
            for (int i = r; i < H; i++) {
                for (int j = c; j < W; j++) {
                    grid[i][j] = 0;

                    backtrack(grid, i, j + 1, N - 1, H, W);

                    grid[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement();

        System.out.println(bp.findMinDistance(3, 2, 1));
    }
}