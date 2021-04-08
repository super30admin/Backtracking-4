#Time Complexity:O(n^(m/n))
#Space Complexity:O(n)
class Solution:
    def expand(self, s: str) -> List[str]:
        if not s:
            return []
        self.result=[]                                      #a list to store the final result
        blocks=[]                                   
        i=0
        while i<len(s):                                     #covert the groups within the string into list of lists
            c=s[i]
            block=[]
            if c=='{':                                      #groups within brackets are one list
                i+=1
                while s[i]!='}':
                    if s[i]!=',':
                        block.append(s[i])
                    i+=1
            else:
                block.append(s[i])                          #single characters are a list
            blocks.append(block)
            i+=1
        self.backtrack(blocks,0,'')                         #use backtracking to find string combinations and fill the result
        self.result.sort()                                  #sort the list alphabetically before returning
        return self.result
    
    def backtrack(self,blocks:List[List[str]],idx:int,string:str)->None:
        if idx==len(blocks):                                #if the index is same as length of blocks , return after appending the string into result
            self.result.append(string)
            return
        block=blocks[idx]                                   #get the list at the idxth location in blocks
        for i in range(len(block)):                         #for each character in the block
            string+=block[i]                                #add the char to current string
            self.backtrack(blocks,idx+1,string)             #call the backtrack function with next block to be considered
            string=string[:-1]                              #backtrack by removing the last element in the array