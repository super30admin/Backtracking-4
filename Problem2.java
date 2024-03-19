public class Solution {
    /**
     * @param s: A string
     * @return: All words that can be formed
     */
     //TC: O(k^(l/k)
    //(l/k) -- number of groups
   // k -- (avg  size of each group)
     //SC: O(l)
     List<String> result;
    public String[] bracketExpansion(String s) {
        // write your code here
        this.result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> group = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ',') group.add(s.charAt(i));
                    i++;
                }
            }else{
                group.add(c);
            }
            i++;
            Collections.sort(group);
            groups.add(group);
        }
        backtrack(groups, 0 , new StringBuilder());
        String [] arr = new String[result.size()];
        for(int k = 0; k < arr.length; k++){
            arr[k] = result.get(k);
        }
        return arr;
    }
    private void backtrack(List<List<Character>> groups, int idx, StringBuilder sb){
        //base
        if(idx == groups.size()){
            result.add(sb.toString());
            return;
        }

        //logic
        List<Character> currGroup = groups.get(idx);
        for(int i = 0; i < currGroup.size(); i++){
            char c = currGroup.get(i);
            //action
            sb.append(c);
            //recurse
            backtrack(groups,idx+1,sb);  //take element from next Character
            //backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }


















}
