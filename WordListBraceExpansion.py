# Time Complexity : O(m^n) where m is the m is the number of blocks and n is the length of the block 
# Space Complexity :O(2n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach

class Solution:
    result = None
    def expand(self, s):
        if len(s) == 0:
            return []
        self.result = []
        i = 0 
        words = []
       
       # O(n)
        while i < len(s):
            temp = []
            if s[i] == '{':
                i+=1 
                while s[i] != '}':
                    if s[i] != ',':
                        temp.append(s[i])
                    i += 1  
                words.append(temp)
            else:
                temp.append(s[i])
                words.append(temp)
            i+=1
        
        self.backtrack(words, 0, [])
        return self.result 
    
    def backtrack(self, words, indx, sb):
        # base 
        if indx == len(words):
            self.result.append(''.join(sb))
            return 
        #logic 
        for c in words[indx]:
            sb.append(c) 
            self.backtrack(words, indx+1, sb)
            sb.pop()
        
        return
                    

if __name__ =="__main__":
    s = Solution()
    # Input= "{a,b}c{d,e}f"
    # Output= ["acdf","acef","bcdf","bcef"]
    # res = s.expand(Input)
    # print(res)
    # assert res == Output
    
    input = 'abcd'
    output = ['abcd']
    res = s.expand(input)
    print(res)
    res == output
    