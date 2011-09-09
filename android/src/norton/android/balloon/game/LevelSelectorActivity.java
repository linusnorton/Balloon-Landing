package norton.android.balloon.game;

import norton.android.balloon.MainActivity;
import norton.android.balloon.R;
import norton.android.balloon.R.id;
import norton.android.balloon.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LevelSelectorActivity extends Activity {
    public static final int LEVELS_PER_WORLD = 5;
    private int firstLevel;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.level_selector);

        int world = getIntent().getExtras().getInt("world");
        firstLevel = (world - 1) * LEVELS_PER_WORLD;
        
        enableButtons();        
        MainActivity.tracker.trackPageView("/world" + Integer.toString(world));
    }
    
    /**
     * Launch the first level in this world
     * @param view
     */
    public void launchLevel1(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 1);
        startActivityForResult(intent, firstLevel + 1);    
    }

    /**
     * Launch the second level in this world
     * @param view
     */
    public void launchLevel2(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 2);
        startActivityForResult(intent, firstLevel + 2);
    }

    /**
     * Launch the third level in this world
     * @param view
     */
    public void launchLevel3(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 3);
        startActivityForResult(intent, firstLevel + 3);
    }

    /**
     * Launch the fourth level in this world
     * @param view
     */
    public void launchLevel4(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 4);
        startActivityForResult(intent, firstLevel + 4);
    }

    /**
     * Launch the fifth level in this world
     * @param view
     */
    public void launchLevel5(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 5);
        startActivityForResult(intent, firstLevel + 4);
    }
    
    /**
     * Was the level completed successfully?
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK && requestCode >= MainActivity.getMaxLevel()) {
            MainActivity.setMaxLevel(requestCode + 1);
        	enableButtons();
        }        
    }
    
    /**
     * Use the maxLevel to work out what levels are enabled
     */
    private void enableButtons() {
    	int maxButtonIndex = MainActivity.getMaxLevel() - firstLevel;
    	
    	if (maxButtonIndex >= 1) {
    		Button b = (Button)findViewById(R.id.button4);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 2) {
    		Button b = (Button)findViewById(R.id.button5);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 3) {
    		Button b = (Button)findViewById(R.id.button6);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 4) {
    		Button b = (Button)findViewById(R.id.button7);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 5) {
    		Button b = (Button)findViewById(R.id.button8);
            b.setEnabled(true);
    	}    	
    }
    
}
