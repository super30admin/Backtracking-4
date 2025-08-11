class OptimalBuilding:
    def __init__(self):
        self.min = float("inf")
        
    def bfs(self, grid):
        q = collections.deque()

        visited = set()
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == True:
                    q.append(i)
                    q.append(j)
                    visited.add((i, j))

        directions = [[1, 0], [0,1], [-1, 0], [0, -1]]
        maxDist = -1
        while q:
            qSize = len(q)
            for l in range(qSize//2):
                i = q.popleft()
                j = q.popleft()
                for x, y in directions:
                    newX, newY = x + i, y + j
                    if newX < 0 or newY < 0 or newX >= len(grid) or newY >= len(grid[0]) or (newX, newY) in visited:
                        continue
                    visited.add((newX, newY))
                    q.append(newX)
                    q.append(newY)
            maxDist += 1
        return maxDist

    def dfs(self, i, j, grid, n): #placement of buildings
        if n == 0:
            maxDist = self.bfs(grid)
            self.min = min(self.min, maxDist)
            return

        if i < 0 or j < 0 or i >= len(grid) or j >= len(grid[0]):
            return 

        for x in range(i, len(grid)):
            for y in range(len(grid[0])):
                if x == i and y < j: #start right after where we placed the last building
                    continue
                grid[x][y] = True
                if y == len(grid[0]) - 1:
                    self.dfs(x + 1, 0, grid, n - 1)
                else:
                    self.dfs(x, y + 1, grid, n - 1)
                grid[x][y] = False

    def main(self):
        w=4
        h=4
        n=3
        grid = [[False for i in range(w)] for j in range(h)]
        self.dfs(0,0, grid, n)
if __name__ == "__main__":
    obj = OptimalBuilding()
    obj.main()
    print(obj.min)
