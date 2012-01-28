/**
 * 
 */
package norton.android.balloon.customlevels;

import norton.android.balloon.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Linus Norton <linusnorton@gmail.com>
 *
 */
public class CustomLevelsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.custom_levels);
    }
    
    public void createLevel(View view) {
        Intent intent = new Intent(this, CreateLevelActivity.class);
        startActivity(intent);    	
    }
}
