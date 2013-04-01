/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightiles;

import astar.AstarNode;
import java.util.*;
import utils.Pair;

/**
 *
 * @author praveen
 */
public class EightTilesNode extends AstarNode {
    
    public static final int EMPTY_TILE = 0;
    private static final int EDGE = 2;
    int[][] board;                         
    
    public EightTilesNode()
    {
        board = new int[3][3];
        
        ArrayList<Integer> list = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EMPTY_TILE));
        
        Collections.shuffle(list);
        
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = it.next(); 
    }
    
    public EightTilesNode(List<Integer> list)
    {
        board = new int[3][3];
        
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = it.next();
    }
    public EightTilesNode(EightTilesNode node){
        board = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = node.getTileAt(i, j);
    }
    
    public int getTileAt(int i, int j)
    {
        return board[i][j];
    }
    
    public Pair<Integer, Integer> getCoordinates(int tile)
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == tile)
                    return new Pair<>(i, j);
        
        return null;
    }
    
    public ArrayList<Integer> getCurrentPositionAsList()
    {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                list.add(board[i][j]);
        return list;
    }

    @Override
    /*
     *  Generate all possible board positions starting from the
     *  current position
     */
    public ArrayList<AstarNode> computeSuccessors() 
    {
        ArrayList<AstarNode> list = new ArrayList<>();
        Pair<Integer, Integer> coord = this.getCoordinates(EMPTY_TILE);        
        int i = coord.getFirst();
        int j = coord.getSecond();
        ArrayList<Integer> curpos = this.getCurrentPositionAsList();
        
        if (j > 0)
        {
            ArrayList<Integer> newpos = new ArrayList<>(curpos);
            Collections.swap(newpos, j + i * 3, j + i * 3 - 1);
            list.add(new EightTilesNode(newpos));
        }
        
        if (j < EDGE)
        {
            ArrayList<Integer> newpos = new ArrayList<>(curpos);
            Collections.swap(newpos, j + i * 3, j + i * 3 + 1);
            list.add(new EightTilesNode(newpos));
        }
        
        if (i > 0)
        {
            ArrayList<Integer> newpos = new ArrayList<>(curpos);
            Collections.swap(newpos, j + i * 3, j + (i - 1) * 3);
            list.add(new EightTilesNode(newpos));
        }
        
        if (i < EDGE)
        {
            ArrayList<Integer> newpos = new ArrayList<>(curpos);
            Collections.swap(newpos, j + i * 3, j + (i + 1) * 3);
            list.add(new EightTilesNode(newpos));
        }
        
        return list;
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof EightTilesNode)
        {
            EightTilesNode node = (EightTilesNode) o;
        
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] != node.getTileAt(i, j))
                        return false;
        
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }

    @Override
    public void printNode() 
    {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++)
        {
            System.out.print("|");
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] != 0)
                    System.out.print(" " + board[i][j] + " ");
                else
                    System.out.print(" X ");
                System.out.print("|");
            }
            System.out.println("\n+---+---+---+");
        }                
    }
    
    @Override
    public EightTilesNode clone(){
        return new EightTilesNode(this);
    }
}
