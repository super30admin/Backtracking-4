from itertools import combinations
from collections import deque


def minMaxDistance(w, h, n):
    # Get all possible building placements
    all_cells = [(i, j) for i in range(h) for j in range(w)]
    min_farthest = float("inf")

    # Try all ways to place `n` buildings
    for buildings in combinations(all_cells, n):
        # Run BFS from all building positions
        queue = deque(buildings)
        dist = [[-1] * w for _ in range(h)]

        # Initialize BFS queue
        for x, y in queue:
            dist[x][y] = 0

        max_distance = 0
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        # BFS to calculate shortest distances
        while queue:
            x, y = queue.popleft()
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < h and 0 <= ny < w and dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    max_distance = max(max_distance, dist[nx][ny])
                    queue.append((nx, ny))

        # Update the minimum of the worst-case distances
        min_farthest = min(min_farthest, max_distance)

    return min_farthest
