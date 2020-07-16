'''
Solution:
1.  Iterate once from start till end maintaining one more iteration till a chunk is added..
2.  Once we get a chunk, modify the final result so that everything till the current chunk has a correct solution
3.  Return the final updated result array.

Time Complexity:    O(exponential)
Space Complexity:   O(C * l) #  C is avg. number of chunks and l is avg. size of current list (for temp list that forms)
--- Passed all testcases successfully on Leetcode 
'''

class BraceExpansion:

    def __updateFinalList(self, finalList, currentList):

        #   append each char of current list to each string of already existing list
        tempList = []
        for eachStr in finalList:
            for eachChar in currentList:
                tempList.append(eachStr + eachChar)

        return tempList

    def expand(self, S: str) -> List[str]:

        #   initialize final result with list of empty string
        finalList = ['']
        cursor = 0

        #   iterate till the cursor reaches the end index
        while (cursor < len(S)):

            #   one inside while loop covers one chunk, add it to this current list
            currentList = []

            #   case 1 when { is encountered
            if (S[cursor] == '{'):
                cursor += 1

                #   iterate until } is found and append it to current list
                while (S[cursor] != '}'):
                    if (S[cursor] != ','):
                        currentList.append(S[cursor])
                    cursor += 1

            #   case 2 when { not found, simply append the character to current list
            else:
                currentList.append(S[cursor])

            #   update final list using this helper function
            finalList = self.__updateFinalList(finalList, currentList)
            cursor += 1

        #   sort and return the final result array according to lexicographical order
        finalList.sort()
        return finalList