# Time: Exponential
# Space: Exponential
class Solution(object):
    def __init__(self):
        self.ans = 1000000000007
    def helper(self, grid, height, width, buildings, row, col):
        if buildings == 0:
            self.bfs(grid, height, width)
            return
        if col == width:
            col = 0
            row += 1
        for i in range(row, height):
            for j in range(col, width):
                grid[i][j] = 0
                self.helper(grid, height, width, buildings-1, row, col+1)
                grid[i][j] = -1
            col = 0
    def find_min_distance(self, height, width, buildings):
        grid = [[-1 for i in range(height)] for j in range(width)]
        self.helper(grid, height, width, buildings, 0, 0)
        return self.ans
    def bfs(self, grid, height, width):
        visited = [[False for i in range(height)] for j in range(width)]
        rows = []
        cols = []
        dirs = [[0,1], [0, -1], [1,0], [-1,0]]
        for i in range(0, height):
            for j in range(0, width):
                if grid[i][j] == 0:
                    rows.append(i)
                    cols.append(j)
                    visited[i][j] = True
        distance = 0
        while len(rows) != 0:
            size = len(rows)
            for k in range(size):
                curr_row = rows.pop(0)
                curr_col = cols.pop(0)
                for direction in dirs:
                    mod_row = curr_row + direction[0]
                    mod_col = curr_col + direction[1]
                    if mod_row >=0 and mod_row< height and mod_col >=0 and mod_col < width and visited[mod_row][mod_col] == False:
                        rows.append(mod_row)
                        cols.append(mod_col)
                        visited[mod_row][mod_col] = True
            distance += 1
        self.ans = min(self.ans, distance-1)
solution = Solution()
solution.find_min_distance(4,4,3)
    
    
    
