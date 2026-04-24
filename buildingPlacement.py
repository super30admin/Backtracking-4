from collections import deque
class BuildingPlacement:
    def __init__(self, W, H):
        self.W = W
        self.H = H
        self.Grid = [[False]*W for _ in range(H)]
        self.minDistance = float('inf')
        self.res = None
        
    
    def bfs(self, grid):
        grid_copy = [row[:] for row in grid]
        q = deque([])
        dirs = [(0,1), (1,0), (0,-1), (-1,0)]
        for i in range(self.H):
            for j in range(self.W):
                if grid_copy[i][j]:
                    q.append((i,j))
        
        dist = 0
        while q:
            size = len(q)
            dist+=1
            for _ in range(size):
                cr, cc = q.popleft()
                for dr, dc in dirs:
                    nr,nc = cr+dr,cc+dc
                    if nr>=0 and nr<self.H and nc>=0 and nc<self.W and grid_copy[nr][nc]==False:
                        grid_copy[nr][nc]=dist
                        q.append((nr,nc))
        
        dist-=1
        
        if dist<self.minDistance:
            self.minDistance = dist
            self.res = grid_copy
                        
    def solve(self, idx, builds):
        #base
        if builds==0:
            self.bfs(self.Grid)
            return
        
        for i in range(idx,self.W*self.H):
            row = i//self.W
            col = i%self.W
            
            #action
            self.Grid[row][col]=True
            #recurse
            self.solve(i+1, builds-1)
            #backtrack
            self.Grid[row][col]=False
    
BP = BuildingPlacement(4,4)
BP.solve(0,3)
print(BP.minDistance)
print(BP.res)
        
            
        
        
        
        
        
    
    
        