//TC:O(k^ (n/k))
//SC: O(KM^2), where M is the block in the array
//running on leetcode: yes
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if(s==null || s.length()==0) return new String[] {};
        result=new ArrayList<>();
        //iterate through the given string and separate the characters from group of characters
        List<List<Character>> blocks = new ArrayList<>();
        int i=0;
        while(i<s.length()){
            List<Character> block = new ArrayList<>();
            if(s.charAt(i)=='{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else{
                block.add(s.charAt(i));
            }
            i++;
            blocks.add(block);
        }
        
        backtrack(blocks, 0, new StringBuilder());
        String[] answer = new String[result.size()];
        for(int j=0; j<result.size(); j++){
            answer[j]=result.get(j);
        }
        Arrays.sort(answer);
        return answer;
    }
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        //logic
        List<Character> block = blocks.get(index);
        for(int i=0; i<block.size(); i++){
            //action
            sb.append(block.get(i));
            //recurse
            backtrack(blocks, index+1, sb);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}
