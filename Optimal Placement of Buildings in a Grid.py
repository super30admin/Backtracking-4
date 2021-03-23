from collections import deque

class Solution:
    def findMinDistance(self, H, W, n):
        #Approach: Backtracking
        #Time Complexity: Exponential  //  O((HW)C(n) * HW)
        #Space Complexity: O(H * W)
        #where, C is the symbol for combinations
        
        grid = [[0 for _ in range(W)] for _ in range(H)]
        self.minDistance = inf
        
        self.backtrack(grid, 0, 0, H, W, n)
        return self.minDistance
    
    def bfs(self, grid, H, W):
        de = deque()
        visited = [[False for _ in range(W)] for _ in range(H)]
        for i in range(H):
            for j in range(W):
                if grid[i][j] == 1:
                    de.append((i, j))
                    visited[i][j] = 1
                    
        dirArr = [(-1, 0), (0, -1), (0, 1), (1, 0)]
        dist = 0
        while de:
            sz = len(de)
            for _ in range(sz):
                popped = de.popleft()
                for dir in dirArr:
                    r = popped[0] + dir[0]
                    c = popped[1] + dir[1]
                    
                    if r >= 0 and r < H and c >= 0 and c < W and not visited[r][c]:
                        de.append((r, c))
                        visited[r][c] = 1
            
            dist += 1
            
        self.minDistance = min(self.minDistance, dist - 1)
    
    def backtrack(self, grid, r, c, H, W, n):
        #base
        if n == 0:
            self.bfs(grid, H, W)
            return
        
        #logic
        if c >= W:
            c = 0
            r += 1
        
        for i in range(r, H):
            for j in range(c, W):
                grid[i][j] = 1                                  #action; put the building
                self.backtrack(grid, i, j+1, H, W, n-1)   #recursion
                grid[i][j] = 0                                  #backtracking
            c = 0

#Driver code
H = 5
W = 5
n = 3
sol = Solution()
print(sol.findMinDistance(H, W, n))