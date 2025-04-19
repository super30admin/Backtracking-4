class buildingPlacement:
    
    def bfs(self,grid,h,w):
        q = collections.deque()
        visited = [[False]*w]*h
        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    q.append((i,j))
                    visited[i][j] = True
        dist = 0
        dirs = [(0,1),(1,0),(0,-1),(-1,0)]
        while q:
            cursize = len(q)
            while cursize > 0:
                cur = q.popleft()
                cursize -= 1
                for dx,dy in dirs:
                    nr,nc = cur[0]+dx,cur[1]+dy
                    if nr >= 0 and nr < h and nc >= 0 and nc < w and visited[nr][nc] == False:
                        q.append((nr,nc))
                        visited[nr][nc] = True
            dist += 1
        self.mindist = min(self.mindist,dist)
                
        
                    
    def backtrack(self,grid,row:int,col:int,n:int,h:int,w:int):
        # base
        if n == 0:
            self.bfs(grid,h,w)
            return
        if col == w:
            row += 1
            col = 0
        # logic
        
        for i in range(row,h):
            for j in range(col,w):
                # action -- place the building
                grid[i][j] = 0
                # recurse
                self.backtrack(grid,i,j+1,n-1,h,w)
                # backtrack
                grid[i][j] = -1
            col = 0
        return self.mindist
            
                
                
    def findMinDist(self,h:int,w:int,n:int):
        grid = [[-1]*w]*h
        self.mindist = float('inf')
        return self.backtrack(grid,0,0,n,h,w)
        

        
bp = buildingPlacement()
print(bp.findMinDist(4,4,3))