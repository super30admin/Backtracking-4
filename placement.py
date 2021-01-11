#As taught in class, using BFS + backtracking to solve this probleem
#Time Complexitty: exponential
#Space complexity: O(n)
minDist = 99999
def findMinDist(H,W,n):
    grid = [[0]*H]*W
    for i in range(H):
        for j in range(W):
            gird[i][j] = -1
    backtrack(0,0,grid,n,H,W)
    return minDist

def bfs(grid, H, W):
    q = list()
    visited = [[False] for i in range(H)]*W
    for i in range(H):
        for j in range(W):
            if grid[i][j] == 0:
                q.append(list(tuple(i,j)))
                visited[i][j] = True
    dist = 0
    dirs = [(0,1),(0,-1),(1,0),(-1,0)]
    while(len(q)!=0):
        size = len(q)
        for i in range(size):
            curr = q.pop(0)
            for dir in dirs:
                nr = curr[0] + dir[0]
                nc = curr[1] + dir[1]
                if (nr >= 0 and nc >= 0 and nr < H and nc < W and visited[nr][nc] == False):
                    q.append(tuple(nr,nc))
                    visited[i][j] = True
            dist += 1
        minDist = min(minDist,dist)

def backtrack(grid, r, c, n, H, W):
    if n == 0:
        bfs(grid, H, W)
        return
    if c == W:
        r += 1
        c = 0
    for i in range(r,H):
        for j in range(c,W):
            grid[i][j] = 0
            backtrack(grid, i, j+1, n-1)
            grid[i][j] = -1