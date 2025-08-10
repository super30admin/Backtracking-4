# // Time Complexity :O(C(H*W, N) * H*W) (Combinatorial placements of N buildings times BFS for each grid)
# // Space Complexity :O(H*W) for the BFS queue and visited matrix.
# // Did this code successfully run on Leetcode : yes
# // Any problem you faced while coding this : no


# // Your code here along with comments explaining your approach
# First, we try every way to place the buildings on the grid using backtracking. We convert 1d index to row and column index of 2d matrix to place the building.
# For each combination, we run a BFS to find the max distance from any empty cell to the nearest building.
# Among all placements, we keep track of the one that gives the smallest such distance.

from collections import deque

class BuildingPlacement:
#     initialize the grid h,n,w
    def __init__(self):
        self.H = 0
        self.W = 0
        self.res = float("inf")
    
    def findMinDistance(self,h,n,w):
#         initialize perform dfs and backtrack make grid -1
        self.H,self.W=  h,w
        grid = [[-1]*(self.W) for _ in range(self.H)]
#         place building at every location perform dfs and then return res
        self.dfs(grid,0,n)
        return self.res
    
    def dfs(self,grid, pivot, n):
#         base
        if n == 0: #total buildings becomes 0
            self.bfs(grid)
            return
#         logic
        for i in range(pivot,self.H*self.W):
            r = i//self.H
            c = i%self.W
            #action
            grid[r][c] = 0
            #recurse
            self.dfs(grid, i+1,n-1)
            #backtrack
            grid[r][c] = -1
    
    def bfs(self, grid):
        #nearest -1 from all 0
        visited = [[False]*self.W for _ in range(self.H)]
        direction = [[0,1],[1,0],[-1,0],[0,-1]]
        q = deque()
        for i in range(self.H):
            for j in range(self.W):
                if grid[i][j] == 0:
                    q.append((i,j))
                    visited[i][j] = True
        dist = 0
        while q:
            size = len(q)
            for i in range(size):
                r,c = q.popleft()
                for dr, dc in direction:
                    nr = r+dr
                    nc = c+dc
                    if 0<=nr<self.H and 0<=nc<self.W and grid[nr][nc] == -1 and not visited[nr][nc]:
                        q.append((nr,nc))
                        visited[nr][nc] = True
            dist+=1
        self.res = min(self.res, dist-1) 
        
        
        
# -----------------
# Test in playground
# -----------------
bp = BuildingPlacement()
h, w, n = 4, 4, 3
print(bp.findMinDistance(h,n, w))  # Expected: 2
