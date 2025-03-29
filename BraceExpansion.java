import java.util.*;
class BraceExpansion{
    List<List<Character>> blocks; 
    List<String> result;
    public String[] expand(String s){
        if(s==null || s.length()==0){
            return new String[]{};
        }
        blocks = new ArrayList<>();
        result = new ArrayList<>();
        int i=0;
        while(i<s.length()){
            char c = s.charAt(i);
            List<Character> list = new ArrayList<>();
            if(c=='{'){
                i++;
                while(s.charAt(i)!='}'){
                    if(s.charAt(i)==','){
                        i++;
                        continue;
                    }
                    list.add(s.charAt(i));
                    i++;
                }
            }
            else{
                list.add(c);
            }
            Collections.sort(list);
            blocks.add(list);
            i++;
        }
        System.out.println(blocks);
        backtracking(0,new StringBuilder());
         System.out.println(result);
         String[] str = new String[result.size()];
        for(int k=0;k<result.size();k++){
            str[k]=result.get(k);
        }
        return str;
    }
    public void backtracking(int index, StringBuilder sb){
        //base
        if(index==blocks.size()){
            result.add(sb.toString());
            return;
        }
        //add
       
        List<Character> list = blocks.get(index);
        for(char li : list){
            sb.append(li);
            backtracking(index+1,sb);
            int length = sb.length();
            sb.setLength(length-1);
        }
    }
    public static void main(String[] args){
        BraceExpansion b = new BraceExpansion();
        String s = "{a,b}c{d,e}f";
        b.expand(s);
    }
}