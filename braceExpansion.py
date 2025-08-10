"""
Traverse the expression character by character — if {} is encountered, treat it as a choice list
Use DFS recursion to try each option and build the final string
Collect results in a set or list, then sort them at the end
"""
"""
Time Complexity: O(k^n)	n = number of groups, k = max options
Space Complexity: O(k^n) - For result storage and recursion depth
"""

class braceExpansion:
    def expand(self, s: str) -> list[str]:
        res = []

        def dfs(index, path):
            if index == len(s):
                res.append("".join(path))
                return

            if s[index] == '{':
                j = index
                while s[j] != '}':
                    j += 1
                options = sorted(s[index+1:j].split(','))
                for char in options:
                    dfs(j+1, path + [char])
            else:
                dfs(index + 1, path + [s[index]])

        dfs(0, [])
        return res

if __name__ == "__main__":
    obj = braceExpansion()
    print(obj.expand("{a,b}c{d,e}f")) 
    print(obj.expand("abcd"))             
    print(obj.expand("{a,b}{z,x,y}")) 
