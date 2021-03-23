//TC:O(N ^ M/N), M-> string length, N : no. of decisions at each level, M/N -> no. of groups
//SC:Exponential
// Did it run successfully on Leetcode? : Yes
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if ( s == null || s.length() == 0)
            return new String[0];
        result = new ArrayList();
        List<List<Character>> blocks = new ArrayList();
        int i = 0;
        while ( i < s.length()){
            char c = s.charAt(i);
            List<Character> block = new ArrayList();
            if (c == '{'){
                i++;
                while (s.charAt(i) != '}'){
                    if (s.charAt(i) != ','){
                       block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else {
                block.add(c);   
            }
            Arrays.sort(block.toArray());
            blocks.add(block);
            i++;
        }
        backtrack(blocks, 0, new StringBuilder());
        String[] ans = new String[result.size()];
        for ( int j = 0; j < result.size(); j++)
        {
            ans[j] = result.get(j);
        }
        Arrays.sort(ans);
        return ans;
    }
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb)
    {
        //base
        if (index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        //logic
        List<Character> block = blocks.get(index);
        for ( int i = 0; i < block.size(); i++)
        {
            char c = block.get(i);
            //action
            sb.append(c);      
            //recurse
            backtrack(blocks, index+1, sb);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}
