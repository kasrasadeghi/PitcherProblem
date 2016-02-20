/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblemSolverDisplay extends PitcherProblemDisplay
{
    private PitcherProblemSolver solver;
    private static final int timePerStep = 1000;
    private int timeToNextStep;
    private ArrayList<PitcherMove> moves;
    
    public PitcherProblemSolverDisplay() 
    {
        solver = new PitcherProblemSolver();
        
        timeToNextStep = timePerStep;
        moves = null;
    }
    
    public void handleTimePassage( PitcherProblemModel model, int ea, int dt )
    {
        
        if ( moves != null && moves.size() > 0 )
        {
            timeToNextStep -= dt;
            while ( timeToNextStep < 0 )
            {
                model.pour( moves.remove(0) );
                timeToNextStep += timePerStep;
            }
        }
    }
    
    public void handleMouseClick( PitcherProblemModel model, int ea, MouseEvent me )
    {
        if ( moves == null )
        {
            moves = solver.solve( model.getCurrentConfiguration(), model.getGoal() );
        }
    }
    
    public void reset()
    {
        moves = null;
    }
    
}
