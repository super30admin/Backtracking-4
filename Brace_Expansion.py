# Time: O(n)
# Space: O(l)


class Solution:
    def expand(self, s: str) -> List[str]:
        if not s or len(s) == 0:
            return []
        self.result = []
        self.backtrack(s, [], 0)
        return self.result
    
    def backtrack(self, s, sb, idx):
        # base case
        if idx == len(s):
            self.result.append(''.join(sb))
            return 
        
        # logic 
        choices = []
        if s[idx].isalpha():
            choices.append(s[idx])
        else:
            while s[idx] != '}':
                if s[idx].isalpha():
                    choices.append(s[idx])
                idx += 1
            choices.sort()
        #print(choices)
        for choice in choices:
            sb.append(choice)                       # action
            # print(sb)
            self.backtrack(s, sb, idx + 1)          # recursion
            sb.pop()                                # backtracking
        
