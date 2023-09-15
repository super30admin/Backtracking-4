# Time Complexity : O(n)
# Space Complexity :O(n)
# Passed on Leetcode: yes

def maxDistance(grid, n):
    def isPossible(distance):
        maxDist = -1
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 0:
                    minDist = float('inf')
                    for r in range(len(grid)):
                        for c in range(len(grid[0])):
                            if grid[r][c] == 1:
                                dist = abs(r - i) + abs(c - j)
                                minDist = min(minDist, dist)
                    maxDist = max(maxDist, minDist)
        return maxDist <= distance

    left, right = 0, len(grid) + len(grid[0])
    while left < right:
        mid = (left + right) // 2
        if isPossible(mid):
            right = mid
        else:
            left = mid + 1

    return left
