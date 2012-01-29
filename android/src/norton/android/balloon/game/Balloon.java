package norton.android.balloon.game;

import norton.android.util.game.Sprite;
import norton.android.util.graphics.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public class Balloon extends Sprite implements Drawable {
    private Bitmap image;
    
    /**
     * Create Balloon 
     */
    public Balloon(int x, int y, Bitmap image) {
        super(x, y, image.getWidth(), image.getHeight());
        
        this.image = image;
    }

    /**
     * Draw the Balloon
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);        
    }
    
    /**
     * If the given pixel is not transparent then it is filled
     * @param x
     * @param y
     * @return
     */
    public boolean isFilled(int x, int y) {
        return image.getPixel(x, y) != Color.TRANSPARENT;
    }    
        
}
