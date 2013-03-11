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
    
    public static void main(String[] args)
    {
        /*
        EightTiles E = new EightTiles(
                new ArrayList<>(Arrays.asList(6,5,EightTilesNode.EMPTY_TILE,8,7,4,2,1,3)), 
                EightTiles.DISPLACED_TILES);
        
        // search using displaced tiles heuristic
        System.out.println("Searching using displaced tiles heuristic");
        System.out.println("-----------------------------------------");
        E.search();
        
        // search using manhattan distance heuristic
        System.out.println("Searching using manhattan distance heuristic");
        System.out.println("--------------------------------------------");
        E.setHeuristic(EightTiles.MANHATTAN);        
        E.search();        
        */
        
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
        dij.search();

        
//        Pair<Integer, Integer> r = new Pair<>(0, 0);
//        Pair<Integer, Integer> t = new Pair<>(9, 0);
//        RobotNavigation R = new RobotNavigation(10, r, t, RobotNavigation.STRAIGHT_LINE);
//        
//        ArrayList<Pair<Integer, Integer>> wall1 = new ArrayList<>();
//        ArrayList<Pair<Integer, Integer>> wall2 = new ArrayList<>();
//        ArrayList<Pair<Integer, Integer>> wall3 = new ArrayList<>();
//        for (int i = 1; i <= 4; i++)
//            wall1.add(new Pair<>(i, 3));
//        for (int i = 0; i <= 3; i++)
//            wall2.add(new Pair<>(i, 1));
//        for (int i = 0; i <= 5; i++)            
//                wall3.add(new Pair<>(5, i));
//        
//        R.setWall(wall1);
//        R.setWall(wall2);
//        R.setWall(wall3);
//        
//        R.search();
    }
    
}
