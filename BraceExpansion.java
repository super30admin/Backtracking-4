// Time Complexity :O(k^n/k) where k is the number of blocks
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    List<String> result;
    public String[] expand(String S) {
        if(S==null || S.length()==0) return new String[0];
        result= new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i =0;
        while(i<S.length())
        {
            List<Character> temp = new ArrayList<>();
            if(S.charAt(i)=='{')
            {
                i++;
                while(S.charAt(i)!='}')
                {
                    Character c = S.charAt(i);
                    if(c!=',')
                    {
                        temp.add(c);
                    }
                    i++;
                }
            }
            else
            {
                temp.add(S.charAt(i));
            }
            blocks.add(temp);
            i++;
        }
        
        backtrack(blocks,0, new StringBuilder());
        String[] out = result.toArray(new String[result.size()]);
        Arrays.sort(out);
        return out;
    }
    private void backtrack(List<List<Character>> blocks,int block, StringBuilder sb)
    {
        //base case
        if(sb.length()==blocks.size())
        {
            result.add(sb.toString());
            return;
        }
        // logic
    
        for(Character c: blocks.get(block))
        {
            //action
            sb.append(c);
            backtrack(blocks,block+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
        
}