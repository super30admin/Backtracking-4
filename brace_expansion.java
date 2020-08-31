
//TC - O(M^N) [where  is avg length of each block and n is number of blocks]
//SC - O(N)  [Not sure]
import java.util.*;

class Solution {
	List<String> result;

	public String[] expand(String S) {
		// edge
		if (S == null || S.length() == 0)
			return new String[0];
		result = new ArrayList<>();
		List<List<Character>> blocks = new ArrayList<>();
		int i = 0;
		while (i < S.length()) {
			List<Character> block = new ArrayList<>();
			if (S.charAt(i) == '{') {
				i++;
				while (S.charAt(i) != '}') {
					if (S.charAt(i) != ',')
						block.add(S.charAt(i));
					i++;
				}
			} else
				block.add(S.charAt(i));
			i++;
			blocks.add(block);
		}
		backtrack(blocks, 0, new StringBuilder());
		String resultArray[] = new String[result.size()];
		for (int j = 0; j < result.size(); j++) {
			resultArray[j] = result.get(j);
		}
		Arrays.sort(resultArray);
		return resultArray;
	}

	private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
		// base
		if (index == blocks.size()) {
			result.add(sb.toString());
			return;
		}
		// logic
		for (char c : blocks.get(index)) {
			// action
			sb.append(c);
			// recurse
			backtrack(blocks, index + 1, sb);
			// backtrack
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}