'''
1087 Brace Expansion
https://leetcode.com/problems/brace-expansion/description/

You are given a string s representing a list of words. Each letter in the word has one or more options.
    If there is one option, the letter is represented as is.
    If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].

Return all words that can be formed in this manner, sorted in lexicographical order.

Example 1:
Input: s = "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]

Example 2:
Input: s = "abcd"
Output: ["abcd"]

Constraints:
1 <= s.length <= 50
s consists of curly brackets '{}', commas ',', and lowercase English letters.
s is guaranteed to be a valid input.
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.

Solution
1. DFS w/ Backtracking:
We format the string into character groups stored in sublists. Each sublist is sorted individually.
eg.  {a,b}c{d,e}f -> [ [a,b], [c], [d,e], [f] ] (there are 4 char groups: [a,b], [c], [d,e], [f])

Then we use DFS w/ backtracking to build all possible strings by picking one character from each group. When we finish all groups, we add the built string to the final result list.

Time: O(k^n), or O(k^(N/k)), Space: O(n + k^n)
n is the number of groups, k is the max group size and N is the total length of the string (hence, n = N/k)

https://youtu.be/c7PW52OPg2M?t=3454

'''
def expand(s):
    def str2blocks(s, blocks):
        """
        Parses the input string into blocks and appends them to the blocks list in place.
        Side effect: Mutates the blocks parameter by appending parsed groups.
        Example: "{a,b}c{d,e}f" -> [['a', 'b'], ['c'], ['d', 'e'], ['f']]
        Example: "abcd" -> [['abcd']]
        """
        i = 0
        while i < N:
            c = s[i]
            curr_group = []
            if c == '{':
                i += 1
                while s[i] != '}':
                    if s[i] == ',':
                        i += 1
                        continue
                    else:
                        curr_group.append(s[i])
                        i += 1
                i += 1
            else:
                while s[i] != '{':
                    curr_group.append(s[i])
                    i += 1
                    if i >= N: break
                curr_group = ["".join(curr_group)]

            blocks.append(curr_group)

    def recursion(blocks, i, path):
        if i == B:
            result.append(path)
            return

        strings_in_block = blocks[i]
        for s in strings_in_block:
            recursion(blocks, i+1, path + s)
        return


    if not s:
        return ""
    N = len(s)
    blocks = [] # list of lists
    result = []

    # convert string into blocks (list of lists)
    # "{a,b}c{d,e}f" -> [['a', 'b'], ['c'], ['d', 'e'], ['f']]
    str2blocks(s, blocks)

    B = len(blocks)
    recursion(blocks, 0, "")
    return sorted(result)


def run_tests():
    tests = [("{a,b}c{d,e}f", ['acdf', 'acef', 'bcdf', 'bcef']),
             ("{a,b}{z,x,y}", ["ax","ay","az","bx","by","bz"]),
             ("abcd", ['abcd']),
             ("{a,b}", ['a', 'b']),
    ]
    for test in tests:
        s, ans = test[0], test[1]
        result = expand(s)
        print(f"\ns = {s}")
        print(f"result = {result}")
        passed = (ans == result)
        assert passed == True, f"Test case failed"
        print(f"Pass: {passed}")
    print(f"\nDONE")

run_tests()