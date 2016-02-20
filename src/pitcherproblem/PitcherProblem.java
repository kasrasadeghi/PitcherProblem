/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;


import apcscvm.CVMProgram;
import java.util.ArrayList;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblem {

    public static ArrayList<PitcherConfiguration> solveDFS( PitcherConfiguration pcs, int goal, boolean [][][] visited )
    {
        return null;
    }
    
    
    
    public static void launchPlayerWindow()
    {
        /*
        AnimationWindow win = new AnimationWindow( "Pitcher Player", 800, 700, 50 );
        
        PitcherProblemModel m = new PitcherProblemModel( 12, 7, 6, 9 );
        win.addAnimatedObject( new PitcherProblemPlayerControl( m ) );
        
        win.run();
        */
    }
    
    public static void launchSolverWindow()
    {
        /*
        AnimationWindow win = new AnimationWindow( "Pitcher Solver", 800, 700, 50 );
        
        PitcherProblemModel m = new PitcherProblemModel( 12, 7, 6, 3 );
        win.addAnimatedObject( new PitcherProblemSolverDisplay( m ) );
        
        win.run();
        */
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PitcherProblemGUI.main( args );
        
    }
    
}
