class OptimalPlacement:
    def __init__(self, height, width, buildings):
        self.height = height
        self.width = width
        self.number = buildings
        self.output = float("inf")

    def find_min_distance(self):
        grid = [[-1]*self.width for _ in range(self.height)]
        self._backtrack(grid, 0,0, self.number)
        return self.output
    def _backtrack(self, grid, r, c, n):
        
        if n == 0:
            # if self._bfs(grid)>20:
            #     print("yoyoyo")
            #     print(self._bfs(grid))
            
            num = self._bfs(grid[:])
            # print("Passing grid as",grid,n,num )
            self.output = min(self.output, num)
            return 
        if c == self.width:
            r+=1
            c =0
        
        for i in range(r,self.height):
            for j in range(c, self.width):
                #action
                grid[i][j] = 1
                #recurse

                self._backtrack(grid, i, j+1, n-1)                    
                #backtrack
                grid[i][j] = -1
            c = 0

    def _bfs(self, grid):
        
        queue = deque([[i,j] for i in range(len(grid)) for j in range(len(grid[0])) if grid[i][j] == 1])        

        # print("Length of queue",len(queue))
        distance = 0
        visited = set()
        while queue:
            for _ in range(len(queue)):
                a,b = queue.popleft()
                visited.add((a,b))
                for i,j in [[a+1,b],[a-1,b],[a,b+1],[a,b-1]]:
                    if 0<=i<self.height and 0<=j<self.width and grid[i][j] == -1 and (i,j) not in visited:
                        # print("Adding", i,j)
                        queue.append([i,j])
                        visited.add((i,j))
            distance+=1
        
        return distance-1
                    

def main():
    p = OptimalPlacement(4, 4, 3)
    print(p.find_min_distance())


main()
'''
# TIME COMPLEXITY: O(height*width C buildings) - nCr -> Asymtotically, exponential
# SPACE COMPLEXITY: O(height*width)
