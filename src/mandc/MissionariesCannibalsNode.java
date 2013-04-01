/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandc;

import astar.AstarNode;
import java.util.ArrayList;

/**
 *
 * @author Raghav
 */
public class MissionariesCannibalsNode extends AstarNode {
    protected static int N; // No of missionaries and cannibals
    protected static int boatSize;
    int missionaries;
    int cannibals;
    int boatPos; // initially pos is '1', final pos '0'
    
    public MissionariesCannibalsNode(){
        missionaries = N;
        cannibals = N;
        boatPos = 1;
    }
    public MissionariesCannibalsNode(int m, int c, int b){
        missionaries = m;
        cannibals = c;
        boatPos = b;
    }
    public MissionariesCannibalsNode( MissionariesCannibalsNode mc){
        missionaries = mc.getMissionaries();
        cannibals = mc.getCannibals();
        boatPos = mc.getBoatPos();
    }
    
    int getMissionaries() { return missionaries; }
    int getCannibals() { return cannibals; }
    int getBoatPos() { return boatPos; }
    
    public int getNewBoatPos(int boatPos){
        if(boatPos == 0) return 1;
        else if(boatPos == 1) return 0;
        else return -1;
    }
    public int operate(int a, int b, int boatPos){
        if(boatPos == 0) return a+b;
        else if(boatPos == 1) return a-b;
        else return a;
    }
    
    public boolean boundaryCondition(int missionaries, int cannibals){
        return (missionaries >=0 && missionaries <= N && cannibals >= 0 && cannibals <= N);
    }
    
    public boolean macCondition(int missionaries, int cannibals){
        if(missionaries == 0) return true;
        else if(missionaries == N) return true;
        else return(missionaries == cannibals);
    }
    
    @Override
    public ArrayList<AstarNode> computeSuccessors(){
        ArrayList<AstarNode> next = new ArrayList<>();
        int newBoatPos = getNewBoatPos(boatPos);
        int newMissionaries, newCannibals;
        
        for(int i=0; i<=boatSize; ++i){
            for(int j=0; j<=boatSize; ++j){
                if( (i+j <= boatSize) && (i | j) != 0 ) {
                    newMissionaries = operate(missionaries,i,boatPos);
                    newCannibals = operate(cannibals,j,boatPos);
                    if(boundaryCondition(newMissionaries, newCannibals) && macCondition(missionaries, cannibals))
                        next.add(new MissionariesCannibalsNode(newMissionaries, newCannibals, newBoatPos));
                }
            }
        }
        return next;
    }
    
    @Override
    public void printNode(){
        System.out.println("M = " + missionaries + " --------------------- " + "M = " + (N - missionaries));
        System.out.println("C = " + cannibals + " --------------------- " + "C = " + (N - cannibals));
        if(boatPos == 0)
            System.out.println("      --------------------- #####");
        else if(boatPos == 1)
            System.out.println("##### --------------------- ");
        System.out.println("");
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof MissionariesCannibalsNode)
        {
            MissionariesCannibalsNode mc = (MissionariesCannibalsNode) o;
            return  (
                    missionaries == mc.getMissionaries() &&
                    cannibals == mc.getCannibals() &&
                    boatPos == mc.getBoatPos() );
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.missionaries;
        hash = 97 * hash + this.cannibals;
        hash = 97 * hash + this.boatPos;
        return hash;
    }
    
    @Override
    public MissionariesCannibalsNode clone(){
        return new MissionariesCannibalsNode(this);
    }
    
}
