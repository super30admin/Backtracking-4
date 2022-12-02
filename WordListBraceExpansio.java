//Time - O(n^n/k)
//Space - O(nk)
class Solution {
    List<String> result = new ArrayList<>();
    List<List<Character>> blocks = new ArrayList<>();

    public String[] expand(String s) {
        int i=0;
        while(i< s.length()){
            List<Character> block = new ArrayList<>();
            if(s.charAt(i) =='{'){
                i++;
                while(s.charAt(i)!='}'){
                    if(s.charAt(i)!=','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }else{
                block.add(s.charAt(i));
            }
            Collections.sort(block);
            blocks.add(block);
            i++;
        }
        backtrack(blocks,0, new StringBuilder());
        String[] ar = new String[result.size()];
        for(int j=0; j<result.size(); j++){
            ar[j] = result.get(j);
        }
        return ar;
    }

    void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb){
        //base
        if(idx == blocks.size()){
            result.add(sb.toString());
            return;
        }
        //logic
        for(int i=0; i<blocks.get(idx).size();i++){
            int l = sb.length();
            sb.append(blocks.get(idx).get(i));
            backtrack(blocks, idx+1, sb);
            sb.setLength(l);
        }
    }
}