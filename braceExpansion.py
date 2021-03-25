# Time Complexity : expontential
# Space Complexity : O(L) L = number of letters
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach


class Solution:
    def expand(self, s: str) -> List[str]:
        if not s:
            return []
        
        blocks = []
        #need to process s
        i = 0
        while i < len(s):
            c = s[i]
            if c == '{':
                j = i+1
                block = []
                while j < len(s):
                    if s[j] == '}':
                        #j += 1
                        break
                    elif s[j] == ',':
                        j += 1
                        continue
                    else:
                        block.append(s[j])
                    j += 1
                blocks.append(block)
                i = j     
            else:
                blocks.append([s[i]])
            i += 1
        
        res = []
        self.backtrack(blocks, 0, [], res)
        answer = ['' for i in range(len(res))]
        for i in range(len(res)):
            answer[i] = res[i]
        answer.sort()
        return answer
        
    def backtrack(self, blocks, index, curr, res):
        if index == len(blocks):
            res.append((''.join(curr)))
            return res
        block = blocks[index]
        for i in range(len(block)):
            curr.append(block[i])
            self.backtrack(blocks, index+1, curr, res)
            curr.pop()