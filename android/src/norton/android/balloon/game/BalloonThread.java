package norton.android.balloon.game;

import java.util.HashSet;
import java.util.Set;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import norton.android.util.game.GameThread;
import norton.android.util.graphics.Drawable;

/**
 * This class contains the main game logic. The parent class calls the tick method
 * until the game ends or is paused
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class BalloonThread extends GameThread implements OnTouchListener {

    public BalloonThread() {
        super();
    }
    
    public BalloonThread(int fps) {
        super(fps);
    }

    @Override
    protected void tick() {
        
    }
    
    @Override
    public Set<Drawable> getDrawables() {
        return new HashSet<Drawable>();
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {        
        return false;
    }

}
