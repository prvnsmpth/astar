/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Objects;

/**
 *
 * @author praveen
 * 
 * A Collection to hold a pair of values
 */
public class Pair<First, Second> {
    
    private final First first;
    private final Second second;
    
    public Pair(First f, Second s)
    {
        this.first = f;
        this.second = s;
    }
    
    public First getFirst() { return first; }
    public Second getSecond() { return second; }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.first.equals(pairo.getFirst()) &&
            this.second.equals(pairo.getSecond());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.first);
        hash = 79 * hash + Objects.hashCode(this.second);
        return hash;
    }
    
}
