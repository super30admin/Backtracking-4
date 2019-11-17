class OptimalPlacement:
    def findMinDist(self, W = 4, H = 3, n = 3):
        self.grid = [[float('inf') for i in range(W)] for j in range (H)]
        self.global_min = float("inf")

        self.building_placer(0, 0, W, H, n)
        return self.global_min

    def building_placer(self, r, c, W, H, n):

        if n == 0:
            return self.bfs()

        for i in range(r, W):
            for j in range(c, H):
                self.grid[i][j] = 0
                self.building_placer(i+1, j+1, W, H, n-1)
            self.grid[i][j] = float('inf')


    def bfs(self):
        queue = []

        for i in range(len(self.grid)):
            for j in range(len(self.grid[0])):
                if self.grid[i][j] == 0:
                    queue.append([i,j])

        dirs = [[1,0],[-1,0],[0,1],[0,-1]]
        while queue:
            curr = queue.pop()
            for dir in dirs:
                r = curr[0] + dir[0]
                c = curr[1] + dir[1]
                if r >= 0 and r <= len(self.grid) and c >= 0 and c <= len(self.grid[0]) and self.grid[r][c] > self.grid[curr[0]][curr[1]] +1 :
                    self.grid[r][c] = self.grid[curr[0]][curr[1]] +1
                    self.global_min = min(self.global_min, self.grid[r][c])
                    queue.append([r,c])
