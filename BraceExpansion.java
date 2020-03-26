package com.example.problems;

import java.util.*;
//Time Complexity : O(N!) N = SIZE OF STRING 
//Space Complexity : O(N) 
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

//Your code here along with comments explaining your approach

/*
* Backtracking approach

*/
public class BraceExpansion {
	List<String> result;

	public String[] expand(String S) {
		List<List<Character>> blocks = new ArrayList<>();
		result = new ArrayList<>();
		char array[] = S.toCharArray();
		int i = 0, size = array.length;
		while (i < size) {
			List<Character> list = new ArrayList<>();
			if (array[i] == '{') {
				i++;
				while (array[i] != '}') {
					if (array[i] != ',') {
						list.add(array[i]);
					}
					i++;
				}
			} else {
				list.add(array[i]);
			}
			blocks.add(list);
			i++;

		}
		backtrack(blocks, 0, new StringBuilder());
		Collections.sort(result);
		return Arrays.copyOf(result.toArray(), result.size(), String[].class);
	}

	public void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
		// backtrack
		if (index == blocks.size()) {
			result.add(sb.toString());
			return;
		}
		List<Character> adaj = blocks.get(index);
		for (char ch : adaj) {
			// Action
			sb.append(ch);
			// Recur
			backtrack(blocks, index + 1, sb);
			// Backtrack
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String args[]) {
		String S = "{a,b}c{d,e}f";
		BraceExpansion expansion = new BraceExpansion();

		System.out.print(expansion.expand(S));
	}

}
