package norton.android.balloon.game;

import android.util.Log;
import norton.android.util.geometry.Vector;

public class VariableVector extends Vector {
    private float minSpeed;
    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    /**
     * Create a variable force vector
     * @param direction
     * @param magnitude
     * @param minSpeed
     * @param maxSpeed
     * @param acceleration
     * @param deceleration
     */
    public VariableVector(float direction, 
                          float magnitude,
                          float minSpeed,
                          float maxSpeed,
                          float acceleration,
                          float deceleration) {
        super(direction, magnitude);
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }
    
    /**
     *Increase the magnitude of the vector
     */
    public void accelerate() {
        if (this.magnitude < maxSpeed) {
            this.magnitude += acceleration;
        }
    }
    
    /**
     * Decrease the magnitude of the vector
     */
    public void decelerate() {
        if (this.magnitude > minSpeed) {
            this.magnitude -= deceleration;
        }
    }

    /**
     * @return the minSpeed
     */
    public float getMinSpeed() {
        return minSpeed;
    }

    /**
     * @param minSpeed the minSpeed to set
     */
    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    /**
     * @return the maxSpeed
     */
    public float getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the acceleration
     */
    public float getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return the deceleration
     */
    public float getDeceleration() {
        return deceleration;
    }

    /**
     * @param deceleration the deceleration to set
     */
    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }       

}
