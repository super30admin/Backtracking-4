class Solution {
    List<String> result;
    public String[] expand(String s) {
        this.result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        int i=0;

        while(i<s.length()){
            List<Character> group = new ArrayList<>();
            if (s.charAt(i)=='{'){
                i++;
                while (s.charAt(i)=='}')
                {
                    if (s.charAt(i)!=','){
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                i++;
            }
            else {
                group.add(s.charAt(i));
                i++;
            }
            Collections.sort(group);
            groups.add(group);
        }
        helper(groups,0,new StringBuilder());
        return result.toArray(new String[0]);
    }
    private void helper(List<List<Character>> groups, int index, StringBuilder sb){
        if(idx==groups.size())
        {
            result.add(path.toString());
            return;
        }
        List<Character> currGroup= groups.get(idx);
        for(int i=0;i<currGroup.size();i++)
        {
            int le= path.length();
            path.append(currGroup.get(i));
            helper(groups,index+1,sb);
            path.setLength(le);
        }


    }
}