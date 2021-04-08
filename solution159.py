#Try various possible position combinations of n buildings within the grid using the backtracking function and for each combination use
#bfs to calculate the distance to the farthest place and find minimum distance amongst them all.

#Time Complexity:O(n^2!)
#Space Complexity:O(2mn)
from collections import deque
width=4
height=4
n=1
minDistance=inf
grid=[[-1 for i in range(width)] for j in range(height)]

def bfs(grid:List[List[int]],H:int,W:int):
    global minDistance
    q=deque()
    dirs=[[1,0],[0,1],[-1,0],[0,-1]]
    visited=[[False for i in range(width)] for j in range(height)]
    for i in range(W):
        for j in range(H):
            if grid[i][j]==0:
                q.append([i,j])
                visited[i][j]=True
    dist=0
    while q:
        size=len(q)
        for i in range(size):
            curr=q.popleft()
            for d in dirs:
                nr=curr[0]+d[0]
                nc=curr[1]+d[1]
                if nr>=0 and nc>=0 and nc<W and nr<H and not visited[nr][nc]:
                    q.append([nr,nc])
                    visited[nr][nc]=True
        dist+=1
    minDistance=min(minDistance,dist-1)
    return

def backtrack(grid:List[List[int]],row:int,column:int,height:int,width:int,n:int):
    if n==0:
        bfs(grid,height,width)
        return
    if column>=width:
        row+=1
        column=0
    for i in range(row,height):
        for j in range(column,width):
            grid[i][j]=0
            backtrack(grid,i,j+1,height,width,n-1)
            grid[i][j]=-1
        column=0
        
backtrack(grid,0,0,height,width,n)
print(minDistance)
