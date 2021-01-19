# Time:- (number of building to be placed)^(m*n)*(m*n)
# Space:- (m*n)
# Approach:- Backtrack and place all the buildings one by one and then do a bfs to find the distance of the 
# parking lot which is at the greatest distance from the building. 
import sys
import collections
class OptimalPlacement:
    def __init__(self,height,width,buildings):
        self.height=height
        self.width=width
        self.buildings=buildings
        self.min_distance=sys.maxsize
        self.grid=[]
        for i in range(width):
            self.grid.append([])
            for j in range(height):
                self.grid[i].append(-1)
        self.dirs=[[0,1],[1,0],[-1,0],[0,-1]]
        
    def find_min_distance(self):
        self.backtrack(self.grid,0,0,self.buildings)
        return self.min_distance

    def backtrack(self,grid,r,c,buildings):
        if buildings==0:
            self.bfs(grid)
            return
        if c==self.width:
            r+=1
            c=0
        for i in range(r,self.height):
            for j in range(c,self.width):
                grid[i][j]=0
                self.backtrack(grid,i,j+1,buildings-1)
                grid[i][j]=-1

    def bfs(self,grid):
        q=collections.deque([])
        visited=set()
        distance=0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j]==0:
                    q.append([i,j])
                    visited.add((i,j))
        while(q):
            size=len(q)
            for i in range(size):
                k,l=q.popleft()
                for i,j in self.dirs:
                    if 0<=k+i<len(grid[0]) and 0<=l+j<len(grid) and (k+i,l+j) not in visited:
                        q.append([k+i,l+j])
                        visited.add((k+i,l+j))
            distance+=1
        self.min_distance=min(self.min_distance,distance-1)


placement=OptimalPlacement(4,4,3)
placement.find_min_distance()