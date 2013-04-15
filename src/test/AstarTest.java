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
import mandc.MissionariesCannibals;
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
    private static final int MAC = 3;
    
    public static void main(String[] args)
    {
        
        EightTiles E = new EightTiles(
                new ArrayList<>(Arrays.asList(1,7,3,5,6,8,2,EightTilesNode.EMPTY_TILE,4)), 
                EightTiles.DISPLACED_TILES);
        
        EightTiles E2 = new EightTiles(
                new ArrayList<>(Arrays.asList(1,7,3,5,6,8,2,EightTilesNode.EMPTY_TILE,4)), 
                EightTiles.MANHATTAN);
        
        /*
        EightTiles E = new EightTiles(
                new ArrayList<>(Arrays.asList(7,1,3,5,2,6,EightTilesNode.EMPTY_TILE,4,8)), 
                EightTiles.DISPLACED_TILES_RANDOM);
        
        EightTiles E2 = new EightTiles(
                new ArrayList<>(Arrays.asList(7,1,3,5,2,6,EightTilesNode.EMPTY_TILE,4,8)), 
                EightTiles.MANHATTAN_RANDOM);
        */
        HashMap<Integer, AstarNode> nodes = new HashMap();
        DijkstraNode[] D = new DijkstraNode[5];
        for(int i=0; i<5; ++i){
            D[i] = new DijkstraNode(i);
            nodes.put(i,D[i]);
        }
        HashMap<Pair<Integer, Integer>, Integer> edges = new HashMap();
        
        edges.put(new Pair(0 , 1), 3);
        edges.put(new Pair(0 , 2), 1);
        edges.put(new Pair(1 , 2), 1);
        edges.put(new Pair(1 , 3), 2);
        edges.put(new Pair(3 , 4), 3);
        edges.put(new Pair(2 , 4), 100);
        /*
        edges.put(new Pair(0 , 1), 10);
        edges.put(new Pair(0 , 2), 1);
        edges.put(new Pair(2 , 3), 1);
        edges.put(new Pair(1 , 3), 10);
        edges.put(new Pair(3 , 4), 50);
        */
        
        Dijkstra dij = new Dijkstra(nodes, edges, D[0], D[4]);
        
        Pair<Integer, Integer> r = new Pair<>(0, 0);
        Pair<Integer, Integer> t = new Pair<>(9, 9);
        RobotNavigation R = new RobotNavigation(10, r, t, RobotNavigation.MANHATTAN);

        R.setWall(new Pair(1, 1), new Pair(8, 1));
        R.setWall(new Pair(3, 1), new Pair(3, 4));
        R.setWall(new Pair(5, 7), new Pair(1, 7));
        R.setWall(new Pair(6, 6), new Pair(1, 6));
        R.setWall(new Pair(1, 4), new Pair(8, 4));
    
        MissionariesCannibals MC = new MissionariesCannibals(3,2,MissionariesCannibals.PEOPLE_BOAT_CAP_RATIO);
        
        int test = EP;
        
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
                dij.search(2,4);
                break;
            case MAC:
                MC.bisearch();
                break;    
        }              
    }
    
}
