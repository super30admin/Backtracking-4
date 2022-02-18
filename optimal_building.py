#optimal placement of building in a grid
#time h*w*hwPn(permutation for placemnt of building, each placement we do bfs)
from collections import deque
class Solution:
    def __init__(self):
        self.min=inf
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0]]
    def optimalbuilding(self,n,w,h):
        self.findmin(w,h,n)
    def findmin(self,w,h,n):
        grid=[[-1 for i in range(w)] for j in range(h)]
        print(grid)
        self.backtrack(grid,0,0,n,w,h)
        print(self.min)
        
    def bfs(self,grid,h,w):
        q=deque()
        visited=[[0 for i in range(w)] for j in range(h)]
        for i in range(h):
            for j in range(w):
                if grid[i][j]==0:
                    q.append([i,j])
                    visited[i][j]=1
        dist=0
        while q:
            size=len(q)
            for i in range(size):
                curr=q.popleft()
                for dirs in self.dirs:
                    x=curr[0]+dirs[0]
                    y=curr[1]+dirs[1]
                    if x>=0 and y>=0 and x<h and y<w and grid[x][y]==-1 and visited[x][y]==0:
                        q.append([x,y])
                        visited[x][y]=1
            dist+=1
        self.min=min(self.min,dist-1)
                    
    def backtrack(self,grid,r,c,n,w,h):
        #base
        if n==0:
            self.bfs(grid,h,w)
            return
        
        #logic
        if c==w:
            r+=1
            c=0
        for i in range(r,h):
            for j in range(c,w):
                #action
                grid[i][j]=0
                #recurse
                self.backtrack(grid,r,c+1,n-1,w,h)
                #backtrack
                grid[i][j]=-1
        
        
        




obj=Solution()
obj.optimalbuilding(2,4,4)