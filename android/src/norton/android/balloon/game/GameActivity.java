package norton.android.balloon.game;

import norton.android.balloon.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * This class handles the UI for the game.
 * 
 * It is also responsible for creating and managing both the GameView
 * and GameThread
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameActivity extends Activity {
    private GameView surface;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
        
        surface = (GameView)findViewById(R.id.gameView);
        surface.init();        
    }
}
