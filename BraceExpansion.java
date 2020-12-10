// Time Complexity : not sure what the worst case would be 
// Space Complexity : maybe O(N), for max case recursion depth, keeping the sorted chars
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : ArrayList to char[]/String[] for the expected output

// Your code here along with comments explaining your approach
// approach is backtracking, use stringbuilder, when we encounter '{', extract candidates and do for each
// store string when idx==S.length  

class Solution {
    List<String> output;
    
    public String[] expand(String S) {
        output = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        helper(S, idx, sb);   
        
        String[] result = new String[output.size()];
        for(int i=0; i<result.length; i++){
            result[i] = output.get(i);
        }
        
        return result;
    }
    
    private void helper(String S, int idx, StringBuilder sb){
        if(idx==S.length()){
            output.add(sb.toString());
            return;
        }
        
        char ch = S.charAt(idx);
        
        if(Character.isLetter(ch)){
            sb.append(ch);
            helper(S, idx+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
        else{
            Pair<char[], Integer> output = getCandidates(S, idx);
            char[] candidates = output.getKey();
            idx = output.getValue();

            for(int i=0; i<candidates.length; i++){
                sb.append(candidates[i]);
                helper(S, idx+1, sb);
                sb.deleteCharAt(sb.length()-1);
            }    
        }
    }
    
    private Pair<char[], Integer> getCandidates(String S, int idx){
        int i = idx+1;
        List<Character> candidates = new ArrayList<>(); 
        
        while(i<S.length()){
            char ch = S.charAt(i); 
            if(ch=='}'){
                break;
            }
            else if(Character.isLetter(ch)){
                candidates.add(ch);
            }
            i++;
        }
        
        Collections.sort(candidates);
        
        char[] result = new char[candidates.size()];
        
        for(int j=0; j<candidates.size(); j++){
            result[j] = candidates.get(j);
        }
        
        Pair<char[], Integer> output = new Pair<>(result, i);
        
        return output;
    }
}