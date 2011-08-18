package norton.android.balloon.game;

import norton.android.balloon.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * This class is responsible for creating and managing both the GameView
 * and GameThread
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameActivity extends Activity {
    private GameView view;
    private BalloonThread game;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
        init();
    }
    
    /**
     * Create a new BalloonThread, init the surface and set the game going
     */
    private void init() {
        GameObjectInflator inflator = new GameObjectInflator(getResources());
        game = inflator.getGameThread();
        
        view = (GameView)findViewById(R.id.gameView);
        view.setOnTouchListener(game);
        
        game.setRenderer(view);
        new Thread(game).start();
    }
    
    /**
     * Stop the game thread
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        game.end();
    }   
    
}
