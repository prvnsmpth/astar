/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import astar.Astar;
import astar.AstarNode;
import java.util.ArrayList;
import java.util.Iterator;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class RobotNavigation extends Astar {
            
    private Pair<Integer, Integer> startRobotCoords;
    public static final int STRAIGHT_LINE = 0;
    public static final int MANHATTAN = 1;
    private int heuristic;
    
    public RobotNavigation
    (
        int gridSize, 
        Pair<Integer, Integer> robotCoords,
        Pair<Integer, Integer> targetCoords,
        int heuristic
    )
    {      
        SearchState.gridSize = gridSize;
        SearchState.targetCoords = targetCoords;
        SearchState.grid = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                SearchState.grid[i][j] = SearchState.OPEN;
        this.startRobotCoords = robotCoords;
        this.heuristic = heuristic;
        
        this.setStart(new SearchState(robotCoords));
        this.setGoal(new SearchState(targetCoords));
    }
    
    public void setHeuristic(int h)
    {
        this.heuristic = h;
    }
    
    public void setWall(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2)
    {
        if (p1.getFirst() == p2.getFirst())
        {
            int a = Math.min(p1.getSecond(), p2.getSecond());
            int b = Math.max(p1.getSecond(), p2.getSecond());            
            for (int i = a; i <= b; i++)
                SearchState.grid[p1.getFirst()][i] = 
                        SearchState.BLOCKED;
        }
        else if (p1.getSecond() == p2.getSecond())
        {
            int a = Math.min(p1.getFirst(), p2.getFirst());
            int b = Math.max(p1.getFirst(), p2.getFirst()); 
            for (int i = a; i <= b; i++)
                SearchState.grid[i][p1.getSecond()] = 
                        SearchState.BLOCKED;
        }
    }
    
    private int straightLineDistance(AstarNode a, AstarNode b)
    {
        double dist = 0;
        SearchState anode = (SearchState) a;
        SearchState bnode = (SearchState) b;
        Pair<Integer, Integer> acoord = anode.getRobotCoords();
        Pair<Integer, Integer> bcoord = bnode.getRobotCoords();
        dist = Math.sqrt(Math.pow(acoord.getFirst() - bcoord.getFirst(), 2) +
                Math.pow(acoord.getSecond() - bcoord.getSecond(), 2));
        return (int) (dist + 0.5);
    }
    
    private int manhattanDistance(AstarNode a, AstarNode b)
    {
        SearchState anode = (SearchState) a;
        SearchState bnode = (SearchState) b;
        Pair<Integer, Integer> acoord = anode.getRobotCoords();
        Pair<Integer, Integer> bcoord = bnode.getRobotCoords();
        return Math.abs(acoord.getFirst() - bcoord.getFirst()) +
                Math.abs(acoord.getSecond() - bcoord.getSecond());
    }

    @Override
    /*
     *  Straight line distance heuristic
     */
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        if (heuristic == STRAIGHT_LINE)
            return straightLineDistance(a, b);
        else if (heuristic == MANHATTAN)
            return manhattanDistance(a, b);
        
        return 0;
    }
    
}
