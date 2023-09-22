import java.util.*;

// tc : exponential 
// sc : O(w*h) (+ memory stack which is less that w*h)



public class PlaceBuildings {

    // Inner class
    class PlaceBuilding {
        int w, h, totalBuildings;
        int[][] grid;
        int minDist;

        public PlaceBuilding(int w, int h, int n) {
            this.w = w;
            this.h = h;
            this.totalBuildings = n;
            this.minDist = Integer.MAX_VALUE;
            this.grid = new int[h][w];
        }

        public int minDist() {
            for (int[] g : grid)
                Arrays.fill(g, -1);
            // get the min dist
            this.minDist = Integer.MAX_VALUE;
            backtrack(0, 0, totalBuildings);
            return minDist;
        }

        // method to backtrack
        public void backtrack(int r, int c, int n) {
            // base case
            if (n == 0) {
                calculateDistance();
                return;
            }
            if (c == w) {
                r++;
                c = 0;
            }
            // logic
            for (int i = r; i < h; i++) {
                for (int j = c; j < w; j++) {
                    // action
                    grid[i][j] = 0;
                    // recurse
                    backtrack(i, j + 1, n - 1);
                    // backtrack
                    grid[i][j] = -1;
                }
            }
        }

        // method get minDist -> at the current location
        public void calculateDistance() {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] seen = new boolean[h][w];
            // add locations
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == 0) {
                        q.add(new int[] { i, j });
                        seen[i][j] = true;
                    }
                }
            }

            int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; // R L U D

            // bfs
            int distance = -1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] currLoc = q.poll();
                    for (int[] dir : dirs) {
                        int nr = dir[0] + currLoc[0];
                        int nc = dir[1] + currLoc[1];
                        if (nr >= 0 && nr < h && nc >= 0 && nc < w && !seen[nr][nc]) {
                            seen[nr][nc] = true;
                            q.add(new int[] { nr, nc });
                        }
                    }
                }
                distance++; // Increment distance for each level in BFS.
            }

            minDist = Math.min(minDist, distance);
        }
    }

    public static void main(String[] args) {
        PlaceBuildings placeBuildings = new PlaceBuildings();
        PlaceBuilding build = placeBuildings.new PlaceBuilding(4, 4, 3);
        System.out.println(build.minDist());
    }
}
