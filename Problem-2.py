#Approach
#convert the given string into to listlistof charcter where string in {} has ths to choose one.
# Explore all the list ain strings and back track and find the final string



#COmplexities
#Time : O(n*m)n = len(list) and m : avglen(list in list)
#space: O(N)




class Solution:
    def expand(self, nums: str) ->list[str] :
        self.result =[]
        listMap =[]
        maps =[]
        i=0
        while i <len(nums):
            if nums[i]=="{":
                i+=1
                while nums[i]!="}":
                    if nums[i]!=",":
                        maps.append(nums[i])
                    i+=1
                i+=1
                maps.sort()
                listMap.append(maps.copy())
                maps.clear()
            else:
                maps.append(nums[i])
                listMap.append(maps.copy())
                maps.clear()
                i+=1

        self.dfs(listMap,0,[])
        print(self.result)
        return self.result

    def dfs(self,arr,i,result):

        if i == len(arr):
            if len(result)>0:
                self.result.append("".join(result))
            return

        for char in arr[i]:
            result.append(char)
            self.dfs(arr,i+1,result)
            result.pop()









s= Solution()
s.expand("{a,b}{c,d}{e,f}")
s.expand("a{c,b}")
s.expand("abcd")

