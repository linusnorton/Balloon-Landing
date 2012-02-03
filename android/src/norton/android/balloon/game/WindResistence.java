package norton.android.balloon.game;

import norton.android.util.geometry.VariableVector;

/**
 * Encapsulates the winds behaviour
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class WindResistence extends VariableVector {

    private boolean changing;
    private int changeFrequency;
    private float newMaxSpeed;
    private float minWindSpeed;
	private float maxWindSpeed;

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
                          int changeFrequency,
                          float minWindSpeed,
                          float maxWindSpeed) {
        super(direction, magnitude, minSpeed, maxSpeed, acceleration, deceleration);
        
        changing = false;
        newMaxSpeed = maxSpeed;        
        
        this.minWindSpeed = minWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.changeFrequency = changeFrequency;
    }
    
    /**
     * Roll for a change 
     */
    public void rollForChange() {
        if (changing && newMaxSpeed == magnitude) {            
            changing = false;
            maxSpeed = newMaxSpeed;
        }
        else if (changeFrequency >= (int) (Math.random() * 1000))  {
            changing = true;
            newMaxSpeed = minWindSpeed + (float) Math.random() * (maxWindSpeed - minWindSpeed);
            
            if (newMaxSpeed > maxSpeed) {
            	maxSpeed = newMaxSpeed;
            }
        }
    }

    /**
     * Decelerates if changing direction and new speed is lower, otherwise
     * accelerates until maximum speed
     * 
     * @param wind
     */
    public void update() {
        if (changing && magnitude > newMaxSpeed) {
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
    public boolean isChanging() {
        return changing;
    }
}
