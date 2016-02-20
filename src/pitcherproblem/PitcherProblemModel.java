/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;

import java.util.ArrayList;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblemModel 
{
    protected ArrayList<PitcherConfiguration> prevConfigs;
    protected int curIndex;
    protected int goal;
    
    public PitcherProblemModel( int A, int B, int C, int G )
    {
        prevConfigs = new ArrayList<>();
        prevConfigs.add( new PitcherConfiguration( A, B, C ) );
        
        curIndex = 0;
        goal = G;
    }
    
    public PitcherProblemModel( PitcherConfiguration pc, int G )
    {
        reset( pc, G );
    }
    
    public void reset( PitcherConfiguration pc, int G )
    {
        prevConfigs = new ArrayList<>();
        prevConfigs.add( pc );
        
        curIndex = 0;
        
        goal = G;
    }
    
    public PitcherConfiguration getCurrentConfiguration()
    {
        return prevConfigs.get( curIndex );
    }
    
    public int getPitcherCapacity( int i )
    {
        return getCurrentConfiguration().getCapacity( i );
    }
    
    public int getPitcherContents( int i )
    {
        return getCurrentConfiguration().getCurrentContents( i );
    }
    
    public void pour( PitcherMove m )
    {
        int src = m.source;
        int dest = m.destination;
      
        PitcherConfiguration pc = getCurrentConfiguration().executeMove(m);
        if (pc != null)
        {
            while (curIndex > 0)
            {
                prevConfigs.remove(0);
                curIndex--;
            }
            prevConfigs.add(0, pc);
        }
    }
    
    public void rewind()
    {
        if ( curIndex < prevConfigs.size() - 1 )
        {
            curIndex++;
        }
    }
    
    public int getGoal() 
    {
        return goal;
    }
    
    public boolean meetsGoal()
    {
        return getCurrentConfiguration().hasAPitcherWithContents(goal );
    }
    
}
