package norton.android.balloon.game;

import java.util.HashSet;
import java.util.Set;

import norton.android.balloon.R;
import norton.android.util.game.OnTickListener;
import norton.android.util.geometry.VariableVector;
import norton.android.util.geometry.Vector;
import norton.android.util.graphics.Drawable;
import norton.android.util.graphics.Scene;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * This class contains the main game logic. The parent class calls the tick method
 * until the game ends or is paused
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class BalloonGame implements OnTouchListener, OnTickListener, Scene {
    private HashSet<Drawable> drawables;
    private Balloon balloon;
    private Train train;
    private VariableVector wind;
    private VariableVector windResistence;
    private Vector gravity;
    private VariableVector lift;
    private boolean burnerOn;
    private boolean windBlowing;
    private int maxHeight;
    private int maxWidth;
    private GameListener listener;
	private int windChangeFrequency;
	private boolean windChangingDirection;    
    
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
    public BalloonGame(int widthPixels, 
                       Balloon balloon, 
                       Train train,
                       VariableVector wind,
                       VariableVector windResistence,
                       Vector gravity,
                       VariableVector lift,
                       int windChangeFrequency) {
        this.burnerOn = false;
        this.windBlowing = false;
        this.windChangingDirection = false;
        this.maxHeight = train.getBottom();
        this.maxWidth = widthPixels;
        this.balloon = balloon;
        this.train = train;
        this.wind = wind;
        this.windResistence = windResistence;
        this.gravity = gravity;
        this.lift = lift;
        this.windChangeFrequency = windChangeFrequency;

        drawables = new HashSet<Drawable>();
        drawables.add(balloon);
        drawables.add(train);
    }

    /**
     * Main game loop, apply the forces to the balloon
     */
    public void onTick() {
        applyVectors();               
        updateVariableVectors();
        changeWindDirection();
        checkBounds();
        checkCollisions();
    }

    private void changeWindDirection() {
    	// if were changing direction and have come to a stop
    	if (windChangingDirection && windResistence.getMagnitude() == 0) {
    		// change direction and start to accelerate again
    		windResistence.setDirection((int) (135 + (Math.random() * 90)));
    		windChangingDirection = false;
    	}
    	else if (windChangeFrequency >= (int) (Math.random() * 1000))  {
			windChangingDirection = true;
    	}
	}

	private void checkCollisions() {
        if (areColliding(balloon, train)) {        	
            //calculate whether the collision was a successful landing or crash
            int topDiff = balloon.getBottom() - train.getTop();
            
            if (topDiff < 5) {
                balloon.setY(train.getY() - balloon.getHeight());
                
                levelComplete();                    
            }
            else {
                levelFailed();
            }
        }
        
        if (balloon.getBottom() > maxHeight) {
        	levelFailed();
        }
    }
    
	/**
	 * Calculate the over crossing areas and scan each pixel for a collision
	 * 
	 * @param balloon
	 * @param train
	 * @return
	 */
    private boolean areColliding(Balloon balloon, Train train) {
    	int left = Math.max(balloon.getLeft(), train.getLeft());
		int right = Math.min(balloon.getRight(), train.getRight());
		int top = Math.max(balloon.getTop(), train.getTop());
		int bottom = Math.min(balloon.getBottom(), train.getBottom());

		for (int x = left; x < right; x++) {
		    for (int y = top; y < bottom; y++) {
		        if (balloon.isFilled(x - balloon.getLeft(), y - balloon.getTop()) && 
	        		train.isFilled(x - train.getLeft(), y - train.getTop())) {
		        	return true;
		        }
		    }
		}
		
		return false; 
    }
    
    /**
     * Make sure the balloon isn't out of bounds 
     */
    private void checkBounds() {
        if (balloon.getX() < 5) {
            balloon.setX(5);
        }
        
        if (balloon.getX() > maxWidth) {
            balloon.setX(maxWidth);
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
        
        if (windChangingDirection) {
        	windResistence.decelerate();
        	wind.decelerate();
        }
        else {
        	windResistence.accelerate();
        }
    }

    /**
     * Apply the wind, wind resistance, gravity and lift
     */
    private void applyVectors() {
        balloon.applyVector(gravity);
        balloon.applyVector(windResistence);
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
    
    /**
     * Level was successfully completed so notify the listeners
     */
    private void levelComplete() {
        if (listener != null) {
            listener.onLevelSuccess();
        }
    }
    
    /**
     * Level was failed, notify listeners
     */
    private void levelFailed() {
        if (listener != null) {
            listener.onLevelFailed();
        }        
    }

    /**
     * Set the game listener
     * @param gameListener
     */
    public void setGameListener(GameListener gameListener) {
        listener = gameListener;
    }

}
