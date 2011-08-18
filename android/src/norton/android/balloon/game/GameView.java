package norton.android.balloon.game;

import norton.android.util.graphics.Drawable;
import norton.android.util.graphics.Renderer;
import norton.android.util.graphics.Scene;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class GameView extends SurfaceView implements SurfaceHolder.Callback, Renderer {

    /**
     * Constructor
     * @param context
     * @param attrs
     */
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
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

    /**
     * Draw the game to the surface
     */
    @Override
    public void render(Scene scene) {
        Canvas canvas = getHolder().lockCanvas();
        
        if (canvas != null) {            
            canvas.drawColor(Color.WHITE);
            
            for (Drawable d : scene.getDrawables()) {
                d.onDraw(canvas);
            }
            getHolder().unlockCanvasAndPost(canvas);                   
        }
    }

}
