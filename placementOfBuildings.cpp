#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
First, we try every way to place the buildings on the grid using backtracking.
For each combination, we run a BFS to find the max distance from any empty cell to the nearest building.
Among all placements, we keep track of the one that gives the smallest such distance.
*/
class BuildingPlacement{
public:
    int H, W;
    int result;

    int findMinDistance(int h, int w, int n){
        H = h;
        W = w;
        result = INT_MAX;
        vector<vector<int>> grid(H, vector<int>(W, -1));
        dfs(grid, 0, 0, n);
        return result;
    }

    void dfs(vector<vector<int>>& grid, int i, int j, int n){
        if(n == 0){
            bfs(grid);
            return;
        }
        if(j == W){
            i++;
            j = 0;
        }
        for(int r = i; r < H; r++){
            for(int c = (r == i ? j :0); c < W; c++){
                grid[r][c] = 0;
                dfs(grid, r, c+1, n-1);
                grid[r][c] = -1;
            }
        }
    }

    void bfs(vector<vector<int>>& grid){
        vector<vector<bool>> visited(H, vector<bool>(W, false));
        queue<pair<int, int>> q;
        int dirs[4][2] = {{-1, 0}, {1,0}, {0,1},{0,-1}};

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(grid[i][j] == 0){
                    q.push({i, j});
                    visited[i][j] = true;
                }
            }
        }

        int dist = 0;
        while(!q.empty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                auto [x, y] = q.front(); q.pop();
                for(auto& dir : dirs){
                    int r = x + dir[0];
                    int c = y + dir[1];
                    if(r >= 0 && c >= 0 && r < H && c < W && !visited[r][c]){
                        q.push({r, c});
                        visited[r][c] = true;
                    }
                }
            }
            dist++;
        }
        result = min(result, dist-1);
    }
};
