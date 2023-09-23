//Problem 1: Brace Expansion
// Time Complexity : O(K^(N/K))
// Space Complexity : O(NK)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//K^(N/K) //O(NK)
//try to form groups of individual characters beyond braces and for characters within braces, form just 1 list of characters.
//do dfs on groups to form number of strings.
class Solution {
    List<String> res;
    public String[] expand(String s) {
        this.res=new ArrayList<>();
        List<List<Character>> group=new ArrayList<>();
        int i=0;
        while(i<s.length()){
            char c=s.charAt(i);
            List<Character> grp=new ArrayList<>();
            if(c=='{'){
                i++;
                while(s.charAt(i) !='}'){
                    if(s.charAt(i) !=','){
                        grp.add(s.charAt(i));
                    }
                    i++;
                }//while
                // i++; //escape the closing brace
            }else{
                grp.add(c);
                // i++;
            }
            i++;
            Collections.sort(grp);
            group.add(grp);
        }

        backtrack(group, 0, new StringBuilder());
        String[] resArr=new String[res.size()];
        for(int j=0;j<resArr.length;j++){
            resArr[j]=res.get(j);
        }

        return resArr;
    }

    private void backtrack(List<List<Character>> group, int idx, StringBuilder sb){
        //base
        if(idx==group.size()){
            res.add(sb.toString());
            return;
        }

        //logic
        List<Character> curGrp= group.get(idx);
        for(int i=0;i<curGrp.size();i++){
            char c=curGrp.get(i);
            //action
            int len=sb.length();
            sb.append(c);

            //recurse
            backtrack(group, idx+1, sb);

            //backtrack
            sb.setLength(len);
        }
    }
}

//Problem 1: Optimal Building Placement
// Time Complexity : O(HWcN)
// Space Complexity : O(HW)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : tough one


// Your code here along with comments explaining your approach
public class Main {
    public static void main(String[] args) {
        BuildPlace bp= new BuildPlace();
        System.out.println(bp.findmin(7,7,3));
    }
    
    public static class BuildPlace{
        int h, w, min;
        
         public int findmin(int H, int W, int n){
            this.h=H;
            this.w=W;
            this.min=Integer.MAX_VALUE;
            
            int[][] grid= new int[h][w];
            
            for(int i=0;i<h;i++){
               for(int j=0;j<w;j++){
                    grid[i][j]=-1;
               } 
            }
            backtrack(grid, n, 0);
                return min;
        }
        
        private void bfs(int[][] grid){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[h][w];
            int[][] dirs= new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
            for(int i=0;i<h;i++)
               for(int j=0;j<w;j++)
                   if(grid[i][j]==0){
                       q.add(new int[]{i,j});
                       visited[i][j]=true;
                   }
            
            int dist=0;
            while(!q.isEmpty()){
                int size= q.size();
                
                while(size-- >0){
                    int[] cur=q.poll();
                    
                    for(int[] dir: dirs){
                        int nr=dir[0]+cur[0];
                        int nc=dir[1]+cur[1];
                        if(nr<h && nr>=0 && nc<w && nc>=0 && 
                            !visited[nr][nc] && grid[nr][nc]==-1){
                            q.add(new int[]{nr,nc});
                            visited[nr][nc]=true;
                        }
                    }
                }
                dist++;
            }
                min= Math.min(min, dist-1);
            
        }
        
        private void backtrack(int[][] grid, int n, int pivot){
            //base
            if(n==0){
                bfs(grid);
                return;
            }
            //logic
            for(int i=pivot; i<h*w;i++){
                int r=i/w;
                int c=i%w;
                
                grid[r][c]=0;
                
                backtrack(grid, n-1, i+1);
                //backtrack
                grid[r][c]=-1;
            }
        }
    }
}