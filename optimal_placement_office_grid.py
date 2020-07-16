"""
Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.

"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2". 

1 0 1 2

2 1 2 1

1 0 1 0

2 1 2 1

-1 -1 -1 -1

-1 -1 -1 -1

-1  0 -1 -1

-1 -1 -1 -1



"""
"""
// Time Complexity : O(HWcN)
// Space Complexity : O(HW)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
Algorithm explanation
Idea
- This is on simlar lines of the 01 matrix where added part is getting all combinations
of the grid of placing buildings(similar to placing queens in N queens problem albeit the constraint)
- Summary - Backtracking + BFS
Main Algorithm
- BFS
    - Initiate queue 
    - For i = 0 to grid.length
        For j = 0 to grid[0].length
            if grid[i][j] == 0 then
                Add grid[i][j] to queue
    - Initiate visisted array(for each combination seperate)
    - While queue not empty
        - Iterating over current element of size s
            - Update the queue to include the valid non visited next position from 4 directions
        - increment the distance
    - Update the min distance 

- Backtracking(placing buildings(0) in the grid) - start with (0,0)
    - Base case
        - if n == 0 # all buildings are placed  
            - Run bfs on this combination
            - Get the updated min_distance
        - Boundary case( end of col)
            - if col == grid[0].length
                row = row + 1
                col = 0
    - Logic
        - for i = 0 to grid.length
            for j = 0 to grid[0].length
                #place the building
                grid[i][j] = 0
                #recurse to next column
                grid[i][j] = -1 #backtrack

- Driver fn
    - Set all the values in the grid of H vs W to -1
    - Call backtrack function -> computes the minimum distance
    - Return the min distance
"""
from collections import deque


def bfs(grid,h,w):
    global min_distance
    q = deque([])
    visited  = [[False for _ in range(w)] for _ in range(h)]
    for i in range(h):
        for j in range(w):
            if grid[i][j] == 0:
                q.append([i,j])
                visited[i][j] = True
    distance = 0
    while q:
        for i in range(len(q)):
            curr_i,curr_j = q.popleft()
            directions = [[0,-1],[-1,0],[0,1],[1,0]]
            for dirs in directions:
                new_i =  curr_i + dirs[0]
                new_j =  curr_j + dirs[1]

                if 0<=new_i < h and 0<= new_j < w and not visited[new_i][new_j]:
                    visited[new_i][new_j] = True
                    q.append([new_i,new_j])
                    #grid[new_i][new_j] = distance
        distance+=1
    min_distance = min(min_distance,distance-1)
    #print("MD",min_distance)

def backtrack(grid,r,c,n):
    #base
    if n == 0:
        #print(grid)
        bfs(grid,len(grid),len(grid[0]))
        return
    
    if c >= len(grid[0]):
        r = r + 1
        c = 0
    
    #logic
    for i in range(r,len(grid)):
        for j in range(c,len(grid[0])):
            #action
            grid[i][j] = 0

            #recurse
            backtrack(grid,i,j+1,n-1)

            #backtrack
            grid[i][j] = -1
        c = 0


def find_min_distance(h,w,n):
    global min_distance
    grid = [[-1 for _ in range(w)] for _ in range(h)]
    #min_distance = float("inf")
    backtrack(grid,0,0,n)
    return min_distance


if __name__ == '__main__':
    min_distance = float("inf")
    
    print(find_min_distance(4,4,1))
    print(find_min_distance(4,4,3))
    print(find_min_distance(4,4,16))

    assert find_min_distance(4,4,3) == 2 ,"Answer should be 2"
    assert find_min_distance(4,4,1) == 4 ,"Answer should be 4"
    assert find_min_distance(4,4,16) == 0 ,"Answer should be 0"