class Solution:
    def expand(self, S: str) -> List[str]:
        S = S.replace("{", " ").replace("}"," ").strip().split(" ")
        s = []
        
        for i in S:
            temp = []
            for j in i.split(","):
                if j!=",":
                    temp.append(j)
            s.append(temp)

        
        output = []
        def backtrack(curr, index):

            if index == len(s):
                output.append("".join(curr[:]))
            else:
                print(index, s[index])
                for i, value in enumerate(s[index]):
                    curr.append(value)
                    backtrack(curr, index+1)
                    curr.pop()
        
        backtrack([],0)
        return sorted(output)
Time: Assuming the length of the input in m and th elength of each block is n, N^(m/n)
Space: O(length of each * number)
