# Time Complexity: O(k^(n/k)) - where k is size of each block, n/k will be avg number of blocks
# Space Complexity: O(n) where n is number of blocks - recursive stack space

class Solution:
    def expand(self, s: str) -> List[str]:
        self.result = []
        if s == None or len(s) == 0:
            return self.result
        
        i = 0
        blocks = []
        while i < len(s):
            block = []
            if s[i] == '{':
                i += 1
                while s[i] != '}':
                    if s[i] != ',':
                        block.append(s[i])
                    i += 1
                i += 1
            else:
                block.append(s[i])
                i += 1
            blocks.append(block)
        self.backtrack(blocks, 0, [])
        self.result.sort()
        return self.result
        
    def backtrack(self, blocks, idx, path):
        if idx == len(blocks):
            self.result.append(''.join(path.copy()))
            return
        
        block = blocks[idx]
        for i in range(len(block)):
            path.append(block[i])
            self.backtrack(blocks, idx + 1, path)
            path.pop()
        
        
