# // Time Complexity :2^N/K 
# // Space Complexity : o(N)
# // Did this code successfully run on Leetcode : Not tested on Leet code as the problem was locked
# // Any problem you faced while coding this : No


# // Your code here along with comments explaining your approach

class Solution:

    def expand(self,S):

        self.result = []

        block = []

        i = 0

        while(i<len(S)):
            temp = []
            if(S[i]== '{'): # Checking for open brackets
                i+=1
                while(S[i]!='}'): # iterating till closed brackets are encountered
                    if(S[i]!=','):
                        temp.append(S[i]) # adding all the characters to the list
                        i+=1
            else:
                temp.apped(S[i])
                i+=1
            block.append(temp) # adding temp list to the block list
        
        self.backtrack(block,0,[]) # calling the backtrack function to get all possible sequence

        self.result = self.result.sort() # sorting the final resut in lexographical order

        return self.result

    def backtrack(self, block, index,list1):

        #Base case:
        
        if(index==len(block)):
            self.result.append(list1)
            return

        for i in block[index]:
            #Action
            list1.append(i)

            #recurse
            self.backtrack(block, index+1, list1)

            #backtrack

            list1.pop()

Output = Solution() 
print(Output.expand(S = "{a,b,c}d{e,f}"))




            

                    


