package norton.android.balloon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class WorldSelectorActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);        

        setContentView(R.layout.world_selector);
        
        enableButtons();
        MainActivity.tracker.trackPageView("/world-selector");
    }
    
    /**
     * Go to world 1
     * @param view
     */
    public void launchWorld1(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 1);
        startActivityForResult(intent, 0);    
    }
    
    /**
     * Go to world 2
     * @param view
     */
    public void launchWorld2(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 2);
        startActivityForResult(intent, 0);    
    }
    
    /**
     * Go to world 3
     * @param view
     */
    public void launchWorld3(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 3);
        startActivityForResult(intent, 0);    
    }    
    
    /**
     * When we come back from the level selector we might have new worlds enabled
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {        
    	enableButtons();            
    }
    
    /**
     * Use the maxLevel to work out which buttons should be enabled
     */
    private void enableButtons() {
    	int maxButtonIndex = (MainActivity.getMaxLevel() + 4) / LevelSelectorActivity.LEVELS_PER_WORLD;
    	
    	if (maxButtonIndex >= 1) {
    		Button b = (Button)findViewById(R.id.button1);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 2) {
    		Button b = (Button)findViewById(R.id.button2);
            b.setEnabled(true);
    	}
    	
    	if (maxButtonIndex >= 3) {
    		Button b = (Button)findViewById(R.id.button3);
            b.setEnabled(true);
    	}    	
    }    
}
