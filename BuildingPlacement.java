// Time Complexity: O(H*W*(H*W) C (N)), where H are the rows, W are the columns, N is the number of buildings
// Space Complexity: O(H*W)

package OptimizeBuildingPlacement;

import java.util.LinkedList;
import java.util.Queue;

public class BuildingPlacement {

    int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(4, 4, 3));
    }

    public int findMinDistance(int H, int W, int n) {
        int[][] grid = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                grid[i][j] = -1;
            }
        }

        backtrack(grid, 0, 0, n, H, W);

        return minDistance;
    }

    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    private void bfs(int[][] grid, int H, int W) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];


        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {

                if(grid[i][j] == 0) {
                    visited[i][j] = true;
                    q.add(new int[] {i, j});
                }
            }
        }

        int dist = 0;

        while(!q.isEmpty()) {
            int countNodeLevel = q.size();

            for(int i = 0; i <countNodeLevel; i++) {
                int[] current = q.poll();
                for(int k = 0; k < 4; k++) {
                    int x = current[0] + dx[k];
                    int y = current[1] + dy[k];

                    if(isValid(H, W, x, y) && !visited[x][y]) {
                        visited[x][y] = true;
                        q.add(new int[] {x, y});

                    }
                }
            }
            dist++;
        }
        minDistance = Math.min(minDistance, dist - 1);
    }

    private boolean isValid(int m, int n, int x, int y) {
        // bounds check
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    public void backtrack(int[][] grid, int r, int c, int n, int H, int W) {
        // base case
        if(n == 0) {
            bfs(grid, H, W);
//            for(int[] row : grid) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println();
            return;
        }

        // bounds for j
        if(c >= W) {
            r++;
            c = 0;
        }

        for(int i = r; i < H; i++) {
            for(int j = c; j < W; j++) {
                grid[i][j] = 0;
                backtrack(grid, i, j+1, n-1, H, W);
                grid[i][j] = -1;
            }
            c = 0;
        }
    }

}
