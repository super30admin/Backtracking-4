/*
Intuition: We go to the next ciolumn and next row.
First go to the next column and then handle the next row in the recursion base case.

Time Complexity: Expotential
Space Complexity: Exponential



*/


#include<iostream>
#include<vector>
#include<set>
#include<queue>

using namespace std;
class BuildingPlacement{
    public:
        int minDistance = 99999999;
        int findMinDistance(int H, int W, int n){
            vector<vector<int>> grid;
            grid.resize(H, vector<int>(W, -1));
            backtrack(grid, H, W, n, 0, 0);
            cout<<minDistance<<" is the minDistance"<<endl;
        }

        void bfs(vector<vector<int>>grid, int H, int W){
            set<pair<int,int>>visited;
            queue<pair<int,int>> queue;
            for ( int i = 0;i < H; i++){
                for ( int j = 0; j < W; j++){
                    if ( grid[i][j] == 0){
                        queue.push({i,j});
                        visited.insert({i,j});
                    }
                }
            }
            int dist = 0;
            vector<vector<int>>dirs;
            dirs = {{1,0}, {0,1}, {-1,0}, { 0,-1}};
            while ( queue.size() != 0){
                int size = queue.size();
                for ( int i = 0; i < size; i++){
                    auto currPair = queue.front();
                    queue.pop();
                    for ( auto dir: dirs){
                        int newI = dir[0] + currPair.first;
                        int newJ = dir[1] + currPair.second;
                        if ( newI >= 0 and newI < H and newJ >= 0 and newJ < W and grid[newI][newJ] == -1){
                            if ( visited.find({newI, newJ}) == visited.end()){
                                visited.insert({newI, newJ});
                                queue.push({newI, newJ});
                            }
                        }
                    }
                }
                dist++;
            }
            minDistance = min(minDistance, dist-1);

        }

        void backtrack(vector<vector<int>>grid, int H, int W, int n, int r, int c){
            if ( n == 0){
                bfs(grid, H, W);
                return;
            }
            if ( c == W){
                r++;
                c = 0;
            }

            for ( int i = r; i < H; i++){
                for ( int j = c; j < W; j++){
                    grid[i][j] = 0;
                    backtrack(grid, H, W, n-1, i, j+1);
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
};

int main(){
    auto obj1 = BuildingPlacement();
    obj1.findMinDistance(3,2,1);
    auto obj2 = BuildingPlacement();
    obj2.findMinDistance(4,4,1);
    auto obj3 = BuildingPlacement();
    obj3.findMinDistance(4,4,3);
    auto obj4 = BuildingPlacement();
    obj4.findMinDistance(4,4,2);

}