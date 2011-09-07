package norton.android.balloon;

import norton.android.balloon.game.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelSelectorActivity extends Activity {
    private static final int PLAY_LEVEL = 1;
    private static final int LEVELS_PER_WORLD = 5;
    private int firstLevel;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selector);
        Log.d("HERE", "HERE");
        firstLevel = (getIntent().getExtras().getInt("world") - 1) * LEVELS_PER_WORLD;
    }
    
    public void launchLevel1(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 1);
        startActivityForResult(intent, PLAY_LEVEL);    
    }

    public void launchLevel2(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 2);
        startActivityForResult(intent, PLAY_LEVEL);
    }

    public void launchLevel3(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 3);
        startActivityForResult(intent, PLAY_LEVEL);
    }

    public void launchLevel4(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 4);
        startActivityForResult(intent, PLAY_LEVEL);
    }

    public void launchLevel5(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 5);
        startActivityForResult(intent, PLAY_LEVEL);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            //increment level
            //Button b = (Button)findViewById(R.id.button5);
            //b.setEnabled(true);
        }        
    }
    
}
