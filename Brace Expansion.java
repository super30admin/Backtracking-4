class Solution {
    List<String> result;
    public String[] expand(String s) {

        result = new LinkedList<>();
        //How do we go about solving this?
        //Form all the blocks of text. 

        int s_index = 0;
        List<List<Character>> blocks = new LinkedList<>();

        while(s_index < s.length()){

            List<Character> curr_block = new LinkedList<>();
            char curr_char = s.charAt(s_index);
            if(curr_char == '{'){

                
                s_index++;
                curr_char = s.charAt(s_index);
                while(curr_char != '}'){
                    
                    //keep collecting everything in a block.
                    //ignore the comma

                    if(curr_char != ','){
                        curr_block.add(curr_char);
                    }
                    s_index++;
                    curr_char = s.charAt(s_index);
                }
                blocks.add(curr_block);
                //to skip the closing brace. 
                s_index ++;
            }

            else{
                //Just append the letters to the curr block.

                
                
                curr_char = s.charAt(s_index);
                curr_block.add(curr_char);
                s_index ++;
                blocks.add(curr_block);
                
            }

        }

        dfs(blocks, 0, new StringBuilder());
        String[] result_arr = new String[result.size()];

        //Fill up the result array.
        for(int i = 0 ; i < result.size(); i++){
            result_arr[i] = result.get(i);
        }

        Arrays.sort(result_arr);
        return result_arr;

        //Now we need to recursively go over the blocks. 


    }

    
        public void dfs(List<List<Character>> blocks, int block_index, StringBuilder curr_string){

            //bases

            if(block_index == blocks.size()){
                result.add(curr_string.toString());
                return;
            }

            //if you have covered all the blocks then put the 

            //for going over the block horizontally. 
            for(int i = 0; i < blocks.get(block_index).size(); i++){

                curr_string.append(blocks.get(block_index).get(i));
                //call takes us vertically. 

                dfs(blocks, block_index + 1, curr_string);
                //remove the last appended character so the next one can be added. 

                curr_string.deleteCharAt(curr_string.length() - 1);

            }

        }

    
}