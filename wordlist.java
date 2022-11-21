/*
Time complexity: O(N * 3^{N/7})
Space complexity: O(N * 3^{N/7})
*/
class Soluton{
public String[] expand(String s) {
        List reformatString = new ArrayList<>();
        
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '{'){
                int j = i+1;
                StringBuilder sb = new StringBuilder();
                while(j < n && s.charAt(j)!= '}'){
                    if(s.charAt(j) == ','){
                        j++;
                        continue;
                    }
                    
                    sb.append(s.charAt(j));
                    j++;
                    
                }

            reformatString.add(sb.toString());
            i = j;
            }
            else
                  reformatString.add(Character.toString(s.charAt(i)));
        }
        
        List result = new ArrayList<>();
        
        backtrack(0,reformatString, result, new StringBuilder());
        
        String[] permutations = result.toArray(new String[result.size()]);
        Arrays.sort(permutations);
        return permutations;
    }
    
    public void backtrack(int pos, List reformat, List result, StringBuilder sb){
        if(sb.length() == reformat.size()){
            result.add(sb.toString());
            return;
        }
        
        for(char c: reformat.get(pos).toCharArray()){
            sb.append(c);
            backtrack(pos+1,reformat,result, sb);
            sb.setLength(sb.length()-1);
        }
    }
}
}