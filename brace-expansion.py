#Time Complexity : O(n!)
#Space Complexity : O(n) 
# Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No


def backtrack(str_list, i, s, res):
    #base
    if i == len(str_list):
        comp_s = s
        res.append(comp_s)
        return
    #logic
    for c in str_list[i]:
        #action
        s += c
        #recurse
        backtrack(str_list, i+1, s, res)
        #backtrack
        s = s[:-1]
    

class Solution:
    def expand(self, s: str) -> List[str]:
        res = []
        i = 0
        str_list = []
        while i<len(s):
            li = []
            if s[i] == '{':
                i += 1
                while s[i] != '}':
                    if s[i] != ',':
                        li.append(s[i])
                    i += 1
            else:
                li.append(s[i])
            i+=1
            li.sort()
            str_list.append(li)
        
        backtrack(str_list, 0, '', res)
        return res
        
        