class Solution:
    def __init__(self):
        self.H = 0
        self.W = 0

    def bfs(self, grid, buildings):
        visited = [[-1] * self.W for _ in range(self.H)]
        queue = deque(buildings)
        max_distance = 0
        directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]

        for x, y in buildings:
            visited[x][y] = 0

        while queue:
            x, y = queue.popleft()
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < self.H and 0 <= ny < self.W and visited[nx][ny] == -1:
                    visited[nx][ny] = visited[x][y] + 1
                    max_distance = max(max_distance, visited[nx][ny])
                    queue.append((nx, ny))
        return max_distance

    def find_min_distance(self, h, w, n):
        self.H = h
        self.W = w
        min_distance = float("inf")
        cells = [(i, j) for i in range(h) for j in range(w)]

        for buildings in combinations(cells, n):
            min_distance = min(
                min_distance, self.bfs([[0] * w for _ in range(h)], buildings)
            )
        return min_distance


if __name__ == "__main__":
    building_placement = Solution()
    print(building_placement.find_min_distance(5, 4, 3))
