// "static void main" must be defined in a public class.

//TC: O(hw) * n!)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // w=4, h=4 and n=3.
        int h = 4;
        int w = 4;
        int n = 3;
        Solution s1 = new Solution();
        int min = s1.findMinDistance(h,w,n);
        System.out.println(min);
    }
}

class Solution{
    int minDist;
    int h;
    int w;
    public int findMinDistance(int h, int w, int n){
        
        this.minDist = Integer.MAX_VALUE;
        int[][]grid = new int[h][w];
        
        this.h = h;
        this.w = w;
        for(int i = 0 ; i < h;i++){
            for(int j = 0; j < w;j++){
                grid[i][j] = -1;
            }
        }
        backtrack(grid,0,0,n);
        return minDist;
    }
    
    
    private void backtrack(int[][]grid, int r, int c, int n){
        //base
        if(n == 0){
            bfs(grid);
            return;
        }
        if(c >= w){ //when col is going out of bound
            r++;c = 0;
        }
        //case
        for(int i = r;i < h;i++){
            for(int j = c;j < w;j++){
                //action
                grid[i][j] = 0;
                //recurse
                backtrack(grid,i,j+1,n-1);
                //backtrack
                grid[i][j] = -1;
            }
            c = 0; //reset col to 0
        }
    }
    
    private void bfs(int[][]grid){
        int[][]dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        Queue<int[]> queue = new LinkedList<>();
         boolean[][]visited = new boolean[h][w];
        for(int i = 0;i < h;i++){
            for(int j = 0; j < w;j++){
                if(grid[i][j] == 0){
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0;k < size;k++){
                int[]curr = queue.poll();
                 int i = curr[0];
                int j = curr[1];
                for(int[]d : dirs){
                    int nr = d[0] + i;
                    int nc = d[1] + j;
                    if(nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]){
                        queue.add(new int[]{nr,nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        minDist = Math.min(minDist, dist-1);
    }
}