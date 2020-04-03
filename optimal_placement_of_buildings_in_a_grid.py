from itertools import combinations
from collections import deque


class Solution:
    def find_min_distance(self, height, width, n):
        arr = []
        for r in range(height):
            for c in range(width):
                arr.append((r, c, 0))

        ans = float("inf")
        for points in combinations(arr, n):
            q = deque()
            visited = set()
            for m, n, dist in points:
                q.append((m, n, dist))
                visited.add((m, n))
            distAns = 0
            while q:
                r, c, dist = q.popleft()
                distAns = max(dist, distAns)
                for x, y in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
                    if 0 <= x < height and 0 <= y < width and (x, y) not in visited:
                        q.append((x, y, dist + 1))
                        visited.add((x, y))
            ans = min(distAns, ans)

        return ans


if __name__ == '__main__':
    print(Solution().find_min_distance(4, 4, 3))
    print(Solution().find_min_distance(5, 1, 1))
