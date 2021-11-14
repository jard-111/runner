public class Camera {
    private double x;
    private int y;
    private double ax;
    private double vx;
    private float km= (float)3;
    private float fm= (float) 1.2;

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

    public void physique(Heros hero){
        this.ax=  (km*(hero.getX()-this.x)-fm*vx);
        this.vx+=  (ax*0.016);
        this.x+= (vx*0.016);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void update(){
    }

    public int getY() {

        return y;
    }
}
