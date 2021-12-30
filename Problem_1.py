class Solution:
    def minDistance(self, h, w, n):
        if h == 0 or w == 0 or n == 0:
            return 0
        self.minDist = float('inf')
        grid = [[-1 for j in range(w)] for i in range(h)]
        self.backtrack(grid, h, w, n, 0, 0)
        return self.minDist
    
    def bfs(self, grid, h, w):
        q = deque()
        visited = [[False for j in range(w)] for i in range(h)]
        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    q.append([i,j])
                    visited[i][j] = True
        dist = 0
        dirs = [[0,1], [1,0], [-1,0], [0,-1]]
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                for direc in dirs:
                    nr = direc[0] + curr[0]
                    nc = direc[1] + curr[1]
                    if nr >= 0 and nr < h and nc >= 0 and nc < w and not visited[nr][nc]:
                        q.append([nr,nc])
                        visited[nr][nc] = True
            dist += 1
        self.minDist = min(self.minDist, dist-1)
                        
                    
    def backtrack(self, grid, h, w, n, r, c):
        if n == 0:
            self.bfs(grid, h, w)
            return
        if c == w:
            c = 0
            r += 1
        for i in range(r, h):
            for j in range(c, w):
                grid[i][j] = 0
                self.backtrack(grid, h, w, n-1, r, c+1)
                grid[i][j] = -1
            c = 0
            
m = Solution()
print(m.minDistance(4, 4, 3))
print(m.minDistance(3, 3, 2))

# Time Complexity : (h * w)!
# Space Complexity : (h * w)