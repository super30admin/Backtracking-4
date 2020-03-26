package com.example.problems;

import java.util.*;

//Time Complexity : O(N*K!) 
//Space Complexity : O(N^2)  
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/*
 * BFS AND BACKTRACKING
 * */
public class OptimalBuildingPlacement {

	static int min = 0;

	public int findMinDistance(int H, int W, int n) {
		int[][] grid = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				grid[i][j] = -1;
			}
		}
		backtrack(grid, 0, 0, n, H, W);
		return min;
	}

	public static void bfs(int[][] grid, int H, int W) {
		Queue<int[]> queue = new LinkedList<>();

		boolean visited[][] = new boolean[H][W];
		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		int dist = 1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (grid[i][j] == 0) {
					visited[i][j] = true;
					queue.add(new int[] { i, j });
				}
			}
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int current[] = queue.poll();
				for (int dir[] : dirs) {
					int r = current[0] + dir[0];
					int c = current[1] + dir[1];
					if (r >= 0 && c >= 0 && r < W && c < W && !visited[r][c]) {
						visited[r][c] = true;
						queue.add(new int[] { r, c });
					}
				}
			}
			dist++;
		}
		min = Math.min(min, dist - 1);
	}

	public static void backtrack(int[][] grid, int r, int c, int n, int H, int W) {
		if (n == 0) {
			bfs(grid, H, W);
			return;
		}
		// logic
		for (int i = r; i < H; i++) {
			for (int j = c; j < W; j++) {
				grid[i][j] = 0;
				if (j < W) {
					backtrack(grid, i, j + 1, n - 1, H, W);
				} else {
					backtrack(grid, i + 1, 0, n - 1, H, W);
				}
			}
		}
	}

	public static void main(String srgs[]) {
		OptimalBuildingPlacement placement = new OptimalBuildingPlacement();
		int n = 1, H = 5, W = 1;
		System.out.print(placement.findMinDistance(H, W, n));
	}
}
