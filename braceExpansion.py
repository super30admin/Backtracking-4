class BE:
    def __init__(self, s):
        self.s = s
        
    def expand(self):
        res = []
        def helper(i, S):
            #base
            if i==len(self.s):
                res.append("".join(S))
                return
            #logic
            if self.s[i]=='{':
                st=[]
                while self.s[i]!='}':
                    if self.s[i].isalpha():
                        st.append(self.s[i])
                    i+=1
                i+=1
                while st:
                    #action
                    S.append(st.pop())
                    #recurse
                    helper(i, S)
                    #backtrack
                    S.pop()
            
            else:
                #action
                S.append(self.s[i])
                #recurse
                helper(i+1, S)
                #backtrack
                S.pop()
        
        helper(0, [])
        return res

s = "{a,b}c{d,e}"
obj = BE(s)
print(obj.expand())
            
        
        