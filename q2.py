# Time Complexity : O(k^N), Where N is number of elemnts in array, K is average number of elements in the array
# Space Complexity : O(n), Where n is number of elements in the string s
# Did this code successfully run on Leetcode : Yes 
# Any problem you faced while coding this : Nothing specific

from typing import List

class Solution:
    def __init__(self):
        self.result=[]
    def solution(self,index,array,path):
        #Base Case
        if(index==len(array)):
            self.result.append(path)
            return
        #Actual Logic
        for i in array[index]:
            path=path+i
            self.solution(index+1,array,path)
            path=path[:len(path)-1]
        
    def expand(self, s: str) -> List[str]:
        i=0
        n=len(s)
        array=[]#This array will store the array version of string s
        
        while(i<n):
            if(s[i]=='{'):
                temp=[]
                i+=1#increment i to next index
                #iterate until we encounter "}"
                while(s[i]!='}'):
                    if(s[i]==','):
                        i+=1
                    else:
                        temp.append(s[i])    
                        i+=1
            else:
                temp=[]
                temp.append(s[i])
            temp.sort()
            array.append(temp)
            i+=1
        self.solution(0,array,"")
        return self.result
        
            
            
        