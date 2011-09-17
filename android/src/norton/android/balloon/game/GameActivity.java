package norton.android.balloon.game;

import norton.android.balloon.MainActivity;
import norton.android.balloon.R;
import norton.android.util.game.GameThread;
import norton.android.util.graphics.ParallaxScrollingBackground;
import norton.android.util.graphics.Scene;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class is responsible for creating and managing both the GameView
 * and GameThread
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public class GameActivity extends Activity implements GameListener {
    private GameThread thread;
    private int level;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.game);
        
        level = getIntent().getExtras().getInt("level");
        MainActivity.tracker.trackPageView("/level" + Integer.toString(level));
        
        initLevel();
    }
    
    /**
     * Create a new BalloonThread, init the surface and set the game going
     */
    private void initLevel() {
    	thread = new GameThread();
    	
    	try {
        	GameObjectInflator inflator = new GameObjectInflator(getResources(), level);
        	ParallaxScrollingBackground background = inflator.getBackground();
        	
            BalloonGame game = inflator.getBalloonGame();
            game.setGameListener(this);
            
            Button button1 = (Button)findViewById(R.id.button1);
            button1.setOnTouchListener(game);
            
            Button button2 = (Button)findViewById(R.id.button2);
            button2.setOnTouchListener(game);

            GameView view = (GameView)findViewById(R.id.gameView);
            view.setOnTouchListener(game);
            
            view.addScene(background);            
            view.addScene(game);            
            
            thread.addTickListener(view);
            thread.addTickListener(background);            
            thread.addTickListener(game);            
            
            startLevel();
    	}
    	catch(Exception e) {    		
    		Log.e("MakeLevel", Log.getStackTraceString(e));
    		Toast.makeText(getApplicationContext(), R.string.xmlError, Toast.LENGTH_LONG).show();
    		finish();
    	}        
    }
    
    private void startLevel() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Land the balloon on the train. Use the burner to go up and wind to go right.")
    		   .setTitle("Level " + Integer.toString(level))
    	       .setCancelable(false)
    	       .setPositiveButton("Let's Go!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   new Thread(thread).start();
    	           }
    	       });
    	builder.create().show();
    }
    
    /**
     * Stop the game thread
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (thread != null) {
        	thread.end();
        }
    }

    /**
     * Level was a success
     */
    @Override
    public void onLevelSuccess() {
        this.setResult(RESULT_OK);
        finish();
    }

    /**
     * Level failed
     */
    @Override
    public void onLevelFailed() {
        this.setResult(RESULT_CANCELED);
        finish();        
    }   
        
}
