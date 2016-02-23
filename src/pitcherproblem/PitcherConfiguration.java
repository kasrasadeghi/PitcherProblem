/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**s
 *
 * @author DSTIGANT
 */

// A pitcher problem consists of 3 pitcher of different sizes
// (Initially) the largest pitcher is full and the other two are not
// but over the course of the puzzle, water is poured from one pitcher into
// another.  The capacities of the 3 pitchers do not change, but the current
// amount of water in each can change.
public class PitcherConfiguration 
{
    static class Pitcher {
        public int size, volume;

        public Pitcher(int size, boolean isFilled) {
            this.size = size;
            volume = isFilled? size : 0;
        }

        public Pitcher(int size) {
            this.size = size;
            this.volume = 0;
        }

        public Pitcher(int size, int volume) {
            this.size = size;
            this.volume = volume;
        }

        public int getCapacity() {
            return size;
        }

        public int getCurrentContents() {
            return volume;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pitcher pitcher = (Pitcher) o;

            if (size != pitcher.size) return false;
            return volume == pitcher.volume;

        }

        @Override
        public int hashCode() {
            int result = size;
            result = 31 * result + volume;
            return result;
        }

        public Pitcher copy() {
            return new Pitcher(size, volume);
        }

        public void changeVol(int delta) {
            volume += delta;
        }
    }

    // Add member variables here to represent the state of a pitcher problem
    private List<Pitcher> pitchers;
    private List<PitcherMove> parentPath;
    
    // Constructor - takes the sizes of the three pitchers
    // The largest pitcher should start out full, while the other two are empty
    public PitcherConfiguration( int A, int B, int C )
    {
        pitchers = new ArrayList<>();
        pitchers.add(new Pitcher(A, true));
        pitchers.add(new Pitcher(B));
        pitchers.add(new Pitcher(C));
        parentPath = new ArrayList<>();
    }

    public PitcherConfiguration( List<Pitcher> ps, List<PitcherMove> parentPath){
        pitchers = new ArrayList<>();
        for (Pitcher p : ps) {
            pitchers.add(p.copy());
        }
        this.parentPath = new ArrayList<>(parentPath);
    }

    public List<PitcherMove> getParentPath() {
        return parentPath;
    }
    
    public List<PitcherMove> getPossibleMoves()
    {
        List<PitcherMove> moves = new ArrayList<>();
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                if (j != i) moves.add(new PitcherMove(i, j));
        return moves;
    }
   
    // copy - returns a copy (new PC) of this configuration
    public PitcherConfiguration copy()
    {
        return new PitcherConfiguration(pitchers, parentPath);
    }
    
    // executeMove - creates a new state which is the same as this state except
    // that the move m has been executed
    public PitcherConfiguration executeMove( PitcherMove m )
    {
        parentPath.add(m);
        PitcherConfiguration next = copy();
        next.handlePitcherMove(m);
        return next;
    }

    private void handlePitcherMove(PitcherMove m) {
        //if the destination has more empty in it than the source has volume, then delta = sourceVol
        //if the destination has less empty in it than the source has volume, then delta = destEmpty

        Pitcher dest = pitchers.get(m.destination);
        Pitcher src = pitchers.get(m.source);

        int destEmpty = dest.size - dest.volume;
        int srcVol = src.volume;
        int delta = (destEmpty > srcVol)?  srcVol:destEmpty;

        pitchers.get(m.source).changeVol(-delta);
        pitchers.get(m.destination).changeVol(delta);
    }
    
    // toString() - returns a String representing the state of each of the pitchers.
    // For example, if the pitchers have capacities 5, 4, and 3 and there are currently
    // 2 units in the 5 pitcher, 1 in the 4 and 2 in the 3 pitcher, then the returned
    // string will be "[ 2/5 1/4 2/3 ]"
    public String toString()
    {
        String output = "[ ";
        for (Pitcher pitcher : pitchers)
            output += pitcher.getCurrentContents() + "/" + pitcher.getCapacity();
        output += " ]";
        return output;
    }
    
    // hasAPitcherWithContents - returns true if one (or more) of the pitchers
    // currently contains exactly g units
    public boolean hasAPitcherWithContents( int g )
    {
        for (Pitcher pitcher : pitchers)
            if (pitcher.getCurrentContents() == g)
                return true;
        return false;
    }
    
    // getCurrentContents - gets the current amount of water in pitcher i
    public int getCurrentContents( int i )
    {
        return pitchers.get(i).getCurrentContents();
    }

    // getCapacity - gets the capacity of pitcher i
    public int getCapacity(int i) {
        return pitchers.get(i).getCapacity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PitcherConfiguration that = (PitcherConfiguration) o;

        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return pitchers != null ? pitchers.hashCode() : 0;
    }
}
