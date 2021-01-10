"""
Asked frequently in Mathworks
combination of 2 problems N queens to place the building in correct place -Backtracking
0-1 mATRIX minimum nearest zero -BFS/DFS approach . we use BFS here 

TC= o(hwcn)
SC=O(hw)
"""

from collections import deque

class Solution:

    def __init__(self):
        self.minDist = float('inf')
        self.dirs = [(-1, 0), (1, 0), (0, 1), (0, -1)]
    #h- height w-wiedth and n- no of building
    def optimalPlacement(self, h, w, n):
        grid = [[-1 for i in range(w)] for _ in range(h)]
        self.backtrack(grid, 0, 0, h, w, n)
        return self.minDist

    def backtrack(self, grid, r, c, h, w, n):
        #base
        if n == 0:
            self.bfs(grid, h, w)
            return
        
        #logic
        if c == w: #if col becomes last column reset to next row and 0th col
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
            c = 0 #when it moves to next row , start from first col

    def bfs(self, grid, h, w):
        q = deque([])
        visited = [[False for _ in range(w)] for x in range(h)]
        for i in range(h):
            for j in range(w):
                if grid[i][j] == 0:
                    q.append([i, j])
                    visited[i][j] = True
        dist = 0
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                for d in self.dirs:
                    nr = curr[0]+d[0]
                    nc = curr[1]+d[1]
                    if nr >= 0 and nc >= 0 and nr < h and nc < w and not visited[nr][nc]:
                        q.append([nr, nc])
                        visited[nr][nc] = True
            dist += 1
        self.minDist = min(self.minDist, dist-1) #after last level dist variable would have gone one step ahead so dist-1


def main():
    s = Solution()
    print(s.optimalPlacement(3, 2, 1))#2


if __name__ == '__main__':
    main()