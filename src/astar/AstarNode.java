/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;

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
    public int hscore;
    
    public boolean isStartNode;
    public boolean isGoalNode;
    ArrayList<AstarNode> successors; 
    
    /*
     *  predecessor node.
     *  Needed to retrace path once search completes.
     */
    public AstarNode predecessor;
    
    public void setSuccessors(ArrayList<AstarNode> alist)
    {
        successors = alist;
    }
    
    public ArrayList<AstarNode> getSuccessors()
    {
        if (successors == null)
            successors = computeSuccessors();
        return successors;
    }
    
    /*
     *  abstract functions to be over-riden by implementations.
     */        
    public abstract ArrayList<AstarNode> computeSuccessors(); /* needed for blind search */
    public abstract boolean equals(AstarNode a);
    public abstract int distance(AstarNode a);
    public abstract void printNode(); /* A method to display the node */
    
}
