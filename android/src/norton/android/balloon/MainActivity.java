package norton.android.balloon;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private static final String GOOGLE_ACCOUNT = "UA-25579809-1";	
	private static int maxLevel;
	public static GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences settings = getPreferences(0);
        maxLevel = settings.getInt("maxLevel", 1);
        
        tracker = GoogleAnalyticsTracker.getInstance();
        tracker.start(GOOGLE_ACCOUNT, 20, this);
        tracker.trackPageView("/home");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	SharedPreferences settings = getPreferences(0);
    	SharedPreferences.Editor editor = settings.edit();
        editor.putInt("maxLevel", maxLevel);
        editor.commit();
    }
    
    public void newGame(View view) {
        Intent intent = new Intent(this, WorldSelectorActivity.class);
        startActivity(intent);
    }
    
    /**
     * return the last level the user has unlocked.
     * @return
     */
    public static int getMaxLevel() {
    	return maxLevel;
    }
    
    /**
     * Set the max level the user can play
     * @param level
     */
    public static void setMaxLevel(final int level) {
    	maxLevel = level;
    }
    
}