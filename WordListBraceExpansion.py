# // Time Complexity :O(k^n), or O(k^ (N/k))
# // Space Complexity :O(n + k^n) 
# // Did this code successfully run on Leetcode : yes
# // Any problem you faced while coding this : no

# // Your code here along with comments explaining your approach
# First, we try every way to place the buildings on the grid using backtracking.
# For each combination, we run a BFS to find the max distance from any empty cell to the nearest building.
# Among all placements, we keep track of the one that gives the smallest such distance.

from typing import List
class Solution:
    def expand(self, s: str) -> List[str]:
        self.res = []
        groups = []
        n,i = len(s), 0
        while i <n:
            group = []
            if s[i] == "{":
                i+=1
                while s[i]!="}":
                    if s[i]!=",":
                        group.append(s[i])
                    i+=1
                i+=1
            else:
                group.append(s[i])
                i+=1
            group.sort()
            groups.append(group)
        # since we have the groups now we can apply the dfs with groups and idx and 
        self.dfs(groups,0,[])
        return self.res 

    def dfs(self,groups,idx,path):
        # base
        if idx == len(groups):
            self.res.append("".join(path))
            return
        # logic
        # try all the  characters from one group and then jump to other
        currgroup = groups[idx]
        for i in range(len(currgroup)):
            # action
            size = len(path) 
            path.append(currgroup[i])
            # recurse
            self.dfs(groups,idx+1,path)
            # backtrack
            path = path[:size]
        



        