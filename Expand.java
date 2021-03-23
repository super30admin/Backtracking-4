// Time Complexity : The time complexity is exponential
// Space Complexity : Te space complexity is O(n) where n is the number of characters
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {

    List<String> res;

    public String[] expand(String s) {

        if(s == null || s.length() == 0){
            return new String[0];
        }

        List<List<Character>> list = new ArrayList<>();
        res = new ArrayList<>();

        int i=0;

        //create a list of lists by expanding the given string
        while(i < s.length()){

            List<Character> each = new ArrayList<>();

            if(s.charAt(i) == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        each.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else if(s.charAt(i) != '}'){
                each.add(s.charAt(i));
            }
            Collections.sort(each);
            list.add(each);
            i++;
        }

        //backtract to find all possible combinations
        backtrack(list,0,new StringBuilder());

        String[] output = new String[res.size()];

        for(int j=0;j<res.size();j++){
            output[j] = res.get(j);
        }

        return output;

    }

    public void backtrack(List<List<Character>> list,int index,StringBuilder sb){

        if(index == list.size()){
            res.add(sb.toString());
            return;
        }

        List<Character> cur = list.get(index);

        for(int i=0;i<cur.size();i++){
            sb.append(cur.get(i));
            backtrack(list,index+1,sb);
            sb.setLength(sb.length()-1);
        }
    }
}
