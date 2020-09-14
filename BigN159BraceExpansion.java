// Time complexity is O(K^n/k) i.e exponential
// Space complexity is O(nk) + O(n/k) = O(nk)
// This solution is not submitted on leetcode !!!!!!!!!!!! (I dont have a premium account)
// is there any way to test this code I have tested with given inputs

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigN159BraceExpansion {
	List<String> result;

	public String[] expand(String S) {
		result = new ArrayList<>();
		int i = 0;
		List<List<Character>> block = new ArrayList<>();
		while (i < S.length()) {
			List<Character> temp = new ArrayList<>();
			if (S.charAt(i) == '{') {
				i++;
				while (S.charAt(i) != '}') {
					if (S.charAt(i) != ',')
						temp.add(S.charAt(i));
					i++;
				}
			} else {
				temp.add(S.charAt(i));
			}
			block.add(temp);
			i++;
		}
		backtrack(new StringBuilder(), 0, block);

		String[] res = result.toArray(new String[result.size()]);
		Arrays.sort(res);
		return res;

	}

	private void backtrack(StringBuilder sb, int index, List<List<Character>> block) {
		// base
		if (index == block.size()) {
			result.add(sb.toString());
			return;
		}

		// logic
		for (char c : block.get(index)) {
			sb.append(c); // action
			backtrack(sb, index + 1, block);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String args[]) {
		BigN159BraceExpansion brace = new BigN159BraceExpansion();
		String S = "{a,b}c{d,e}f";
		// String S = "abcd";
		String[] res = brace.expand(S);
		for (String cs : res) {
			System.out.println(cs);
		}
	}
}