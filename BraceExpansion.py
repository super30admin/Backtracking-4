# Time Complexity: O(k^N/k) where n is no. of groups, k is the max group size and N is the total length of the string
# Space Complexity: O(n+k^n) - recursion stack + result list

class Solution:
    def expand(self, s: str) -> List[str]:
        self.result = []
        groups = []
        i = 0
        while i < len(s):
            group = []
            if s[i] == '{':
                i+=1
                while s[i] != '}':
                    if s[i] != ',':
                        group.append(s[i])
                    i+=1
                i+=1
            else:
                group.append(s[i])
                i+=1

            group.sort()
            groups.append(group)
        self.helper(groups, 0, [])
        return self.result
    
    def helper(self, groups, idx, path):
        # base
        if idx == len(groups):
            self.result.append("".join(path))
            return
        # logic
        currGroup = groups[idx]
        for i in range(len(currGroup)):
            le = len(path)
            path.append(currGroup[i])
            self.helper(groups, idx+1, path)
            path = path[:le]