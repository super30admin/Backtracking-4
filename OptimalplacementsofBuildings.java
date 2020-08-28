import java.util.*;
// Class declaration
class OptimalplacementsofBuildings{
    int gmin = Integer.MAX_VALUE;
    int[][] grid;
    int h;
    int w;

    public OptimalplacementsofBuildings(){}
    public OptimalplacementsofBuildings(int h, int w){
        this.h = h;
        this.w = w;
        this.grid = new int[h][w];
        initialize();
    }

    // intialize grid cell with Inf
    public void initialize(){
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                grid[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    // Driver function 
    public static void main(String args[]){
        int w = 4, h =4, n=3;
        OptimalplacementsofBuildings obj = new OptimalplacementsofBuildings(h,w);
        System.out.println(obj.backtrack(h,w,0,0,n));
    }

    // backtrack, if all the builduings has been placed then find out maximum diatance 
    // dist between empty lot and builduing
    private int backtrack(int h, int w, int r, int c, int n){
        //base case
        if(n ==0 ){
            return dfs();
        }
        int res = 0;
        // place buildings into the cell
        for(int i=r; i<h;i++){
            for(int j=c; j<w;j++){
                grid[i][j] = 0;
                if(j+1 < w)
                    res = backtrack(h,w,i,j+1,n-1);
                else
                    res = backtrack(h,w,i+1,0,n-1);
                gmin = Math.min(res,gmin);
                grid[i][j] = Integer.MAX_VALUE;
            }
        }
        return gmin;
    }

    // this function calculate maximin distance between builduing and lot
    private int dfs(){
        int[][] directions = {{0,-1},{0,1},{1,0},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();

        // Add position of builduing into the queue
        for(int i=0; i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 0)
                    queue.add(new int[]{i,j});
            }
        }
        int min = Integer.MIN_VALUE;
        // check all entries of queue and find out maximum diatnce between builduing and a lot
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            for(int[] dir : directions){
                int x = temp[0] + dir[0];
                int y = temp[1] + dir[1];
                if(x>=0 && x<grid.length && y >=0 && y <grid[0].length && grid[x][y]  > grid[temp[0]][temp[1]] + 1){
                    grid[x][y] = grid[temp[0]][temp[1]] +1;
                    queue.offer(new int[]{x,y});
                    min = Math.max(min,grid[x][y]);
                }
            }
        }
        return min;
    }
}