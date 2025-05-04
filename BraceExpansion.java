import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Time Complexity: O(n * 2^n)
 * Space Complexity: O(n)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * 1. Get all the options and characters in a list
 * 2. Backtrack and create strings for each paths
 * 3. Sort the result
 * 4. Convert to String[]
 */

public class BraceExpansion {
    List<String> result;
    public String[] expand(String s) {
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;

        char[] str = s.toCharArray();
        result = new ArrayList<>();
        while (i < str.length) {
            List<Character> options = new ArrayList<>();
            if (str[i] == '{') {
                i++;
                while(i < str.length && str[i] != '}') {
                    if (str[i] != ',') {
                        options.add(str[i]);
                    }
                    i++;
                }
            } else {
                options.add(str[i]);
            }

            blocks.add(options);
            i++;
        }

        backtrack(blocks, 0, new StringBuilder());

        String[] ans = result.toArray(new String[0]);
        Arrays.sort(ans);
        return ans;
    }


    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
        // base
        if (index == blocks.size()) {
            result.add(sb.toString());
            return;
        }

        // logic
        List<Character> ch = blocks.get(index);
        for (char c: ch) {
            sb.append(c);
            backtrack(blocks, index + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
    public static void main(String[] args) {
        // Your code here
    }
}