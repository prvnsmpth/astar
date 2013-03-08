/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import astar.AstarNode;
import java.util.ArrayList;
import java.util.HashMap;

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
        successorDistance = new HashMap<>();
    }    
    
    @Override
    public ArrayList<AstarNode> computeSuccessors() 
    {
        return successors;
    }

    @Override
    public void printNode() 
    {
        System.out.print("Node <" + id + ">");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
