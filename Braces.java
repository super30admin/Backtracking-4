// Time Complexity : exponential    
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We use backtracking to solve this
// we first traverse through the given string and then create a list of strings removing the braces
// Then we perform backtracking on the list

class Solution {
    List<String> result;
    List<List<Character>> blocks;

    public String[] expand(String s) {
        if (s == null || s.length() == 0)
            return new String[] {};
        result = new ArrayList<>();
        blocks = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(s.charAt(i));
            }
            i++;
            blocks.add(block);
        }
        backtrack(0, new StringBuilder());
        String[] ans = new String[result.size()];
        for (int j = 0; j < result.size(); j++) {
            ans[j] = result.get(j);
        }
        Arrays.sort(ans);
        return ans;

    }

    public void backtrack(int index, StringBuilder sb) {
        if (index == blocks.size()) {
            result.add(sb.toString());
            return;
        }

        List<Character> block = blocks.get(index);

        for (int i = 0; i < block.size(); i++) {
            sb.append(block.get(i));

            backtrack(index + 1, sb);

            sb.setLength(sb.length() - 1);
        }
    }
}