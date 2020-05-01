# Time Complexity : Not sure
# Space Complexity : O(WH)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

from collections import deque
class Solution:
    def __init__(self):
        self.dx = [-1,0,1,0]
        self.dy = [0,1,0,-1]
        self.minDistance = float('inf')

    def findPlacement(self, W, H, n):

        grid = [[-1]*W for i in range(H)]

        self.backtrack(grid, W, H, 0, 0, n)

        return self.minDistance


    def bfs(self, grid, W, H):

        queue = deque()
        dist = 0
        visited = [[False]*W for i in range(H)]
        for i in range(H):
            for j in range(W):
                if grid[i][j] == 0:
                    queue.append((i,j))
                    visited[i][j] = True

        while queue:
            
            count = len(queue)
            for i in range(count):
                current = queue.popleft()
                for k in range(4):
                    x = current[0] +self.dx[k]
                    y = current[1] +self.dy[k]
                    if self.isValid(grid, W, H, x, y) and not visited[x][y]:
                        queue.append((x,y))
                        visited[x][y] = True
            dist +=1
        self.minDistance = min(self.minDistance,dist-1)
                

    def backtrack(self, grid, W, H, r, c, n):

        #basecase
        if n == 0:
            self.bfs(grid, W, H)
            return 

        if c >=W:
            r +=1
            c = 0

        #backtrack

        for i in range(r, H):
            for j in range(c, W):
                grid[i][j] = 0
                self.backtrack(grid, W, H, i, j+1, n-1)
                grid[i][j] = -1
            c = 0

    def isValid(self,grid, W, H, x, y):
        return x>=0 and y>=0 and x<H and y<W


print(Solution().findPlacement(4,4,3))

