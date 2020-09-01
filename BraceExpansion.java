


class Solution {
    List<String> result = new ArrayList<>();
    
    public String[] expand(String S) {
        
        int startIndex = 0;
        Queue<String[]> queue = new LinkedList<>();
        while(S.indexOf('{',startIndex) != -1){
            int openbracket = S.indexOf("{",startIndex);
            int closebracket = S.indexOf("}",startIndex);
            if(openbracket!= startIndex){ // elements before opening brackets
                queue.add(new String[]{S.substring(startIndex,openbracket)});
            } 
            queue.add(S.substring(openbracket+1,closebracket).split(","));
            startIndex= closebracket+1;
        }
        // start index to end case
        if(startIndex != S.length()){
            queue.add(new String[]{S.substring(startIndex,S.length())});
        }
        backtracking(queue,"");
        String[] returnArray = result.toArray(new String[result.size()]);
        Arrays.sort(returnArray);
        return returnArray;
    }
    private void backtracking(Queue<String[]> queue, String curr){
        //base case
        if(queue.isEmpty()){
            result.add(curr);
            return;
        }
        //recursive case
        String[] front = queue.poll();
        for(String letter : front){
            Queue<String[]> tempqueue = new LinkedList<>(queue);
            backtracking(tempqueue,curr+letter);
        }
        
    }
}
