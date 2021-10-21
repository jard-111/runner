import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class staticThing {
    private double X;
    private double Y;
    private ImageView IV;

    public staticThing(double pX,double pY,String filename){
        this.X=pX;
        this.Y=pY;

        IV=new ImageView(filename);
    }

    public ImageView getIV() {
        return IV;
    }
}
