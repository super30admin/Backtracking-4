class OptimalPlacementOfBuildingsInGrid {

    // Time Complexity: O(m*n)
    // Space Complexity: O(m*n)

    public static class BuildingPlacement {
        int minDist = Integer.MAX_VALUE;

        public int findMinDistance(int h, int w, int n){
            int[][] grid = new int[h][w];
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    grid[i][j] = -1;
                }
            }

            backtracking(grid, 0, 0, n, h, w);

            return minDist;
        }

        private void bfs(int[][] grid, int h, int w){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[h][w];

            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    if(grid[i][j] == 0){
                        q.offer(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }

            int level = 0;
            int[][] dirs = {{-1, 0}, {1,0}, {0,1}, {0,-1}};
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    int[] front = q.poll();
                    int r = front[0];
                    int c = front[1];

                    for(int[] dir : dirs){
                        int nextRow = r + dir[0];
                        int nextCol = c + dir[1];
                        if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w && visited[nextRow][nextCol] == false){
                            q.offer(new int[]{nextRow, nextCol});
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
                level++;
            }
            minDist = Math.min(minDist, level - 1);
        }

        private void backtracking(int[][] grid, int row, int col, int n, int h, int w){
            if(n <= 0){
                bfs(grid, h, w);
                return;
            }          

            if(col >= w){
                row++;
                col = 0;
            }
            
            for(int i = row; i < h; i++){
                for(int j = col; j < w; j++){
                    grid[i][j] = 0;
                    backtracking(grid, i, j, n-1, h, w);
                    grid[i][j] = -1;
                }
                col = 0;
            } 
        }
    }

    public static void main(String[] args){
        BuildingPlacement bp = new BuildingPlacement();
        System.out.println(bp.findMinDistance(3,3,2));
    }
}