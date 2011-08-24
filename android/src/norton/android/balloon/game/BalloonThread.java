package norton.android.balloon.game;

import java.util.HashSet;
import java.util.Set;

import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import norton.android.balloon.R;
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
    private Train train;
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
                         Train train,
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
        this.train = train;
        this.wind = wind;
        this.windResistence = windResistence;
        this.gravity = gravity;
        this.lift = lift;

        drawables = new HashSet<Drawable>();
        drawables.add(balloon);
        drawables.add(train);
    }

    /**
     * Main game loop, apply the forces to the balloon
     */
    @Override
    protected void tick() {
        applyVectors();               
        updateVariableVectors();        
        checkBounds();
        checkCollisions();
    }

    private void checkCollisions() {
        Rect bRect = new Rect(
            (int) balloon.getX(), 
            (int) balloon.getY(), 
            balloon.getWidth(), 
            balloon.getHeight()
        );
        Rect tRect = new Rect(
            (int) train.getX(), 
            (int) train.getY(), 
            train.getWidth(), 
            train.getHeight()
        );
        
        if (Rect.intersects(bRect, tRect)) {
            end();
        }
    }
    
    /**
     * Make sure the balloon isn't out of bounds 
     */
    private void checkBounds() {
        if (balloon.getX() < 5) {
            balloon.setX(5);
        }
        
        if (balloon.getY() > maxHeight) {
            balloon.setY(maxHeight);
        }
    }

    /**
     * Accelerate / decelerate wind and lift
     */
    private void updateVariableVectors() {
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
    }

    /**
     * Apply the wind, wind resistence, gravity and lift
     */
    private void applyVectors() {
        //apply the constant forces
        balloon.applyVector(gravity);
        balloon.applyVector(windResistence);
        //apply the variable forces
        balloon.applyVector(lift);
        balloon.applyVector(wind);
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
        if (v.getId() == R.id.button1 && event.getAction() == MotionEvent.ACTION_DOWN) {
            burnerOn = true;
        }
        else if (v.getId() == R.id.button1 && event.getAction() == MotionEvent.ACTION_UP) {
            burnerOn = false;
        } 
        else if (v.getId() == R.id.button2 && event.getAction() == MotionEvent.ACTION_DOWN) {
            windBlowing = true;
        } 
        else if (v.getId() == R.id.button2 && event.getAction() == MotionEvent.ACTION_UP) {
            windBlowing = false;
        } 

        return false;
    }

}
