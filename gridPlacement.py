'''
// Time Complexity : O(HWcN)
// Space Complexity : O(HW)
'''

from collections import deque
min_distance = 1000

def bfs(grid,h,w): 
    global min_distance
    q = deque([])
    visited = [[False for _ in range(w)] for _ in range(h)]
    
    for i in range(h):
        for j in range(w):
            if grid[i][j] == 0:
                q.append([i,j])
                visited[i][j] = True
    distance=0
    while q:
        for i in range(len(q)):
            dirs = [[1,0],[0,1],[-1,0],[0,-1]]
            ci,cj = q.popleft()
            for d in dirs:
                nr = d[0] + ci
                nc = d[1] + cj
                if nr>=0 and nr<h and nc>=0 and nc<w and not visited[nr][nc]:
                    q.append([nr,nc])
                    visited[nr][nc] = True
        distance +=1
        
    min_distance = min(min_distance,distance-1)
                    
                
            
def backtrack(grid,r,c,n):
    
    if n == 0:
        bfs(grid,len(grid),len(grid[0]))
        return
        
    if c >= len(grid[0]):
        r = r+1
        c=0
        
    for i in range(r, len(grid)):
        for j in range(c,len(grid[0])):
            grid[i][j] = 0
            backtrack(grid, i, j+1, n-1)
            grid[i][j] = -1
        c=0
            

def find_min_distance(h,w,n):
    global min_distance
    grid = [[-1 for _ in range(w)] for _ in range(h)]
    #min_distance = float("inf")
    backtrack(grid,0,0,n)
    return min_distance

print(find_min_distance(4,4,1))  #4
print(find_min_distance(4,4,3))  #2 
print(find_min_distance(4,4,16)) #0