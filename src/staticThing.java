import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double X;//position en X sur le spreadsheet
    private double Y;//position en Y sur le spreadsheet
    private double height;
    private double width;
    protected ImageView IV;

    public staticThing(double x, double y, double height, double width, String filename) {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;

        Image image=new Image(filename);

        this.IV =new ImageView(image);
        IV.setViewport(new Rectangle2D(X,Y,width,height));
    }

    public ImageView getIV() {
        return IV;
    }
}
