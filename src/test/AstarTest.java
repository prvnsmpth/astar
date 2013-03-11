/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import eightiles.EightTiles;
import eightiles.EightTilesNode;
import java.util.ArrayList;
import java.util.Arrays;
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
        
        Pair<Integer, Integer> r = new Pair<>(0, 0);
        Pair<Integer, Integer> t = new Pair<>(7, 5);
        RobotNavigation R = new RobotNavigation(10, r, t, RobotNavigation.MANHATTAN);                
        
        R.setWall(new Pair<> (1, 1), new Pair<>(8,1));
        R.setWall(new Pair<> (3, 3), new Pair<>(9,3));
        R.setWall(new Pair<> (5, 7), new Pair<>(5,1));
        R.setWall(new Pair<> (5, 8), new Pair<>(5,2));
        R.setWall(new Pair<> (4, 1), new Pair<>(8,1));       
        
        R.search();
    }
    
}
