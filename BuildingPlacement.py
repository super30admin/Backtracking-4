'''
Solution:
1.  First generate all possible grids with n number of 0s using Backtracking.
2.  For each configuration of the grid, calculate the max distance in that configuration, then compare with global min
    distance.
3.  Finally, return the global min distance

Time Complexity:    O(exponential)
Space Complexity:   O(gridSize * n) #   recursive stack space times the grids

--- Passed custom testcases
'''


from collections import deque

class BuildingPlacement:

    def __init__(self):

        #   initializations
        self.minDistance = float('inf')
        self.dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]

    def getMinDistance(self):
        return self.minDistance

    def __isValid(self, cr, cc, H, W):

        #   check for cell validity
        if (cr >= 0 and cr < H and cc >= 0 and cc < W):
            return True
        return False

    def __bfs(self, grid, H, W):

        #   BFS for getting the maximum distance and compare it with minimum distance seen so far
        queue = deque([])
        visited = [[False for w in range(W)] for h in range(H)]

        #   append all cells with value = 0
        for cr in range(H):
            for cc in range(W):
                if grid[cr][cc] == 0:
                    visited[cr][cc] = True
                    queue.append([cr, cc])

        #   initilaize the distance
        distance = 0

        #   iterate until queue is empty
        while (len(queue) > 0):
            levelCount = len(queue)

            #   for all cells in a level
            for i in range(levelCount):

                #   get current cell and move in all 4 valid directions
                current = queue.popleft()
                for direction in self.dirs:
                    nr = current[0] + direction[0]
                    nc = current[1] + direction[1]

                    #   check if valid and not visited, append to the queue
                    if (self.__isValid(nr, nc, H, W) and not visited[nr][nc]):
                        visited[nr][nc] = True
                        queue.append([nr, nc])

            #   increment the distance after each level
            distance += 1

        #   compare with global min distance
        self.minDistance = min(self.minDistance, distance - 1)

    def __backtrackGrid(self, grid, sr, sc, H, W, n):

        #   base case for backtracking
        if (n == 0):
            # for row in grid:
            #     print(row)
            self.__bfs(grid, H, W)
            return

        #   from starting row to end, and from starting col to end
        for row in range(sr, H):
            for col in range(sc, W):

                #   mark it as 0, perform recursion and then backtrack
                grid[row][col] = 0
                self.__backtrackGrid(grid, row, col + 1, H, W, n-1)
                grid[row][col] = -1

            #   now make starting col to 0 after the first row to cover all valid cells
            sc = 0

    def computeMinDistance(self, H, W, n):

        #   initialize and call backtracking
        grid = [[-1 for w in range(W)] for h in range(H)]

        self.__backtrackGrid(grid, 0, 0, H, W, n)

if __name__ == '__main__':
    bp = BuildingPlacement()
    bp.computeMinDistance(4, 4, 3)
    minDistance = bp.getMinDistance()
    print(minDistance)