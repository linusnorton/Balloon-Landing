package norton.android.balloon.game;

public class Force {
    private float dx;
    private float dy;
      
    public Force(float dx, float dy) {
        this.setDx(dx);
        this.setDy(dy);
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDx() {
        return dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getDy() {
        return dy;
    }
}
