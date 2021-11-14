import javafx.geometry.Rectangle2D;

import java.util.concurrent.CancellationException;

public class Foe extends staticThing{

    private int X;
    private int Y;

    private boolean collision=false;//variable que l'on passe a true lorsque la collision a eu lieu, elle sert a eviter plusieurs collisions avec le meme object

    public Foe(double x, double y, double height, double width, String filename, int X,int Y) {
        super(x, y, height, width, filename);
        this.X=X;
        this.Y=Y;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isCollision() {
        return collision;
    }

    public void update(Camera camera){
        IV.setX(X- camera.getX());
     }

     public void resetcollision(){
        collision=false;
     }

    public Rectangle2D getHitBox(){

        return (new Rectangle2D(X,Y,60,60));
    }

}
