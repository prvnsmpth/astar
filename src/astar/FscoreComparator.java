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
        int afscore = a.gscore + a.hscore;
        int bfscore = b.gscore + b.hscore;
        
        if (afscore < bfscore)
            return -1;        
        else if (afscore == bfscore)        
            return 0;        
        else        
            return 1;        
    }
    
    
    
}
