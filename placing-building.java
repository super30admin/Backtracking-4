// Time, Space - O(exp), O(HW)
// "static void main" must be defined in a public class.
public class Main {
    public static class BuildingPlacement {
        int minDistance;
        public BuildingPlacement() {
            minDistance = Integer.MAX_VALUE;
        }
        
        private int findMinDistance(int H, int W, int n) {
            int[][] grid = new int[H][W];
            for(int i=0;i<H;i++) {
                for(int j=0;j<W;j++) {
                    grid[i][j] = -1;
                }
            }
            
            backtrack(grid, 0, 0, H, W, n);
            return minDistance;
        }
        private void bfs(int[][] grid, int H, int W) {
            boolean[][] visited = new boolean[H][W];
            Queue<int[]> q = new LinkedList<>();
            for(int i=0;i<H;i++) {
                for(int j=0;j<W;j++) {
                    if(grid[i][j] == 0) {
                        q.add(new int[] {i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int dist = 0;
            int[][] dirs = {{0,1}, {0,-1},{1,0},{-1,0}};
            while(!q.isEmpty()) {
                int size= q.size();
                for(int i=0;i<size;i++) {
                    int[] cur = q.poll();
                    for(int[] dir : dirs) {
                        int r= dir[0] + cur[0];
                        int c = dir[1] + cur[1];
                        if(r>=0 && c>=0 && r<H&&c<W &&!visited[r][c]) {
                            q.add(new int[] {r,c});
                            visited[r][c] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(dist - 1, minDistance);
        }
        private void backtrack(int[][] grid, int r, int c, int H, int W, int n) {
            if(n == 0) {
                bfs(grid, H, W);
                return;
            }
            if(c == W) {
                r++;
                c=0;
            }
            for(int i=r;i<H;i++) {
                for(int j=c;j<W;j++) {
                    // action
                    grid[i][j] = 0;
                    // recurse
                    backtrack(grid, i, j + 1, H, W, n - 1);
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
    }
    public static void main(String[] args) {
        BuildingPlacement buildings = new BuildingPlacement();
        System.out.println(buildings.findMinDistance(4, 4, 1));
    }
    
}
