class Solution{
    List<String> result;
    public String[] expand(String s){
        if(s == null || s.length() == 0){
            return new String[0];
        }
      result = new ArrayList<>();
      List<List<Character>> blocks = new ArrayList<>();
      int i = 0;
      
      while(i < s.length()){
          List<Character> block = new ArrayList<>();
          if(s.charAt(i) == '{'){
              i++;
              while(s.charAt(i) != '}'){
                if(s.charAt(i) != ','){
                  block.add(s.charAt(i));
                }
                i++;
              }
          }else{
            block.add(s.charAt(i));
          }
          blocks.add(block);
          i++;
      }
      backtrack(blocks, 0, new StringBuilder());
      String [] arr = new String [result.size()];
      for(int k =0; k < result.size(); k++){
        arr[k] = result.get(k);
      }
      Arrays.sort(arr);
      return arr;
      
  
  private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb){
    //base
    if(idx == blocks.size()){
      result.add(sb.toString());
      return;
    }
    
    //logic
    List<Character> block = blocks.get(idx);
    for(int j = 0; j < block.size(); j++){
      //action
      sb.append(block.get(j));
      
      //recurse
      backtrack(blocks, idx+1, sb);
      
      //backtrack
      sb.setLength(sb.length() - 1);
    }
  }
}
  
//TC: O(k ^ n) exponential
      
      
      
      
