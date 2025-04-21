"""
Approach --
1.  Iterate once from start till end maintaining one more iteration till a chunk is added..
2.  Once we get a chunk, modify the final result so that everything till the current chunk has a correct solution
3.  Return the final updated result array.

TC - O(k^n) ==> exponential
For sorting O(nk log k) where k is the avg size of 1 block
For backtracking O(k^n)
Since, O(k^n) is the dominant TC

SC - O(C * l) #  C is avg. number of chunks and l is avg. size of block list (for temp list that forms)
"""


class Solution:
    def updatertnData(self, rtnData, block):
        templist = []

        for eachStr in rtnData:
            for eachChar in block:
                templist.append(eachStr + eachChar)
        return templist

    def expand(self, s: str) -> List[str]:
        if s is None or len(s) == 0: return []

        rtnData = ['']

        i = 0

        # step 1: convert given string to list of lists
        while i < len(s):
            c = s[i]
            # print(s[i])
            block = []
            if c == '{':
                i += 1
                while s[i] != '}':
                    if s[i] != ',':
                        block.append(s[i])
                    i += 1
            else:
                block.append(c)
            # sort at block itself, so you dont need to sort the entire final rtnData list
            block.sort()
            # print("block: ", block)

            rtnData = self.updatertnData(rtnData, block)
            i += 1
            # print(rtnData)

        return rtnData

        # def dfs(i, path):
        #     nonlocal sub_string, res

        #     # action
        #     if i == len(sub_string):
        #         res += [path]
        #         return

        #     # recur over ch inside the sub array inside sub_string
        #     for ch in sub_string[i]:
        #         dfs(i + 1, path + ch)

        # n = len(s)

        # # 1. sub segment string into arrays
        # sub_string = []
        # i = 0

        # while i < n:
        #     # if seeing '{' keep sliding and add all numbers till reaching '}'
        #     if s[i] == '{':
        #         sub = []
        #         i += 1
        #         while s[i] != '}':
        #             # update temp if we see a charter
        #             if s[i] != ',':
        #                 sub += [s[i]]
        #             i += 1

        #         sub_string += [sub]

        #     # otherwise keep adding s[i]
        #     else:
        #         sub_string += [s[i]]

        #     i += 1

        # res = []

        # # 2. run dfs on sub_string array
        # dfs(0, '')

        # # 3. sort lexicographically
        # res = sorted(res)

        # return res
