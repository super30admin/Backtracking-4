# TIME COMPLEXITY: O(n^m) where m is the average length of every block and n is the number of blocks
# SPACE COMPLEXITY: O(n*m)
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if (c == '{') {
                i++;
                while (s.charAt(i) != '}'){
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else {
                 block.add(s.charAt(i));
            }
            blocks.add(block);
            i++;
        }
        backtrack(blocks, 0, new StringBuilder());
        String[] strArray = new String[result.size()];
        int k = 0;
        for (String res : result) {
            strArray[k++] = res;
        }
        Arrays.sort(strArray);
        return strArray;
    }
    
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sp){
        if (index == blocks.size()) {
            result.add(sp.toString());
            return;
        }
        List<Character> block = blocks.get(index);
        for (int i = 0; i < block.size(); i++) {
            sp.append(block.get(i));
            backtrack(blocks, index+1, sp);
            sp.setLength(sp.length()-1);  
        }
    }
}
