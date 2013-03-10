/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import astar.AstarNode;
import java.util.ArrayList;
import java.util.Objects;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class SearchState extends AstarNode {
    
    static int[][] grid;
    static int gridSize;
    static Pair<Integer, Integer> targetCoords;
    private Pair<Integer, Integer> robotCoords;
    static final int BLOCKED = 0;
    static final int OPEN = 1;
    static char westDir  = '⇐'; 
    static char northDir = '⇑'; 
    static char eastDir  = '⇒'; 
    static char southDir = '⇓'; 
    static char nwDir    = '⇖'; 
    static char neDir    = '⇗'; 
    static char seDir    = '⇘'; 
    static char swDir    = '⇙'; 
    
    public SearchState(Pair<Integer, Integer> robotCoords)
    {
        this.robotCoords = robotCoords;
    }
    
    @Override
    public ArrayList<AstarNode> computeSuccessors()
    {
        ArrayList<AstarNode> next = new ArrayList<>();
        int row = robotCoords.getFirst();
        int col = robotCoords.getSecond();
        
        if (row < gridSize - 1)
        {
            if (grid[row + 1][col] != BLOCKED)
            {
                SearchState south =
                        new SearchState(new Pair<> (row + 1, col));
                next.add(south);
            }
        }
        if (row < gridSize - 1 && col < gridSize - 1)
        {
            if (grid[row + 1][col + 1] != BLOCKED)
            {
                SearchState southEast =
                        new SearchState(new Pair<> (row + 1, col + 1));
                next.add(southEast);
            }
        }
        if (row < gridSize - 1 && col > 0)
        {
            if (grid[row + 1][col - 1] != BLOCKED)
            {
                SearchState southWest =
                        new SearchState(new Pair<> (row + 1, col - 1));
                next.add(southWest);
            }
        }
        if (row > 0)
        {
            if (grid[row - 1][col] != BLOCKED)
            {
                SearchState north =
                        new SearchState(new Pair<> (row - 1, col));
                next.add(north);
            }
        }
        if (row > 0 && col < gridSize - 1)
        {
            if (grid[row - 1][col + 1] != BLOCKED)
            {
                SearchState northEast =
                        new SearchState(new Pair<> (row - 1, col + 1));
                next.add(northEast);
            }
        }
        if (row > 0 && col > 0)
        {
            if (grid[row - 1][col - 1] != BLOCKED)
            {
                SearchState northWest =
                        new SearchState(new Pair<> (row - 1, col - 1));
                next.add(northWest);
            }
        }
        if (col > 0)
        {
            if (grid[row][col - 1] != BLOCKED)
            {
                SearchState west =
                        new SearchState(new Pair<> (row, col - 1));
                next.add(west);
            }
        }
        if (col < gridSize - 1)
        {
            if (grid[row][col + 1] != BLOCKED)
            {
                SearchState west =
                        new SearchState(new Pair<> (row, col + 1));
                next.add(west);
            }
        }
        
        return next;
    }
    
    public Pair<Integer, Integer> getRobotCoords()
    {
        return robotCoords;
    }
    
    public char orientation(SearchState a, SearchState b)
    {
        Pair<Integer, Integer> ac = a.getRobotCoords();
        Pair<Integer, Integer> bc = b.getRobotCoords();
        
        int r1 = ac.getFirst();
        int c1 = ac.getSecond();
        int r2 = bc.getFirst();
        int c2 = bc.getSecond();
        
        if (r1 == r2)
        {
            if (c1 < c2)
                return eastDir;
            else
                return westDir;
        }
        else if (r1 == r2 - 1)
        {
            if (c1 == c2)
                return southDir;
            else if (c1 < c2)
                return seDir;
            else
                return swDir;
        }
        else if (r1 == r2 + 1)
        {
            if (c1 == c2)
                return northDir;
            else if (c1 < c2)
                return neDir;
            else
                return nwDir;
        }
        
        return 'a';
    }
    
    @Override
    public void printNode()
    {
        // mark all ancestor directions
        AstarNode pred = predecessor;
        SearchState _this = this;
        while (pred != null)
        {
            SearchState s = (SearchState) pred;            
            Pair<Integer, Integer> c = s.getRobotCoords();
            grid[c.getFirst()][c.getSecond()] = (int) orientation(s, _this);
            _this = s;
            pred = s.predecessor;
        }
        
        String sep = "+";
        for (int i = 0; i < gridSize; i++)
            sep += "---+";
        System.out.println(sep);
        for (int i = 0; i < gridSize; i++)
        {
            System.out.print("|");
            for (int j = 0; j < gridSize; j++)
            {
                if (i == robotCoords.getFirst() && j == robotCoords.getSecond())
                    System.out.print(" R ");
                else if (i == targetCoords.getFirst() && j == targetCoords.getSecond())
                    System.out.print(" ✚ ");
                else if (grid[i][j] == BLOCKED)
                    System.out.print(" ● ");
                else if (grid[i][j] > 1)
                    System.out.print(" " + (char) grid[i][j] + " ");
                else if (grid[i][j] == OPEN)
                    System.out.print("   ");
                
                System.out.print("|");
            }
            System.out.println("\n" + sep);
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof SearchState)
        {
            SearchState s = (SearchState) o;
            return robotCoords.equals(s.robotCoords);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.robotCoords);
        return hash;
    }

}
