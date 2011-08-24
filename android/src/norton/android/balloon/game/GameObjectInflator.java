package norton.android.balloon.game;

import norton.android.balloon.R;
import norton.android.util.geometry.Vector;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

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
            r.getDisplayMetrics().heightPixels,
            r.getDisplayMetrics().widthPixels,
            getBalloon(),
            getTrain(),
            getWind(), 
            getWindResistence(), 
            getGravity(),
            getLift()
        );
    }
    
    /**
     * Create train object
     * @return
     */
    private Train getTrain() {
        return new Train(
            r.getInteger(R.integer.trainStartX),
            r.getDisplayMetrics().heightPixels,
            r.getInteger(R.integer.trainWidth),
            r.getInteger(R.integer.trainHeight)
        );
    }

    /**
     * Create the lift from the xml config. 
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private VariableVector getLift() {
        return new VariableVector(
            r.getInteger(R.integer.liftDirection), 
            dpToPx(Float.parseFloat(r.getString(R.string.lift))),
            dpToPx(Float.parseFloat(r.getString(R.string.minimumLift))),
            dpToPx(Float.parseFloat(r.getString(R.string.maximumLift))),
            Float.parseFloat(r.getString(R.string.liftAcceleration)),
            Float.parseFloat(r.getString(R.string.liftDeceleration))
        );
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
    private VariableVector getWind() {
        return new VariableVector(
                r.getInteger(R.integer.windDirection), 
                dpToPx(Float.parseFloat(r.getString(R.string.wind))),
                dpToPx(Float.parseFloat(r.getString(R.string.minimumWind))),
                dpToPx(Float.parseFloat(r.getString(R.string.maximumWind))),
                Float.parseFloat(r.getString(R.string.windAcceleration)),
                Float.parseFloat(r.getString(R.string.windDeceleration))
            );
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
