import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedThing {
    protected double X;
    protected double Y;
    protected ImageView IVanimated;

    protected int attitude=1;
    protected int index=1;
    protected int maxindex=5;
    protected int periode=0;
    protected int maxperiode=7;

    public AnimatedThing(double x, double y,String filename) {
        this.X = x;
        this.Y = y;

        Image image=new Image(filename);

        this.IVanimated =new ImageView(image);
    }

    public void setX(double x) {
        X = x;
    }

    public double getX() {
        return X;
    }

    public ImageView getIVanimated() {
        return IVanimated;
    }


}
