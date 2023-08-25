class Solution {
    List<String> result;
    public String[] expand(String s) {
        this.result=new ArrayList<>();
        List<List<Character>> blocks=new ArrayList<>();
        int idx=0;
        while(idx<s.length()){
            char c=s.charAt(idx);
            List<Character> block=new ArrayList<>();
            if(c=='{'){
                idx++;
                while(s.charAt(idx)!='}'){
                    if(s.charAt(idx)!=','){
                        block.add(s.charAt(idx));
                    }
                    idx++;
                }
            }
            else{
                block.add(c);
            }
            idx++;
            Collections.sort(block);
            blocks.add(block);
        }
        backtrack(blocks, 0,new StringBuilder());
        String[] returnResult=new String[result.size()];
        for(int i=0;i<result.size();i++){
            returnResult[i]=result.get(i);
        }

        return returnResult;
    }
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb){
        if(index==blocks.size()){
            result.add(sb.toString());
            return;
        }
         for(char c : blocks.get(index)){
            sb.append(c);
            backtrack(blocks, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);

        }

    }
}
