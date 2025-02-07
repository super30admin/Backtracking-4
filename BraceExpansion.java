// Approach : Transform given input string to list of lists (groups) will help you for easyily builidng the decision tree and then perform DFS
// Time : K -> Avg group size , N-> String length then k^(N/k)
// Space : O(h) // recursive stack space
class Solution {
    List<String> res;

    public String[] expand(String s) {
        this.res = new ArrayList<>();
        // Parse string and form groups based on curly braces
        List<List<Character>> groups = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            List<Character> group = new ArrayList<>();
            if (ch == '{') {
                // skip the current Character - '{'
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        group.add(s.charAt(i));
                    }
                    i++; // skip the current character is ','
                }
                i++; // skip the current closed character - '}'
            } else{
                // we are on a character
                group.add(s.charAt(i));
                i++;
            }
            Collections.sort(group); // sorting here because group size will be very small
            groups.add(group);
        }
        System.out.println(groups);
        dfs(groups,0,new StringBuilder()); // start with first group

        String[] re = new String[res.size()];
        for(int k=0;k<res.size();k++){
            re[k] = res.get(k);
        }
        return re;
    }

    private void dfs(List<List<Character>> groups, int idx , StringBuilder path){
        // Base case
        if(idx == groups.size()) {
            res.add(path.toString());
            return;
        };

        // Logic
        List<Character> currGroup = groups.get(idx);
        for(int i=0;i<currGroup.size();i++){
            int le = path.length();
            path.append(currGroup.get(i));
            dfs(groups,idx+1,path);
            // backtrack
            path.setLength(le);
        } 

    }
}