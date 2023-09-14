from collections import deque

def optimalBuildingPlacement(w, h, n):
    grid = [[float('inf')] * w for _ in range(h)]  
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]  

    def bfs(start_row, start_col):
        queue = deque([(start_row, start_col)])
        distance = [[float('inf')] * w for _ in range(h)]
        distance[start_row][start_col] = 0

        while queue:
            row, col = queue.popleft()

            for dr, dc in directions:
                new_row, new_col = row + dr, col + dc

                if 0 <= new_row < h and 0 <= new_col < w and distance[new_row][new_col] == float('inf'):
                    distance[new_row][new_col] = distance[row][col] + 1
                    queue.append((new_row, new_col))

        return distance

    for row in range(h):
        for col in range(w):
            if grid[row][col] == float('inf'):
                distance = bfs(row, col)
                grid[row][col] = max(distance[r][c] for r in range(h) for c in range(w))

   
    min_distance = min(grid[r][c] for r in range(h) for c in range(w))

    return min_distance