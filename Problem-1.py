# Time Complexity : O((w*h)^n * (w*h))  # Try all ways to place n buildings and compute distances for each cell
# Space Complexity : O(w*h)             # Grid storage and BFS queue
# Did this code successfully run on Leetcode : No (custom problem)
# Any problem you faced while coding this : No

# Explanation:
# We try all combinations of placing n buildings in the grid.
# For each placement, we run multi-source BFS from all buildings to compute the minimum distance to every cell.
# The placement with the minimum possible maximum distance among all cells is returned.

from collections import deque
from itertools import combinations

class Solution:
    def optimalBuildingPlacement(self, w: int, h: int, n: int) -> int:
        cells = [(i, j) for i in range(h) for j in range(w)]
        ans = float('inf')

        for comb in combinations(cells, n):
            dist = [[-1]*w for _ in range(h)]
            q = deque()

            for r, c in comb:
                q.append((r, c))
                dist[r][c] = 0

            directions = [(1,0),(-1,0),(0,1),(0,-1)]

            while q:
                r, c = q.popleft()
                for dr, dc in directions:
                    nr, nc = r+dr, c+dc
                    if 0<=nr<h and 0<=nc<w and dist[nr][nc] == -1:
                        dist[nr][nc] = dist[r][c] + 1
                        q.append((nr,nc))

            maxd = max(max(row) for row in dist)
            ans = min(ans, maxd)

        return ans