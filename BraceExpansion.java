
/*
tc : O(mn)
*/
class Solution {
    
    List<String> result;
    public String[] expand(String s) {
        if(s.length() == 0 || s == null){
            return new String[0];
        }
        
        this.result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        preprocessInput(s,blocks);
        
        
        backtrack(blocks, 0, new StringBuilder());
        String[]answer = new String[result.size()];
        int i = 0;
        for(String str : result){
            answer[i] = str;i++;
        }
        Arrays.sort(answer);
        return answer;
    }
    
    
    private void backtrack( List<List<Character>> blocks, int index, StringBuilder sb){
        //base case
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        
        //logic
        List<Character> curr = blocks.get(index);
        for(int i = 0; i < curr.size();i++){
            sb.append(curr.get(i));
            backtrack(blocks, index+1,sb);
            sb.setLength(sb.length()-1);
            
        }
    }
    
    private void preprocessInput(String s,  List<List<Character>> blocks){
        int i = 0;
        
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> currBlock = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ',')currBlock.add(s.charAt(i));
                    i++;
                }
            }
            else{
                currBlock.add(s.charAt(i));
                
            }
            blocks.add(currBlock);
            i++;
        }
    }
}