package norton.android.balloon.game;

import norton.android.balloon.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
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
    private GameView view;
    private BalloonThread game;
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
        initLevel();
    }
    
    /**
     * Create a new BalloonThread, init the surface and set the game going
     */
    private void initLevel() {    	
    	try {
        	GameObjectInflator inflator = new GameObjectInflator(getResources(), level);
        	
            game = inflator.getGameThread();
            game.setGameListener(this);
            
            view = (GameView)findViewById(R.id.gameView);
            view.setOnTouchListener(game);
            
            Button button1 = (Button)findViewById(R.id.button1);
            button1.setOnTouchListener(game);
            
            Button button2 = (Button)findViewById(R.id.button2);
            button2.setOnTouchListener(game);
            
            game.setRenderer(view);
            startLevel();
    	}
    	catch(Exception e) {    		
    		Log.e("MakeLevel", Log.getStackTraceString(e));
    		Toast.makeText(getApplicationContext(), R.string.xmlError, Toast.LENGTH_LONG);
    	}        
    }
    
    private void startLevel() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Land the balloon on the train. Use the burner to go up and wind to go right.")
    		   .setTitle("Level " + Integer.toString(level))
    	       .setCancelable(false)
    	       .setPositiveButton("Let's Go!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   new Thread(game).start();
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
        
        if (game != null) {
        	game.end();
        }
    }

    /**
     * Level was a success
     */
    @Override
    public void onLevelSuccess() {
        //level++;
        //initLevel();
        finish();
    }

    /**
     * Level failed
     */
    @Override
    public void onLevelFailed() {
        finish();        
    }   
    
}
