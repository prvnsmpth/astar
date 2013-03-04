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
        
    public Dijkstra(ArrayList<AstarNode> n)
    {
        nodeList = n;
    }
    
    @Override
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        return 0;
    }
    
    public static void main(String Args[])
    {                
        DijkstraNode n1 = new DijkstraNode(1);        
        DijkstraNode n2 = new DijkstraNode(2);        
        DijkstraNode n3 = new DijkstraNode(3);        
        DijkstraNode n4 = new DijkstraNode(4);        
        DijkstraNode n5 = new DijkstraNode(5);
        DijkstraNode n6 = new DijkstraNode(6);
        
        n1.addSuccessor(n2);
        n1.addSuccessor(n3);
        n2.addSuccessor(n1);
        n2.addSuccessor(n3);
        n2.addSuccessor(n4);
        n2.addSuccessor(n5);
        n3.addSuccessor(n1);
        n3.addSuccessor(n2);
        n3.addSuccessor(n4);
        n3.addSuccessor(n5);
        n4.addSuccessor(n2);
        n4.addSuccessor(n3);
        n4.addSuccessor(n5);
        n4.addSuccessor(n6);
        n5.addSuccessor(n2);
        n5.addSuccessor(n3);
        n5.addSuccessor(n4);
        n5.addSuccessor(n6);
        n6.addSuccessor(n4);
        n6.addSuccessor(n5);
        
        ArrayList<AstarNode> l = new ArrayList<>();
        l.add(n1);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        l.add(n5);
        l.add(n6);
        
        Dijkstra d = new Dijkstra(l);
        d.setStart(n1);
        d.setGoal(n2);
        
        d.search();
        
    }
    
}
