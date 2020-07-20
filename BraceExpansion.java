// Time Complexity : O(m^n) where m is the number of blocks and n is the number of characters
// Space Complexity : O(h) height of the tree
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None
/* Your code here along with comments explaining your approach: Do backtracking. Put the whole string into the arraylist blocks for convinience.
Start backtracking from the first element of each block and combine with all the elements of the remaining blocks. Move to the next element of 
the current block and repeat the previous step again. This will enable to generate all the possible combinations. Append the combinations into
the stringbuilder and put the string into the result list. */
class Solution {
    List<List<Character>> blocks;
    List<String> str;
    public String[] expand(String S) {
        if(S == null || S.length() == 0) return new String[0];
        str = new ArrayList<String>();
        blocks = new ArrayList<>();
        int i  = 0;
        while(i < S.length()){
            List<Character> temp =new ArrayList<>();                                                            // Parse the string
            char c = S.charAt(i);
            if(c == '{'){                                                                                   // Block start
                i++;
                while(S.charAt(i) != '}'){
                    c = S.charAt(i);
                    if(c == ',') {i++;continue;}                                                                // ignore comma
                    temp.add(c);                                                                            // Add it to the block till } occurs
                    i++;
                }
            } else {
                temp.add(c);                                                                    // Add outside characters with a new block
            }
            blocks.add(temp);   
            i++;
        }      
        backtrack(blocks, 0, new StringBuilder());                                                                      // Start backtracking
        String[] strArr = str.toArray(new String[0]);
        Arrays.sort(strArr);                                                                            // Sort the result before display
        return strArr;
    }
    public void backtrack(List<List<Character>> blocks, int i, StringBuilder sb){
        if(i == blocks.size()){                                                                                 // i has reached the end
            str.add(sb.toString());                                                                     // Add the string to the result
            return;
        }
        for(int j = 0; j < blocks.get(i).size(); j++){
                char c = blocks.get(i).get(j);                                                              // Get the blocks and elements
                sb.append(c);                                                                   // Enter into stringbuilder
                backtrack(blocks, i+1, sb);
                sb.setLength(sb.length()-1);                                                                    // Backtrack
            }
    }
    
}