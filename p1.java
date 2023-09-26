// Time Complexity : O(K^n) where K is the average length of the groups and n is the number of groups
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public String[] expand(String s) {
        ArrayList<ArrayList<Character>> lists = new ArrayList<>();

        int i = 0;
        //Covert the input string to arraylist of characters
        while(i < s.length()){
            ArrayList<Character> list = new ArrayList<>();
            char c = s.charAt(i);
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        list.add(s.charAt(i));
                    }
                    i++;
                }
                i++;
            } else{
                list.add(c);
                i++;
            }
            Collections.sort(list);
            lists.add(list);
        }

        // System.out.println(lists);
        ArrayList<String> r = new ArrayList<>();
        //call recursive function
        dfs(lists, 0, new StringBuilder(), r);
        String[] result = new String[r.size()];
        for(i=0; i<r.size(); i++){
            result[i] = r.get(i);
        }
        return result;
    }

    private void dfs(ArrayList<ArrayList<Character>> lists, int idx, StringBuilder sb, ArrayList<String> r){
        //base case
        if(idx == lists.size()){
            r.add(sb.toString());
            return;
        }

        //logic
        ArrayList<Character> list = lists.get(idx);
        for(int i=0; i<list.size(); i++){
            //action
            char c = list.get(i);
            sb.append(c);
            //recurse
            dfs(lists, idx+1, sb, r);
            //backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
        return;
    }
}