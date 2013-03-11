/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightiles;

import astar.Astar;
import astar.AstarNode;
import java.util.ArrayList;
import java.util.Arrays;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class EightTiles extends Astar {
    
    public static final int MANHATTAN = 0;
    public static final int DISPLACED_TILES = 1;
    private int heuristic;
    
    public EightTiles(int heuristic)
    {
        // create start node
        EightTilesNode node = new EightTilesNode();
        this.setStart(node);
        
        // create goal node
        EightTilesNode gnode = new EightTilesNode(new ArrayList<> 
                (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EightTilesNode.EMPTY_TILE)));        
        this.setGoal(gnode);     
        
        // set heuristic
        this.heuristic = heuristic;
    }
        
    public EightTiles(ArrayList<Integer> config, int heuristic)
    {
        // create start node
        EightTilesNode node = new EightTilesNode(config);
        this.setStart(node);
        
        // create goal node
        EightTilesNode gnode = new EightTilesNode(new ArrayList<> 
                (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EightTilesNode.EMPTY_TILE)));
        this.setGoal(gnode);
        
        // set heuristic
        this.heuristic = heuristic;
    }
    
    /*
     *  set the heuristic method
     */
    public void setHeuristic(int heuristic)
    {
        this.heuristic = heuristic;
    }
    
    /*
     *  Returns the sum of the manhattan distances of tiles in
     *  board position 'a' to the corresponding tiles in 'b'
     */
    private int manhattanHeuristic(AstarNode a, AstarNode b)
    {
        int cost = 0;
        EightTilesNode anode = (EightTilesNode) a;
        EightTilesNode bnode = (EightTilesNode) b;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {          
                int tile = anode.getTileAt(i, j);
                if (tile != EightTilesNode.EMPTY_TILE)
                {
                    Pair<Integer, Integer> coord = bnode.getCoordinates(tile);
                    cost += Math.abs(coord.getFirst() - i) + Math.abs(coord.getSecond() - j);
                }
            }
        }
        return cost;
    }
    
    /*
     *  Returns the number of tiles in 'a' that are displaced w.r.t 'b'
     */
    private int displacedTilesHeuristic(AstarNode a, AstarNode b)
    {
        int cost = 0;
        EightTilesNode anode = (EightTilesNode) a;
        EightTilesNode bnode = (EightTilesNode) b;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {                   
                if (anode.getTileAt(i, j) == EightTilesNode.EMPTY_TILE)
                    continue;
                if (anode.getTileAt(i, j) != bnode.getTileAt(i, j))                    
                        cost++;
            }
        }
        
        return cost;
    }

    @Override
    /*
     *  Either manhtattan distance or the displaced tiles heuristic
     */
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        int cost = 0;
        if (heuristic == MANHATTAN)
        {
            cost = manhattanHeuristic(a, b); 
        }
        else if (heuristic == DISPLACED_TILES)
        {
            cost = displacedTilesHeuristic(a, b);                        
        }
        return cost;
    }
    
}
