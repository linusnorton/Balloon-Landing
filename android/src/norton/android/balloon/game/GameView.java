package norton.android.balloon.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The GameView is responsible for drawing the current game.
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Constructor
     * @param context
     * @param attrs
     */
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Set up the surface
     */
    public void init() {
        
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        
    }

}
