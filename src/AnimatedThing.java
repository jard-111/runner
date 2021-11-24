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

    //on peut donner les positions d'une animatedThing pour relancer le jeu une fois gagne/perdu.
    //On aurait pu creer une fonction avec des positions predefinies mais un setter permet de faire des essais
    public void setPositions(double x,double y) {
        X = x;
        Y = y;
    }

    public double getX() {
        return X;
    }

    public ImageView getIVanimated() {
        return IVanimated;
    }

}
