from typing import List

class Solution:
    def backtrack(self,idx,curstr):
        # base
        if idx == len(self.blocks):
            self.res.append("".join(curstr))
            return
        # logic
        li = self.blocks[idx]
        for ch in li:
            # action
            curstr.append(ch)
            #recurse
            self.backtrack(idx+1,curstr)
            # backtrack
            curstr.pop()
        
    def expand(self, s: str) -> List[str]:
        if s is None or len(s) == 0:
            return []
        n = len(s)
        # step 1 preprocessing
        self.blocks = []
        i = 0
        while i < len(s):
            c = s[i]
            block = []
            if c == '{':
                i += 1
                while s[i] != "}":
                    if s[i] != ",":
                        block.append(s[i])
                    i += 1
            else:
                block.append(s[i])
                block.sort()
            self.blocks.append(block)
            i += 1
        
        # step 2
        self.res= []
        self.backtrack(0,[])
        return self.res
        
        
            
            
sol = Solution()
print(sol.expand("{a,b}c{d,e}f"))