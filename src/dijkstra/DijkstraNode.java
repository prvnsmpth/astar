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
        if (o instanceof DijkstraNode){
            DijkstraNode node = (DijkstraNode) o;
            return (node.id == this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        return hash;
    }

}
