/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.Astar;
import astar.AstarNode;
import java.util.ArrayList;


/**
 *
 * @author raghavsagar
 */
public class Dijkstra extends Astar {

    Dijkstra(ArrayList<AstarNode> n){
        init(n);
    }
    @Override
    public int heuristicEstimate(AstarNode a, AstarNode b) {
        return 0;
    }
    public static void main(String Args[]){
        
        //new Dijkstra();
    }
    
}
