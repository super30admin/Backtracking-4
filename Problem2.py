"""
time : exponential
space : o(n) number of blocks
"""
class Solution:
    def backtrack(self, blocks, idx, s):
        if idx == len(blocks):
            self.res.append(s)
            return
        
        for i in blocks[idx]: #for all elements in a block
            #action
            s = s + i
            #recurse
            self.backtrack(blocks, idx + 1, s) #take one element from current block, move to next block
            #backtrack
            s = s[:-1]
            
            
    def expand(self, S: str) -> List[str]:
        
        if not S:
            return []
        self.res = []
        #create blocks
        i = 0
        blocks = []
        while i < len(S):
            block =  []
            
            if S[i] == '{':
                i += 1
                while S[i] != '}':
                    if S[i] != ',':
                        block.append(S[i]) 
                    i += 1
                
            else:
                block.append(S[i])
            i += 1
            blocks.append(block)
        #backtrack on blocks    
        self.backtrack(blocks, 0, "")
        
        return sorted(self.res)
        
        
        
        