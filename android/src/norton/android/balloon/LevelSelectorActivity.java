package norton.android.balloon;

import norton.android.balloon.game.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelSelectorActivity extends Activity {
    private static final int LEVELS_PER_WORLD = 5;
    private int firstLevel;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selector);
        firstLevel = (getIntent().getExtras().getInt("world") - 1) * LEVELS_PER_WORLD;
    }
    
    public void launchLevel1(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 1);
        startActivity(intent);    
    }

    public void launchLevel2(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 2);
        startActivity(intent);    
    }

    public void launchLevel3(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 3);
        startActivity(intent);    
    }

    public void launchLevel4(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 4);
        startActivity(intent);    
    }

    public void launchLevel5(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", firstLevel + 5);
        startActivity(intent);    
    }

}
