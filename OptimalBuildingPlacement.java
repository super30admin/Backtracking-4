//TC: O(hw* (hw Permutation n)
//SC: O(hw)
//approach: backtracking with bfs

import java.util.LinkedList;
import java.util.Queue;

public class OptimalBuildingPlacement {
    static int minDistance;
    public static void main(String[] args) {
        System.out.println(findMinDistance(4,4,3));
    }

    public static int findMinDistance(int h, int w, int n){
        int[][] grid = new int[h][w];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                grid[i][j]=-1;
            }
        }
        minDistance = Integer.MAX_VALUE;
        backtrack(grid, 0,0, n);
        return minDistance;
    }

    private static void backtrack(int[][] grid, int i, int j, int n){
        if (n==0){
            bfs(grid);
            return;
        }
        if (j==grid[0].length){
            i++;
            j=0;
        }

        for (int r=i;r< grid.length;r++){
            for (int c=j;c<grid[0].length;c++){
                grid[r][c]=0;
                backtrack(grid, r, c+1, n-1);
                grid[r][c]=-1;
            }
            j=0;
        }
    }

    private static void bfs(int[][] grid){
        Queue<int[]> q = new LinkedList<>();
        int d = -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0;i<size;i++){
                int[] curr = q.poll();
                for (int[] dir:dirs){
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];
                    if (nr>=0 && nr< grid.length && nc>=0 && nc<grid[0].length && !visited[nr][nc]){
                        q.add(new int[]{nr,nc});
                        visited[nr][nc]=true;
                    }
                }
            }
            d++;
        }
        minDistance = Math.min(d, minDistance);
    }
}
