#Time Complexity : O(k^(n/k)), n -> Length of string; k -> Average Length of each block
# Space Complexity : O(m*k), m -> Number of strings that can be formed, k -> Average Length of each block

class Solution:
    def expand(self, s: str) -> List[str]:
        if not s or len(s)==0:
            return []
        self.result = []
        blocks = []
        i = 0
        while(i < len(s)):
            b = []
            if s[i] == '{':
                i+=1
                while(s[i]!='}'):
                    if s[i]!=',':
                        b.append(s[i])
                    i+=1
                
            else:
                b.append(s[i])
            blocks.append(b)
            i+=1
        self.backtrack(blocks,0,'')
        self.result.sort()
        return self.result
    
    
    def backtrack(self,blocks,index,stringb):
        #base
        if index==len(blocks):
            self.result.append(stringb)
            return
        #logic
        b = blocks[index]
        for i in range(0,len(b)):
            stringb+=b[i]
            self.backtrack(blocks,index+1,stringb)
            stringb = stringb[:-1]
                