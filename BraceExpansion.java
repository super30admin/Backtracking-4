//TC: O(k^n)
//SC: O(n)
//approach: backtracking

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion {
    List <String> result;
    public String[] expand(String s){
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        for (int i=0; i<s.length();i++){
            char c = s.charAt(i);
            List<Character> temp = new ArrayList<>();
            if (c=='{'){
                i++;
                while (i<s.length() && s.charAt(i)!='}'){
                    if (s.charAt(i)!=',') temp.add(s.charAt(i));
                    i++;
                }
            }else {
                temp.add(c);
            }
            blocks.add(temp);
        }

        backtrack(blocks, new StringBuilder(), 0);
        String[] answer = result.toArray(new String[result.size()]);

        Arrays.sort(answer);
        return answer;

    }

    private void backtrack(List<List<Character>> blocks, StringBuilder sb, int index){
        if (index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        for (char c:blocks.get(index)){
            sb.append(c);
            backtrack(blocks, sb, index+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
