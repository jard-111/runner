public class Camera {
    private int x;
    private int y;

    public Camera(int pX, int pY){
        this.x=pX;
        this.y=pY;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
