# TC: O(k^n) + (nk)log(nk)
# SC: O(n) where n is the length of string

class Solution:
    def expand(self, s: str) -> List[str]:
        if s is None or len(s) == 0:
            return []

        self.result = []

        li = []
        i = 0
        while i < len(s):
            temp = []
            if s[i] == "{":
                i += 1
                while s[i] != "}":
                    if s[i] != ",":
                        temp.append(s[i])
                    i += 1

            else:
                temp.append(s[i])

            li.append(temp)
            i += 1

        self.helper(li, 0, "")
        self.result = sorted(self.result)
        return self.result

    def helper(self, li, i, path):
        # base case
        if i == len(li):
            self.result.append(path)
            return
        # logic
        tempLi = li[i]

        for num in tempLi:
            path += num
            self.helper(li, i + 1, path)
            path = path[:-1]


