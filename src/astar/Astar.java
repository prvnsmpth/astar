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
    protected ArrayList<AstarNode> nodeList;
    
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
     *  specify start and goal nodes
     */
    public void setStart(AstarNode a)
    {
        start = a;
    }
    
    public void setGoal(AstarNode a)
    {
        goal = a;
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
                
                System.out.println("Search was successful. The path:\n");
                ArrayList<AstarNode> path = reconstructPath(curnode.predecessor, curnode);
                
                // print out the path
                Iterator<AstarNode> it = path.iterator();
                while (it.hasNext())
                {
                    AstarNode node = it.next();
                    node.printNode();
                    System.out.println("\n");
                }
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
                int temp_g_score = successor.gscore + successor.getSuccessorDistance(curnode);
             
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
                    successor.hscore = heuristicEstimate(successor, goal);
                    
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
    public ArrayList<AstarNode> reconstructPath(AstarNode parent, AstarNode node)
    {
        ArrayList<AstarNode> path;
        if (parent != null)       
            path = reconstructPath(parent.predecessor, parent);                    
        else        
            path = new ArrayList<>();        
        path.add(node);
        return path;
    }
    
    /*
     *  abstract methods to be over-ridden by implementations
     */
    public abstract int heuristicEstimate(AstarNode a, AstarNode b);
    
}
