'''
Optimal Placement of Buildings in a grid (not a leetcode problem)

Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. The goal is for the furthest of all buildings to be as near as possible to an empty lot (for eg. parking lot).
That is, if there are n=3 buildings, and
di = distance between building i and parking lot, i = 1, 2, 3
then the objective to minimize max(d1, d2, d3). In other words, find the position of d1, d2, d3 such that min (max(d1, d2, d3)). For hw buildings,
n buildings can be chosen in hw C n ways. We want to find the min of those hw C n ways.

Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance between the most distant parking lot from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.

1 0 1 2

2 1 2 1

1 0 1 0

2 1 2 1

"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

Another such placement is:
2 1 0 1

2 1 1 1

1 0 1 0

2 1 2 1

https://youtu.be/HgTVjpgRTIk?t=3227 (problem definition using an example)

Solution:
1. Backtracking + BFS
Backtracking is used to explore the placement of the buildings, BFS is used to compute the distance of a building to the farthest empty lot (parking lot).

We use two approaches. They differ in the way backtracking is implemented.

Method 1:
First, we try every way to place the buildings on the grid using backtracking. We convert 1d index to row and column index of 2d matrix to place the building.
For each combination, we run a BFS to find the max distance from any empty cell to the nearest building. Among all placements, we keep track of the one that gives the smallest such distance.

Method 2:
First, we try every way to place the buildings on the grid using backtracking. We use the indices of the 2d matrix to perform backtracking. Once we place a building in cell (i,j), we increment the col index and start a new recursive call to place the next building in cell (i, j+1). If the col index exceeds the width, then we reset column index to 0 and increment the row index by 1 (i+1).
BFS to find min distance of is run as in method 1.

The BFS method to find the distance is common across both the methods. Using the 'mutate' flag, we show that we can compute the min distance by mutating the grid or by not mutating the grid. If we don't mutate the grid, we use an additional matrix visted[][] to keep track of the visited cells. If we mutate the grid, a cell containing a value of -1 is unvisited, 0 is where the building is placed, and a value > 0 is interpreted as the distance from nearest building. Mutating the grid helps to visualize where the buildings are placed in the optimal placement scenario and
the distances of the buildings from each non-occupied (non-building cells).
Notations (if grid is mutated)
  grid[x][y] == -1 (unvisited)
  grid[x][y] == 0  (ref cell)
  grid[x][y] == 1, 2 ... (distance from ref cell)

To visualize the grid, uncomment the line
mprint(dist_mat) in the base case (k==0) of recurse() function

Time: O(C(H*W, N) * H*W)
(Note:
The time complexity is due to backtracking and BFS
Backtracking: C(HW, N) = HW choose N = no. of combinations of placing N buildings
BFS: H*W
Total Time: O(backtracking * BFS) = O(C(H*W, N) * HW)
Space: O(H*W) for the BFS queue and visited matrix (if mutated).

https://youtu.be/HgTVjpgRTIk?t=3222

'''
from collections import deque
from copy import deepcopy as dcp
def mprint(matrix):
    print('\n'.join(['\t'.join([str(cell) for cell in row]) for row in matrix]))

def minDistance(grid, mutate):
    ''' Find max distance from src cells to farthest cells using bfs; mutate
        the grid if mutate=True
        Notations:
        grid[x][y] == -1 (unvisited)
        grid[x][y] == 0  (ref cell)
        grid[x][y] == 1, 2 ... (distance from ref cell)
    '''

    def bfs(grid):
        q = deque()
        for i in range(M): # O(MN)
            for j in range(N):
                if grid[i][j] == 0:
                    q.append([i,j])
                    if not mutate:
                        visited[i][j] = True

        dist = 0
        dirs = [[-1,0], [1,0], [0,-1], [0,1]] # top, down, left, right
        while q:
            sz = len(q)
            for _ in range(sz):
                curr = q.popleft()
                for dir in dirs:
                    x, y = curr[0] + dir[0], curr[1] + dir[1]
                    if mutate:
                        if 0 <= x <= M-1 and 0 <= y <= N-1 and grid[x][y] == -1:
                            q.append([x,y])
                            grid[x][y] = dist
                    else:
                        if 0 <= x <= M-1 and 0 <= y <= N-1 and not visited[x][y]:
                            q.append([x,y])
                            visited[x][y] = True
            dist += 1

        return dist - 1

    if not grid:
        return -1
    M, N = len(grid), len(grid[0])
    if not mutate:
        visited = [[False]*N for _ in range(M)]
    dist_mat = dcp(grid)
    max_dist = bfs(dist_mat)
    return max_dist, dist_mat

def optimalPlacement_1(w, h, n, mutate):
    '''
    2d grid converted to 1d array
    '''
    def recurse(grid, pivot, n):
        nonlocal minimax_dist
        # base
        if pivot == h*w:
            return

        if n == 0:
            max_dist, dist_mat = minDistance(grid, mutate)
            if max_dist < minimax_dist:
                minimax_dist = max_dist
                # mprint(grid)
                # print(f"\nminimax distance = {minimax_dist}")
                # mprint(dist_mat)
            return

        # logic
        for i in range(pivot, h*w):
            r, c = i // w, i % w
            grid[r][c] = 0
            recurse(grid, i+1, n-1)
            grid[r][c] = -1
        return


    grid = [[-1]*w for _ in range(h)]
    minimax_dist = float('inf')
    recurse(grid, 0, n)
    #mprint(grid)
    return minimax_dist


def optimalPlacement_2(w, h, n, mutate):
    '''
        2d grid treated as-is
    '''
    def recurse(grid, row, col, k):
        nonlocal minimax_dist
        if k == 0:
            max_dist, dist_mat = minDistance(grid, mutate=mutate)
            if max_dist < minimax_dist:
                minimax_dist = max_dist
                # print(grid)
                # print(f"\nminimax distance = {minimax_dist}")
                # mprint(dist_mat)
            return

        if col == w:
           row += 1
           col = 0

        # logic
        for i in range(row, h):
            for j in range(col, w):
                grid[i][j] = 0
                recurse(grid, i, j+1, k-1)
                grid[i][j] = -1
            col = 0
        return

    grid = [[-1]*w for _ in range(h)]
    minimax_dist = float('inf')
    recurse(grid, 0, 0, n)
    return minimax_dist

def run_tests():
    tests = [(5, 6, 3, 3),
             (5, 4, 2, 3),
             (4, 4, 3, 2),
             (5, 4, 6, 1),
    ]
    for test in tests:
        w, h, n, ans = test[0], test[1], test[2], test[3]
        print(f"\nWidth = {w}, Height = {h}")
        print(f"Num buildings to place = {n}")
        for method in [1, 2]:
            for mutate in [True, False]:
                if method == 1:
                    dist = optimalPlacement_1(w, h, n, mutate)
                elif method == 2:
                    dist = optimalPlacement_2(w, h, n, mutate)
                print(f"Method - {method}, Mutate - {mutate}: distance = {dist}")
                passed = (ans == dist)
                assert passed == True, f"Test case failed"
                print(f"Pass: {passed}")

    print(f"\nDONE")

run_tests()
