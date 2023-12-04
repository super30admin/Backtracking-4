import java.util.ArrayList;
import java.util.List;

public class BraceExpansion {
    List<String> result;
    List<List<Character>> blocks;
    public String[] expand(String s){
        if(s == null || s.length() == 0){
            return new String[] {};
        }
        result = new ArrayList<>();
        blocks = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else{
                block.add(c);
            }
            blocks.add(block);
            i++;
        }
        backtrack(0, new StringBuilder());
        String[] answer = new String[result.size()];
        for(int j = 0; j<result.size(); j++){
            answer[j] = result.get(j);
        }
        return answer;
    }

    private void backtrack(int index, StringBuilder sb){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }

        //logic
        List<Character> block = blocks.get(index);
        for(char c : block){
            //action
            sb.append(c);
            //recurse
            backtrack(index + 1, sb);
            //backtrack
            sb.setLength(sb.length()-1);
        }
    }

    public static void main(String[] args){
        BraceExpansion obj = new BraceExpansion();
        String s = "{a,b}c{d,e}f";
        String[] result = obj.expand(s);
        for (String res : result) {
            System.out.println(res);
        }
    }

}
