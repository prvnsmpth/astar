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
    
    public EightTiles()
    {
        EightTilesNode node = new EightTilesNode();
        this.setStart(node);
        
        EightTilesNode gnode = new EightTilesNode(new ArrayList<> 
                (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EightTilesNode.EMPTY_TILE)));
        this.setGoal(gnode);
    }
    
    public EightTiles(ArrayList<Integer> config)
    {
        EightTilesNode node = new EightTilesNode(config);
        this.setStart(node);
        
        EightTilesNode gnode = new EightTilesNode(new ArrayList<> 
                (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EightTilesNode.EMPTY_TILE)));
        this.setGoal(gnode);
    }

    @Override
    /*
     *  Based on Manhattan Distance of tiles
     */
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        int cost = 0;
        EightTilesNode anode = (EightTilesNode) a;
        EightTilesNode bnode = (EightTilesNode) b;
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {                                                
                int tile = anode.getTileAt(i, j);
                Pair<Integer, Integer> coord = bnode.getCoordinates(tile);
                cost += Math.abs(coord.getFirst() - i) + Math.abs(coord.getSecond() - j);
            }
        }
        
        return cost;
    }
    
}
