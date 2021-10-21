import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heros {
    private double X;
    private double Y;

    private ImageView IVHeros;

    public Heros(double pX,double pY,String filename){
        this.X=pX;
        this.Y=pY;

        IVHeros=new ImageView(filename);
        IVHeros.setViewport(new Rectangle2D(20,15,60,80));
        IVHeros.setX(X);
        IVHeros.setY(Y);
    }

    public ImageView getiVHeros(){return IVHeros;}

    public double getPosX() {
        return X;
    }

    public double getPosY() {
        return Y;
    }
}
