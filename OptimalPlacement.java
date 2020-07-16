//time - O(HW C n) as in nCr
// space - O(HW)
class optimalPlacement {
    
    int result = Integer.MAX_VALUE; //return value
    
    public int bfs(int[][] board) {
        //same as 01 matrix
        int maxDistance = 0; //max distance between a parking lot(-1) and building(0)
        Queue<int[]> support = new LinkedList<>();
        //cant manipulate board[][] as it comes from backtrack() - so create a temp[][] as a copy of board
        int[][] temp = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                //process all 0s together, and replace -1s with infinity
                if(board[i][j] == 0)
                {
                    temp[i][j] = 0;
                    support.offer(new int[]{i, j});
                }
                else
                {
                    temp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while(!support.isEmpty())
        {
            int[] current = support.poll();
            for(int[] dir : dirs)
            {
                //for each neighbor, relax edge if a shorter distance is found
                int nRow = current[0] + dir[0];
                int nCol = current[1] + dir[1];
                if(nRow >= 0 && nRow < temp.length && nCol >= 0 && nCol < temp[0].length)
                {
                    if(temp[nRow][nCol] > 1 + temp[current[0]][current[1]])
                    {
                        temp[nRow][nCol] = 1 + temp[current[0]][current[1]];
                        support.offer(new int[]{nRow, nCol});
                        maxDistance = Math.max(maxDistance, temp[nRow][nCol]);
                    }
                }
            }
        }
        
        return maxDistance; //return max distance
    }
    
    public void backtrack(int[][] board, int buildings, int currentRow, int currentCol) {
        //base
        if(buildings == 0) //all buildings placed
        {
            int currentVal = bfs(board); //do dfs and update result if needed
            result = Math.min(result, currentVal);
            return;
        }
        //logic
        if(currentCol >= board[0].length)
        {
            currentRow++;
            currentCol = 0;
        }
        for(int i = currentRow; i < board.length; i++)
        {
            for(int j = currentCol; j < board[0].length; j++)
            {
                board[i][j] = 0; //place building at (i,j)
                backtrack(board, buildings - 1, i, j + 1); //recurse & place building at same row next col
                board[i][j] = -1; //bactrack to explore other possiblities
            }
        }
        return;
    }
    
    public int findMin(int H, int W, int n) {
        int[][] board = new int[H][W];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            { 
                board[i][j] = -1; //fill each cell with -1s
            }
        }
        backtrack(board, n, 0, 0); //start placing from cell 0,0
        return result;
    }
    
    public static void main(String[] args) {
        optimalPlacement obj = new optimalPlacement();
        System.out.println(obj.findMin(4, 4, 1));
    }
}
