# Time:- O(2^n/2)
# Approach:- Put all the charachters seperated by {} in an array and then all the other charachters in 
# their seperate arrays and then we can backtrack on all the arrays picking one charachter from each 
# local array and then constructing a string from them. 
class Solution:
    def expand(self, S: str) -> List[str]:
        final_stack=[]
        i=0
        while(i<len(S)):
            # put charachter seperated by {} in a seperate local array 
            if S[i]=='{':
                i+=1
                local_stack=[]
                while(S[i]!='}'):
                    if S[i].isalpha():
                        local_stack.append(S[i])
                    i+=1
                final_stack.append(local_stack)
            put all the charachters which are not enclosed within {} in a seperate array
            elif S[i].isalpha():
                final_stack.append([S[i]])
            i+=1
            
        def backtrack(final_stack,index,res):
            # when we have reached the last index we have a possible result
            if index==len(final_stack):
                self.final_res.append("".join(res))
                return
            # pick a charachter from each array and move on to the next array
            for i in final_stack[index]:
                res.append(i)
                backtrack(final_stack,index+1,res)
                res.pop()
        
        self.final_res=[]
        backtrack(final_stack,0,[])
        return sorted(self.final_res)