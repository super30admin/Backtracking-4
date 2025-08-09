"""
Time Complexity: O(k^(N/k))
Space Complexity: O(k^n)
"""
class Solution:
    def expand(self, s):
        i = 0
        expandedWords = [""]
        
        while i < len(s):
            group = []
            if s[i] == '{':
                i += 1
                while s[i] != '}':
                    if s[i] != ',':
                        group.append(s[i])
                    i += 1
                i += 1
            else:
                group.append(s[i])
                i += 1

            group.sort()
            currWords = []
            for word in expandedWords:
                for c in group:
                    currWords.append(word + c)
            expandedWords = currWords

        return expandedWords