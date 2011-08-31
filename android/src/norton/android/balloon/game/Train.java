/**
 * 
 */
package norton.android.balloon.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import norton.android.util.game.Sprite;
import norton.android.util.geometry.Particle;
import norton.android.util.graphics.Drawable;

/**
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class Train extends Sprite implements Drawable {
    private Paint paint;
    
    /**
     * Create train object
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Train(int x, int y, int width, int height) {
        super(x, y - 10 - height, width, height);
        
        paint = new Paint();
        paint.setColor(Color.BLUE);        
    }
    
    /**
     * Draw the train
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

}
