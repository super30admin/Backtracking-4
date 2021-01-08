"""
Time Complexity : O(n^m) - such that total number of blocks is m and average length of a block is n 
Space Complexity : O(m*n)- array
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
Here, we first make a list of lists out of the given string and then perform normla backtracking on it to get results.
"""


class Solution:
    def expand(self, S: str) -> List[str]:
        if not S:
            return []
        mainList = []
        self.result = []
        i = 0
        l = len(S)
        res = [""]
        # A = S.replace('{', ' ').replace('}', ' ').strip().split(' ')
        while i < l:
            tempArr = []
            if S[i] == '{':
                i += 1
                while S[i] != "}":
                    if S[i] != ',':
                        tempArr.append(S[i])
                    i += 1
            else:
                tempArr.append(S[i])
            mainList.append(tempArr)
            i += 1
        self.backtrack(mainList, 0, [])
        self.result.sort()
        return self.result

    def backtrack(self, mainList, index, arr):
        if index == len(mainList):
            self.result.append(''.join(arr))
            return

        block = mainList[index]
        for i in range(len(block)):
            arr.append(block[i])
            self.backtrack(mainList, index+1, arr)
            arr.pop()
