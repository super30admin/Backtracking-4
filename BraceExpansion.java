// Time Complexity : O(n ^ 2)
// Space Complexity : O(n ^ 2)

class Solution {
    List<String> result;

    public String[] expand(String S) {
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();

        char [] sArr = S.toCharArray(); int i = 0;
        while(i < sArr.length){
            List<Character> temp  = new ArrayList<>();
            if(sArr[i] == '{'){
                i++;
                while(i < sArr.length && sArr[i] != '}'){
                    if(sArr[i] != ',') temp.add(sArr[i]);
                    i++;
                }
            } else {
                temp.add(sArr[i]);
            }
            blocks.add(temp);
            i++;
        }

        backtrack(blocks, new StringBuilder(), 0);
        String[] resultArray = result.toArray(new String[result.size()]);
        Arrays.sort(resultArray);
        
        return resultArray;
    }

    private void backtrack(List<List<Character>> blocks, StringBuilder sb, int index){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        //logic
        for(char c : blocks.get(index)){
            sb.append(c);
            backtrack(blocks, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}