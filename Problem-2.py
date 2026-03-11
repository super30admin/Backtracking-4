# Time Complexity : O(K * 2^K)  # K is length of string, branching occurs for each brace option
# Space Complexity : O(2^K)     # Store all expanded strings
# Did this code successfully run on Leetcode : No
# Any problem you faced while coding this : No

# Explanation:
# We parse the string and when encountering braces, split the options.
# Using backtracking we append each option and continue expanding the rest of the string.
# All generated strings are collected and returned in sorted order.

class Solution:
    def expand(self, s: str) -> List[str]:
        res = []

        def backtrack(i, path):
            if i == len(s):
                res.append(path)
                return

            if s[i] == '{':
                j = i
                while s[j] != '}':
                    j += 1
                options = s[i+1:j].split(',')
                for ch in options:
                    backtrack(j+1, path + ch)
            else:
                backtrack(i+1, path + s[i])

        backtrack(0, "")
        return sorted(res)