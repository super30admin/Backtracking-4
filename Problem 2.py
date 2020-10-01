# time complexity: O(len(S)^2)
# space complexity: O(len(S))

class Solution:
    def expand(self, S: str) -> List[str]:

        # queue for storing the list of elements in braces and outside the braces for the reason of iteration
        queue = []
        # idx is used whilr creating the queue from the string
        idx = 0

        # whilr the idx is less than S, iterate and insert the elements in  queue
        while idx < len(S):
            # find start and end index of braces
            start = S.find("{", idx)
            end = S.find("}", idx)

            # if no more braces are present, then insert the remining elements if any in the queue
            if start == -1:
                queue.append(S[idx:len(S)].split(","))
                break
            # if there are some elemets outside the braces, then insert them in the queue
            if start > idx:
                queue.append(S[idx:start].split(","))
                # insert the elements inside the braces in the queue
            queue.append(S[start + 1:end].split(","))
            idx = end + 1

        result = []

        # helper function for backtracking and finding all possible combinations
        def helper(queue, ans):
            # if the last queue is reached, then we have a combination and insert it in result
            if not queue:
                result.append(ans)
                return
            # else, sort it first as we need answers in lexographical order
            present = sorted(queue.pop(0))

            # for each element present in the list popped from the queue
            for l in present:
                # create a copy of queue, othervise in the next iteration itself, we will haave an empty queue
                temp = queue[:]
                # call the helper function again with a new queue and updates ans
                helper(temp, ans + l)

        helper(queue, "")
        return result