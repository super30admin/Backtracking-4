class Solution:
    def expand(self, s: str) -> List[str]:
        #Tc: O(2^N)  Sc: O(N)
        def helper(s, idx, current, result):
            if idx == len(s):
                result.append(current)
                return
            if s[idx] == '{':
                end = s.find('}', idx)
                options = s[idx+1:end].split(',')
                for option in options:
                    helper(s, end+1, current+option, result)
            else:
                helper(s, idx+1, current+s[idx], result)

        result = []
        helper(s, 0, '', result)
        return sorted(result)


        