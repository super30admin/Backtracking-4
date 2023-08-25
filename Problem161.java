package problems.backtrack;

import java.util.LinkedList;
import java.util.Queue;

public class Problem161 {
    public static void main(String[] args) {
        Problem161 buildingPlacement = new Problem161();
        System.out.print(buildingPlacement.findMinDistance(5, 5, 2));
      //  System.out.print(buildingPlacement.findMinDistance(8, 7, 2));

    }
    int h;
    int w;
    int min;

    private int findMinDistance(int height, int width, int n) {
        this.w=width;
        this.h=height;
        this.min=Integer.MAX_VALUE;
        int[][] grid=new int[height][width];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                grid[i][j]=-1;
            }
        }
        backtrack(grid,n,0);
        return min;
    }
    private void backtrack(int[][] grid,int n,int idx){
        if(n<0)
            return;
        if(n==0)
            bfs(grid);
        for(int i=idx;i<h*w;i++){
            int nr=i/w;
            int nc=i%w;
            grid[nr][nc]=0;
            backtrack(grid,n-1,i+1);
            grid[nr][nc]=-1;
        }
    }
    private void bfs(int[][] grid){
        Queue<int []> q = new LinkedList<>();
        boolean [][] visited = new boolean[h][w];
        int [][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
        for(int i = 0; i< h; i++){
            for(int j = 0; j < w; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        //process bfs
        int dist = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int [] curr = q.poll();
                for(int [] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < h && nc < w && !visited[nr][nc]){
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        min = Math.min(min, dist-1);
    }
}
