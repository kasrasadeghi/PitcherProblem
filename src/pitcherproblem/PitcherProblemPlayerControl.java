/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblemPlayerControl extends PitcherProblemDisplay
{
    protected int source;
    
    public PitcherProblemPlayerControl() 
    {
        super();
        source = -1;
    }
    
    public void paint( PitcherProblemModel model, Graphics g, int w, int h )
    {
        super.paint( model, g, w, h );
        if ( source != -1 )
        {
            g.setColor( Color.GREEN );
            g.fillOval(
                (int)(getPitcherSpacing() + source*(getPitcherSpacing() + getPitcherWidth() + 2*getPitcherWallWidth() ) ),
                (int)(getPitcherBase() + 2*getPitcherWallWidth()),
                (int)(2*getPitcherWallWidth() + getPitcherWidth() ),
                (int)(10 )
            );
        }
    }
    
    @Override
    public void handleMouseClick( PitcherProblemModel model, int ea, MouseEvent me )
    {
        int x = me.getX();
        int y = me.getY();
        if ( source == -1 )
        {
            source = getPitcher( x, y );
        }
        else {
            int target = getPitcher( x, y );
            if ( target != -1 )
            {
                model.pour( new PitcherMove(source, target) );
                source = -1;
            }
        }
    }
}
