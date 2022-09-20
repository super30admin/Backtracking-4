//Time Complexity: O( K^(n)); where k is the no. of characters within curly braces & n is the no. of different curly braces.
//Space Complexity: O(K^(n))
//Code run successfully on LeetCode.

public class Problem2 {

    List<List<Character>> blocks;
    List<String> result;
    
    public String[] expand(String s) {
        
        result = new ArrayList<>();
        
        if(s == null|| s.length() == 0)
            return new String[0];
        
        blocks = new ArrayList<>();
        
        int i =0;
        while(i < s.length()){
            
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            
            if(c == '{')
            {
                i++;
                while(s.charAt(i) != '}')
                {
                    if(s.charAt(i) != ',')
                        block.add(s.charAt(i));
                    
                    i++;
                }
            }
            else{
                block.add(c);
            }
            
            i++;
            Collections.sort(block);
            blocks.add(block);
        }
        
        backtrack(0, new StringBuilder());
        
        String[] answer = new String[result.size()];
        
        for(int j =0; j < result.size(); j++)
            answer[j] = result.get(j);
        
        return answer;
    }
    
    private void backtrack(int index, StringBuilder sb)
    {
        if(index == blocks.size())
        {
            result.add(sb.toString());
            return;
        }
        
        List<Character> list = blocks.get(index);
        
        for(int i =0; i < list.size(); i++)
        {
            sb.append(list.get(i));
            backtrack(index +1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
