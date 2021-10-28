import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedThing {
    private double X;
    private double Y;
    private ImageView IVanimated;

    private int attitude;
    private int index=1;
    private int maxindex=3;
    private int periode;
    private int sizeX;
    private int sizeY;

    public AnimatedThing(double x, double y) {
        this.X = x;
        this.Y = y;

        Image image=new Image("C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\heros.png");

        this.IVanimated =new ImageView(image);
        IVanimated.setViewport(new Rectangle2D(20,15,60,80));
        IVanimated.setX(X);
        IVanimated.setY(Y);
    }

    public void update() {
        switch (index) {
            case 1:
                IVanimated.setViewport(new Rectangle2D(20, 15, 60, 80));
                index=2;
                break;
            case 2:
                IVanimated.setViewport(new Rectangle2D(90, 0, 160, 100));
                index=1;
                break;
        }
    }

    public ImageView getIVanimated() {
        return IVanimated;
    }
}
