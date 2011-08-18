package norton.android.balloon.game;

import norton.android.balloon.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * This class uses the given XML configuration to create
 * objects for the GameActivity
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameObjectInflator {
    private Resources resources;
    
    /**
     * Create the GameObjectInflator with the given Resources
     * @param resources
     */
    public GameObjectInflator(Resources resources) {
        this.resources = resources;        
    }
    
    public BalloonThread getGameThread() {        
        return new BalloonThread(getBalloon());
    }
    
    private Balloon getBalloon() {
        int width = dpToPx(resources.getInteger(R.integer.balloonWidth));
        int height = dpToPx(resources.getInteger(R.integer.balloonHeight));
        
        return new Balloon(width, height);
    }
    
    /**
     * Resize the given bitmap image
     * 
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return resized bitmap
     */
    private Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        newWidth = dpToPx(newWidth);
        newHeight = dpToPx(newHeight);
        
        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }
    
    /**
     * Convert dp to px for this display
     * 
     * @param dp
     * @return px
     */
    private int dpToPx(int dp) {
        return (int) (0.5f + dp * resources.getDisplayMetrics().density);
    }
}
