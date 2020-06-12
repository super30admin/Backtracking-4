"""
// Time Complexity : O(2^n)
// Space Complexity : O(maxDepth)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
Algorithm explanation
Given below
"""
class Solution:
    def expand(self, S: str) -> List[str]:
        """
        Idea - divide the input into two parts - a) characters outside the chars b) string with braces
        - For (a), we just concatenate the character to the result_string and recurse with next index pointer with backtracking
        - For (b),
            - Process the characters(split by ',') between {} and recurse on each character to get a pairing of all combinations with backtracking
        - finally return the  sorted result
        
        Time - (2^n)
        Space - O(maxdepth)
        """
        
        def helper(i,prev_string):
            #Time - O(2^n), Space - O(maxdepth)
            if i == len(S):
                #forming the result from last updated string
                result.append(prev_string)
                return
            if S[i] == '{':
                right_curly_pt = i + 1
                #finding the right brace
                while S[right_curly_pt] != '}':
                    right_curly_pt += 1
                
                #processing the chars between the braces and backtracking
                for ch in (S[i+1:right_curly_pt].split(",")):
                    helper(right_curly_pt + 1, prev_string + ch)

            else:
                #processing the chars outside the braces and backtracking
                helper(i+1, prev_string+S[i])
    
        result = []
        helper(0,"")
        return sorted(result)