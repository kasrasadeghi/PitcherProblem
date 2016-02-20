/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;


import apcscvm.DefaultControl;
import apcscvm.GraphicsUtilityFunctions;
import apcscvm.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblemDisplay extends DefaultControl<PitcherProblemModel> implements View<PitcherProblemModel>
{

    private int width, height, maxCapacity;
    private Font font;
    
    public PitcherProblemDisplay()
    {
        width = 0;
        height = 0;
        maxCapacity = 0;
    }
    
    protected double getPitcherBase()
    {
        return height*11.0/14;
    }
    
    protected double getPitcherWidth()
    {
        return width/5.0;
    }
    
    protected double getPitcherSpacing()
    {
        return 13*width/160.0;
    }
    
    protected double getPitcherWallWidth()
    {
        return width/80.0;
    }
    
    protected double getPitcherHeight( int c )
    {
        return c*height*5.0/7/maxCapacity;
    }
    
    protected int getPitcher( int x, int y )
    {
        double l = getPitcherSpacing();
        for ( int i = 0; i < 3; i++ )
        {
            if ( x > l && x < l + getPitcherWidth() + 2*getPitcherWallWidth() )
            {
                return i;
            }
            l += getPitcherWidth() + 2*getPitcherWallWidth() + getPitcherSpacing();
        }
        return -1;
    }
    
    @Override
    public void paint( PitcherProblemModel model, Graphics g, int w, int h ) 
    {
        if ( font == null && height != h )
        {
            font = GraphicsUtilityFunctions.getFont( h/20 );
        }
        
        width = w;
        height = h;
        PitcherConfiguration pc = model.getCurrentConfiguration();
        maxCapacity = Math.max( pc.getCapacity( 0 ), Math.max( pc.getCapacity(1), pc.getCapacity(2) ) );
        
        double pitcherBase = getPitcherBase();
        double pitcherWidth = getPitcherWidth();
        double pitcherSpacing = getPitcherSpacing();
        double pitcherWallWidth = getPitcherWallWidth();
        
        for ( int i = 0; i < 3; i++ )
        {
            int pitcherCap = pc.getCapacity( i );
            int pitcherCur = pc.getCurrentContents(i );
            
            double pitcherHeight = getPitcherHeight( pitcherCap );
            double waterHeight = getPitcherHeight( pitcherCur );
            
            
            g.setColor( Color.BLACK );
            g.fillRect( (int)(pitcherSpacing + (pitcherWidth + 2*pitcherWallWidth + pitcherSpacing)*i), 
                    (int)(pitcherBase - pitcherHeight),
                    (int)(pitcherWallWidth), 
                    (int)pitcherHeight);
            
            g.fillRect( (int)(pitcherSpacing + pitcherWidth + pitcherWallWidth + (pitcherWidth + 2*pitcherWallWidth + pitcherSpacing)*i ),
                    (int)(pitcherBase - pitcherHeight), 
                    (int)(pitcherWallWidth), 
                    (int)(pitcherHeight) );
            
            g.fillRect( (int)(pitcherSpacing + (pitcherWidth + 2*pitcherWallWidth + pitcherSpacing)*i), 
                    (int)(pitcherBase), 
                    (int)(pitcherWidth + 2*pitcherWallWidth),
                    (int)(pitcherWallWidth) );
            
            g.setColor( Color.BLUE );
            
            g.fillRect( (int)(pitcherSpacing + pitcherWallWidth + (pitcherWidth + 2*pitcherWallWidth + pitcherSpacing)*i),
                    (int)(pitcherBase - waterHeight),
                    (int)(pitcherWidth),
                    (int)(waterHeight) );
            
            
            g.setColor( Color.BLACK );
            for ( int j = 0; j < pitcherCap; j++ )
            {
                g.drawLine(
                        (int)(pitcherSpacing + i*(pitcherWidth + 2*pitcherWallWidth + pitcherSpacing) ),
                        (int)(pitcherBase - (j+1)*pitcherHeight/pitcherCap),
                        (int)(pitcherSpacing + pitcherWallWidth + i*(pitcherWidth + 2*pitcherWallWidth + pitcherSpacing) + pitcherWidth ),
                        (int)(pitcherBase - (j+1)*pitcherHeight/pitcherCap)
                );
            }
            
            GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                    g, "Goal: " + model.getGoal(), font, 
                    (int)(w/8), 
                    (int)(pitcherBase + h/10), w/2, h/20);
                  
            if ( pc.hasAPitcherWithContents(model.getGoal() ) )
            {
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                    g, "DONE!", font, 
                    (int)(5*w/8), 
                    (int)(pitcherBase + h/10), w/2, h/20);
            }
        }
    }

    public void reset()
    {
        
    }
    
}
