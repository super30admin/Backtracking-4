
//Time Complexity-O(nlogn)
//Space-O(n)
class Solution {
    List<String>output=new ArrayList();
    public String[] expand(String S) {
        if(S==null||S.length()==0)
        {
            return new String[]{};
        }
        List<String>blocks=new ArrayList();
        int i=0;
        while(i<S.length())
        {
            StringBuilder sb=new StringBuilder();
            if(S.charAt(i)=='{')
            {
                i++;
                while(S.charAt(i)!='}')
                {
                    if(S.charAt(i)!=',')
                    {
                    sb.append(S.charAt(i));
                    }
                    i++;
                }
                blocks.add(sb.toString());
            }
            else{
                if(S.charAt(i)!='{'&&S.charAt(i)!='}')
                {
                    sb.append(S.charAt(i));
                    blocks.add(sb.toString());
                }
                i++;
            }
        }
        backTrack(0,blocks,new StringBuilder());
        String[]arr=output.toArray(new String[0]);
        Arrays.sort(arr);
        return arr;
        
    }
    private void backTrack(int index,List<String>blocks,StringBuilder sb)
    {
        if(index==blocks.size())
        {
            output.add(sb.toString());
            return;
        }
        String newStr=blocks.get(index);
        for(int i=0;i<newStr.length();i++)
        {
            sb.append(newStr.charAt(i));
            backTrack(index+1,blocks,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

}