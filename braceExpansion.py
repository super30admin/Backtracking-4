class Solution:
    def expand(self, s: str) -> List[str]:
        def backtrack(index, path):
            if index == len(parsed):
                result.append("".join(path))
                return
            for char in parsed[index]:
                backtrack(index + 1, path + [char])

        # Step 1: Parse the string into a list of choices
        parsed = []
        i = 0
        while i < len(s):
            if s[i] == "{":
                j = i
                while s[j] != "}":
                    j += 1
                parsed.append(
                    sorted(s[i + 1 : j].split(","))
                )  # Extract and sort choices
                i = j  # Move to end of brace
            else:
                parsed.append([s[i]])  # Single character as a choice
            i += 1

        # Step 2: Use backtracking to generate all combinations
        result = []
        backtrack(0, [])

        return result
