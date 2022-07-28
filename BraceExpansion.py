#Time Complexity: O(k**n/k)
#Space Complexity: O(k**n/k)
class Solution:
    def expand(self, s: str) -> List[str]:
        
        n = len(s)
        i = 0
        blocks = []
        while i<n:
            if s[i] == '{':
                block = []
                i = i+1
                while i<n and s[i]!='}':
                    if s[i] != ',':
                        block.append(s[i])
                    i += 1
            if s[i] == '}':
                block.sort()
                blocks.append(block)
                i += 1
                
            else:
                blocks.append([s[i]])
                i += 1
        self.res = []
        self.helper(blocks,0,[])
        return self.res
    
    def helper(self,blocks,idx,path):
        
        if idx == len(blocks):
            string = "".join(path)
            self.res.append(string)
            return
        
        for i in range(len(blocks[idx])):
            path.append(blocks[idx][i])
            self.helper(blocks,idx+1,path)
            path.pop()
            