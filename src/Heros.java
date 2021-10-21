import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heros {
    private double posX;
    private double posY;

    private  static Image spriteSheet=null;
    private static ImageView sprite;

    public void Heros(double pX,double pY,String filename){
        this.posX=pX;
        this.posY=pY;

        spriteSheet=new Image(filename);
        sprite=new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,15,60,80));
    }
}
