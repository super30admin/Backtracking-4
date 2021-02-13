//Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. 
//The goal is for the furthest of all lots to be as near as possible to a building.
// "static void main" must be defined in a public class.
// TC exponential SC O(H*W)
public class Main {
    public static class BuildingPlacement {
        int minDistance; 
        public BuildingPlacement() {
            minDistance =  Integer.MAX_VALUE;
        }
        
        private int findMinDistance(int H, int W, int n) {
            int[][]grid = new int[H][W];
            for (int i =0; i<H; i++) {
                for (int j = 0; j <W; j++) {
                    grid[i][j]=-1;
                }
            }
            backtrack(grid, 0, 0, H, W, n);
            return minDistance;
        }
        
        private void bfs(int [][] grid, int H, int W) {
            boolean[][] visited = new boolean[H][W];
            Queue<int[]> q  = new LinkedList<>();
            for (int i = 0; i < H; i ++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        q.add(new int [] {i, j});
                        visited[i][j] = true;
                    }
                }
            }
            int dist = 0;
            int [][]dirs = {{0,1}, {0, -1}, {1,0}, {-1, 0}};
            while (!q.isEmpty()){
                int size = q.size();
                for (int i = 0; i < size; i ++) {
                    int [] curr = q.poll();
                    for (int [] dir : dirs) {
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc]) {
                            q.add(new int[] {nr, nc});
                             visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(minDistance, dist-1);
        }
        private void backtrack(int [][] grid, int r, int c, int H, int W, int n) {
            if (n == 0){
                bfs(grid, H, W);
                return;
            }
            if (c == W) {
                r++;
                c = 0;
            }
            for (int i = r; i < H; i++) {
                for (int j = c; j < W; j++){
                    // action
                    grid[i][j] = 0;
                    // recurse
                    backtrack(grid, i, j, H, W, n-1);
                    // backtrack
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
    }
    public static void main(String[] args) {
        BuildingPlacement buildings = new BuildingPlacement();
        System.out.println(buildings.findMinDistance(3, 2, 1));
    }
}
