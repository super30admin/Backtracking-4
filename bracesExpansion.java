class Solution {
    List<String> result;
    public String[] expand(String S) {
        result=new ArrayList<>();
        int i=0;
        char[] arr=S.toCharArray();
        List<List<Character>> blocks=new ArrayList<>();
        while(i<S.length())
        {
            List<Character> temp=new ArrayList<>();
            if(arr[i]=='{')
            {
                i++;
                while(arr[i]!='}')
                {
                    if(arr[i]!=',')
                        temp.add(arr[i]);
                    i++;
                }
            }
            else 
            {
                temp.add(arr[i]);
            }
            blocks.add(temp);
            i++;
        }
        backtrack(blocks,0,new StringBuilder());
        String[] resultArr=result.toArray(new String[result.size()]);
        Arrays.sort(resultArr);
        return resultArr;
    }
    
    public void backtrack(List<List<Character>> blocks,int index,StringBuilder sb)
    {
        // base
        if(index==blocks.size())
        {
            result.add(sb.toString());
            return;
        }
        
        // Logic
        for(char c:blocks.get(index))
        {
            sb.append(c);
            backtrack(blocks,index+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}

// Time Complexity: O(n^(n/k))
// Space Complexity: O(n*k)