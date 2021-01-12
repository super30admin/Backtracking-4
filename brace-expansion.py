# TIME COMPLEXITY: O(n^m) where m is the average length of every block and n is the number of blocks
# SPACE COMPLEXITY: O(n*m)
class Solution(object):
    def expand(self, S):
        """
        :type S: str
        :rtype: List[str]
        """
        # PART 1: Preprocess the input
        # Every group in curly braces forms a single block
        # Every character outside braces is put in it's own individual block
        # EG: {a,b}cxy{d,e}f -> [[a, b], [c], [x], [y], [d, e], [f]]
        blocks = []
        i = 0
        while i < len(S):
            if S[i] == '{':
                i += 1
                block = []
                while i < len(S) and S[i] != '}':
                    if S[i] != ',':
                        block.append(S[i])
                    i += 1
                blocks.append(block)
            else:
                block = []
                block.append(S[i])
                blocks.append(block)
            i += 1

        # PART 2: Backtrack

        # Initialize list to hold result
        result = []

        # Backtracking Function
        def backtrack(index, slate):
            # Base Case
            # If the index is at the last block, we have picked one character from every block
            if index == len(blocks):
                result.append("".join(slate))
                return
            # We pick a character from every block in blocks and append it to our slate
            # get the current block pointed by index
            curr_block = blocks[index]
            for c in curr_block:
                # Append one character from every block to the slate
                slate.append(c)
                # Call the backtrack function with the index to the next block
                backtrack(index + 1, slate)
                # Clean/Undo last operation
                slate.pop()

        backtrack(0, [])

        # The result needs to be lexicographically sorted
        return sorted(result)
