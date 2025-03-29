// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    List<List<Character>> blocks = new ArrayList<>();
    List<String> result = new ArrayList<>();
    public String[] expand(String s) {
        int i=0;
        while(i < s.length()) {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if (c == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(c);
            }
            Collections.sort(block);
            System.out.println(block);
            blocks.add(block);
            i++;
        }
        backtrack(0, new StringBuilder());
        String[] answer = new String[result.size()];
        for (int j=0;j<result.size();j++) {
            answer[j] = result.get(j);
        }
        return answer;
    }
    private void backtrack(int index, StringBuilder sb) {
        if (index == blocks.size()) {
            result.add(new StringBuilder(sb).toString());
            return;
        }
        List<Character> chlist = blocks.get(index);
        for (int i=0;i<chlist.size();i++) {
            int length = sb.length();
            sb.append(chlist.get(i));
            backtrack(index+1, sb);
            sb.setLength(length);
        }
    }
}
