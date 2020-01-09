class Solution:
    global H;
    global W;

    global grid;

    def __init__(self):
        self.minOfAllMax = float("inf")

    def findMinDist(self, height, width, n):
        self.H = height
        self.W = width

        self.grid = [[-1 for i in range(self.W)] for i in range(self.H)]

        # backtracking function to fill houses
        return self.backtracking(0, 0, n)


    def bfs(self): # return the max of particular grid
        queue = []

        for i in range(self.H):
            for j in range(self.W):
                if self.grid[i][j] == 0:
                    queue.append([i,j])
                else:
                    self.grid[i][j] = float("inf")

        dirs = [[0,1],[0,-1],[-1,0],[1,0]]

        while queue:
            curr = queue.pop(0)

            for dir in dirs:
                row = curr[0] + dir[0]
                col = curr[1] + dir[1]

                if row < 0 or row >= self.H or col < 0 or col >= self.W or self.grid[row][col] <= self.grid[curr[0]][curr[1]] + 1:
                    continue
                queue.append([row,col])
                self.grid[row][col] = self.grid[curr[0]][curr[1]] + 1

        maxInGrid = float("-inf")
        # calculate max in grid
        for i in range(self.H):
            for j in range(self.W):
                maxInGrid = max(maxInGrid,self.grid[i][j])
        return maxInGrid


    def backtracking(self, r, c, nLeft): # nLeft - no. of houses left to place.
        # base case
        if nLeft == 0:
            # call bfs function
            return self.bfs()

        # recursive case
        for i in range(r,self.H):
            for j in range(c,self.W):
                self.grid[i][j] = 0

                val = 0

                if j < self.W:
                    val = self.backtracking(i, j+1, nLeft-1)
                else:
                    val = self.backtracking(i+1, 0, nLeft-1)

                self.minOfAllMax = min(self.minOfAllMax, val)
                self.grid[i][j] = -1

        return self.minOfAllMax


sol = Solution()

print(sol.findMinDist(4, 4, 3))
