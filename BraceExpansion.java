// Time Complexity: O(nk^n/k)
// Space Complexity: O(n)

class Solution {

	List<String> result;

	public String[] expand(String S) {
		int cursor = 0;
		result = new ArrayList<>();
		List<List<Character>> blocks = new ArrayList<>();

		while(cursor < S.length()) {
			List<Character> currentBlock = new ArrayList<>();

			if(S.charAt(cursor) == '{') {
				cursor++;
				while(S.charAt(cursor) != '}') {
					if(S.charAt(cursor) != ',')
						currentBlock.add(S.charAt(cursor));
					cursor++;
				}
			} else {
				currentBlock.add(S.charAt(cursor));
			}

			blocks.add(currentBlock)
			cursor++;
		}

		backtrack(blocks, 0, new StringBuilder());

		Collections.sort(result);
		String[] arr = new String[result.size()];

		for(int i = 0; i < result.size(); i++) {
			arr[i] = result.get(i);
		}

		return arr;
	}

	private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {

		if(index == blocks.size()) {
			result.add(sb.toString());
			return;
		}

		for(char c: blocks.get(index)) {
			sb.append(c);
			backtrack(blocks, index + 1, sb);
			sb.deleteCharAt(sb.length()-1);
		}
	}
}