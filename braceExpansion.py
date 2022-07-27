#time compleexity: K power n/k (k is number of elements in braces)
#Space complexity: O(kn)
class Solution:
    def expand(self, s: str) -> List[str]:
        self.res=[]
        lst=[]
        i=0
        while i<len(s):
            if s[i]=='{':
                i+=1
                lst1=[]
                while i<len(s) and s[i]!='}':
                    if s[i]!=',':
                        lst1.append(s[i])
                    i+=1
                lst1.sort()
                lst.append(lst1)
                i+=1
            if i<len(s) and s[i].isalpha():
                lst.append(s[i])
                i+=1
        print(lst)
        self.dfs(lst,0,[])
        return self.res
    
    def dfs(self,lst,idx,path):
        if idx==len(lst):
            self.res.append("".join(path))
            return
        
        for i in range(len(lst[idx])):
            path.append(lst[idx][i])
            self.dfs(lst,idx+1,path)
            path.pop()
        
        