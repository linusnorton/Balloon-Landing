package norton.android.balloon.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import norton.android.util.geometry.Particle;
import norton.android.util.graphics.Drawable;

public class Balloon extends Sprite implements Drawable {
    private Paint paint;
    
    /**
     * Create Balloon 
     */
    public Balloon(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        paint = new Paint();
        paint.setColor(Color.RED);            
    }

    /**
     * Draw the Balloon
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
        
}
