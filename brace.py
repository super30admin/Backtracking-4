# Time Complexity: O(n * m * k)
# Space Complexity: O(k)
def expand(s):
    result = []
    i = 0
    def backtrack(temp, i):
        if i == len(s):
            result.append("".join(temp))
            return
        if s[i] == "{":
            j = s.find("}", i)
            options = s[i + 1:j].split(",")
            for option in options:
                temp.append(option)
                backtrack(temp, j + 1)
                temp.pop()
        else:
            temp.append(s[i])
            backtrack(temp, i + 1)
            temp.pop()

    backtrack([], 0)
    return sorted(result)
