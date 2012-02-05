/**
 * 
 */
package norton.android.balloon.game;

import norton.android.util.game.Sprite;
import norton.android.util.graphics.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class Train extends Sprite {
    private int speed;
    private int minX;
    private int maxX;
    
    /**
     * Create train object
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Train(int x, int y, Bitmap image, int speed, int minX, int maxX) {
        super(x, y - 10 - image.getHeight(), image);
        
        this.speed = speed;
        this.minX = minX;
        this.maxX = maxX - image.getWidth();        
    }
    
    /**
     * The train back and forth
     */
    public void move() {
    	x += speed;
    	
    	if (x > maxX || x < minX) {
    		speed = -speed;
    	}
    }

}
