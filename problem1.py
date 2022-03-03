import sys


class Solution:

    def __init__(self):
        self._min = sys.maxsize

    def findDistance(self, h, w, n):
        grid = [[-1] * w for i in range(h)]

        self.helper(grid, 0, 0, n, w, h)

        return self._min

    def bfs(self, grid, h, w):
        visited = [[False] * w for i in range(h)]
        dirs = [[0, 1], [0, -1], [-1, 0], [1, 0]]
        queue = []

        for i in range(0, h):
            for j in range(0, w):
                if grid[i][j] == 0:
                    queue.append([i, j])
                    visited[i][j] = True

        dist = 0
        while queue:
            size = len(queue)
            for i in range(0, size):
                curr = queue.pop(0)
                for dir in dirs:
                    nr = curr[0] + dir[0]
                    nc = curr[1] + dir[1]
                    if nr >= 0 and nc >= 0 and nr < h and nc < w and visited[nr][nc] != True:
                        queue.append([nr, nc])
                        visited[nr][nc] = True

            dist += 1
        self._min = min(self._min, dist - 1)

    def helper(self, grid, r, c, n, w, h):

        # base case
        if n == 0:
            self.bfs(grid, h, w)
            return

        if c == w:
            c = 0
            r += 1

        # logic
        for i in range(r, h):
            for j in range(c, w):
                grid[i][j] = 0
                self.helper(grid, i, j + 1, n - 1, w, h)
                grid[i][j] = -1


sol = Solution()
print(sol.findDistance(4, 4, 2))
