# Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. 
# The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the 
# distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

# For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.
# "0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

# 1 0 1 2

# 2 1 2 1

# 1 0 1 0

# 2 1 2 1

from tkinter import W


def bfs(grid):

def backtrack(grid, idx, cnt):
    # logic
    if(cnt==0):
        bfs(grid)
    for i in range(idx, H*W):  
        r=i/W
        c=i%W
        # action
        grid[r][c]=0
        backtrack(grid,i+1,cnt-1)

def bfs(grid):
    q=Queue()
    dirs=[[0,1],[1,0],[0,-1],[-1,0]]
    for i in range(h):
        for j in range(w):
            if(grid[i][j]==0):
                q.put([i,j])
    dist=0
    while(q):
        curr=q.poll()
        for dir in dirs:
            r=dir[0]+curr[0]
            c=dir[1]+curr[1]
            if(r>=0 and c>=0 and r<H and c<W and !visisted[r][c]):
               visited[r][c]=True
               q.add([r,c])
    
H=H
w=W
minel=float('inf')
grid=[[-1 for _ in the range(w)]for _ in the range(h)]
backtrack(grid,0,n)
return minel