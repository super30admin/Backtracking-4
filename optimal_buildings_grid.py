// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
we used backtracking and BFS to solve this problem.
1)Backtracking --> to generate all the different possibilites in a grid where buildings can be placed.
2)FOr every generated Backtracking possiblity matrix we calculate the  no of buildings that can be accomodated in the grid.In this way we try this logic for every generated backtracking matrix to get the optimal placement of building in a grid.

# Time complexity --> o(((h*w)C(no of buildings))*(h*w)
# space complexity --> o(h*w)
from collections import deque
class optimal_buil:
    def __init__(self):
        self.dirs=[[1,0],[-1,0],[0,1],[0,-1]]
        self.maxi=float('inf')
    def isvalid(self,row,col,h,w):
        if 0<=row<h and 0<=col<w:
            return True
        return False
    # BFS function to calculate the min building possibility in a matrix.
    def bfs(self,grid,h,w):
        q=deque()
        visited=[[False for i in range(len(grid[0]))]for j in range(len(grid))]
        # we start with all the elements which has 0 as value in the matrix
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j]==0:
                    q.append([i,j])
                    visited[i][j]=True
        count=0
        while len(q)!=0:
            size=len(q)
            for i in range(size):
                val=q.popleft()
                row=val[0]
                col=val[1]
                # then we go with the neighbours using dirs array
                for k in self.dirs:
                    dx=row+k[0]
                    dy=col+k[1]
                    # checking if the generated row,col are within inbounds or not
                    if self.isvalid(dx,dy,h,w) and visited[dx][dy]==False:
                        q.append([dx,dy])
                        visited[dx][dy]=True
            count=count+1
        # maintaining the min number of buildings possiblity in each generated backtracking matrix
        self.maxi=min(self.maxi,count-1)
    # backtrack function to create all the matrix possibities
    def backtrack(self,r,c,h,w,grid,n):
        if n==0:
            # print(grid)
            # for every matrix created in backtracking technique we call the BFS function to calculate the min number of buildngs that can be accomodated.
            self.bfs(grid,h,w)
            return
        if c>=w:
            c=0
            r=r+1
        for i in range(r,h):
            for j in range(c,w):
                grid[i][j]=0
                self.backtrack(i,j+1,h,w,grid,n-1)
                grid[i][j]=-1
            c=0

    def optimal_number_buildings(self,h,w,n):
        grid=[[-1 for i in range(w)]for i in range(h)]
        # we use backtracking in here to get all matrix possibilities.uncomment line 38 to see all the possibilities we nee.
        self.backtrack(0,0,h,w,grid,n)
        return self.maxi


if __name__=="__main__":
    width=4
    height=4
    no_of_buildings=3
    buil=optimal_buil()
    print(buil.optimal_number_buildings(height,width,no_of_buildings))
