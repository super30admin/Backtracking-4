# Time Complexity : O(((h*w) C n) *(h*w)), Where h, w are height,width of the 2d array respectively
# Space Complexity : O(h*w), Where h, w are height,width of the 2d array respectively
# Did this code successfully run on Leetcode : Yes 
# Any problem you faced while coding this : Nothing specific

import sys
from collections import deque

class BuildingPlacement:
    def __init__(self,H: int, W: int, n: int):
        self.H=H
        self.W=W
        self.n=n
        self.grid=[[0 for i in range(W)] for j in range(H)] #Here 0 means empty slots
        self.mindistance=sys.maxsize
        
    def findmindistance(self):
        #First let us create all the possible of the buildings using backtrack
        self.backtrack(0,0,self.n)
        return self.mindistance
    
    def calculatefarthestdistance(self):
        
        visitedarray=[[False for i in range(self.W)] for j in range(self.H)] #This will keep track of the visted array
        dirs=[[0,1],[0,-1],[1,0],[-1,0]]#Right,Left,Bottom,Top
        q=deque()
        #Put the building in the root
        for i in range(self.H):
            for j in range(self.W):
                if(self.grid[i][j]==1):
                    #There is a building 
                    q.append([i,j])
                    visitedarray[i][j]=True
        
        level=0
        while(len(q)!=0):
            size=len(q)
            for i in range(size):
                top=q[0]
                #Action with each node
                for dire in dirs:
                    nr=top[0]+dire[0]
                    nc=top[1]+dire[1]
                    if(nr in range(self.H) and nc in range(self.W) and visitedarray[nr][nc]==False):
                        q.append([nr,nc])
                        visitedarray[nr][nc]=True
                q.popleft()
        
            level+=1
        
        
        self.mindistance=min(self.mindistance,level-1)
    
    def backtrack(self,r,c,buildings):
        #Base Case
        if(buildings==0):
            #Do the calculations using BFS or DFS and calculate farthest distance, Since we have placed all the buildings 
            self.calculatefarthestdistance()
            return
        
        if(c==self.W):
            #We reset the r,c to next row and column=0
            r+=1
            c=0
        #Actual logic
        for i in range(r,self.H):
            for j in range(c,self.W):
                self.grid[i][j]=1#Action
                self.backtrack(r,j+1,buildings-1)
                self.grid[i][j]=0#Undo Action

dp=BuildingPlacement(4,4,3)
print(dp.findmindistance())
