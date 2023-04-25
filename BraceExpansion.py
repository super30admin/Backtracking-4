
#Could not be tested on leetcode as premium problem

class Solution:
    

    #Here firstly we create blocks of chars within these {} brackets. Then we perform dfs using backtracking to 
    #create diff combinations of strings. EX: [a,b], [c], [d,e] -> 
    #  root 
    #  / \
    # a   b  (a or b choice)
    # |  |  
    # c  c    (only c is the choice)
    # /\ /\
    #d e d e  (d or e choice for both bracnches)
    #Time complexity - O(k^(n/k)) - where n-length of s, k-avg length of each block, n/k- number of blocks. 
    #In backtarcking for n/k blocks each of them have k choices to select from.
    #Space complexity - O(n) - blocks are stored as list of lists
    def braceExpansion(self, s):
        
        blocks = [] #hold block of chars i.e. each block has choices of chars
        i = 0
        res = []
        while i<len(s):
            block = []
            if s[i]=="{":
                i+=1
                while s[i]!="}":
                    if s[i]==",":
                        i+=1
                        continue
                    block.append(s[i])
                    i+=1
            else:
                block.append(s[i])
            #sort each block here so that final result is in lexicographical order
            block.sort()
            blocks.append(block)
            i+=1
                
        def backtrack(blocks, idx, path):
            if idx==len(blocks):
                res.append(path)
                return
            
            cur = blocks[idx]
            for i in range(len(cur)):
                #action
                path = path + cur[i]
                #recurse
                backtrack(blocks, idx+1, path)
                #backtrack
                path = path[:-1]
            
        print(blocks)
        backtrack(blocks, 0, '')
        print(res)
        
        return res
            
            
s = Solution()
result = s.braceExpansion('{a,b,c}d{e,f}')
#print(result)