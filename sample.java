//brace expansion
//tc exponential
//sc size of string
class Solution {
    public String[] expand(String s) {
        
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        
        while(i<s.length()){
            
            List<Character> block = new ArrayList<>();
            
            if(s.charAt(i)=='{'){
                
                i++;
                
                while(s.charAt(i)!='}'){
                    
                    if(s.charAt(i)!=','){
                        
                        block.add(s.charAt(i));
                    
                    }
                    
                    i++;
                }
                
            }
            else{
                
                block.add(s.charAt(i));
                
            }
            blocks.add(block);
            i++;
        }
        
        for(int j = 0; j < blocks.size(); j++){
            System.out.println(blocks.get(j));
        }
        
        List<String> result = new ArrayList<>();
        
        helper(blocks, 0, new StringBuilder(), result);
        
        Collections.sort(result);
        
        return result.toArray(new String[0]);
    }
    
    private void helper(List<List<Character>> blocks, int i, StringBuilder curr, List<String> result){
        
        if(i==blocks.size()){
            
            result.add(curr.toString());
            return;
            
        }
        
        for(int j = 0; j < blocks.get(i).size(); j++){
            
            
            curr.append(blocks.get(i).get(j));
            
            helper(blocks, i+1, curr, result);
            
            curr.deleteCharAt(curr.length() - 1);
            
        }
        return;
    }
    
}
