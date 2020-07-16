// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
we used backtracking in this problem.
we split the input into a list of lists for easy traversal.
we start from index 0 pick a character then go to index 1 pick a character and concatenate them.we do this till we reach the leaf node.when leaf node is reached then we add the concatenated string to the out array.we use backtracking to generate all the combinations.


# Time complexity --> o(k**(n/k)) where k is the number of blocks and n is the total number of elements
# space complexity --> o(product of the number of eleements in each block)
class Solution(object):
    def __init__(self):
        self.out=[]
    def dfs(self,result,index,str1):
        if index==len(result):
            self.out.append(str1)
            return
        val1=result[index]
        for j in val1:
            #adding a character to the string 
            str1=str1+j
            self.dfs(result,index+1,str1)
            #removing the last character in the string
            str1=str1[:-1]
        
    def expand(self, S):
        """
        :type S: str
        :rtype: List[str]
        """
        result=[]
        if S==None or len(S)==0:
            return []
        i=0
        #converting {a,b}cz{d,e}f into [[a,b],[c],[z],[d,e],[f]]
        while i<len(S):
            val=[]
            if S[i]=='{':
                while S[i]!='}':
                    if S[i]!=',' and S[i]!='{':
                        val.append(S[i])
                    i=i+1
            if len(val)==0:
                val=[S[i]]
            result.append(val)
            i=i+1
        self.dfs(result,0,'')
        self.out.sort()
        return self.out
        
            
            
            
                    
                    
                
        