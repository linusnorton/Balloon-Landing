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
    private Vector wind;
    private Vector windResistence;
    private Vector gravity;
    private Vector lift;
    private boolean burnerOn;
    private boolean windBlowing;
    
    /**
     * Create the game thread
     * 
     * @param balloon
     * @param wind
     * @param windResistence
     * @param gravity
     * @param lift
     */
    public BalloonThread(Balloon balloon, 
                         Vector wind,
                         Vector windResistence,
                         Vector gravity,
                         Vector lift) {
        super();

        this.burnerOn = false;
        this.windBlowing = false;
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
        
       
        if (burnerOn && lift.getMagnitude() < 12) {
            lift.setMagnitude(lift.getMagnitude() + 0.2f);
        }
        else if (lift.getMagnitude() > 0){
            lift.setMagnitude(lift.getMagnitude() - 0.06f);
        }
        
        if (windBlowing && wind.getMagnitude() < 11) {
            wind.setMagnitude(wind.getMagnitude() + 0.26f);
        }
        else if (wind.getMagnitude() > 0){
            wind.setMagnitude(wind.getMagnitude() - 0.14f);
        }
        
        if (balloon.getX() < 5) {
            balloon.setX(5);
        }
        
        if (balloon.getY() > 200) {
            balloon.setY(200);
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
