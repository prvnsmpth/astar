/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandc;

import astar.Astar;
import astar.AstarNode;

/**
 *
 * @author Raghav
 */
public class MissionariesCannibals extends Astar{
    public static final int NO_OF_PEOPLE = 0;
    public static final int PEOPLE_BOAT_CAP_RATIO = 1;
    private int heuristic;
    
    public MissionariesCannibals(int heuristic){
        MissionariesCannibalsNode.N = 3;
        MissionariesCannibalsNode.boatSize = 2;
        this.setStart( new MissionariesCannibalsNode() );
        this.setGoal( new MissionariesCannibalsNode(0,0,0) );
        this.heuristic = heuristic;
    }
    public MissionariesCannibals(int N, int boatSize, int heuristic){
        MissionariesCannibalsNode.N = N;
        MissionariesCannibalsNode.boatSize = boatSize;
        this.setStart( new MissionariesCannibalsNode() );
        this.setGoal( new MissionariesCannibalsNode(0,0,0) );
        this.heuristic = heuristic;
    }
    public MissionariesCannibals( MissionariesCannibals mc ){
        this.setStart( mc.getStart().clone() );
        this.setGoal( mc.getGoal().clone() );
        this.heuristic = mc.heuristic;
    }
    
    public int noOfPeopleHeuristic(AstarNode a, AstarNode b) {
        MissionariesCannibalsNode anode = (MissionariesCannibalsNode) a;
        MissionariesCannibalsNode bnode = (MissionariesCannibalsNode) b;
        return (Math.abs(anode.getMissionaries() - bnode.getMissionaries()) + Math.abs(anode.getCannibals()- bnode.getCannibals()) - 1);
    }
    public int peopleBoatRatioHeuristic(AstarNode a, AstarNode b) {
        MissionariesCannibalsNode anode = (MissionariesCannibalsNode) a;
        MissionariesCannibalsNode bnode = (MissionariesCannibalsNode) b;
        int noOfPeople = (Math.abs(anode.getMissionaries() - bnode.getMissionaries()) + Math.abs(anode.getCannibals()- bnode.getCannibals()) );
        return (noOfPeople / MissionariesCannibalsNode.boatSize);
    }
    
    
    @Override
    public int heuristicEstimate(AstarNode a, AstarNode b) {
        switch(this.heuristic){
            case NO_OF_PEOPLE:
                return noOfPeopleHeuristic(a,b);
            case PEOPLE_BOAT_CAP_RATIO:
                return peopleBoatRatioHeuristic(a,b);
        }
        return 0;
    }
    
    @Override
    public Astar clone(){
        return new MissionariesCannibals(this);
    }
    
}
