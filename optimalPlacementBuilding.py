# Time Complexity: O(m*n)
# Space Complexity: O(m*n)
from collections import deque
class Solution:

    def __init__(self):
        self.minDistance = float('inf')

    def placeBuilding(self, h, w, k):

        grid = [[-1 for i in range(w)] for j in range(h)]

        self.backtrack(grid, 0, 0, h, w, k)
        return self.minDistance

    def backtrack(self, grid, r, c, h, w, k):

        if k == 0:
            self.bfs(grid, h, w)
            return
        if c >= w:
            c = 0
            r += 1

        for i in range(r, h):
            for j in range(c, w):
                grid[i][j] = 0
                self.backtrack(grid, i, j + 1, h, w, k - 1)
                grid[i][j] = -1
            c = 0

    def bfs(self, grid, h, w):
        q = deque()
        visited = [[False for i in range(w)] for j in range(h)]

        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    q.append((i, j))
                    visited[i][j] = True

        dist = 0
        direc = [[-1, 0], [0, -1], [1, 0], [0, 1]]

        while q:
            size = len(q)

            for k in range(size):
                curr = q.popleft()

                for d in direc:
                    r = d[0] + curr[0]
                    c = d[1] + curr[1]

                    if 0 <= r < h and 0 <= c < w and not visited[r][c]:
                        q.append((r, c))
                        visited[r][c] = True

            dist += 1

        self.minDistance = min(self.minDistance, dist - 1)


trial = Solution()
print(trial.placeBuilding(3, 2, 1))


