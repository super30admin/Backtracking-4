// Time Complexity :O(K^n/k)
// Space Complexity :O(N)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    List<String> ans;
    public String[] expand(String s) {
        List<List<Character>> groups = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            List<Character> group = new ArrayList<>();
            char ch = s.charAt(i);
            if(ch == '{'){
                i++;
                while(s.charAt(i)!='}'){
                    if(s.charAt(i) != ','){
                        group.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else{
                group.add(ch);
            }
            Collections.sort(group);
            groups.add(group);
            i++;
        }
        this.ans = new ArrayList<>();
        backtrack(groups,0,new StringBuilder());
        String[] res = new String[ans.size()];
        for(int j = 0; j < res.length; j++){
            res[j] = ans.get(j);
        }
        return res;
        
    }
    private void backtrack(List<List<Character>> groups, int idx, StringBuilder sb){
        //base
        if(idx == groups.size()){
            System.out.println(sb.toString());
            ans.add(sb.toString());
            return;
        }
        //logic
        List<Character> group = groups.get(idx);
        for(int i = 0; i < group.size(); i++){
            char ch = group.get(i);
            sb.append(ch);
            backtrack(groups,idx+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}