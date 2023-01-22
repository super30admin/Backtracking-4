class Solution:
    def expand(self, s: str) -> List[str]:

        ## T.C = O(2^n)
        ## S.C = O(N)

        blocks = []
        result = []
        i = 0

        while i<len(s):
            tmp = []
            if s[i] == '{':
                i += 1
                
                while s[i] != '}':
                    if s[i] != ',':
                        tmp.append(s[i])
                    i += 1
            else:
                tmp.append(s[i])
            
            tmp.sort()
            blocks.append(tmp)
            i += 1
        
        #print(blocks)

        def recurse(idx, path):
            ## base case
            if len(path) == len(blocks):
                string = ''.join(path)
                result.append(string)
                return

            ## logic
            block = blocks[idx]
            for i in range(len(block)):
                ## action
                path.append(block[i])

                ## recurse
                recurse(idx + 1, path)

                ## backtrack
                path.pop()

        recurse(0, [])
        return result




