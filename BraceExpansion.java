// Time Complexity : O(k^(l/n)) k- avg char per group, l - length of string, n - number of groups
// Space Complexity : O(k^(l/n))
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// We flatten the string into arraylist with each characters in groups
// Sort the blocks first to make sure the results are alphabetically sorted
// We use recursive backtracking to generate all possible combinations iterating over the blocks

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    public String[] expand(String s) {
        int n = s.length();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;

        while (i < n) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',')
                        block.add(s.charAt(i));
                    i++;
                }
            } else
                block.add(s.charAt(i));

            Collections.sort(block);
            blocks.add(block);
            i++;
        }

        List<String> li = new ArrayList<>();
        backtrack(blocks, 0, new StringBuilder(), li);
        String[] result = new String[li.size()];
        for (i = 0; i < li.size(); i++)
            result[i] = li.get(i);
        return result;
    }

    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb, List<String> result) {

        if (idx == blocks.size()) {
            result.add(sb.toString());
            return;
        }

        List<Character> block = blocks.get(idx);
        for (int i = 0; i < block.size(); i++) {
            sb.append(block.get(i));
            backtrack(blocks, idx + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}