// Time Complexity : O(N∗3 ^N/7)
// Space Complexity : O(N∗3 ^N/7)

class Solution {
    public String[] expand(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (c == '{') {
                c = s.charAt(i++);
                List<Character> clist = new ArrayList<>();
                while (c != '}') {
                    if (Character.isLetter(c)) {
                        clist.add(c);
                    }
                    c = s.charAt(i++);
                }
                if (result.isEmpty()) {
                    for (char nc : clist) {
                        result.add(nc + "");
                    }
                } else {
                    List<String> res = new ArrayList<>();
                    for (char nc : clist) {
                        for (String ss : result) {
                            res.add(ss + nc);
                        }
                    }
                    result.clear();
                    result = res;
                }
                continue;
            }
            if (result.isEmpty()) {
                result.add(c + "");
            } else {
                for (int j = 0; j < result.size(); j++) {
                    result.set(j, result.get(j) + c);
                }
            }
        }
        Collections.sort(result);
        return result.toArray(new String[result.size()]);
    }
}