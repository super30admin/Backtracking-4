//approach : Group the characters, make list of list [[a,b,c], d,e, [f,g,h], k] - adefk, adegk, adehk
//tc: O(k^N) = k = avg length of grouping, N = total number of groups-> n/k ; n is length of string, k = groups
//sc: Stack: O(n) length of string + Result list  
class Solution {
   List<String> result;
    public String[] expand(String s) {
        
        
        //base case
        if(s == null || s.length() ==0) return null;
    
         result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        
        for(int i=0; i<s.length(); i++)
        {
            List<Character> group = new ArrayList<>();
            
            char c = s.charAt(i);
            if(c == '{')
            { 
                i++;
                while(s.charAt(i) != '}')
                {
                    if( s.charAt(i) != ',')
                    {
                       group.add(s.charAt(i)); 
                    }
                    i++;
                }
                
            }else
            {
                if(c == ',') continue;
                group.add(c);
            }
            Collections.sort(group);
            groups.add(group);
        }
        
        
        helper(groups, 0, new StringBuilder());
        String[] re = new String[result.size()];
        
        for(int k=0; k<result.size(); k++)
        {
           re[k] = result.get(k); 
        }
        //System.out.println(groups.toString());
                           
        return re;
    }
    
    private void helper(List<List<Character>> groups, int index, StringBuilder path)
    {
        //base case
        if(index == groups.size())
        {
            result.add(path.toString());
            return;
        }
        
        List<Character> group = groups.get(index);
        
        //logic
      
        for(int i=0; i<group.size(); i++)
        {
            int le = path.length();
            path.append(group.get(i));
            //recursion
            helper(groups, index+1, path);
               //backtrack
            path.setLength(path.length() -1);
        }
     
    }
}