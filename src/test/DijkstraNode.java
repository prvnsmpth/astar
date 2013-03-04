/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.AstarNode;
import java.util.ArrayList;

/**
 *
 * @author raghavsagar
 */
public class DijkstraNode extends AstarNode {
    
    public int id;    
    
    public DijkstraNode(int id)
    {
        this.id = id; 
        successors = new ArrayList<>();
    }    
    
    @Override
    public ArrayList<AstarNode> computeSuccessors() 
    {
        return successors;
    }

    @Override
    public boolean equals(AstarNode a) 
    {
        DijkstraNode d = (DijkstraNode) a;
        return id == d.id;
    }

    @Override
    public void printNode() 
    {
        System.out.println("Node <" + id + ">");
    }

    @Override
    public int distance(AstarNode a) 
    {
        return 1;
    }
}
