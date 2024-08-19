// Time Complexity : O( (H*W)!/(H*W - n)! * O(H * W)
// Space Complexity : O(H * W)
// Did this code successfully run on Leetcode :ywa
// Any problem you faced while coding this :
public class Main {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(4, 4, 3));
    }

    public static class BuildingPlacement{
        int H ; int W; int min;
        private int[][] optimalPlacement;
        public int findMinDistance(int h, int w, int n){
            this.H = h;
            this.W = w;
            this.min = Integer.MAX_VALUE;
            this.optimalPlacement = new int[H][W];
            int[][] grid = new int[H][W];
            for(int[] row : grid){
                Arrays.fill(row, -1);
            }
            backtrack(grid, 0, n);
            return min;

        }

        private void backtrack(int[][] grid, int pivot, int n){
            if(n == 0){
                //all the n buildings have been placed
                bfs(grid);
            }

            for(int i = pivot; i < H*W; i++){
                int row = i / W;
                int col = i % W;

                //place a building here
                grid[row][col] = 0;
                backtrack(grid, i + 1, n-1);
                grid[row][col] = -1;

            }

        }

        private void bfs(int[][] grid){

            boolean[][] visited = new boolean[H][W];
            Queue<int[]> q = new LinkedList<>();

            for(int row = 0; row < H; row++){
                for(int col = 0; col < W; col++){
                    if(grid[row][col] == 0){
                        visited[row][col] = true;
                        q.add(new int[] {row, col});
                    }
                }
            }

            int[][] neighbors = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
            int distance = 0;

            while(! q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    int[] currentCell = q.poll();
                    for(int[] neighbor : neighbors){
                        int nr = neighbor[0] + currentCell[0];
                        int nc = neighbor[1] + currentCell[1];

                        if(nr >=0 && nr < H && nc >=0 && nc < W && ! visited[nr][nc]){
                            visited[nr][nc] = true;
                            q.add(new int[] {nr, nc});
                        }
                    }
                }
                distance++;
            }

            min = Math.min(min, distance-1);
        }
    }
}