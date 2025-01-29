# The expand method generates all possible strings from an expanded string with braces.

# Approach:
# - Parse the input `s` to extract groups of characters within `{}`.
# - Store single characters and groups separately in a list `a`.
# - Use recursion to generate all possible combinations from `a`.

# TC: O(k^n) - k choices per group, n groups.
# SC: O(n) - Recursive call stack.


class Solution:
    def expand(self, s: str) -> List[str]:
        i = j = 0
        l = len(s)
        found = False
        a = []
        while i < l:
            c = s[i]
            if c == "{":
                found = True
                j = i
            else:
                if c == "}":
                    a.append(s[j+1:i].split(","))
                    found = False
                else:
                    if not found:
                        a.append(c)
            i += 1

        if found:
            a.append(s[j+1:i].split(","))

        self.total = []
        self.rec(a, "", 0, len(a))
        return self.total

    def rec(self, a: list, s: str, depth: int, end: int):
        if depth == end:
            self.total.append(s)
            return
        
        for c in sorted(a[depth]):
            self.rec(a, s+c, depth+1, end)