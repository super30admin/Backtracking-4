#time:Exponential
#space:max brace groups
class Solution:
    def expand(self, s: str) -> List[str]:
        #is string is empty
        if not s:
            return []
        
        #convert string into blocks so that it is easy to iterate
        string_converter = []
        
        #use a while loop to iterate
        i=0
        n=len(s)
        while(i<n):
            if s[i]!="{":
                temp_list = []
                temp_list.append(s[i])
                string_converter.append(temp_list)
                i+=1
            else:
                i+=1
                temp_list = []
                while(i<n and s[i]!="}"):
                    if s[i]!=",":
                        temp_list.append(s[i])
                    i+=1
                i+=1
                string_converter.append(sorted(temp_list))
        
        
        n_blocks = len(string_converter)
        
        #function to generate all the combinations
        #global answer
        ans = []
        
        def backtrack(index, curr_list, n_blocks,string_converter):
            if len(curr_list)==n_blocks:
                ans.append("".join(curr_list[:]))
                return
            for i in range(len(string_converter[index])):
                curr_list.append(string_converter[index][i])
                backtrack(index+1, curr_list, n_blocks,string_converter)
                curr_list.pop()
        backtrack(0, [], n_blocks,string_converter)
        return ans