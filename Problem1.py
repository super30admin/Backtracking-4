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
    result.sort()  
    return result