# The find_min_distance method finds the maximum distance from empty land to the nearest building.

# BFS Approach:
# - Initialize a queue with all building locations.
# - Perform BFS to expand from buildings and update distances in the grid.
# - Track the maximum distance reached.

# TC: O(h * w) - Each cell is visited once.
# SC: O(h * w) - Space for the visited grid and queue.


def find_min_distance(h, w, n):
    def bfs(grid):
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        visited = [[False for _ in range(w)] for _ in range(h)]
        queue = deque()

        # Add all building locations to the queue and mark them as visited
        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    queue.append((i, j))
                    visited[i][j] = True

        max_distance = 0

        while queue:
            x, y = queue.popleft()
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < h and 0 <= ny < w and not visited[nx][ny]:
                    visited[nx][ny] = True
                    grid[nx][ny] = grid[x][y] + 1
                    max_distance = max(max_distance, grid[nx][ny])
                    queue.append((nx, ny))

        return max_distance
