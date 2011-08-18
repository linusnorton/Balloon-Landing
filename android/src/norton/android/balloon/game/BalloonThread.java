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
    private HashSet<Drawable> drawables;
    private Balloon balloon;
    
    public BalloonThread(Balloon balloon) {
        this(20, balloon);
    }
    
    public BalloonThread(int fps, Balloon balloon) {
        super(fps);

        this.balloon = balloon;

        drawables = new HashSet<Drawable>();
        drawables.add(balloon);
    }

    @Override
    protected void tick() {
        
    }
    
    @Override
    public Set<Drawable> getDrawables() {
        return drawables;
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {        
        return false;
    }

}
