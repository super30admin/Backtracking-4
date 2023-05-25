class Solution:
    # Time O(n**2)
    # Space O(h) h is the len of recursion stack
    def expand(self, s: str) -> List[str]:
        result = []
        blocks = []
        i = 0
        block = []
        while i < len(s):
            if s[i] == '{':
                i+=1
                while s[i] != '}':
                    if s[i] != ',':
                        block.append(s[i])
                    i+=1
            else:
                block.append(s[i])
            i+=1
            block.sort()
            blocks.append(block)
            block = []
                
        print(blocks)
        # Recursion and backtracking starts
        def backtrack(s, idx):
            # Base Case
            if len(s) == len(blocks):
                result.append(s)
                return
            # Logic
            for i in range(len(blocks[idx])):
                prev = s
                s += blocks[idx][i]
                # Recurse
                backtrack(s, idx+1)
                # Backtrack
                s = prev
        backtrack('',0)
        return result
