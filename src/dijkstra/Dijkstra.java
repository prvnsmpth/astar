/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import astar.Astar;
import astar.AstarNode;
import java.util.ArrayList;


/**
 *
 * @author raghavsagar
 */
public class Dijkstra extends Astar {
        
    public Dijkstra(ArrayList<AstarNode> n)
    {
        nodeList = n;
    }
    
    @Override
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        return 0;
    }
    
//    public static void main(String Args[])
//    {                
//        DijkstraNode n1 = new DijkstraNode(1);        
//        DijkstraNode n2 = new DijkstraNode(2);        
//        DijkstraNode n3 = new DijkstraNode(3);        
//        DijkstraNode n4 = new DijkstraNode(4);        
//        DijkstraNode n5 = new DijkstraNode(5);
//        DijkstraNode n6 = new DijkstraNode(6);
//        
//        n1.addSuccessor(n2, 1);
//        n1.addSuccessor(n3, 2);
//        n2.addSuccessor(n1, 1);
//        n2.addSuccessor(n3, 10);
//        n2.addSuccessor(n4, 40);
//        n2.addSuccessor(n5, 10);
//        n3.addSuccessor(n1, 2);
//        n3.addSuccessor(n2, 10);
//        n3.addSuccessor(n4, 3);
//        n3.addSuccessor(n5, 6);
//        n4.addSuccessor(n2, 40);
//        n4.addSuccessor(n3, 3);
//        n4.addSuccessor(n5, 1);
//        n4.addSuccessor(n6, 7);
//        n5.addSuccessor(n2, 10);
//        n5.addSuccessor(n3, 6);
//        n5.addSuccessor(n4, 1);
//        n5.addSuccessor(n6, 8);
//        n6.addSuccessor(n4, 7);
//        n6.addSuccessor(n5, 8);
//        
//        ArrayList<AstarNode> l = new ArrayList<>();
//        l.add(n1);
//        l.add(n2);
//        l.add(n3);
//        l.add(n4);
//        l.add(n5);
//        l.add(n6);
//        
//        Dijkstra d = new Dijkstra(l);
//        d.setStart(n1);
//        d.setGoal(n6);
//        
//        d.search();
//        
//    }
    
}
