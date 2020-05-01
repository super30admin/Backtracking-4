# Time Complexity : O(nk^n/k)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def expand(self, S: str) -> List[str]:
        
        options = []
        cursor = 0
        while cursor < len(S):
            
            option = []
            if S[cursor] == '{':
                cursor +=1
                while S[cursor] != '}':
                    if S[cursor]!=',':
                        option.append(S[cursor])
                    cursor +=1
            else:
                option.append(S[cursor])
            cursor +=1
            options.append(option)
            
        #backtrack
        solution = []
        self.backtrack(options, 0, "", solution)
        result = sorted(solution)
        return result
        
    def backtrack(self, options, index, sb, solution):
        
        #isValidState
        if index == len(options):
            solution.append(sb)
            return
               
        #backtrack
        for c in options[index]:
            sb +=c
            self.backtrack(options, index+1, sb, solution)
            sb = sb[:-1]
            
        
        
        
                
                
                
        