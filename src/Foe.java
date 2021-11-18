import javafx.geometry.Rectangle2D;

import java.util.concurrent.CancellationException;

public class Foe extends staticThing{

    private int X;
    private int Y;

    public Foe(double x, double y, double height, double width, String filename, int X,int Y) {
        super(x, y, height, width, filename);
        this.X=X;
        this.Y=Y;
    }

    public void update(Camera camera){
        IV.setX(X- camera.getX());
     }

    public Rectangle2D getHitBox(){

        return (new Rectangle2D(X,Y,60,60));
    }

}
