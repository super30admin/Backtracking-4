"""
Time Complexity : O(hw c n) 
Space Complexity : O(hw)- for the matrix
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
To me, this problem seems a combination of 2 problems. The N queens problem , where we determine the correct placement and the
01 matrix problem where we find the minimum  distance for the nearest 0. Here also, we make a grid of given height and width
and pass it to a backtrack function to find the correct placement of buildings. To do that, we try all the combinations, and for 
every combination, we perform bfs for finding out the minimum distance from the farthest parking lot. 
"""
from collections import deque


class Solution:

    def __init__(self):
        self.minDistance = float('inf')
        self.dirs = [(-1, 0), (1, 0), (0, 1), (0, -1)]

    def optimalPlacement(self, h, w, n):
        grid = [[-1 for i in range(w)] for j in range(h)]
        self.backtrack(grid, 0, 0, h, w, n)
        return self.minDistance

    def backtrack(self, grid, r, c, h, w, n):
        if n == 0:
            self.bfs(grid, h, w)
            return

        if c == w:
            r += 1
            c = 0

        for i in range(r, h):
            for j in range(c, w):
                # action
                grid[i][j] = 0
                # recurse
                self.backtrack(grid, r, c+1, h, w, n-1)
                # backtrack
                grid[i][j] = -1
            c = 0

    def bfs(self, grid, h, w):
        q = deque([])
        visited = [[False for i in range(w)] for j in range(h)]
        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    q.append([i, j])
                    visited[i][j] = True
        distance = 0
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                for d in self.dirs:
                    r = curr[0]+d[0]
                    c = curr[1]+d[1]
                    if r >= 0 and c >= 0 and r < h and c < w and not visited[r][c]:
                        q.append([r, c])
                        visited[r][c] = True
            distance += 1
        self.minDistance = min(self.minDistance, distance-1)


def main():
    s = Solution()
    print(s.optimalPlacement(4, 4, 2))


if __name__ == '__main__':
    main()
