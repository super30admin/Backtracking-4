# Time Complexity: O(w * h * n)
# Space Complexity: O(w * h)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

def Solution(object):
    def calculate_distance(x1, y1, x2, y2):
        return (abs(x1 - x2) + abs(y1 - y2))

    def min_distance_to_building(grid):
        max_distance = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 0:
                    min_dist = float('inf')
                    for x in range(len(grid)):
                        for y in range(len(grid[0])):
                            if grid[x][y] == 1:
                                dist = calculate_distance(i, j, x, y)
                                min_dist = min(min_dist, dist)
                    max_distance = max(max_distance, min_dist)
        return max_distance

    def optimal_building_placement(w, h, n):
        grid = [[0 for _ in range(w)] for _ in range(h)]
        grid[h // 2][w // 2] = 1
        n -= 1
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        curr_dir_idx = 0
        curr_x, curr_y = (h // 2), (w // 2)
        steps = 1
        while (n > 0):
            for _ in range(2):
                dx, dy = directions[curr_dir_idx]
                for _ in range(steps):
                    curr_x += dx
                    curr_y += dy
                    if ((0 <= curr_x < h) and (0 <= curr_y < w) and (grid[curr_x][curr_y] == 0)):
                        grid[curr_x][curr_y] = 1
                        n -= 1
                        if n == 0:
                            break
                curr_dir_idx = ((curr_dir_idx + 1) % 4)
            steps += 1
        max_distance = min_distance_to_building(grid)
        return max_distance