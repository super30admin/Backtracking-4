class Solution:
    def expand(self, s: str) -> List[str]:
        #Approach: Backtracking
        #Time Complexity: O(n)
        #Space Complexity: O(n * l)
        #where, n is the number of possible words, and l is the average length of a word
        
        self.result = []
        self.backtrack(s, [], 0)
        return self.result
    
    def backtrack(self, s, sb, idx):
        #base
        if idx == len(s):
            self.result.append(''.join(sb))
            return
        
        #logic
        choices = []
        if s[idx].isalpha():
            choices.append(s[idx])
            
        else:   #s[idx] == '{'
            while s[idx] != '}':
                if s[idx].isalpha():
                    choices.append(s[idx])
                idx += 1
            choices.sort()
            
        for choice in choices:
            sb.append(choice)                       # action
            self.backtrack(s, sb, idx + 1)          # recursion
            sb.pop()                                # backtracking