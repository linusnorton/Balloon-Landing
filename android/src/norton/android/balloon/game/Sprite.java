package norton.android.balloon.game;

import android.graphics.Rect;
import norton.android.util.geometry.Particle;

public class Sprite extends Particle {
    protected int width;
    protected int height;
    
    public Sprite(int x, int y, int width, int height) {
        super();       
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;              
    }
    
    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Return the left most point of the sprite
     * @return
     */
    public int getLeft() {
        return (int) x;
    }
    
    /**
     * Return the top most point
     * @return
     */
    public int getTop() {
        return (int) y;
    }
    
    /**
     * Return the right most point
     * @return
     */
    public int getRight() {
        return (int) x + width;
    }
    
    /**
     * Return the bottom most point
     */
    public int getBottom() {
        return (int) y + height;
    }
    
    /**
     * Return the collidable area
     * @return
     */
    public Rect getCollisionMap() {
        return new Rect(getLeft(), getTop(), getRight(), getBottom());
    }
}
