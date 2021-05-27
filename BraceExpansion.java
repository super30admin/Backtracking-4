class BraceExpansion {
    
    // Time Complexity: O(k ^ n)    (where n -> number of blocks and k -> maximum characters in a block)
    // Space Complexity: O(n)
    
    public String[] expand(String s){
        List<List<Character>> blocks = new ArrayList<>();

        int i = 0;
        char[] sChar = s.toCharArray();

        while(i < sChar.length){
            List<Character> temp = new ArrayList<>();
            if(sChar[i] == '{'){
                i++;
                while(i < sChar.length && sChar[i] != '}'){
                    if(sChar[i] != ',')
                        temp.add(sChar[i]);
                    i++;
                }
            }else{
                temp.add(sChar[i]);
            }

            blocks.add(temp);
            i++;
        }
        List<String> output = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        backtracking(blocks, output, 0, sb);

        String[] result = out.toArray(new String[output.size()]);
        Arrays.sort(result);
        return result;
    }

    private void backtracking(List<List<Character>> blocks, List<String> output, int index, StringBuilder sb){
        if(index >= blocks.size()){
            output.add(sb.toString());
            return;
        }

        for(Character c : blocks.get(index)){
            sb.append(c);
            backtracking(blocks, output, index+1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}