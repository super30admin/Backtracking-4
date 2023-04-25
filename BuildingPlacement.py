import collections

class Solution:
    
    #Here we check for every combination of placing n buildings what is the min dist of parking lot using BFS. Combinations are created by Backtracking
    #time complexity - O(h*w)*O(h*wCn) - where h*c is for bfs and combinations of choosing place for n buildings is h*wCn.
    #space complexity - O(h*w) - bfs queue
    def optimalBuildingPlacement(self, h, w, n):
        grid = [[-1]*w for i in range(h)]
        self.minDist = math.inf
        
        def bfs(grid):
            
            q = collections.deque()
            visited = [[False]*w for i in range(h)] #we use visited set so that we dont modify grid which is used by backtack function again
            directions = [[1,0], [-1,0], [0,1], [0,-1]]
            level=0
            for i in range(h):
                for j in range(w):
                    if grid[i][j]==0:
                        q.append((i,j))
                        visited[i][j] = True
            
            while q:
                for i in range(len(q)):
                    curR, curC = q.popleft()
                    for dr, dc in directions:
                        nr = dr+curR
                        nc = dc+curC
                        if nr>=0 and nc>=0 and nr<h and nc<w and visited[nr][nc]==False:
                            q.append((nr,nc))
                            visited[nr][nc]=True
                level+=1
            if (self.minDist>level-1):
                print(grid)
            self.minDist = min(self.minDist, level-1)
                            
                
            
        def backtrack(grid, idx, n):
            if n==0:
                bfs(grid)
            
            for i in range(idx, (h*w)):
                r = i//w
                c = i%w
                grid[r][c] = 0
                backtrack(grid, i+1, n-1)
                grid[r][c] = -1
            
        
        backtrack(grid, 0, n)
        return self.minDist
            
                  
s = Solution()
result = s.optimalBuildingPlacement(4,4,3)
print(result)