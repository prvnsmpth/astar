/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.AstarNode;
import dijkstra.Dijkstra;
import dijkstra.DijkstraNode;
import eightiles.EightTiles;
import eightiles.EightTilesNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import robot.RobotNavigation;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class AstarTest {  
    
    private static final int ROBOT = 0;
    private static final int DIJK = 1;
    private static final int EP = 2;
    
    public static void main(String[] args)
    {
        
        EightTiles E = new EightTiles(
                new ArrayList<>(Arrays.asList(1,7,3,5,6,8,2,EightTilesNode.EMPTY_TILE,4)), 
                EightTiles.DISPLACED_TILES);    
        
        EightTiles E2 = new EightTiles(
                new ArrayList<>(Arrays.asList(1,7,3,5,6,8,2,EightTilesNode.EMPTY_TILE,4)), 
                EightTiles.MANHATTAN);          
        
        
        ArrayList<AstarNode> nodes = new ArrayList();
        DijkstraNode[] D = new DijkstraNode[5];
        for(int i=0; i<5; ++i){
            D[i] = new DijkstraNode(i);
            nodes.add(D[i]);
        }
        HashMap<Pair<AstarNode, AstarNode>, Integer> edges = new HashMap();
        edges.put(new Pair(D[0] , D[1]), 3);
        edges.put(new Pair(D[0] , D[2]), 1);
        edges.put(new Pair(D[1] , D[2]), 1);
        edges.put(new Pair(D[1] , D[3]), 2);
        edges.put(new Pair(D[3] , D[4]), 3);
        edges.put(new Pair(D[2] , D[4]), 100);
        Dijkstra dij = new Dijkstra(nodes);
        dij.setStart(D[0]);
        dij.setGoal(D[4]);
        dij.buildNodeList(edges);        

        
        Pair<Integer, Integer> r = new Pair<>(0, 0);
        Pair<Integer, Integer> t = new Pair<>(9, 9);
        RobotNavigation R = new RobotNavigation(10, r, t, RobotNavigation.MANHATTAN);

        R.setWall(new Pair(1, 1), new Pair(8, 1));
        R.setWall(new Pair(3, 1), new Pair(3, 4));
        R.setWall(new Pair(5, 7), new Pair(1, 7));
        R.setWall(new Pair(6, 6), new Pair(1, 6));
        R.setWall(new Pair(1, 4), new Pair(8, 4));
        
        
        int test = ROBOT;
        
        switch(test)
        {
            case ROBOT:
                R.search();
                break;
            case EP:
                System.out.println("Searching using displaced tiles heuristic");
                System.out.println("-----------------------------------------");
                E.search();
                System.out.println("Searching using manhattan distance heuristic");
                System.out.println("--------------------------------------------");
                E2.search();
                break;
            case DIJK:
                dij.search();
                break;
        }
                        
    }
    
}
