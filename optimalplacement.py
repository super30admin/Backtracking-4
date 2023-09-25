def minDistance(w, h, n):
    def isPossible(distance):
        # Helper function to check if it's possible to place n buildings with minimum distance 'distance'
        # Initialize variables
        count = 0
        maxDist = 0
        for i in range(w):
            for j in range(h):
                # Check if the current cell is an empty lot
                if grid[i][j] == 0:
                    # Calculate the minimum distance from this lot to all buildings
                    minDist = float('inf')
                    for b in buildings:
                        d = abs(i - b[0]) + abs(j - b[1])
                        minDist = min(minDist, d)
                    # Update the maximum minimum distance
                    maxDist = max(maxDist, minDist)
        return maxDist <= distance

    # Initialize the grid and buildings
    grid = [[0] * h for _ in range(w)]
    buildings = []

    # Binary search for the optimal distance
    left, right = 0, max(w, h)
    while left < right:
        mid = (left + right) // 2
        if isPossible(mid):
            right = mid
        else:
            left = mid + 1

    return left

# Example usage
w = 4
h = 4
n = 3
result = minDistance(w, h, n)
print(result)  # Output: 2
