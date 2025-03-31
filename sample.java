// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Sample(){
    /*****************PROBLEM-1***************/

//TC:0(hw * HW P N)
//sc:0(hw)
    public class Main {
        static int minDistance;
        public static class buildingPlacement{

            public buildingPlacement(){
            }
            int findMinDistance(int H,int W,int n){
                int[][] grid=new int[H][W];
                minDistance=Integer.MAX_VALUE;
                for(int i=0;i<H;i++){
                    for(int j=0;j<W;j++){
                        grid[i][j]=-1;
                    }
                }
                backtrack(grid,0,0,n,H,W);
                return minDistance;
            }
        }
        private static void bfs(int[][] grid,int H,int W){
            Queue<int[]> queue=new LinkedList<>();
            boolean[][] visited=new boolean[H][W];
            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    if(grid[i][j]==0){
                        queue.add(new int[]{i,j});
                        visited[i][j]=true;
                    }
                }
            }
            int dist=0;
            int[][] dirs=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};//
            while(!queue.isEmpty()){
                int size=queue.size();
                for(int i=0;i<size;i++){
                    int[] curr=queue.poll();
                    for(int[] dir:dirs){
                        int nr=curr[0]+dir[0];
                        int nc=curr[1]+dir[1];
                        if(nr>=0 && nr<H && nc>=0 && nc<W && !visited[nr][nc]){
                            queue.add(new int[]{nr,nc});
                            visited[nr][nc]=true;
                        }
                    }
                }
                dist++;
            }
            minDistance=Math.min(minDistance,dist-1);
        }
        private static void backtrack(int[][] grid,int row,int col,int n,int H,int W){
            //base
            if(n==0){
                bfs(grid,H,W);
                return;
            }
            if(col==W){
                row++;
                col=0;
            }
            //logic
            for(int i=row;i<H;i++){
                for(int j=col;j<W;j++){
                    //action
                    grid[i][j]=0;
                    //recurse
                    backtrack(grid,row,col+1,n-1,H,W);
                    //backtrack
                    grid[i][j]=-1;
                }
                col=0;
            }
        }

        public static void main(String[] args) {
            buildingPlacement bp=new buildingPlacement();
            System.out.println(bp.findMinDistance(4,4,3));
        }
    }
    /*****************PROBLEM-2***************/
    //TC:0(n*2^N)
//SC:0(n)
    class Solution {
        List<String> result;
        List<List<Character>> blocks;
        public String[] expand(String s) {
            if(s==null || s.length()==0){
                return new String[]{};
            }
            blocks=new ArrayList<>();
            result=new ArrayList<>();
            int i=0;
            while(i<s.length()){
                List<Character> block=new ArrayList<>();
                if(s.charAt(i)=='{'){
                    i++;
                    while(s.charAt(i)!='}'){
                        if(s.charAt(i)!=','){
                            block.add(s.charAt(i));
                        }
                        i++;
                    }
                }else{
                    block.add(s.charAt(i));
                }
                Collections.sort(block);
                blocks.add(block);
                i++;
            }
            backtrack(0,new StringBuilder());
            String[] answer=new String[result.size()];
            for(int j=0;j<result.size();j++){
                answer[j]=result.get(j);
            }
            return answer;
        }

        private void backtrack(int index,StringBuilder path){
            if(index==blocks.size()){
                result.add(path.toString());
                return;
            }

            //logic
            List<Character> list=blocks.get(index);
            for(char c:list){
                //action
                int len=path.length();
                path.append(c);

                //recurse
                backtrack(index+1,path);

                //backtrack
                path.setLength(len);
            }

        }
    }
}