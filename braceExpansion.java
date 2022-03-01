// Time Complexity : O(k^(n/k)) where n is the length of the string and k is the number of parethesis blocks
// Space Complexity : O(n*k) where n is the length of the string and k is the number of parethesis blocks
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class braceExpansion {
    List<String> ans;
    public String[] expand(String S) {
        ans = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        char[] sArr = S.toCharArray();
        int i = 0;
        while (i < sArr.length) {
            List<Character> list = new ArrayList<>();
            if (sArr[i] == '{') {
                i++;
                while (sArr[i] != '}') {
                    if (sArr[i] != ',') {
                        list.add(sArr[i]);
                    }
                    i++;
                }
            }
            else {
                list.add(sArr[i]);
            }
            blocks.add(list);
            i++;
        }
        // for (List<Character> l : blocks) {
        //     System.out.println(l);
        // }
        // after these steps, from string {a,b,c}d{e,f} we have list of lists [[a,b,c],[d],[e,f]]
        // now it is easy to backtrack on this list
        backtrack(blocks, 0, new StringBuilder());
        String[] answer = ans.toArray(new String[ans.size()]);
        Arrays.sort(answer);
        return answer;
    }
    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb) {
        // base case
        if (idx == blocks.size()) {
            ans.add(sb.toString());
            return;
        }
        // logic
        for (char c : blocks.get(idx)) {
            sb.append(c);
            backtrack(blocks, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}