package norton.android.balloon.game;

/**
 * Objects that implement this interface can be notified
 * of events such as level changes and game over.
 * 
 * @author Linus Norton <linusnorton@gmail.com>
 */
public interface GameListener {

    void onLevelSuccess();
    
    void onLevelFailed();
        
}
