// Time Complexity : O(exponential)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : NO // premium question
// Any problem you faced while coding this : No

class Solution {
    List<String> result;
    public String[] expand(String s) {

        result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        int i = 0; 
        while(i< s.length()){
            List<Character> group = new ArrayList<>();
            char c = s.charAt(i);
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                i++; //for closing bracket
            }
            else{
                group.add(c);
            }
            i++;
            Collections.sort(group);
            groups.add(group);
        }
        backtrack(groups, 0, new StringBuilder());
        String[] answer = new String[result.size()];

        for(int j = 0; j< result.size(); j++){
            answer[j] = result.get(j);
        }
        return answer;
    }

    private void backtrack(List<List<Character>> groups, int index, StringBuilder sb){
        //base
        if(index == groups.size()){
            result.add(sb.toString());
            return;
        }

        //logic
        List<Character> list = groups.get(index);
        for(char c: list){
            //action
            int len = sb.length();
            sb.append(c);
            //recurse
            backtrack(groups, index +1, sb);
            //backtrack
            sb.setLength(len);
        }   
    }
}