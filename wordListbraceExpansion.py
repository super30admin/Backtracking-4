class Solution:
    def expand(self, s: str) -> List[str]:
        n = len(s)
        groups = []
        res = []
        braces = False
        for i in range(n):
            if s[i] == ",":
                continue

            if s[i] == "}":
                groups[-1].sort()
                braces = False
            elif s[i] == "{":
                braces = True
                groups.append([])
            else:
                if not braces:
                    braces = False
                    groups.append([])
                groups[-1].append(s[i])
            
        def dfs(idx, path):
            if idx == len(groups):
                res.append("".join(path))
                return 
            
            for c in groups[idx]:
                path.append(c)
                dfs(idx + 1, path)
                path.pop()
        dfs(0, [])
        return res
