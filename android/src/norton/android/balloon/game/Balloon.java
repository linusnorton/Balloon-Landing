package norton.android.balloon.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import norton.android.util.graphics.Drawable;

public class Balloon implements Drawable {
    private int x;
    private int y;
    private float dx;
    private float dy;
    private int width;
    private int height;
    private Paint paint;
    
    /**
     * Create Balloon 
     */
    public Balloon(int width, int height) {
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
        
        paint = new Paint();
        paint.setColor(Color.RED);
        
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the Balloon
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    
    /**
     * Apply the given force to the balloon
     * @param force
     */
    public void applyForce(Force force) {        
        dx += force.getDx();
        dy += force.getDy();
    }

}
