#Approach
# Place the grid in three place and find the min distance from all palcements and find the min


#Complexities:
#Time : h*w n b + h*w
#Space : h*w

class Solution:
    def optimalBuildingplacement(self,w,h,n):
        grid =[[-1 for _ in range(w)] for _ in range(h)]
        self.minDistance = float("inf")
        self.dfs(grid,0,0,n)
        return self.minDistance

    def dfs(self,grid,r,c,n):
        if n==0:
            currGridDistance = self.findDistance(grid)
            print(self.minDistance,currGridDistance)
            self.minDistance = min(self.minDistance,currGridDistance )
            return
        if c==len(grid[0]):
            c=0
            r+=1

        for i in range(r,len(grid)):
            for j in range(c,len(grid[0])):
                grid[i][j]=0
                self.dfs(grid,i,c+1,n-1)
                grid[i][j]=-1






    def findDistance(self,grid):
        queue = []
        visited  = [[False for _ in range(len(grid[0]))] for _ in range(len(grid))]
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j]==0:
                    queue.append((i,j))
                    visited[i][j]=True

        directions = [[-1,0],[1,0],[0,-1],[0,1]]
        distance = 0
        while queue:
            size = len(queue)
            for _ in range(size):
                (x,y) = queue.pop(0)
                for r,c in directions:
                    nr = r+x
                    nc = c+y

                    if 0<=nr<len(grid) and 0<=nc<len(grid[0]) and visited[nr][nc]==False:
                        visited[nr][nc] = True
                        queue.append((nr,nc))
            distance+=1
        return distance-1





s= Solution()
print(s.optimalBuildingplacement(4,4,3))
