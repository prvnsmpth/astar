/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import eightiles.EightTiles;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author praveen
 */
public class AstarTest {        
    
    public static void main(String[] args)
    {
        //EightTiles E = new EightTiles(EightTiles.MANHATTAN);
        EightTiles E = new EightTiles(
                new ArrayList<>(Arrays.asList(6,5,0,8,7,4,2,1,3)), 
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
    }
    
}
