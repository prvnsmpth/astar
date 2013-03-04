/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author praveen
 * 
 * An abstract class for Astar graph search
 */
public abstract class Astar {
    
    /*
     *  the start and goal nodes. Implementations of Astar will have to 
     *  initialize these to appropriate values
     */
    AstarNode start;
    AstarNode goal;
    
    /*
     *  node list.
     *  This will be needed for an informed search implementation
     */
    ArrayList<AstarNode> nodeList;
    
    /*
     *  the open and closed lists
     */
    PriorityQueue<AstarNode> openList;
    ArrayList<AstarNode> closedList;
    FscoreComparator comparator;
    
    /*
     *  Initialize scores for all nodes
     */
    public void init()
    {
        
    }
    
    /*
     *  Search for the goal node using Astar search algorithm.
     *  Returns true on successful search, false on failure 
     */
    public boolean search()
    {        
        comparator = new FscoreComparator();
        openList = new PriorityQueue<>(1, comparator);                
        closedList = new ArrayList<>();
        openList.add(start);
        
        while (!openList.isEmpty())
        {
            AstarNode curnode = openList.poll();
            
            if (curnode.equals(goal)) {
                
                System.out.println("The search was successful.");
                ArrayList<AstarNode> path = reconstructPath();
                
                // print out the path
                return true;
            }
                        
            closedList.add(curnode);
            
            /* 
             * get successors of curnode; and set all their scores             
             */
            ArrayList<AstarNode> successors = curnode.getSuccessors();
            Iterator<AstarNode> it = successors.iterator();
            while (it.hasNext())
            {
                AstarNode successor = it.next();
                int temp_g_score = successor.gscore + successor.distance(curnode);
             
                /*
                 * check if successor is in the closedList, and already has a better
                 * distance-from-start score
                 */
                if (closedList.contains(successor) && temp_g_score >= successor.gscore)
                    continue;
                
                /*
                 * we update the g and h scores if the successor has not been 
                 * seen yet or we have found a better gscore
                 */
                if (!openList.contains(successor) || temp_g_score < successor.gscore)
                {
                    successor.predecessor = curnode;
                    successor.gscore = temp_g_score;
                    successor.hscore = successor.heuristicEstimate(goal);
                    
                    // if not in openList, add
                    if (!openList.contains(successor))
                        openList.add(successor);                                        
                }
                
            }
            
        }
        
        
        return false;
    }
    
    /*
     *  reconstruct the path from start to goal
     */
    public ArrayList<AstarNode> reconstructPath()
    {
        
        return null;
    }
    
    /*
     *  abstract methods to be over-ridden by implementations
     */
    public abstract int heuristicEstimate(AstarNode a, AstarNode b);
    
}
