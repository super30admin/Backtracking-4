// Time Complexity :O(m ^ n) --> where m is average length of each block and n is total number of blocks. A block is defined as the group of characters within the braces in the given input string.
// Space Complexity : O(l) --> where l is the length of string
// Did this code successfully run on Leetcode (1087): Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    List<String> result;
    public String[] expand(String S) {
        // edge case
        if (S == null || S.length() == 0) return new String[0];
        
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        
        // convert string into blocks of charracters ex: {[a,b], [c], [d,e]}
        int i = 0;
        while (i < S.length()) {
            List<Character> block = new ArrayList<>();
            if (S.charAt(i) == '{') {
                i++;
               while (S.charAt(i) != '}') {
                   if (S.charAt(i) != ',') block.add(S.charAt(i));
                   i++;
               }
            }
            else block.add(S.charAt(i));
            i++;
            blocks.add(block);
        }
        // got out of block
        backtrack(blocks, 0, new StringBuilder());

        // need to convert the result list into a sorted array
        String resultArray[] = new String[result.size()];
        for (int j = 0; j < result.size(); j++) {
            resultArray[j] = result.get(j); 
        }
        Arrays.sort(resultArray);
        return resultArray;
    }
    
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
        // base case
        if (index == blocks.size()) {
            result.add(sb.toString());
            return;
        }
        
        // logic
        for (char c : blocks.get(index)) {
            // action
            sb.append(c);
            
            // recurse
            backtrack(blocks, index + 1, sb);
            
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}