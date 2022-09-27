//time compleexity: K power n/k (k is number of elements in braces)
//Space complexity: O(kn)

class Solution {
    List<String> result;
    public String[] expand(String s) {
        if (s == null || s.length() == 0){
            return new String [0];
        }
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        
        int i = 0;
        while (i < s.length()){
            List<Character> li = new ArrayList<>();
            if (s.charAt(i) == '{'){
                i++;
                while (s.charAt(i) != '}'){
                    if (s.charAt(i) != ','){
                        li.add(s.charAt(i));
                        
                    }
                    i++;
                }
            }
            else{
                li.add(s.charAt(i));
            }
            i++;
            Collections.sort(li);
            blocks.add(li);
            
        }
        backtrack(blocks, 0, new StringBuilder());
        String [] re = new String [result.size()];
        for(int j = 0; j < re.length; j++){
            re[j] = result.get(j);
        }
        return re;
        
    }
    
    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb){
        // base
        if (idx == blocks.size()){
            result.add(sb.toString());
            return;
        }
        // logic
        List<Character> block = blocks.get(idx);
        for(int i=0; i<block.size(); i++){
            int l = sb.length();
            sb.append(block.get(i));
            backtrack(blocks, idx+1, sb);
            sb.setLength(l);
        }
    }
    
    
}
