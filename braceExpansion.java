//let m is the number of char in a block and n is the number of blocks
//time complexity O(m^n)
//space complexity O(height of the tree)

class Solution {
    List<String> result;
    public String[] expand(String S) {
        if(S == null || S.length() == 0) return new String[0];
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();//[[a,b], [c], [d,e], [f]]
        char [] strArr = S.toCharArray();
        int i = 0;
        while(i < strArr.length){
            List<Character> block = new ArrayList<>();
            if(strArr[i] == '{'){
                i++;
                while(strArr[i] != '}'){
                    if(strArr[i] != ','){
                        block.add(strArr[i]);//[a,b]
                    }
                    i++;
                }
            } else {
                block.add(strArr[i]);
            }
            i++;
            blocks.add(block);
        }
        backtrack(blocks, 0, new StringBuilder());
        String [] resultArr = result.toArray(new String[result.size()]);
        Arrays.sort(resultArr);
        return resultArr;
    }

    public void backtrack(List<List<Character>> blocks, int index, StringBuilder sb){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        for(char c: blocks.get(index)){
            sb.append(c);
            backtrack(blocks, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
