// Time Complexity : O( k ^ (n/ k)) where n is the length of the string s and k is the avg length of each "block"/ options within s
// Space Complexity : O(n/k) - recursive stack space
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no
class BraceExpansion {

    //using backtracking
    private List<String> resultList;
    public String[] expand(String s) {

        this.resultList = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            char currentChar = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(currentChar == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }else{
                block.add(currentChar);
            }
            i++;
            Collections.sort(block);
            blocks.add(block);
        }

        backtrack(blocks, 0, new StringBuilder());
        String[] result = new String[resultList.size()];
        for(int k = 0; k < resultList.size(); k++){
            result[k] = resultList.get(k);
        }
        return result;
    }

    private void backtrack(List<List<Character>> blocks, int levelIdx, StringBuilder path){
        //base
        if(levelIdx == blocks.size()){
            resultList.add(path.toString());
            return;
        }

        List<Character> currentBlock = blocks.get(levelIdx);
        for(int i = 0; i < currentBlock.size(); i++){
            int originalLength = path.length();
            path.append(currentBlock.get(i));
            backtrack(blocks, levelIdx + 1, path);
            path.setLength(originalLength);
        }
    }

    //iterative approach
    // Time Complexity : O( k ^ (n/ k)) where n is the length of the string s and k is the avg length of each "block"/ options within s
    // Space Complexity : O(n/k)
    // Did this code successfully run on Leetcode : yes
    // Any problem you faced while coding this :no
    public String[] expandIterative(String s) {

        List<StringBuilder> suffixList = new ArrayList<>();
        StringBuilder currentSuffix = new StringBuilder();
        List<StringBuilder> result = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            char currentChar = s.charAt(i);
            if(Character.isAlphabetic(currentChar)){
                currentSuffix.append(currentChar);
            }if(currentChar == ','){
                suffixList.add(currentSuffix);
                currentSuffix = new StringBuilder();
            }if( (currentChar == '{' && currentSuffix.length() != 0)
                    || currentChar == '}' || i == s.length() - 1 ){
                suffixList.add(currentSuffix);
                result = process(suffixList, result);

                suffixList = new ArrayList<>();
                currentSuffix = new StringBuilder();
            }
        }

        Collections.sort(result);
        String[] resultArr = new String[result.size()];
        int idx = 0;
        for(StringBuilder sb : result){
            resultArr[idx++] = sb.toString();
        }
        return resultArr;
    }

    private List<StringBuilder> process(List<StringBuilder> suffixList, List<StringBuilder> result){
        List<StringBuilder> newList = new ArrayList<>();
        if(result.isEmpty()){
            for(StringBuilder suffix : suffixList){
                newList.add(suffix);
            }
            return newList;
        }

        for(StringBuilder sb : result){
            for(StringBuilder suffix : suffixList){
                StringBuilder prefix = new StringBuilder(sb);
                prefix.append(suffix);
                newList.add(prefix);
            }
        }
        return newList;
    }
}