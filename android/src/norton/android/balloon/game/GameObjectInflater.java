package norton.android.balloon.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import norton.android.balloon.R;
import norton.android.util.geometry.VariableVector;
import norton.android.util.geometry.Vector;
import norton.android.util.graphics.BackgroundLayer;
import norton.android.util.graphics.ParallaxScrollingBackground;
import norton.android.util.graphics.Scene;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * This class uses the given XML configuration to create
 * objects for the GameActivity
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameObjectInflater {
    private Resources r;
	private HashMap<String, String> values;
    
    /**
     * Create the GameObjectInflator with the given Resources
     * @param r
     * @throws IOException 
     * @throws XmlPullParserException 
     */    
    public GameObjectInflater(Resources resources, final int level) throws XmlPullParserException, IOException {
    	this.r = resources;
    	this.values = new HashMap<String, String>();
    	
    	int levelId = r.getIdentifier("level" + Integer.toString(level), "xml", "norton.android.balloon");
    	XmlResourceParser xml = r.getXml(levelId);
    	
    	parseXML(xml);
	}

	private void parseXML(XmlResourceParser xrp) throws XmlPullParserException, IOException {
		String key = null;
		String value = null;
		
		while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {			
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
    public BalloonGame getBalloonGame() {        
        return new BalloonGame(
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
     * Create the lift from the xml config. 
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private VariableVector getLift() {
        return new VariableVector(
            Integer.parseInt(values.get("liftDirection")), 
            dpToPx(values.get("lift")),
            dpToPx(values.get("minimumLift")),
            dpToPx(values.get("maximumLift")),
            Float.parseFloat(values.get("liftAcceleration")),
            Float.parseFloat(values.get("liftDeceleration"))
        );
    }

    /**
     * Create the gravity vector from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private Vector getGravity() {
        float magnitude = dpToPx(values.get("gravity"));
        return new Vector(Integer.parseInt(values.get("gravityDirection")), magnitude);
    }

    /**
     * Create the wind resistence from the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private WindResistence getWindResistence() {
        return new WindResistence(
    		Integer.parseInt(values.get("windReistenceDirection")), 
    		dpToPx(values.get("windResistence")),
    		0,
    		dpToPx(values.get("windResistence")),
    		Float.parseFloat(values.get("windChangeSpeed")),
    		Float.parseFloat(values.get("windChangeSpeed")),
    		Integer.parseInt(values.get("windChangeFrequency"))
		);
    }

    /**
     * Create the wind vector using the xml config
     * 
     * Note that magnitudes are scaled by the dpToPx conversion
     * @return
     */
    private VariableVector getWind() {
    	return new VariableVector(
            Integer.parseInt(values.get("windDirection")), 
            dpToPx(values.get("wind")),
            dpToPx(values.get("minimumWind")),
            dpToPx(values.get("maximumWind")),
            Float.parseFloat(values.get("windAcceleration")),
            Float.parseFloat(values.get("windDeceleration"))
        );        
    }

    /**
     * Use the balloonWidth and balloonHeight from the xml config to
     * create a balloon. The input values in dp are scaled to px
     * 
     * @return
     */
    private Balloon getBalloon() {
        int height = (int) dpToPx(values.get("balloonHeight"));
        int x = (int) dpToPx(values.get("balloonStartX"));
        int y = (int) dpToPx(values.get("ballooonStartY"));
        
        Bitmap balloon = BitmapFactory.decodeResource(r, R.drawable.balloon);
        balloon = getResizedBitmapByHeight(balloon, height);
        
        return new Balloon(x, y, balloon);
    }
        
    /**
     * Create train object
     * @return
     */
    private Train getTrain() {
        return new Train(
            (int) dpToPx(values.get("trainStartX")),
            r.getDisplayMetrics().heightPixels,
            (int) dpToPx(values.get("trainWidth")),
            (int) dpToPx(values.get("trainHeight"))
        );
    }    
    
    /**
     * Resize the given bitmap image
     * 
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return resized bitmap
     */
    private Bitmap getResizedBitmapByHeight(Bitmap bm, int newHeight) {      
        float scale = ((float) newHeight) / bm.getHeight();
        int width = (int) (bm.getWidth() * scale);
        // recreate the new Bitmap
        return Bitmap.createScaledBitmap(bm, width, newHeight, true);
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
    
    /**
     * Overloaded to take a string
     * @param dp
     * @return
     */
    private float dpToPx(String dp) {
    	return dpToPx(Float.parseFloat(dp));
    }

	public ParallaxScrollingBackground getBackground() {
		int id = r.getIdentifier(values.get("theme")+"_layer1", "drawable", "norton.android.balloon");
		float scrollSpeed = Float.parseFloat(values.get("scrollSpeed"));
		
		Bitmap image = BitmapFactory.decodeResource(r, id);	    
	    image = getResizedBitmapByHeight(image, r.getDisplayMetrics().heightPixels);
		BackgroundLayer layer = new BackgroundLayer(image, scrollSpeed, 0);
		
		LinkedHashSet<BackgroundLayer> layers = new LinkedHashSet<BackgroundLayer>();
		layers.add(layer);
		
		return new ParallaxScrollingBackground(layers);
	}
}
