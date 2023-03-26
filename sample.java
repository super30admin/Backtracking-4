// Time Complexity : O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    List<String> result;
    public String[] expand(String s) {
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        char [] sarr = s.toCharArray();
        int i = 0;
        while(i<sarr.length){
            List<Character> temp = new ArrayList<>();
            if(sarr[i] == '{'){
                i++;
                while(i<sarr.length && sarr[i] != '}'){
                    if(sarr[i] != ',') temp.add(sarr[i]);
                    i++;
                }}
                else temp.add(sarr[i]);
                blocks.add(temp);
                i++;
            }
            backtrack(blocks,new StringBuilder(),0);
            String[] returnArray = result.toArray(new String[result.size()]);
            Arrays.sort(returnArray);
            return returnArray;
        }
        public void backtrack(List<List<Character>> blocks, StringBuilder sb, Integer index){
            if(index==blocks.size()){
                result.add(sb.toString());
                return ;
            }
            for(char c:blocks.get(index)){
                sb.append(c);
                backtrack(blocks,sb,index+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }        
    }