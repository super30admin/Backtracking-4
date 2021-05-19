// TC - O(26 ^ n)

// SC - O(number of blocks)

// LC - 1087


// Initially iterate over string and divide into blocks of characters and backtrack over each block
class Solution {
    public String[] expand(String s) {
        List<List<Character>> blocks = new ArrayList<>();
        
        int i=0;
        char[] sChar = s.toCharArray();
        
        while(i < sChar.length){
            List<Character> temp = new ArrayList<>();
            
            if(sChar[i] == '{'){
                i++;
                while(i < sChar.length && sChar[i] != '}'){
                    if(sChar[i] != ','){
                        temp.add(sChar[i]);
                    }    
                    i++;
                }
            }else{
                temp.add(sChar[i]);
            }
            blocks.add(temp);
            i++;
        }
        System.out.println(blocks);
        List<String> output = new ArrayList<>();
        backtracking(blocks, output, 0, new StringBuilder());
        
        String[] result = output.toArray(new String[output.size()]);
        Arrays.sort(result);
        return result;
        
    }
    
   private void backtracking(List<List<Character>> blocks, List<String> output, int index, StringBuilder sb){
       if(index >= blocks.size()){
           output.add(sb.toString());
           return;
       }
       
       for(char ch : blocks.get(index)){
           sb.append(ch);
           backtracking(blocks, output, index+1, sb);
           sb.deleteCharAt(sb.length() - 1);
       }
   }
}