from collections import deque
width=4
height=4
n=1

minDistance =0
grid=[[-1 for i in range(width)] for j in range(height)]

def bfs(grid):
    global minDistance
    visited = [[False for i in range(width)] for j in range(height)]
    dirs = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    queue = deque()
    for i in range(height):
        for j in range(width):
            if grid[i][j] == 0:
                queue.append([i,j])
                visited[i][j]= True
    dist = 0
    while queue:
        size = len(queue)
        for i in range(size):
            curr = queue.popleft()
            for d in dirs:
                nr = curr[0] + d[0]
                nc = curr[1] + d[1]
                if nr >= 0 and nc >= 0 and nc < width and nr < height and not visited[nr][nc]:
                    queue.append([nr, nc])
                    visited[nr][nc] = True
        dist += 1
    minDistance = min(minDistance, dist - 1)








def backtrack(grid, row, column, nuilding):
    if nuilding == 0:
        bfs(grid)
        return
    if column > width:
        column =0
        row +=1

    for i in range(row,height):
        for j in range(column, width):
            grid[i][j] = 0
            backtrack(grid,i , column +1, nuilding -1 )
            grid[i][j] = -1




backtrack(grid, 0, 0, n)
print(minDistance)
