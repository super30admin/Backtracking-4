class Solution:
    def expand(self, s: str) -> List[str]:
        # Time Complexity: O(n^m/n)
        # Space Complexity: O()
        st = []
        b = False
        s_list = []
        for i in range(0,len(s)):
            if(s[i]=="{"):
                b = True
                st.append(s[i])
            elif(s[i]=="}"):
                x = []
                while(len(st)>0 and st[-1]!="{"):
                    x.append(st.pop())
                x.sort()
                s_list.append(x)
                b = False
            elif(s[i]==","):
                continue
            else:
                if(b==False):
                    s_list.append([s[i]])
                else:
                    st.append(s[i])
        
        ans = []
        def backtracking(path, i):
            if(i==len(s_list)):
                ans.append("".join(path))
                return
            for k in range(0,len(s_list[i])):
                path.append(s_list[i][k])
                backtracking(path, i+1)
                path.pop()
        # backtracking
        backtracking([],0)
        return ans
