"""
Backtracking problem 
seperate the input on blocks and try diff combinations and that are the result and backtrack

{a,b}c{d,e}f =[a,b][c][d,e][f]

Example 1:

Input: "{a,b}c{d,e}f"

Output: ["acdf","acef","bcdf","bcef"]

Example 2:

Input: "abcd"

Output: ["abcd"]

Note:

1 <= S.length <= 50

TC=O(n^(m/n)) where n is avg len in a block and m is avg len of all such blocks
SC=(n*m)

"""

class Solution:
    def expand(self, S: str) -> List[str]:
        if not S:
            return []
        blocks= []
        self.res = []
        i = 0
        res = [""]
        while i < len(S):
            block = []
            if S[i] == '{':
                i += 1
                while S[i] != "}": #iterate to get alphabets after { till we reach }
                    if S[i] != ',': #ignore ','' 
                        block.append(S[i])
                    i += 1
            else:#if its alphabets
                block.append(S[i])
            blocks.append(block)
            i += 1
        self.backtrack(blocks, 0, [])
        self.res.sort()
        print(self.res)
        return self.res

    def backtrack(self, blocks, idx, arr):
        #base
        if idx == len(blocks):
            self.res.append(''.join(arr))
            return
        
        #logic
        block = blocks[idx]
        for i in range(len(block)):
            arr.append(block[i])
            self.backtrack(blocks, idx+1, arr)
            arr.pop()

s=Solution()
s.expand("{a,b}c{d,e}f")