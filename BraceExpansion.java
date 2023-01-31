//Time complexity is O(N*3^(N/7))
//Space complexity is O(N)
class Solution {
    List<String> result = new ArrayList<>();
    public String[] expand(String s) {
        helper(s, 0, "");
        String[] array = result.stream()
                            .toArray(String[]::new);
        Arrays.sort(array);
        return array;
    }
    public void helper(String s, int pos, String curr){
        int n = s.length();
        if(pos>=n){
            result.add(curr);
            return;
        }
        if(s.charAt(pos)=='{'){
            int i=pos;
            List<Character> li = new ArrayList<>(); 
            System.out.println("initial i "+i);
            while(i<n && s.charAt(i)!='}'){
                if(s.charAt(i)==',' || s.charAt(i)=='{'){
                    i=i+1;
                }
                else{
                    li.add(s.charAt(i));
                    i=i+1;
                }
            }
            i=i+1;
            System.out.println("invokes");
            System.out.println(i);
            for(Character ch: li){
                
                helper(s, i, curr+ch);
                //curr.deleteCharAt(curr.length()-1);
            }
        }
        else{
            helper(s, pos+1,curr+s.charAt(pos));
        }
    }
}