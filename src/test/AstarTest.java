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
