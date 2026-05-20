import java.util.*;

public class OptimalPlacement {

    /*
     * Idea: Greedy k-center (farthest-first) placement on grid.
     * Repeatedly place a building at the cell farthest from existing buildings,
     * recomputing shortest distances using multi-source BFS after each placement.
     * 
     * Time Complexity: O(n * w * h * (w * h))
     * Space Complexity: O(w * h)
     */
    public int optimalDistance(int w, int h, int n) {
        List<int[]> buildings = new ArrayList<>();

        int bestMaxDist = Integer.MAX_VALUE;

        for (int b = 0; b < n; b++) {

            int[][] dist = computeDistance(w, h, buildings);

            int maxDist = 0;
            int[] farthestCell = new int[]{0, 0};

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (dist[i][j] > maxDist) {
                        maxDist = dist[i][j];
                        farthestCell = new int[]{i, j};
                    }
                }
            }

            bestMaxDist = maxDist;

            buildings.add(farthestCell);
        }

        return bestMaxDist;
    }

    // Multi-source BFS to compute distance from all buildings
    private int[][] computeDistance(int w, int h, List<int[]> buildings) {

        int[][] dist = new int[h][w];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();

        for (int[] b : buildings) {
            dist[b[0]][b[1]] = 0;
            q.offer(new int[]{b[0], b[1]});
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nc >= 0 && nr < h && nc < w) {
                    if (dist[nr][nc] > dist[cur[0]][cur[1]] + 1) {
                        dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist;
    }
}