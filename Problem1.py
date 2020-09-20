"""
time: exponential
space: o(m*n)
"""
#### Please check correctness of the approach without using the visited matrix and the use of the global variable min_dist(I am unsure about the declaration and use global variables)





from collections import deque
import sys

def optimal_placement(h,w,n):
    global min_dist
    min_dist = sys.maxsize
    
    grid = [[-1 for i in range(w)]for j in range(h)]
    backtrack(grid, 0,0,h,w,n)
    
    return min_dist

def backtrack(grid, r, c, h, w, n):
    #base
    if n == 0: #when all buildings have been place, call bfs to get the min distance
        bfs(grid, h, w)
    #logic
    for i in range(r, h): # trying to place building at each cell
        for j in range(c, w):
            
            grid[i][j] = 0
                
            backtrack(grid, i, j+1, h,w, n-1)
                
            grid[i][j] = -1
                
        c = 0
def bfs(grid, h, w):
    global min_dist
    dirs = [[0,1],[1,0],[0,-1],[-1,0]]
    
    q = deque()
    dist = 0
    #vis = [[False for i in range(w)] for j in range(h)] 
    
    for i in range(h):
        for j in range(w):
            if grid[i][j] == 0:
                q.append((i,j))
                #vis[i][j] = True
            
    while q:
        size = len(q)
        for k in range(size):
            curr = q.popleft()
            for d in dirs:
                r = curr[0] + d[0]
                c = curr[1] + d[1]
                if r >= 0 and r < h and c >= 0 and c < w and grid[r][c] == -1 :#not vis[r][c]:
                    grid[r][c] = dist + 1
                    #vis[r][c] = True
                    q.append((r,c))
                    
            dist += 1     
    min_dist = min(min_dist, dist -1)       
            
#global min_dist
if __name__ == '__main__':
    print(optimal_placement(4,4,3))