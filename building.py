# Time Complexity: O(log(max(w, h)))
# Space Complexity: O(1)

def maxDistance(w, h, n):
    def check(k):
        return ((w + k) // (k + 1) - 1) * ((h + k) // (k + 1) - 1) >= n

    left, right = 0, max(w, h)
    while left < right:
        mid = (left + right) // 2
        if check(mid):
            right = mid
        else:
            left = mid + 1
    return left
