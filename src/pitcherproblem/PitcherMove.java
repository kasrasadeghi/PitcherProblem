/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pitcherproblem;

/**
 *
 * @author DSTIGANT
 */

// A PitcherMove describes a move in the pitcher problem - that is, a pour
// from one pitcher into another
public class PitcherMove
{
    // add instance variables here
    public final int source, destination;
    
    // constructor - should take which pitcher to pour into which pitcher
    public PitcherMove( int s, int d )
    {
        source = s;
        destination = d;
    }
    
    // toString - returns a string representation of this command
    // For example, "Pour pitcher 1 into pitcher 2"
    public String toString()
    {
        return "Pour " + source + " to " + destination;
    }
}
