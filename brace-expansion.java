// Time, Space - O(exp), O(S), all strings S
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if(s == null || s.length() == 0) {
            return new String[0];
        }
        result = new ArrayList<>();
        Queue<Character> q = new LinkedList<>();
        
        List<List<Character>> blocks = new ArrayList<>();
        int i=0;
        while( i< s.length()) {
            char ch = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(ch == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else {
                block.add(ch);
            }
            blocks.add(block);
            i++;
        }
        backtrack(blocks, 0, new StringBuilder());
        
        String[] stringArray = new String[result.size()];
        
        for(int k=0;k<result.size();k++) {
            stringArray[k] = result.get(k);
        }
        Arrays.sort(stringArray);
        
        return stringArray;
        
    }
    
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder s) {
        
        if(index == blocks.size()) {
            result.add(s.toString());
            return;
        }
        List<Character> block = blocks.get(index);
        for(int i=0;i<block.size();i++) {
            s.append(block.get(i));
            backtrack(blocks, index + 1, s);            
            s.setLength(s.length() - 1);
        }
        
    }
}
