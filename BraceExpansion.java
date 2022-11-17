/*class Solution {
    //tc-o(n^k) n is no of blocks and k is avg no of words in each block
    //sc-no of blocks as to the max we can have elemetns equal to no of blocks in stack
    List<String> result;
    public String[] expand(String s) {
        result = new ArrayList<>();
        int sl = s.length();
        List<List<Character>> blocks = new ArrayList<>();
        int i=0;
        while(i<sl)
        {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(c == '{')
            {
                i++;
                while(s.charAt(i) != '}')
                {
                    if(s.charAt(i) != ',')
                    {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else
            {
                block.add(s.charAt(i));
            }
            i++;
            Collections.sort(block);
            blocks.add(block);
        }
        backtrack(blocks,0,new StringBuilder(),result);
    String[] resultArr = new String[result.size()];
    for(int k=0;k<result.size();k++)
    {
        resultArr[k] = result.get(k);
    }
    return resultArr;

    }

    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb,List<String> result)
 {
     //basecase
     if(idx == blocks.size())
     {
         result.add(sb.toString());
         return;
     }
     //logic
     List<Character> block = blocks.get(idx);
     for(int i=0;i<block.size();i++)
     {
         char c = block.get(i);
         //action
         sb.append(c);
         //recurse
         backtrack(blocks,idx+1,sb,result);
         //backtrack
         sb.setLength(sb.length()-1);
     }
 }
    
}*/
 
 

















