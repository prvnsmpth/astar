/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author praveen
 * 
 * An abstract graph node
 */
public abstract class AstarNode {
    
    /*
     *  scores
     */
    public int gscore;
    public int fscore;
    
    protected ArrayList<AstarNode> successors; 
    protected HashMap<AstarNode, Integer> successorDistance;
    
    /*
     *  predecessor node.
     *  Needed to retrace path once search completes.
     */
    public AstarNode predecessor;
    
    public AstarNode()
    {        
        successors = new ArrayList<>();
        successorDistance = new HashMap<>();
        predecessor = null;
    }
    
    public void addSuccessor(AstarNode a, int dist)
    {
        successors.add(a);
        successorDistance.put(a, dist);
    }
    
    public void setSuccessors(ArrayList<AstarNode> alist)
    {
        successors = alist;
    }
    
    public ArrayList<AstarNode> getSuccessors()
    {
        if (successors == null || successors.isEmpty())
        {
            ArrayList<AstarNode> succ = computeSuccessors();
            Iterator<AstarNode> i = succ.iterator();
            while (i.hasNext())
            {
                this.addSuccessor(i.next(), 1);
            }            
        }
        return successors;
    }
    
    public int getSuccessorDistance(AstarNode a)
    {
        return successorDistance.get(a);
    }
    
    /*
     *  abstract functions to be over-riden by implementations.
     */        
    public abstract ArrayList<AstarNode> computeSuccessors(); /* needed for blind search */
    public abstract void printNode(); /* A method to display the node */
    @Override
    public abstract boolean equals(Object o);
    @Override
    public abstract int hashCode();
    
}
