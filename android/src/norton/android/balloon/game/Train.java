/**
 * 
 */
package norton.android.balloon.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import norton.android.util.geometry.Particle;
import norton.android.util.graphics.Drawable;

/**
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class Train extends Particle implements Drawable {
    private Paint paint;
    private int width;
    private int height;
    
    /**
     * Create train object
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Train(int x, int y, int width, int height) {
        super();
        
        paint = new Paint();
        paint.setColor(Color.BLUE);
        
        this.x = x;
        this.y = y - 10 - height;
        this.width = width;
        this.height = height;        
    }
    
    /**
     * Draw the train
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
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
}
