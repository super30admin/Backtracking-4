# Time: O(k^n/k)
# Space: O(k^n/k)
# Did it run on Leetcode: yes

def backtrack(groups, idx, sb, result):
    if idx == len(groups):
        result.append(sb.toString())
        return
    group = groups[idx]
    for i in range(len(group)):
        c = group[i]
        sb.append(c)
        backtrack(groups, idx + 1, sb, result)
        sb.deleteCharAt(len(sb) - 1)

def braceExpansion(s):
    result = []
    groups = []
    i = 0
    while i < len(s):
        c = s[i]
        group = []
        if c == '{':
            i += 1
            while s[i] != '}':
                if s[i] != ',':
                    group.append(s[i])
                i += 1
            i += 1
        else:
            group.append(c)
            i += 1
        group.sort()
        groups.append(group)
    
    sb = StringBuilder()
    backtrack(groups, 0, sb, result)

    return result

# Example usage
s = "{a,b}c{d,e}f"
result = braceExpansion(s)
print(result)
