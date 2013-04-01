/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import astar.Astar;
import astar.AstarNode;
import java.util.HashMap;
import java.util.Map;
import utils.Pair;


/**
 *
 * @author raghavsagar
 */
public class Dijkstra extends Astar {
        
    public Dijkstra(HashMap<Integer, AstarNode> n, HashMap<Pair<Integer, Integer>, Integer> e, AstarNode s, AstarNode g) {
        nodes = n; edges = e;
        setStart(s);
        setGoal(g);
        buildNodeList(e);
    }
    
    public Dijkstra(Dijkstra dij) {
        edges = dij.getEdges();
        nodes.clear(); 
        for (Map.Entry<Integer, AstarNode> entry : dij.getNodes().entrySet()) {
            AstarNode n = entry.getValue();
            AstarNode cl = n.clone();
            nodes.put( entry.getKey(), cl );
            
            if(n == dij.getStart()) this.setStart(cl);
            else if(n == dij.getGoal()) this.setGoal(cl);
        }
        buildNodeList(edges);
    }
    
    @Override
    public int heuristicEstimate(AstarNode a, AstarNode b) 
    {
        /*
        if(a.getId() == 0 && b.getId() == 4)
            return 0;
        if(a.getId() == 1 && b.getId() == 4)
            return 0;
        if(a.getId() == 2 && b.getId() == 4)
            return 50;
        if(a.getId() == 3 && b.getId() == 4)
            return 0;
        if(a.getId() == 4 && b.getId() == 4)
            return 0;
        */
        return 0;
        
    }
    
    @Override
    public  Astar clone(){
        return new Dijkstra(this);
    }    
}
