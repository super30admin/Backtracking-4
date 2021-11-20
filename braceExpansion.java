// Time Complexity : O(k^n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution{
    List<String> result;
    public String[] expand(String s){
        if(s == null || s.length == 0){
            return new String[0];
        }
        result = new ArrayList<>();
        // for the blocks [[], [], []]
        List<List<Character>> blocks = new ArrayList<>();
        
        int i=0;
        while(i < s.length()){
            List<Character> block = new ArrayList<>();
            if(s.charAt(i) == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else{
                block.add(s.charAt(i));
            }
            i++;
            blocks.add(block);
        }
        backtrack(blocks, 0 , new StringBuilder());
        String[] arr = new String[result.size()];
        for(int j=0 j<arr.length; j++){
            arr[j] = result.get(j);
        }
        Arrays.sort(arr);
        return arr;
    }
    private backtrack() List<List<Character>> blocks, int index, StringBuilder sb){
        // base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        
        // logic
        
        List<Character> block = blocks.get(index);
        for(int i=0; i< block.size(); i++){
            char c = block.get(i);
            // action
            sb.append(c);
            // recurse
            backtrack(blocks, index+1, sb);
            
            // backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}