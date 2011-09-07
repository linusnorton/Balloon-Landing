package norton.android.balloon;

import norton.android.balloon.game.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void newGame(View view) {
        Intent intent = new Intent(this, WorldSelectorActivity.class);
        startActivity(intent);
    }
}