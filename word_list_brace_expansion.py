class Solution:
    def expand(self, s: str) -> List[str]:
        groups = []
        i = 0
        while i < len(s):
            group = []
            c = s[i]
            if c == "{":
                i += 1
                while s[i] != "}":
                    if s[i] != ",":
                        group.append(s[i])
                    i += 1
                i += 1
            else:
                group.append(c)
                i += 1
            groups.append(group)
        result = []
        self.dfs(groups, 0, [], result)
        return sorted(result)

    def dfs(self, groups, idx, path, result):
        # Base case: if the path length equals the number of groups, add the path to the result
        if len(path) == len(groups):
            result.append(path)
            return

        # Logic: Get the current group
        group = groups[idx]

        # Iterate over each character in the group
        for char in group:
            # Action: Append the character to the current path
            path.append(char)

            # Recurse with the next group (index + 1)
            dfs(groups, idx + 1, path, result)

            # Backtrack: remove the last character added to path (restore the path)
            path.pop()
