class Solution {
    public String[] expand(String s) {
        List<String> res = new ArrayList<>();
        helper(s, 0, res, new StringBuilder());
        return res.toArray(new String[res.size()]);
    }

    public void helper(String s, int index, List<String> res, StringBuilder sb){
        if(index == s.length()){
            res.add(sb.toString());
            return;
        }
        if(s.charAt(index) == '{'){
            index++;
            boolean chars[] = new boolean[26];
            while(s.charAt(index) != '}'){
                if(s.charAt(index) == ','){
                    index++;
                    continue;
                }
                chars[s.charAt(index++)-'a'] = true;
            }
            index++;
            char c;
            for(int i=0;i<26;i++){
                if(!chars[i])
                    continue;
                c = (char)(i+'a');
                sb = sb.append(c);
                helper(s, index, res, sb);
                sb = sb.deleteCharAt(sb.length()-1);
            }
        }
        else{
            sb = sb.append(s.charAt(index));
            helper(s, index+1, res, sb);
            sb = sb.deleteCharAt(sb.length()-1);
        }
    }
}