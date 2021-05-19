// "static void main" must be defined in a public class.
public class Main {
    public static String[] expand(String s){
        //to keep track of blocks, converting string to list of lists
        List<List<Character>> blocks = new ArrayList<>();
        
        int i = 0;
        char[] sChar = s.toCharArray();
        
        while(i < sChar.length){
            List<Character> temp = new ArrayList<>();
            
            if(sChar[i] == '{'){
                i++;
                while(i < sChar.length && sChar[i] != '}'){
                    if(sChar[i] != ','){
                        temp.add(sChar[i]);
                    }
                    i++;
                }
            }else{
                temp.add(sChar[i]);
            }
            i++;
            blocks.add(temp);
        }
        
        //calling bactracking funtion to get desired output
        List<String> output = new ArrayList<>();
        backtracking(blocks, output, 0, new StringBuilder());
        //converting list to String array and sorting the strings lexographically
        String[] res = output.toArray(new String[output.size()]);
        Arrays.sort(res);
        return res;
    }
    
    public static void backtracking(List<List<Character>> blocks, List<String> output, int index, StringBuilder sb){
        //when the blocksize exceeds, add string builder to the output 
        if(index >= blocks.size()){
            output.add(sb.toString());
            return;
        }
        
        for(Character ch: blocks.get(index)){
            sb.append(ch);
            backtracking(blocks, output, index+1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    public static void main(String[] args) {
        String[] result = expand("{a,b}c{d,e}f");
        for(String str: result){
            System.out.println(str);
        }
    }
    
}
// k-> max block size; n -> total number of blocks
// Time Complexity: O(k^n)
//Space Complexity: O(k^n)