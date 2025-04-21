"""
TC - O((w.hn)(wh))
SC - O(w . h)
"""

def find_min_distance(w, h, n):
    min_distance = [sys.maxsize]

    def bfs(building_positions):
        visited = [[-1] * w for _ in range(h)]
        q = deque()

        for r, c in building_positions:
            q.append((r, c))
            visited[r][c] = 0

        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        max_dist = 0
        while q:
            r, c = q.popleft()
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                if 0 <= nr < h and 0 <= nc < w and visited[nr][nc] == -1:
                    visited[nr][nc] = visited[r][c] + 1
                    max_dist = max(max_dist, visited[nr][nc])
                    q.append((nr, nc))

        return max_dist

    def backtrack(start, buildings):
        if len(buildings) == n:
            dist = bfs(buildings)
            min_distance[0] = min(min_distance[0], dist)
            return

        for i in range(start, w * h):
            r, c = divmod(i, w)
            buildings.append((r, c))
            backtrack(i + 1, buildings)
            buildings.pop()

    backtrack(0, [])
    return min_distance[0]
