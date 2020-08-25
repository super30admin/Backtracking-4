//time complexity O(HW * 2^HW * n)
//space complexity O(2HW)

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class OptimalPlacementOfBuildings{
    public static void main (String[] args){
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(4, 4, 2));
    }

    public static class BuildingPlacement {
        int minDistance = Integer.MAX_VALUE;
        public int findMinDistance(int H, int W, int n){
            int grid[][] = new int[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    grid[i][j] = -1;
                }
            }
            //we start backtracking which finds the combination of
            //BuildingPlacements and then we call BFS on that combination
            //which in turn sets the minDistance
            //Thus with all the combinations we get from backtracking we
            //find the minDistance possible
            backtrack(grid, 0, 0, n, H, W);
            return minDistance;
        }
        private void backtrack(int [][]grid, int r, int c, int n, int H, int W){
            //base
            if(n == 0){
                bfs(grid, H, W);
                return;
            }

            //logic
            for(int i = r; i < H; i++){
                for(int j = c; j < W; j++){
                    //action
                    grid[i][j] = 0;
                    //recurse
                    backtrack(grid, r, c + 1, n -1, H, W);
                    //backtrack
                    grid[i][j] = -1;
                }
                //this c is set to zero because after the iteration of a throw
                //the column should be reset to 0 and not c
                //eg: is r and c is in the middle of the grid
                //when the row is finished r is incremented + 1 in the next iteration
                //but we have to start column from 0
                c = 0;
            }

        }
        private void bfs(int[][]grid, int H, int W){
            Queue<int[]> q = new LinkedList<>();
            boolean [][] visited = new boolean[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){
                        q.add(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            //now we have placed the buildings
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int dist = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int k = 0; k < size; k++){
                    int[] curr = q.poll();
                    for(int[] dir: dirs){
                        int r = curr[0] + dir[0];
                        int c = curr[1] + dir[1];
                        if(r >= 0 && r < H && c >= 0 && c < W && !visited[r][c]){
                            q.add(new int[]{r, c});
                            visited[r][c] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(minDistance, dist -1);
        }
    }

}
