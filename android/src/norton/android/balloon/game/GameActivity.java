package norton.android.balloon.game;


import norton.android.balloon.R;
import norton.android.util.game.GameThread;
import norton.android.util.graphics.ParallaxScrollingBackground;
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
        
        initLevel();
    }
    
    /**
     * Pause the game thread
     */
    @Override
    public void onPause() {
    	super.onPause();
    	thread.pause();
    }
    
    /**
     * Unpause the game thread
     */
    @Override
    public void onResume() {
    	super.onResume();
    	thread.unPause();
    }
    
    /**
     * Create a new BalloonThread, init the surface and set the game going
     */
    private void initLevel() {
    	thread = new GameThread();
    	
    	try {
        	GameObjectInflater inflator = new GameObjectInflater(getResources(), level);
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
    	builder.setMessage(R.string.introText)
    		   .setTitle(getResources().getString(R.string.level) + " " + Integer.toString(level))
    	       .setCancelable(false)
    	       .setPositiveButton(R.string.letsGo, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   new Thread(thread).start();
    	           }
    	       });
    	builder.create().show();
    }
    
    /**
     * Level was a success
     */
    @Override
    public void onLevelSuccess() {
    	if (thread != null) {
        	thread.end();
        }
        
    	runOnUiThread(new Runnable() {
    	    public void run() {
    	    	AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
    	    	builder.setMessage(R.string.wellDone)
    	    		   .setTitle(getResources().getString(R.string.success))
    	    	       .setCancelable(false)
    	    	       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
    	    	           public void onClick(DialogInterface dialog, int id) {
    	    	               setResult(RESULT_OK);
    	    	               dialog.dismiss();
    	    	               finish();        
    	    	           }
    	    	       });
    	    	builder.create().show();
    	    }
    	});
    }

    /**
     * Level failed
     */
    @Override
    public void onLevelFailed() {
        if (thread != null) {
        	thread.end();
        }
    	
    	runOnUiThread(new Runnable() {
    	    public void run() {
    	    	AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
    	    	builder.setMessage(R.string.failed)
    	    		   .setTitle(getResources().getString(R.string.crash))
    	    	       .setCancelable(false)
    	    	       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
    	    	           public void onClick(DialogInterface dialog, int id) {
    	    	               setResult(RESULT_CANCELED); 
    	    	               dialog.dismiss();
    	    	               finish();        
    	    	           }
    	    	       });
    	    	builder.create().show();
    	    }
    	});    	    	
    }   
        
}
