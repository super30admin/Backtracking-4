from typing import List


class Solution:
    def expand(self, S: str) -> List[str]:
        """
            https://leetcode.com/problems/brace-expansion/
            Time Complexity - Exponential
            Space Complexity - O(n)
            'n' is the length of string 'S'. (Stack space)
        """
        blocks = []
        i = 0
        while i < len(S):
            temp = []
            if S[i] == '{':
                i += 1
                while S[i] != '}':
                    if S[i] != ',':
                        temp.append(S[i])
                    i += 1
            else:
                temp.append(S[i])
            blocks.append(temp)
            i += 1
        result = []
        self._backtrack(result, blocks, 0, '')
        return sorted(result)

    def _backtrack(self, result, blocks, index, cur_str):
        # base
        if index == len(blocks):
            result.append(cur_str)
            return

        # logic
        for char in blocks[index]:
            # action
            cur_str += char
            # recurse
            self._backtrack(result, blocks, index + 1, cur_str)
            # backtrack
            cur_str = cur_str[:len(cur_str) - 1]


if __name__ == '__main__':
    print(Solution().expand("{a,b}c{d,e}f"))
