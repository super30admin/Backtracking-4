// Time Complexity : O(k^n), where k = max group size and n = number of groups
// Space Complexity : O(n + k^n) , recursive stack space + result list
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We first gather all the characters in their respective groups including the ones inside curly braces and outside.
We sort them lexicographically and then call helper method where we use for loop based recusrion to iterate
through each group and form all the possible combinations by picking one character from each group. When
the index reaches the groups size, we feed it into the output result.
 */
class Solution {
    List<String> result;
    public String[] expand(String s) {
        this.result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();

        int i = 0;
        while(i < s.length()) {
            List<Character> group = new ArrayList<>();
            if(s.charAt(i) == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',')
                        group.add(s.charAt(i));
                    i++;
                }

            }
            else {
                group.add(s.charAt(i));
            }
            i++;
            Collections.sort(group);
            groups.add(group);
        }
        helper(groups, 0, new StringBuilder());
        return result.toArray(new String[0]);
    }

    private void helper(List<List<Character>> groups, int idx, StringBuilder path) {
        if(idx == groups.size()) {
            result.add(path.toString());
            return;
        }
        List<Character> group = groups.get(idx);
        for(int i = 0 ; i < group.size() ; i++) {
            int len = path.length();
            path.append(group.get(i));
            helper(groups, idx + 1, path);
            path.setLength(len);
        }
    }
}