/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import utils.Pair;

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
     *  the open and closed lists
     */
    PriorityQueue<AstarNode> openList;
    ArrayList<AstarNode> closedList;
    FscoreComparator comparator;
    
    /*
     *  some useful variables
     */    
    int noOfExpandedNodes;
    int noOfRedirections;
    
    public Astar()
    {
        noOfExpandedNodes = 0;
        noOfRedirections = 0;
        comparator = new FscoreComparator();
        openList = new PriorityQueue<>(1, comparator);                
        closedList = new ArrayList<>();
    }
    
    /*
     *  specify start and goal nodes
     */
    public void setStart(AstarNode a) { start = a; }    
    public void setGoal(AstarNode a) { goal = a; }    
    
    /*
     *  build the node list
     *  Useful for informed search
     */
    public void buildNodeList(HashMap<Pair<AstarNode, AstarNode>, Integer> edges)            
    {
        Iterator<Entry<Pair<AstarNode, AstarNode>, Integer>> it 
                = edges.entrySet().iterator();
        while (it.hasNext())
        {
            Entry<Pair<AstarNode, AstarNode>, Integer> en = it.next();
            AstarNode a = en.getKey().getFirst();
            AstarNode b = en.getKey().getSecond();
            Integer c = en.getValue();
            
            a.addSuccessor(b, c);
            b.addSuccessor(a, c);
        }
    }
    
    
    /*
     *  Search for the goal node using the A* search algorithm.
     *  Returns true on successful search, false on failure 
     */
    public boolean search()
    {                
        openList.add(start);
        
        System.out.println("\nStart:");
        start.printNode();
        System.out.println("\nGoal:");
        goal.printNode();
        
        while (!openList.isEmpty())
        {
            AstarNode curnode = openList.poll();
            if (curnode.equals(goal)) 
            {            
                ArrayList<AstarNode> path = reconstructPath(curnode.predecessor, curnode);
                System.out.print("\nSearch was successful."
                        + "\nNumber of nodes expanded: " + noOfExpandedNodes
                        + "\nNumber of parent pointer redirections: " + noOfRedirections
                        +"\nThe path (length " + curnode.gscore + "): \n\n");                
                
                /* print out the path */
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
            noOfExpandedNodes++;
            
            /* 
             * get successors of curnode; and set all their scores             
             */
            ArrayList<AstarNode> successors = curnode.getSuccessors();
            Iterator<AstarNode> it = successors.iterator();
            while (it.hasNext())
            {
                AstarNode successor = it.next();
                int temp_g_score = curnode.gscore + curnode.getSuccessorDistance(successor);
             
                /*
                 * check if successor is in the closedList, and already has a better
                 * distance-from-start score
                 */
                if (closedList.contains(successor) && temp_g_score >= successor.gscore)
                {
                    continue;
                }
                
                /*
                 *  we update the g and h scores if the successor has not been 
                 *  seen yet or we have found a better gscore
                 */
                if (!openList.contains(successor) || temp_g_score < successor.gscore)
                {
                    if (closedList.contains(successor) && temp_g_score < successor.gscore)
                    {
                        /* increment redirection count */
                        noOfRedirections++;
                    }
                    
                    successor.predecessor = curnode;
                    successor.gscore = temp_g_score;
                    successor.fscore = temp_g_score + heuristicEstimate(successor, goal);                       
                    
                    /* if not in openList, add */
                    if (!openList.contains(successor))
                        openList.add(successor);                                        
                }
                
            }                
        }
        
        /* Search failed */
        System.out.println("\nGoal cannot be reached from the given start node.\n");
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
