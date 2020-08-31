/*
Approach: 
Find all the combinations by 
placing the a in position and search for all possibilities  

For dataset {a,b,c}lm{e,f} => output is alme,almf,blme,blmf,clme,clmf
Parse the string and backtrack on the letters in braces as they are one among the options 
without braces are mandatory letters present int the output
*/

class Solution {
     List<String> result;
    public String[] expand(String S) {
       
        if(S== null || S.length() == 0) return new String[0];
        result = new ArrayList<>();
        
        List<List<Character>> blocks = new ArrayList<>();
        //each block is the letters from curly braces
        int i = 0;
         List<Character> block;
        while(i<S.length()){
           block = new ArrayList<>();
            if(S.charAt(i) == '{'){
                i++;
                while(S.charAt(i) != '}'){
                    if(S.charAt(i) !=','){
                       block.add(S.charAt(i)); //ab
                    }
                      i++;
               }
            }
            else{
                block.add(S.charAt(i));
                }
            i++;
            System.out.println(block.toString()+ " block");
             blocks.add(block);
        }
       
    
        //blocks contains the list of block1 = abc block2=l block3=m block4=ef
        backtrack(blocks,0,new StringBuilder()); // backtrack pass datastructure, starting index and resultant datastructure
        
        //need to convert result list into string array
         String[] resultArr = new String[result.size()];
        for(int k = 0 ; k <result.size();k++){
            resultArr[k] = result.get(k);
        }
        Arrays.sort(resultArr);
        return resultArr;
    }
    
    private void backtrack(List<List<Character>> blocks,int index,StringBuilder sb){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());return;
        }
        //logic
        for(Character c: blocks.get(index)){
            //action
            sb.append(c);
            //recurse
            backtrack(blocks,index+1,sb); //move to next block
            //backtrack
            sb.deleteCharAt(sb.length() -1); //remove last element from each block and then go to next block
        }
    }
}

/*
For input "{a,b}c{d,e}f"
blocks are 
[a, b] block
[c] block
[d, e] block
[f] block

For input : "{a,b,c}lm{e,f}"
Output is 
[a, b, c] block
[l] block
[m] block
[e, f] block

Time complexity : O(m^n) where m is no of blocks and n is length of each block ;m and n are average length
Space complexity: O(2n) where n is blocks and n is string builder
*/