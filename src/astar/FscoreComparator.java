/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Comparator;

/**
 *
 * @author praveen
 */
public class FscoreComparator implements Comparator<AstarNode> {

    @Override
    public int compare(AstarNode a, AstarNode b) 
    {   
        if (a.fscore < b.fscore)
            return -1;        
        else if (a.fscore == b.fscore)        
            return 0;        
        else        
            return 1;        
    } 
}
