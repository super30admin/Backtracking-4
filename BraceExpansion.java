// Time Complexity : O(k^n)
// Space Complexity : O(nk)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    
    List<String> result;
    public String[] expand(String s) {
        
        List<List<Character>> table = new ArrayList<>();
        result = new ArrayList<>();
        int i = 0;
        
        while(i < s.length()) {
            char ch = s.charAt(i);
            List<Character> list = new ArrayList<>();

            if(ch == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        list.add(s.charAt(i)); 
                    }
                        
                    i++;
                }
                
            } else {
                list.add(ch);
            }
            Collections.sort(list);
            table.add(list);
            i++;
        }
        
        backtrack(table, new StringBuilder() ,0);
        
        String[] answer = new String[result.size()];
        
        for(int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }
        
        
        return answer;
    }
    
    private void backtrack(List<List<Character>> table, StringBuilder sb, int index) {
        //base case
        if(index == table.size()) {
            result.add(sb.toString());
            return;
        }
        
        //logic
        
        List<Character> chars = table.get(index);
        
        for(int i = 0; i < chars.size(); i++) {
            //action
            sb.append(chars.get(i));
            //recurse
            backtrack(table, sb, index + 1);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}