// Time Complexity : H*W*(H*W C N); H-> rows in grid, w-> columns in grid, N-> No. of buildings
// Space Complexity : H*W ; visited array, Queue
// Did this code successfully run on Leetcode : Question not on leetcode
// Any problem you faced while coding this : No

/* Approach : In this problem, optimal position needs to be found for placing the buildings such that the distance from parking 
lot is minimized. 
There are two main steps involved, select positions for all n buildings.
Once buildings are placed, use BFS to find position from neighbors.
Try different office positions by backtracking and finding respective minimum distances.
*/

import java.util.*;

public class OptimalPlacementOfBuilding {
	
	static int minDist = Integer.MAX_VALUE;
	
	public static void main(String args[]) {
		
		// create grid
		int H = 4, W = 4, n = 3;
		int[][] grid = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				grid[i][j] = -1;
			}
		}
		placeBuilding(grid, 0, 0, n);
		System.out.println(minDist);
		
	}
	
	private static void placeBuilding(int[][] grid, int h, int w, int n) {
		// base case -  when to calculate the distance
		int W = grid[0].length;
		int H = grid.length;
		
		if(n == 0) {

//		for(int i = 0; i < H; i++) {
//			for(int j = 0; j < W; j++) {
//				System.out.print(grid[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		boolean[][] visited = new boolean[H][W];
		minDist = Math.min(minDist, getMinDist(grid, visited));
		
		return;
	}
		
		if(w > W) {
			w = 0;
			h++;
		}
		for(int i = h; i < H; i++) {
			for(int j = w; j < W; j++) {
				grid[i][j] = 0;
				placeBuilding(grid, i, j+1, n-1);
				// backtrack
				grid[i][j] = -1;
			}
			w = 0;
		}
	}
	
	private static int getMinDist(int[][] grid, boolean[][] visited) {
		
		int level = 0;
		
		int W = grid[0].length;
		int H = grid.length;
		
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(grid[i][j] == 0) {
					q.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		int[][] dirs = {{0,-1},{-1,0},{1,0},{0,1}};
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int[] curr = q.poll();
				
				// update neighbors
				for(int[] dir : dirs) {
					int x = curr[0] + dir[0];
					int y = curr[1] + dir[1];
					
					// check validity 
					if(x >= 0 && x < H && y >= 0 && y < W && !visited[x][y]) {
						q.add(new int[] {x,y});
						visited[x][y] = true;
					}
				}
			}
			level++;
		}
		
		return level-1;
	}

}

