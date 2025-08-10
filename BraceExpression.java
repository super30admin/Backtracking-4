import java.util.*;

class Solution {
    List<String> result = new ArrayList<>();

    public String[] expand(String s) {
        int n = s.length();
        List<List<Character>> groups = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Character> group = new ArrayList<>();

            if (c == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                Collections.sort(group); 
            } else {
                group.add(c);
            }
            groups.add(group);
        }

        helper(groups, 0, new StringBuilder());
        return result.toArray(new String[0]);
    }

    public void helper(List<List<Character>> groups, int idx, StringBuilder path) {
        if (idx == groups.size()) {
            result.add(path.toString());
            return;
        }

        List<Character> list = groups.get(idx);
        for (Character c : list) {
            path.append(c);
            helper(groups, idx + 1, path);
            path.setLength(path.length() - 1); // backtrack
        }
    }
}
