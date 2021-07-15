class Solution:
    def expand(self, s: str) -> List[str]:
        if s is None or len(s) == 0: return None
        blocks = []
        indexarray = 0
        self.result = []
        # making the individual blocks
        while (indexarray < len(s)):
            block = []
            if s[indexarray] == "{":
                indexarray += 1
                while s[indexarray] != "}":
                    if s[indexarray] != ",":
                        block.append(s[indexarray])
                    indexarray += 1
            else:
                block.append(s[indexarray])
            blocks.append(block)
            indexarray += 1

        self.backtrack(blocks, 0, '')
        self.result.sort()
        return self.result

    def backtrack(self, blocks, idx, string):
        # base
        if idx == len(blocks):
            self.result.append(string)
            return
        # action
        block = blocks[idx]
        for index in range(len(block)):
            string += block[index]
            self.backtrack(blocks, idx + 1, string)
            string = string[:-1]



#Time Complexity:O(n^(m/n))
#Space Complexity:O(n)