// Time Complexity :O(permutations of braces)
// Space Complexity :O(s)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
class Solution {
    public String[] expand(String s) {
        List<List<Character>> block= new ArrayList<>();
        char[] arr= s.toCharArray();
        int i=0;
        while(i<arr.length){
            List<Character> temp = new ArrayList<>();//converting the input string to list of list of string 
            if(arr[i]=='{'){
                i++;
                while(arr[i]!='}'){
                    if(arr[i]!=',')
                        temp.add(arr[i]);//example [[a,b], [c], [d,e], [f]]
                    i++;
                }
            }
            else{
                temp.add(arr[i]);
            }
            block.add(temp);
             i++;
        }
        StringBuilder sb = new StringBuilder();
        backtrack(0,block,sb);
        String result[]= output.toArray(new String[output.size()]);
        Arrays.sort(result);
        return result;
        
    }
    List<String> output= new ArrayList<>();
    
    public void backtrack(int index, List<List<Character>> block , StringBuilder sb){//finding every permutation
        if(index==block.size()){
            output.add(sb.toString());
            return;
        }
       for(char ch: block.get(index)){
           sb.append(ch);
           backtrack(index+1,block,sb);
           sb.deleteCharAt(sb.length()-1);
       }
    }
}