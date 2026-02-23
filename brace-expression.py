'''
Time Complexity: O(M^K) where M is the number of groups, where each group has K options, and each individual character is a group of length 1 
Space Complexity: Recursive Stack O(K), Output = O(T*k) where T is the number of valid outputs and each output has k characters
'''

class Solution:
    def expand(self, s: str) -> List[str]:
        output = []

        def build_character_array(string: str):
            i = 0
            res = []
            while i < len(string):
                if s[i] == "{":
                    i += 1
                    arr = []
                    while s[i] != "}":
                        if s[i] != ",":
                            arr.append(s[i])
                        i += 1
                    res.append(arr)
                    i += 1
                else:
                    res.append(s[i])
                    i+=1
            return res

        character_array = build_character_array(s)

        def solve(index, string_arr):
            if index >= len(character_array):
                output.append("".join(string_arr))
                return

            options = character_array[index]

            for option in options:
                string_arr.append(option)
                solve(index + 1, string_arr)
                string_arr.pop()

        solve(0, [])

        return sorted(output)
