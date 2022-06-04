package main

import (
	"fmt"
	"math"
)

/*
Problem1: Optimal Placement of Buildings in a grid
Given a grid with w as width, h as height.
Each cell of the grid represents a potential building lot, and we will be adding "n" buildings inside this grid.
The goal is for the farthest of all lots to be as near as possible to a building.
Given an input n, which is the number of buildings to be placed in the lot,
determine the building placement to minimize the distance the most distant empty lot is from the building.
Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3.
An optimal grid placement sets any lot within two unit distance of the building.
The answer for this case is 2.

"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

1 0 1 2
2 1 2 1
1 0 1 0
2 1 2 1
*/

func optimalBuildingPlacement(h, w, n int) int {
	min := math.MaxInt64
	board := make([][]bool, h)
	for idx, _ := range board {
		board[idx] = make([]bool, w)
	}

	var bfs func(board [][]bool)
	bfs = func(board [][]bool) {
		// get the max after processing the whole board
		// then update global min
		visited := make([][]bool, h)
		for idx, _ := range visited {
			visited[idx] = make([]bool, w)
		}
		q := [][]int{}
		for i := 0; i < h; i++ {
			for j := 0; j < w; j++ {
				if board[i][j] {
					// enqueue independent nodes ( buildings )
					q = append(q, []int{i, j})
					visited[i][j] = true
				}
			}
		}
		// process queue level by level
		dirs := [][]int{{1, 0}, {-1, 0}, {0, -1}, {0, 1}}
		level := 0
		for len(q) != 0 {
			qSize := len(q)
			for qSize != 0 {
				dq := q[0]
				q = q[1:]
				cr := dq[0]
				cc := dq[1]
				for _, dir := range dirs {
					r := cr + dir[0]
					c := cc + dir[1]
					if r >= 0 && r < h && c >= 0 && c < w && !visited[r][c] {
						q = append(q, []int{r, c})
						visited[r][c] = true
					}
				}
				qSize--
			}
			level++
		}
		if level-1 < min {
			min = level - 1
		}
	}
	var backtrack func(board [][]bool, r, c, n int)
	backtrack = func(board [][]bool, r, c, n int) {
		// base
		if n == 0 {
			bfs(board)
			return
		}

		// logic
		if c == w-1 {
			r++
			c = 0
		}
		for i := r; i < h; i++ {
			for j := c; j < w; j++ {
				// action
				board[i][j] = true
				// recurse
				backtrack(board, r, c+1, n-1)
				// backtrack
				board[i][j] = false
			}
		}
	}

	for i := 0; i < h; i++ {
		for j := 0; j < w; j++ {
			backtrack(board, i, j, n)
		}
	}
	return min
}

func main() {
	fmt.Println(optimalBuildingPlacement(4, 4, 3))
}
