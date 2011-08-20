package norton.android.balloon.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import norton.android.util.geometry.Particle;
import norton.android.util.graphics.Drawable;

public class Balloon extends Particle implements Drawable {
    private int width;
    private int height;
    private Paint paint;
    
    /**
     * Create Balloon 
     */
    public Balloon(int x, int y, int width, int height) {
        super();
        
        paint = new Paint();
        paint.setColor(Color.RED);
        
        this.setX(x);
        this.setY(y);
        this.width = width;
        this.height = height;        
    }

    /**
     * Draw the Balloon
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(getX(), getY(), getX() + width, getY() + height, paint);
    }    
}
