# // Time Complexity :2^N/K 
# // Space Complexity : o(N)
# // Did this code successfully run on Leetcode : Not tested on Leet code as the problem was locked
# // Any problem you faced while coding this : No



class Solution:
    global H
    global W
    global grid

    # def __init__(self):
    

    def findMinDist(self, height, width, n):
        self.H = height
        self.W = width
        self.mini = float("inf")

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
                r = curr[0] + dir[0]
                c = curr[1] + dir[1]

                if r < 0 or r >= self.H or c < 0 or c >= self.W or self.grid[r][c] <= self.grid[curr[0]][curr[1]] + 1:
                    continue
                queue.append([r,c])
                self.grid[r][c] = self.grid[curr[0]][curr[1]] + 1

        maxgrid = float("-inf")
        # calculate max in grid
        for i in range(self.H):
            for j in range(self.W):
                maxgrid = max(maxgrid,self.grid[i][j])
        return maxgrid


    def backtracking(self, r, c, n): # no. of houses left to place.
        # base case
        if n == 0:
            # call bfs function
            return self.bfs()

        # recursive case
        for i in range(r,self.H):
            for j in range(c,self.W):
                self.grid[i][j] = 0

                val = 0

                if j < self.W:
                    val = self.backtracking(i, j+1, n-1)
                else:
                    val = self.backtracking(i+1, 0, n-1)

                self.mini = min(self.mini, val)
                self.grid[i][j] = -1

        return self.mini


sol = Solution()

print(sol.findMinDist(4, 4, 3))