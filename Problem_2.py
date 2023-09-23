# Time Complexity: O(2^n)
# Space Complexity: O(2^n)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
  def expand(self, s):
    ans = []
    
    def dfs(i, path):
      if i == len(s):
        ans.append(''.join(path))
        return
      if s[i] == '{':
        nextRightBraceIndex = s.find('}', i)
        for c in s[(i + 1):nextRightBraceIndex].split(','):
          path.append(c)
          dfs((nextRightBraceIndex + 1), path)
          path.pop()
      else:
        path.append(s[i])
        dfs((i + 1), path)
        path.pop()
    dfs(0, [])
    return sorted(ans)