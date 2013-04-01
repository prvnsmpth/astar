/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightiles;

import astar.Astar;
import astar.AstarNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class EightTiles extends Astar {
    
    public static final int MANHATTAN = 0;
    public static final int DISPLACED_TILES = 1;
    public static final int MANHATTAN_WITH_BLANK = 2;
    public static final int DISPLACED_TILES_WITH_BLANK = 3;
    public static final int MANHATTAN_RANDOM = 4;
    public static final int DISPLACED_TILES_RANDOM = 5;
    public static final int MANHATTAN_WITH_BLANK_RANDOM = 6;
    public static final int DISPLACED_TILES_WITH_BLANK_RANDOM = 7;
    public static final int MANHATTAN_INVALID = 8;
    public static final int DISPLACED_TILES_INVALID = 9;
    
    public int heuristic;
    
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
    
    public EightTiles(EightTiles et) {
        this.setStart( et.getStart().clone() );
        this.setGoal( et.getGoal().clone() );
        this.heuristic = et.heuristic;
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
    private int manhattanHeuristic(AstarNode a, AstarNode b, int heuristic)
    {
        boolean check_empty_tile = false;
        if(heuristic == MANHATTAN || heuristic == MANHATTAN_RANDOM)
            check_empty_tile = true;
        
        int cost = 0;
        EightTilesNode anode = (EightTilesNode) a;
        EightTilesNode bnode = (EightTilesNode) b;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {          
                int tile = anode.getTileAt(i, j);        
                if (check_empty_tile && tile != EightTilesNode.EMPTY_TILE)
                {
                    Pair<Integer, Integer> coord = bnode.getCoordinates(tile);
                    cost += Math.abs(coord.getFirst() - i) + Math.abs(coord.getSecond() - j);
                }
            }
        }
        if(heuristic == MANHATTAN_RANDOM || heuristic == MANHATTAN_WITH_BLANK_RANDOM){
            cost = (int)(Math.random() * cost);
        }
        else if(heuristic == MANHATTAN_INVALID)
            cost = 25 * (int)(Math.random() * cost);
        return cost;
    }
    
    /*
     *  Returns the number of tiles in 'a' that are displaced w.r.t 'b'
     */
    private int displacedTilesHeuristic(AstarNode a, AstarNode b, int heuristic)
    {
        boolean check_empty_tile = false;
        if(heuristic == DISPLACED_TILES || heuristic == DISPLACED_TILES_RANDOM)
            check_empty_tile = true;
        
        int cost = 0;
        EightTilesNode anode = (EightTilesNode) a;
        EightTilesNode bnode = (EightTilesNode) b;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {                   
                if ( check_empty_tile && (anode.getTileAt(i, j) == EightTilesNode.EMPTY_TILE) )
                    continue;
                if (anode.getTileAt(i, j) != bnode.getTileAt(i, j))                    
                        cost++;
            }
        }
        if(heuristic == DISPLACED_TILES_RANDOM || heuristic == DISPLACED_TILES_WITH_BLANK_RANDOM){
            cost = (int)(Math.random() * cost);
        }
        else if(heuristic == DISPLACED_TILES_INVALID)
            cost = 25 * (int)(Math.random() * cost);
        return cost;
    }

    @Override
    /*
     *  Either manhtattan distance or the displaced tiles heuristic
     */
    public int heuristicEstimate(AstarNode a, AstarNode b) {
        if(heuristic % 2 == 0)
            return manhattanHeuristic(a, b, heuristic); 
        else
            return displacedTilesHeuristic(a, b, heuristic); 
    }
    
    @Override
    public  Astar clone() {
        return new EightTiles(this);
    }
    
}
