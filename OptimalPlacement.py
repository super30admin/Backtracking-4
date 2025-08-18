# Time Complexity:
# O(C(H*W, N) * H*W) (Combinatorial placements of N buildings times BFS for each grid)

# Space Complexity:
# O(H*W) for the BFS queue and visited matrix.

from collections import deque

class BuildingPlacement:

    def __init__(self):
        self.result = float('inf')
        self.H = 0
        self.W = 0

    def findMinDistance(self, h, w, n):
        self.H = h
        self.W = w
        grid = [[-1 for _ in range(w)] for _ in range(h)]
        self.helper(grid, 0, n)
        return self.result

    def helper(self, grid, pivot, n):
        if n == 0:
            self.bfs(grid)
            return
        for i in range(pivot, self.H * self.W):
            r = i // self.W
            c = i % self.W
            grid[r][c] = 0
            self.helper(grid, i + 1, n - 1)
            grid[r][c] = -1

    def bfs(self, grid):
        m = len(grid)
        n = len(grid[0])
        dirs = [[0,1], [0,-1], [1,0], [-1,0]]
        visited = [[False]*self.W for _ in range(self.H)]
        q = deque()
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    q.append((i, j))
                    visited[i][j] = True
        dist = 0
        while q:
            size = len(q)
            for _ in range(size):
                curr = q.popleft()
                for d in dirs:
                    r = d[0] + curr[0]
                    c = d[1] + curr[1]
                    if 0 <= r < self.H and 0 <= c < self.W and grid[r][c] == -1 and not visited[r][c]:
                        q.append((r, c))
                        visited[r][c] = True
            dist += 1
        self.result = min(self.result, dist - 1)
