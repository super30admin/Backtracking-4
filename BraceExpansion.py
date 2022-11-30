#1087. Brace Expansion
"""
Time: O(V + E) 
Space: O(V)
"""
class Solution:
    def expand(self, S):
        graph = collections.defaultdict(list)
        prev_char, capture, pos = "", False, 0
        for char in S:
            if char == "{":
                chars, capture = [], True
            elif char == "}":
                chars.sort()
                graph[pos] += (chars)
                pos += 1
                prev_chars = "".join(chars)
                capture = False
            if capture: 
                if char not in "{},":
                    chars.append(char)
            elif char not in "{},":
                graph[pos].append(char)
                prev_char = char
                pos += 1

        self.res = []
        self.dfs("", graph, 0)
        return self.res

    def dfs(self, path, graph, idx):
        if len(path) == len(graph):
            self.res.append(path)
            return 
        for char in graph[idx]:
            self.dfs(path + char, graph, idx + 1)
