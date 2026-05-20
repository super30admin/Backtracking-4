

/**
 * Uses backtracking to generate all possible strings by choosing one character from each parsed group.
 * First parses the input into sorted character groups, then recursively builds every combination in lexicographical order.
 * Time: O(k * n) where k = total combinations and n = average string length, Space: O(k * n) for recursion + result storage.
 */
public class BraceExpansion {
    List<String> result;
    public String[] expand(String s) {
        this.result = new ArrayList<>(); 

        List<List<Character>> groups = new ArrayList<>();

        int i = 0; 

        while( i < s.length()) {
            List<Character> group = new ArrayList<>();
            if(s.charAt(i) == ('{')) {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                i++;
            } else {
                group.add(s.charAt(i));
                i++;
            }
            Collections.sort(group);
            groups.add(group);
        }
        helper(groups, 0, new StringBuilder());

        return result.toArray(new String[0]);
    }

    private void helper(List<List<Character>> groups, int index, StringBuilder path) {

        if(index == groups.size()) {
            result.add(path.toString());
            return;
        }

        List<Character> currentGroup = groups.get(index);
        int pathLength = path.length();
        for(int i = 0; i < currentGroup.size() ; i++) {
            path.append(currentGroup.get(i));
            helper(groups, index+1, path);
            path.setLength(pathLength);
        }
    }    
}
