package norton.android.balloon.game;

import java.io.IOException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParserException;

import norton.android.balloon.R;
import norton.android.util.geometry.VariableVector;
import norton.android.util.geometry.Vector;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
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
	private HashMap<String, Object> values;
    
    /**
     * Create the GameObjectInflator with the given Resources
     * @param r
     * @throws IOException 
     * @throws XmlPullParserException 
     */    
    public GameObjectInflator(Resources resources, final int level) throws XmlPullParserException, IOException {
    	this.r = resources;
    	this.values = new HashMap<String, Object>();
    	
    	int levelId = r.getIdentifier("level" + Integer.toString(level), "xml", "");
    	XmlResourceParser xml = r.getXml(levelId);
    	
    	parseXML(xml);
	}

	private void parseXML(XmlResourceParser xrp) throws XmlPullParserException, IOException {
		while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
			String key = null;
			String value = null;
			
	        if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                key = xrp.getAttributeValue(null, "name");		                     		 	 		 
	        } 		 
	        else if (xrp.getEventType() == XmlResourceParser.TEXT) {		 		 
                value = xrp.getText();
	        }		 
	        else if (xrp.getEventType() == XmlResourceParser.END_TAG) {
	        	values.put(key, value);
	        }
	        
	        xrp.next();		 
		}
		 
		xrp.close();
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
            (Integer) values.get("trainStartX"),
            r.getDisplayMetrics().heightPixels,
            (Integer) values.get("trainWidth"),
            (Integer) values.get("trainHeight")
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
            (Integer) values.get("liftDirection"), 
            dpToPx((Float) values.get("lift")),
            dpToPx((Float) values.get("minimumLift")),
            dpToPx((Float) values.get("maximumLift")),
            (Float) values.get("liftAcceleration"),
            (Float) values.get("liftDeceleration")
        );
    }

    /**
     * Create the gravity vector from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getGravity() {
        float magnitude = dpToPx((Float) values.get("gravity"));
        return new Vector((Integer) values.get("gravityDirection"), magnitude);
    }

    /**
     * Create the wind resistence from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getWindResistence() {
        float magnitude = dpToPx((Float) values.get("windResistence"));
        return new Vector((Integer) values.get("windReistenceDirection"), magnitude);
    }

    /**
     * Create the wind vector using the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private VariableVector getWind() {
    	return new VariableVector(
            (Integer) values.get("windDirection"), 
            dpToPx((Float) values.get("wind")),
            dpToPx((Float) values.get("minimumWind")),
            dpToPx((Float) values.get("maximumWind")),
            (Float) values.get("windAcceleration"),
            (Float) values.get("windDeceleration")
        );        
    }

    /**
     * Use the balloonWidth and balloonHeight from the xml config to
     * create a balloon. The input values in dp are scaled to px
     * 
     * @return
     */
    private Balloon getBalloon() {
        int width = (int) dpToPx((Integer) values.get("balloonWidth"));
        int height = (int) dpToPx((Integer) values.get("balloonHeight"));
        int x = (int) dpToPx((Integer) values.get("balloonStartX"));
        int y = (int) dpToPx((Integer) values.get("ballooonStartY"));
        
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
