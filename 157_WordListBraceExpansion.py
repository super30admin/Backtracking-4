'''
Not tested on leetcode
time - exponencial
space - O(N), queue space.
'''
class Solution:
    def __init__(self):
        self.result = []

    def expand(self, S):
        queue = []
        startIndex = 0

        while S.find('{',startIndex) != -1:

            openBracket = S.index('{',startIndex)
            closeBracket = S.index('}',startIndex)

            if openBracket != startIndex: # add elements to queue which are before opening bracket.
                queue.append([S[startIndex:openBracket]]) # 'abc'

            queue.append(S[openBracket+1:closeBracket].split(",")) # [d,e]

            startIndex = closeBracket + 1

        # startIndex to end case( add any elements after the closing bracket to queue)
        if startIndex != len(S):
            queue.append([S[startIndex:len(S)]]) # 'fg'

        # Backtracking - for getting all possible combinations.
        self.backtracking(queue,"")
        return self.result


    def backtracking(self, queue, curr):
        # base case
        if len(queue) == 0:
            self.result.append(curr)
            return


        # recursive case
        front = queue.pop(0)

        for letter in front:
            # create a copy of original queue, pass by reference.
            tempQueue = queue[:]
            self.backtracking(tempQueue, curr + letter) # abc + d



sol = Solution()
print(sol.expand("abc{d,e}f"))
