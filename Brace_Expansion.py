# Time Complexity : O(m^m) , the value of 'm' represents the maximum number of options within a single brace
# Space Complexity : O(m^m)
def expand(S):
    def backtrack(idx, curr):
        if idx == n:
            result.append(curr)
            return
        if S[idx] == "{":
            j = idx + 1
            while S[j] != "}":
                j += 1
            for option in S[idx+1:j].split(","):
                backtrack(j+1, curr+option)
        else:
            backtrack(idx+1, curr+S[idx])

    n = len(S)
    result = []
    backtrack(0, "")
    result.sort()  # Sort the result lexicographically
    return result

# Example usage
S = "{a,b}c{d,e}f"
print(expand(S))
