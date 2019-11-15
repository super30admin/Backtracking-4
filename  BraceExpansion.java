
// Time complexity - exponential
// Space complexity - O(n)
// Tested in leetcode


class BraceExpansion {
    public String[] expand(String S) {
        ArrayList<String> res = new ArrayList<>();
        Queue<String[]> queue = new LinkedList<>();
        int index =0;
        
        // Store each substring enclosed by braces into a String arry. 
        // Loop through input string till it comes to end
        while(S.indexOf('{',index) != -1){
            int startIndex =S.indexOf('{',index);
            int endIndex =S.indexOf('}',index);
            
            //if strings are not encloded in curly braces
            if(index != startIndex){
                queue.offer(S.substring(index,startIndex).split(","));
            }
            queue.offer(S.substring(startIndex+1,endIndex).split(","));
            index = endIndex+1;
        }
        
        //stores string if substring are not inside {}
        if(index != S.length()){
            queue.offer(S.substring(index).split(","));
        }
        //call backtracking function, convert into the array and return
        helper(queue,res,"");
        String[] result = res.toArray(new String[res.size()]);
        Arrays.sort(result);
        return result;
    }
    
    // helper function 
    private void helper(Queue<String[]> queue,ArrayList<String> res, String temp){
        //Base condition 
        if(queue.isEmpty()){
            res.add(temp);
            return;
        }       
        String[] str = queue.poll();
        // backtrack
        for(String s : str){
            Queue<String[]> q = new LinkedList<>(queue);
            helper(q,res,temp+s);
        }
    }
}