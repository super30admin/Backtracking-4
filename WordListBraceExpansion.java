// Time Complexity : O(n^n/k)
// Space Complexity : O(n^n/k)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class WordListBraceExpansion {
    class Solution {
        List<String> result;
        public String[] expand(String s) {
            int sLen = s.length();
            this.result = new ArrayList<>();
            List<List<Character>> groups = new ArrayList<>();
            int i = 0;

            while(i < sLen){
                char c = s.charAt(i);
                List<Character> group = new ArrayList<>();
                if(c == '{'){
                    i++;
                    while(s.charAt(i) != '}'){
                        if(s.charAt(i) != ',')
                            group.add(s.charAt(i));
                        i++;
                    }
                    i++;
                }
                else{
                    group.add(c);
                    i++;
                }
                Collections.sort(group);
                groups.add(group);
            }

            backtrack(groups, 0, new StringBuilder());

            String[] re = new String[result.size()];
            for(int k = 0; k < result.size(); k++){
                re[k] = result.get(k);
            }
            return re;
        }

        private void backtrack(List<List<Character>> groups, int idx, StringBuilder sb){
            //base
            if(idx == groups.size()){
                result.add(sb.toString());
                return;
            }
            //logic
            List<Character> group = groups.get(idx);
            for(char c : group){
                //action
                sb.append(c);
                //recurse
                backtrack(groups, idx+1, sb);
                //recurse
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
