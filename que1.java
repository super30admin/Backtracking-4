Time Complexity: O(b * m log m) + O(product of block sizes), where b is the number of blocks and m is the average size of each block.

Space Complexity: O(n + b), where n is the total number of characters and b is the number of blocks.

class Solution {
    private void helper(int index, List<List<Character>> blocks, List<String> result, StringBuilder path) {
        // base
        if(index==blocks.size()){
            result.add(path.toString());
            return;
        }

        // logic
        for (Character c : blocks.get(index)) {
            path.append(c);
            helper(index + 1, blocks, result, path);

            path.deleteCharAt(path.length() - 1);
        }
    }

    public String[] expand(String s) {
        List<String> result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int left = 0;
        while (left < s.length()) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(left) == '{') {
                left++;

                while (s.charAt(left) != '}') {
                    if (s.charAt(left) != ',') {
                        block.add(s.charAt(left));

                    }
                    left++;

                }

            } else {
                block.add(s.charAt(left));
            }
            Collections.sort(block);
            blocks.add(block);
            left++;
        }
        helper(0,blocks,result,new StringBuilder());
        String[] res = new String[result.size()];
        for(int i=0;i<res.length;i++){
            res[i]=result.get(i);
        }
        return res;

    }
}
