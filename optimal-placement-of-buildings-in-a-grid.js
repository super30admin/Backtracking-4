// Time Complexity : O((H*W)PN) where H and W are height and width respectively, P stands for Permute (order matters),
// and N is the given n;
// Space Complexity : O(H*W)
// Did this code successfully run on Leetcode : Not applicable, not on LeetCode
// Any problem you faced while coding this : No

let minDistance;

/*
 * @param {number} w
 * @param {number} h
 * @param {number} n
 * @return {number[][]}
 */ 

const findMinDistance = (H, W, n) => {
    minDistance = Infinity;
    const matrix = new Array(H);
    for (let i = 0; i < matrix.length; i++) {
        matrix[i] = new Array(W).fill(-1);
    }

    backtrack(matrix, 0, 0, n, H, W);
    return minDistance;
};

const backtrack = (matrix, r, c, n, H, W) => {
    // Base case
    if (n == 0) {
        bfs(matrix, H, W);
        return;
    }

    // Logic
    if (c >= W) {
        r++;
        c = 0;
    }
    for (let i = r; i < H; i++) {
        for (let j = c; j < W; j++) {
            // Action
            matrix[i][j] = 0;
            // Recurse
            backtrack(matrix, i, j + 1, n - 1, H, W);
            // backtrack
            matrix[i][j] = -1;
        }
        c = 0;
    }
}

/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
// BFS
var bfs = function(matrix, H, W) {
    if (matrix == null || matrix.length == 0) return;

    const q = [];
    const visited = new Array(H);
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];
    for (let i = 0; i < visited.length; i++) {
        visited[i] = new Array(W).fill(false);
    }

    for (let i = 0; i < H; i++) {
        for (let j = 0; j < W; j++) {
            if (matrix[i][j] == 0) {
                visited[i][j] = true;
                q.push([i, j]);
            }
        }
    }

    let dist = 0;
    
    while (q.length > 0) {
        const countNodeLevel = q.length;

        for (let i = 0; i < countNodeLevel; i++) {
            const curr = q.shift();
            for (let k = 0; k < 4; k++) {
                const x = curr[0] + dx[k];
                const y = curr[1] + dy[k];

                if (isValid(H, W, x, y) && !visited[x][y]) {
                    visited[x][y] = true;
                    q.push([x, y]);
                }
            }
        }
        dist++;
    }

    minDistance = Math.min(minDistance, dist - 1);

};

const isValid = (m, n, x, y) => {
    return x >= 0 && x < m && y >= 0 && y < n;
}
