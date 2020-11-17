    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/brace-expansion/
    Time Complexity for operators : o(nlogn) .. n is the length of the string
    Extra Space Complexity for operators : o(n) n = length of string
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. Backtracking
                    A) 
    */  

class Solution {
    
    List<String> result;
    public String[] expand(String S) {
        result = new ArrayList<>();
        List<String> blocks = new ArrayList<>();
        
        int index = 0;
        while(index < S.length()){
            char ch = S.charAt(index);
            String temp = "";
            
            if(ch == '{'){
                
                index += 1;
                
                while(S.charAt(index) != '}'){
                    if(S.charAt(index) != ','){
                        temp += S.charAt(index);
                    }
                    
                    index += 1;
                }
                blocks.add(temp);
                index += 1;
            }else{
                while(index < S.length() && S.charAt(index) != '{'){
                    temp = ""+ S.charAt(index);
                    blocks.add(temp);
                    index += 1;
                }
            }
        }
        
        backtracking(0, blocks, new StringBuilder());
        
        String[] output = result.toArray(new String[0]);
        Arrays.sort(output);
        return output; 
    }
    
    private void backtracking(int index, List<String> blocks, StringBuilder curr_string){
        // base
        if(index >= blocks.size()){
            result.add(curr_string.toString());
            return;
        }
        // recusrive call
        String to_iterate = blocks.get(index);
        for(int x = 0; x < to_iterate.length(); x++){
            char ch = to_iterate.charAt(x);
            curr_string.append(ch);
            backtracking(index+1, blocks, curr_string);
            curr_string.deleteCharAt(curr_string.length()-1);
        }
    }
}