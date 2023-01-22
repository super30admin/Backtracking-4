class BuildingPlacement:
    
    ## T.C = Exponential
    ## S.C = O(h.w)
    
    def __init__(self, h, w, n):
        self.h = h
        self.w = w
        self.n = n
        self.grid = [[-1]*w for i in range(h)]
        self.min_distance = float('inf')
        self.dirs = [(-1,0),(1,0),(0,-1),(0,1)]
    
    def calc_distance(self):
        queue = []
        visited = [[False]*self.w for i in range(self.h)]
        for i in range(self.h):
            for j in range(self.w):
                if self.grid[i][j] == 0:
                    visited[i][j] = True
                    queue.append([i,j])
        
        distance = 0
        while queue:
            for a in range(len(queue)):
                x, y = queue.pop()
                for i, j in self.dirs:
                    p, q = x + i, y + j
                    if p in range(self.h) and q in range(self.w) and visited[p][q] != True:
                        visited[p][q] = True
                        queue.append([p, q])
            distance += 1
            
        if distance - 1 <= self.min_distance:
            for x in self.grid:
                print(x, distance - 1, self.min_distance)
            print('-----------------------------------')
        self.min_distance = min(self.min_distance, distance-1)

    
    def backtrack(self, r, c, n):
        
        ## base case
        if n == 0:
            self.calc_distance()
            return
            
        if c == self.w:
            r += 1
            c = 0
        
        ## logic
        for i in range(r, self.h):
            for j in range(c, self.w):
                # action
                self.grid[i][j] = 0
                # recurse
                self.backtrack(r, c+1, n-1)
                # backtrack
                self.grid[i][j] = -1
    
    def find_min_distance(self):
        self.backtrack(0, 0, self.n)
        return self.min_distance

x = BuildingPlacement(4,5,2)
print(x.find_min_distance())

