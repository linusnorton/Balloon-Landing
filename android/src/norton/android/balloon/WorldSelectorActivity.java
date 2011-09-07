package norton.android.balloon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorldSelectorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_selector);
    }
    
    public void launchWorld1(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 1);
        startActivity(intent);    
    }
    
    public void launchWorld2(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 2);
        startActivity(intent);    
    }
    
    public void launchWorld3(View view) {        
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra("world", 3);
        startActivity(intent);    
    }    
}
