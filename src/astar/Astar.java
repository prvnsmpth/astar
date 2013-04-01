/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    protected HashMap<Integer, AstarNode> nodes;
    protected HashMap<Pair<Integer, Integer>, Integer> edges;
    private static final int FORWARD = 1;
    private static final int BACKWARD = 0;
    
    /*
     *  the open and closed lists
     */
    PriorityQueue<AstarNode> openList;
    HashSet<AstarNode> closedList;
    AstarNode lastAddedClosedList;
    FscoreComparator comparator;
    
    /*
     *  some useful variables
     */    
    public int noOfExpandedNodes;
    public int noOfRedirections;
    public int pathLength;
    public int search1Mode;
    
    public Astar()
    {
        noOfExpandedNodes = 0;
        noOfRedirections = 0;
        search1Mode = 0;
        nodes = new HashMap<>();
        comparator = new FscoreComparator();
        openList = new PriorityQueue<>(1, comparator);                
        closedList = new HashSet<>();
        lastAddedClosedList = null;
    }
    
    /*
     *  specify start and goal nodes
     */
    public void setStart(AstarNode a) { start = a; }
    public void setGoal(AstarNode a) { goal = a; }
    public AstarNode getStart() { return start; }
    public AstarNode getGoal() { return goal; }
    public AstarNode getLastAddedClosedList() { return lastAddedClosedList; }
    public boolean closedListContains(AstarNode a) { return closedList.contains(a); }
    public HashMap<Integer, AstarNode> getNodes() { return nodes; }
    public HashMap<Pair<Integer, Integer>, Integer> getEdges() { return edges; }
    /*
     *  build the node list
     *  Useful for informed search
     */
    public void buildNodeList(HashMap<Pair<Integer, Integer>, Integer> edges)
    {
        Iterator<Entry<Pair<Integer, Integer>, Integer>> it
                = edges.entrySet().iterator();
        while (it.hasNext())
        {
            Entry<Pair<Integer, Integer>, Integer> en = it.next();
            AstarNode a = getAstarNodeId(en.getKey().getFirst());
            AstarNode b = getAstarNodeId(en.getKey().getSecond());
            Integer c = en.getValue();
            a.addSuccessor(b, c);
            b.addSuccessor(a, c);
        }
    }
    
    public AstarNode getAstarNodeId(int id){
        if(! nodes.isEmpty())
            return nodes.get(id);
        return null;
    }
    
    public void swap(){
        AstarNode temp;
        temp = start; start = goal; goal = temp;
    }
    
    
    /*
     *  Search for the goal node using the A* search algorithm.
     *  Returns true on successful search, false on failure 
     */
    public boolean search()
    {
        openList.clear();
        openList.add(start);
        search1Mode = 1;
        
        System.out.println("\nStart:");
        start.printNode();
        System.out.println("\nGoal:");
        goal.printNode();
        
        while (!openList.isEmpty()) {
            if(search1()){
                search1Mode = 0;
                return true;
            }                
        }
        search1Mode = 0;
        /* Search failed */
        System.out.println("\nGoal cannot be reached from the given start node.\n");
        return true;
    }
    
    public int search(int start, int goal){
        Astar astar = this.clone();
        astar.setStart( astar.getAstarNodeId(start) );
        astar.setGoal( astar.getAstarNodeId(goal) );
        astar.search();
        return astar.pathLength;
    }
    
    public boolean bisearch(){
        Astar astar = this.clone();
        astar.swap();
        System.out.print("\nStart:\n");
        start.printNode();
        System.out.print("\nGoal:\n");
        goal.printNode();
        
        while(true){
            this.search1();
            if( astar.closedListContains( this.getLastAddedClosedList() ) ) break;
            astar.search1();
            if( this.closedListContains( astar.getLastAddedClosedList() ) ) break;
        }
        System.out.print("\n\nSearch was successful.\nOptimal Path is:\n\n");
        
        System.out.print("\nSearch was successful."
                        + "\nNumber of nodes expanded from start: " + noOfExpandedNodes
                        + "\nNumber of parent pointer redirections from start: " + noOfRedirections
                        + "\nNumber of nodes expanded from goal: " + astar.noOfExpandedNodes
                        + "\nNumber of parent pointer redirections from start: " + astar.noOfRedirections
                        + "\n\n");
        AstarNode node;
        ArrayList<AstarNode> path = reconstructPath(this.getLastAddedClosedList().predecessor, this.getLastAddedClosedList());
        
        Iterator<AstarNode> it = path.iterator();
        int size = -2;
        while (it.hasNext())
        {                    
            node = it.next();
            size++;
            node.printNode();
            System.out.print("\n");
        }
        System.out.print("Merged here\n");
        node = astar.getLastAddedClosedList();
        while(node != null){
            size++;
            node.printNode();   
            System.out.print("\n");
            node = node.predecessor;
        }
        node = getLastAddedClosedList();
        if( node.equals( astar.getLastAddedClosedList() ) ){
            System.out.println("Path Length is " + size);
            pathLength = size;
        }
        else
            System.out.println("Path Length is not defined as path is not along merged node");
        return true;
    }
    
    public boolean search1()
    {
        if(search1Mode == 0){
            openList.clear();
            openList.add(start);
            search1Mode = 1;
            return search1();
        }
        else{
            AstarNode curnode = openList.poll();
            if (curnode.equals(goal))
            {            
                ArrayList<AstarNode> path = reconstructPath(curnode.predecessor, curnode);
                System.out.print("\nSearch was successful."
                        + "\nNumber of nodes expanded: " + noOfExpandedNodes
                        + "\nNumber of parent pointer redirections: " + noOfRedirections
                        +"\nThe path (length " + curnode.gscore + "): \n\n");                
                this.pathLength = curnode.gscore;
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
            
            lastAddedClosedList = curnode;
            closedList.add(curnode);
            noOfExpandedNodes++;
            
            /* 
             * get successors of curnode; and set all their scores    
             * 
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
                    continue;                
                
                /*
                 *  we update the g and h scores if the successor has not been 
                 *  seen yet or we have found a better gscore
                 */
                if (!openList.contains(successor) || temp_g_score < successor.gscore)
                {
                    if (closedList.contains(successor) && temp_g_score < successor.gscore) {
                        closedList.remove(successor);
                        noOfRedirections++; /* increment redirection count */
                    }
                    successor.predecessor = curnode;
                    successor.gscore = temp_g_score;
                    successor.fscore = temp_g_score + heuristicEstimate(successor, goal);                       
                    
                    /* if not in openList, add */
                    if (!openList.contains(successor))
                        openList.add(successor);                                        
                }
            }
            return false;
        }
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
    
    @Override
    public abstract Astar clone();
    
}
