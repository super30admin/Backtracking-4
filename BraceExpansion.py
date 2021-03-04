class Solution:
    def expand(self, s: str) -> List[str]:
        
        self.result = []
        blocks = []
        
        i=0
        
        while(i<len(s)):
            currBlock = []
            
            if (s[i]=='{'):
                i+=1
                
                while(i<len(s) and s[i] != '}'):
                    if s[i] != ',':
                        currBlock.append(s[i])
                    i+=1
            else:
                currBlock.append(s[i])
                
            blocks.append(currBlock)
            i+=1
        
        self.backtrack(blocks,0,"")
        
        return sorted(self.result)
    
    def backtrack(self,blocks,index,sb):
        
        if index==len(blocks):
            self.result.append(sb)
            return
        
        for ch in blocks[index]:
            # Make the choice
            sb=sb+ch
            
            # backtrack
            self.backtrack(blocks,index+1,sb)
            
            # Undo the selection
            sb=sb[:len(sb)-1]
        