// 1087.
// assume S is broke into blocks of avg length k
// so ateach point, k choices are available, number of blocks = n/k
// time - O(k^(n/k))
// space - O(n/k)
class Solution {
    public String[] expand(String S) {
        //edge
        if(S == null || S.length() == 0)
        {
            return new String[0]; 
        }
        
        //pre process string into list of blocks 
        List<List<Character>> processed = new ArrayList<>();
        int i = 0; //iteration pointer
        while(i < S.length())
        {
            List<Character> temp = new ArrayList<>(); //list of char for each block
            if(S.charAt(i) == '{')
            {
                i++; //skip { and add all chars except ,s to temp till } is reached
                while(S.charAt(i) != '}')
                {
                    if(S.charAt(i) != ',')
                    {
                        temp.add(S.charAt(i));
                    }
                    i++;
                }
            }
            else 
            {
                temp.add(S.charAt(i));
            }
            processed.add(temp); //add temp to processed
            i++;
        }
        
        List<String> result = new ArrayList<>(); //retrun list
        backtrack(processed, 0, new StringBuilder(), result);
        String[] answer = result.toArray(new String[0]);
        Arrays.sort(answer);
        return answer; //return result as string[] in lexicographic sorted order
    }
    
    private void backtrack(List<List<Character>> processed, int index, StringBuilder path, List<String> result) {
        //base
        if(index == processed.size())
        {
            result.add(path.toString()); //add current path as a string to result and return
            return;
        }
        //logic
        for(Character current : processed.get(index))
        {
            path.append(current); //add current char of current block to path
            backtrack(processed, index + 1, path, result);  // recurse
            path.deleteCharAt(path.length() - 1); //backtrack to explore all possiblities
        }
        return;
    }
}
