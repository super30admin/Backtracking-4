# Time: O(k**n/k)
# Space: O(n)
class Solution(object):
    def __init__(self):
        self.ans = []
    def expand(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        self.helper('', s)
        self.ans.sort()
        return self.ans
    def helper(self, curr, s):
        #print(curr, s)
        val = ''
        i = 0
        while i < len(s) and s[i] != '{':
            val += s[i]
            i += 1
        curr = curr+val
        if i == len(s):
            self.ans.append(curr)
        temp = []
        while i < len(s) and s[i] != '}':
            if s[i] != ',' and s[i] != '{':
                temp.append(s[i])
            i += 1
        for ch in temp:
            self.helper(curr+ch, s[i+1:])
                
        
            
