class Solution:
    def expand(self, s: str) -> List[str]:
        from itertools import product

        groups = []  # List of character groups
        i = 0
        while i < len(s):
            group = []
            if s[i] == '{':
                i += 1
                while s[i] != '}':
                    if s[i] != ',':
                        group.append(s[i])
                    i += 1
                i += 1  # skip the closing '}'
            else:
                group.append(s[i])
                i += 1
            groups.append(sorted(group))  # sort to meet output order

        result = []
        self.backtrack(groups, 0, [], result)
        return result

    def backtrack(self, groups: List[List[str]], idx: int, path: List[str], result: List[str]):
        if idx == len(groups):
            result.append(''.join(path))
            return

        for char in groups[idx]:
            path.append(char)
            self.backtrack(groups, idx + 1, path, result)
            path.pop()
