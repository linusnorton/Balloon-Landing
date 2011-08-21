package norton.android.balloon.game;

import java.util.HashSet;
import java.util.Set;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import norton.android.util.game.GameThread;
import norton.android.util.geometry.Vector;
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
    private VariableVector wind;
    private Vector windResistence;
    private Vector gravity;
    private VariableVector lift;
    private boolean burnerOn;
    private boolean windBlowing;
    private int maxHeight;
    private int maxWidth;
    
    /**
     * Create the game thread
     * @param widthPixels 
     * @param heightPixels 
     * 
     * @param balloon
     * @param wind
     * @param windResistence
     * @param gravity
     * @param lift
     */
    public BalloonThread(int heightPixels, 
                         int widthPixels, 
                         Balloon balloon, 
                         VariableVector wind,
                         Vector windResistence,
                         Vector gravity,
                         VariableVector lift) {
        super();

        this.burnerOn = false;
        this.windBlowing = false;
        this.maxHeight = heightPixels - 10 - balloon.getHeight();
        this.maxWidth = widthPixels;
        this.balloon = balloon;
        this.wind = wind;
        this.windResistence = windResistence;
        this.gravity = gravity;
        this.lift = lift;

        drawables = new HashSet<Drawable>();
        drawables.add(balloon);
    }

    /**
     * Main game loop, apply the forces to the balloon
     */
    @Override
    protected void tick() {
        //apply the constant forces
        balloon.applyVector(gravity);
        balloon.applyVector(windResistence);
        //apply the variable forces
        balloon.applyVector(lift);
        balloon.applyVector(wind);
        
       
        if (burnerOn) {
            lift.accelerate();
        }
        else {
            lift.decelerate();
        }
        
        if (windBlowing) {
            wind.accelerate();
        }
        else {
            wind.decelerate();
        }
        
        if (balloon.getX() < 5) {
            balloon.setX(5);
        }
        
        if (balloon.getY() > maxHeight) {
            balloon.setY(maxHeight);
        }
    }
    
    /**
     * Return all the drawable items 
     */
    @Override
    public Set<Drawable> getDrawables() {
        return drawables;
    }
    
    /**
     * Trigger the burner
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {        
        burnerOn = !burnerOn;
        windBlowing = !windBlowing;
        
        return true;
    }

}
