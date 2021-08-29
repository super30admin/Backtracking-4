# Time Complexity: O(n)
# Space Complexity:O(n)
from typing import List


class Solution:
    def expand(self, s: str) -> List[str]:

        self.result = []

        self.backtrack(s, "")

        self.result.sort()
        return self.result

    def backtrack(self, s, curr_res):

        if not s:
            self.result.append(curr_res)
        else:
            if s[0] == "{":
                i = s.find("}")
                for w in s[1:i].split(','):
                    self.backtrack(s[i + 1:], curr_res + w)
            else:
                self.backtrack(s[1:], curr_res + s[0])
