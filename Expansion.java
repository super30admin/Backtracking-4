//Time Complexity:  O(k^(n/k))
//Space Complexity: O(1)

class Solution {
    List<String> result;
    public String[] expand(String S) {
        result = new ArrayList<>();
        
        List<List<Character>> blocks = new ArrayList<>();
        
        char[] sArray = S.toCharArray();
        int i=0;
        while(i < sArray.length){
            List<Character> temp = new ArrayList<>();
            
            if(sArray[i] == '{'){
                i++;
                while(sArray[i]!='}'){
                    if(sArray[i]!=',')
                        temp.add(sArray[i]);
                    i++;
                }
            }
            else{
                temp.add(sArray[i]);
            }
            i++;
            blocks.add(temp);
        }
        
        backTrack(blocks,0,new StringBuilder());
        String[] resultArray = result.toArray(new String[result.size()]);
        Arrays.sort(resultArray);
        return resultArray;
    }
    
    private void backTrack(List<List<Character>> blocks,int index, StringBuilder sb){
        //Base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;    
        }
        
        //Logic
    
        for(char c : blocks.get(index)){
            sb.append(c);
            backTrack(blocks,index+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}