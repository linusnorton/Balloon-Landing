package norton.android.balloon.game;

import norton.android.balloon.R;
import norton.android.util.geometry.Vector;
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
    private Resources r;
    
    /**
     * Create the GameObjectInflator with the given Resources
     * @param r
     */
    public GameObjectInflator(Resources resources) {
        this.r = resources;        
    }
    
    /**
     * Inflate the game from the xml configuration
     * @return
     */
    public BalloonThread getGameThread() {        
        return new BalloonThread(
            getBalloon(), 
            getWind(), 
            getWindResistence(), 
            getGravity(),
            getLift()
        );
    }
    
    /**
     * Create the lift from the xml config. 
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getLift() {
        float magnitude = dpToPx(Float.parseFloat(r.getString(R.string.lift)));
        return new Vector(r.getInteger(R.integer.liftDirection), magnitude);
    }

    /**
     * Create the gravity vector from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getGravity() {
        float magnitude = dpToPx(Float.parseFloat(r.getString(R.string.gravity)));
        return new Vector(r.getInteger(R.integer.gravityDirection), magnitude);
    }

    /**
     * Create the wind resistence from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getWindResistence() {
        float magnitude = dpToPx(Float.parseFloat(r.getString(R.string.windResistence)));
        return new Vector(r.getInteger(R.integer.windReistenceDirection), magnitude);
    }

    /**
     * Create the wind vector using the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getWind() {
        float magnitude = dpToPx(Float.parseFloat(r.getString(R.string.wind)));
        return new Vector(r.getInteger(R.integer.windDirection), magnitude);
    }

    /**
     * Use the balloonWidth and balloonHeight from the xml config to
     * create a balloon. The input values in dp are scaled to px
     * 
     * @return
     */
    private Balloon getBalloon() {
        int width = (int) dpToPx(r.getInteger(R.integer.balloonWidth));
        int height = (int) dpToPx(r.getInteger(R.integer.balloonHeight));
        int x = (int) dpToPx(r.getInteger(R.integer.balloonStartX));
        int y = (int) dpToPx(r.getInteger(R.integer.ballooonStartY));
        
        return new Balloon(x, y, width, height);
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
        newWidth = (int) dpToPx(newWidth);
        newHeight = (int) dpToPx(newHeight);
        
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
    private float dpToPx(float dp) {
        return 0.5f + dp * r.getDisplayMetrics().density;
    }
}
