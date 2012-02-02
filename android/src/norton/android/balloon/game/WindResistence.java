package norton.android.balloon.game;

import norton.android.util.geometry.VariableVector;

/**
 * Encapsulates the winds behaviour
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class WindResistence extends VariableVector {

    private boolean changingDirection;
	private int changeFrequency;

	/**
	 * 
	 * @param direction
	 * @param magnitude
	 * @param minSpeed
	 * @param maxSpeed
	 * @param acceleration
	 * @param deceleration
	 */
	public WindResistence(float direction, 
						  float magnitude, 
						  float minSpeed,
                          float maxSpeed, 
                          float acceleration, 
                          float deceleration,
                          int changeFrequency) {
        super(direction, magnitude, minSpeed, maxSpeed, acceleration, deceleration);
        
        changingDirection = false;
        this.changeFrequency = changeFrequency;
    }
    
	/**
	 * Roll for a change in direction
	 */
    public void rollForChange() {
    	// if were changing direction and have come to a stop
    	if (changingDirection && magnitude == 0) {
    		// change direction and start to accelerate again
    		direction = (int) (135 + (Math.random() * 90));
    		changingDirection = false;
    	}
    	else if (changeFrequency >= (int) (Math.random() * 1000))  {
			changingDirection = true;
    	}
	}

    /**
     * Decelerates if changing direction
     * 
     * @param wind
     */
	public void updateVariableVectors() {
        if (changingDirection) {
        	decelerate();
        }
        else {
        	accelerate();
        }
		
	}

	/**
	 * Is the wind in the process of changing direction
	 * 
	 * @return
	 */
	public boolean isChangingDirection() {
		return changingDirection;
	}
}
