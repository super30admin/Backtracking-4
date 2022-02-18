#k^n, k block length. n list length, 
# space N
import copy
class Solution:
    def __init__(self):
        self.res=[]
    def expand(self, s: str) -> List[str]:
        li=[]
        i=0
        while i<len(s):
            li.append([])
            if s[i]=='{':
                i+=1
                while s[i]!='}':
                    if s[i].isalpha():
                        li[-1].append(s[i])
                    i+=1
            else:
                li[-1].append(s[i])
            i+=1
            
        self.dfs(li,0,"")
        return sorted(self.res)
    def dfs(self,li,idx,path):
        print(idx,len(li))
        #base
        if idx==len(li):
            temp=copy.copy(path)
            self.res.append(path)
            return
            
        #logic
        for i in li[idx]:
            path=path+i
            self.dfs(li,idx+1,path)
            path=path[:len(path)-1]
            
        
                    
                    
            
            
            
            
        