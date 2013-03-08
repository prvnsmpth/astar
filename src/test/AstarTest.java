/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import eightiles.EightTiles;
import eightiles.EightTilesNode;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author praveen
 */
public class AstarTest {        
    
    public static void main(String[] args)
    {
        EightTiles E = new EightTiles();
        //EightTiles E = new EightTiles(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,EightTilesNode.EMPTY_TILE)));
        E.search();
    }
    
}
