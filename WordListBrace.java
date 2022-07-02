// TC : O(k^n)
// SC : O(nk)
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if(s== null || s.length()==0) return new String[0];

        int idx = 0;
        List<List<Character>> blocks = new ArrayList<>();

        while(idx!=s.length())
        {
            List<Character> block = new ArrayList<>();
            if(s.charAt(idx)=='{')
            {
                idx++;
                while(s.charAt(idx)!='}')
                {
                    if(s.charAt(idx)!=',')
                        block.add(s.charAt(idx));
                    idx++;
                }
            }
            else
            {
                block.add(s.charAt(idx));
            }
            Collections.sort(block);
            blocks.add(block);
            idx++;
        }
        result = new ArrayList<>();
        backtracking(blocks,0,new StringBuilder());

        String[] arr = new String[result.size()];
        for(int i=0;i<result.size();i++)
        {
            arr[i] = result.get(i);
        }
        return arr;
    }

    private void backtracking(List<List<Character>> blocks,int idx, StringBuilder sb){
        //base
        if(idx==blocks.size())
        {
            result.add(sb.toString());
            return;
        }
        //logic
        List block = blocks.get(idx);
        for(int i=0;i<block.size();i++)
        {
            //action
            sb.append(block.get(i));
            //recurse
            backtracking(blocks,idx+1,sb);

            //backtrack
            sb.setLength(sb.length()-1);

        }
    }
}