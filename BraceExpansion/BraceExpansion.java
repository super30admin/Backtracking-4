/* Time Complexity : O(k^(n/k)) 
 *  k - Avg. length of the groups
 *  n - length of inout string */
/* Space Complexity : O(n/k) */
// Did this code successfully run on Leetcode :  
// Any problem you faced while coding this :

class Solution{
	
	List<String> result;
	
	public String[] expand(String s){
		this.result = new ArrayList<>();
		List<List<Character>> groups = new ArrayList<>();
		int i = 0;
		while(i < s.length){
			char c = s.charAt(i);
			List<Character> group = new ArrayList<>();
			if(c == '{'){
				i++;
				while(s.charAt(i) != '}'){
					if(s.charAt(i) != ','){
						group.add(s.charAt(i));
					}
					i++;
				}
				i++;
			} else {
				group.add(c);
				i++;
			}
			Collections.sort(group);
			groups.add(group);
		}
		helper(groups, 0, new StringBuilder());
		String[] re = new String[result.size());
		int(k = 0; k < re.length; k++){
			re[k] = result.get(k);
		}
		return re;
	}
	
	private void helper(List<List<Character>> groups, int idx, StringBuilder sb) {
		//base condn
		if(idx == groups.size()){
			result.add(sb.toString());
			return;
		}
		//logic
		List<Character> group = groups.get(idx);
		for(int i = 0; i < group.size(); i++){
			char c = group.get(i);
			//action
			sb.append();
			//recurse
			backtrack(groups, idx + 1, sb);
			//backtrack
			sb.delete(sb.length() -1);
		}
		
	}
}