
# Time Complexity : O(n)
# Space Complexity :O(n)
# Passed on Leetcode: yes

class Solution:
    def expand(self, S: str):
        result = []

        def backtrack(substring, index):
            if index == len(S):
                result.append(substring)
                return

            if S[index] == '{':
                i = index + 1
                while S[i] != '}':
                    i += 1
                options = S[index + 1:i].split(',')
                for option in options:
                    backtrack(substring + option, i + 1)
            else:
                backtrack(substring + S[index], index + 1)

        backtrack('', 0)
        result.sort()
        return result
