"""
Generate all possible combinations of n building placements (choose n out of w*h positions)
For each placement: Run multi-source BFS starting from all building positions, Track the maximum distance among all empty cells
Keep the minimum of all these maximum distances as the final result
"""


from itertools import combinations
from collections import deque

class buildingInGrid:
    def __init__(self):
        self.dirs = [(-1,0), (1,0), (0,-1), (0,1)]

    def bfs(self, grid, buildings, h, w):
        visited = [[False]*w for _ in range(h)]
        q = deque()
        for r, c in buildings:
            q.append((r, c, 0))
            visited[r][c] = True

        max_dist = 0
        while q:
            r, c, dist = q.popleft()
            max_dist = max(max_dist, dist)
            for dr, dc in self.dirs:
                nr, nc = r + dr, c + dc
                if 0 <= nr < h and 0 <= nc < w and not visited[nr][nc]:
                    visited[nr][nc] = True
                    q.append((nr, nc, dist + 1))
        return max_dist

    def minDistance(self, h, w, n):
        all_cells = [(i, j) for i in range(h) for j in range(w)]
        min_max_dist = float('inf')

        for building_locs in combinations(all_cells, n):
            grid = [[-1]*w for _ in range(h)]
            for r, c in building_locs:
                grid[r][c] = 0  # building

            max_dist = self.bfs(grid, building_locs, h, w)
            min_max_dist = min(min_max_dist, max_dist)

        return min_max_dist
    
if __name__ == "__main__":
    obj = buildingInGrid()
    print(obj.minDistance(4, 4, 3))  # Output: 2
