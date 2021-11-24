import javafx.geometry.Rectangle2D;

public class Foe extends staticThing{

    private int X;//position en X de l'ennemi dans la course
    private int Y;//position en Y de l'ennemi sur l'ecran

    public Foe(double x, double y, double height, double width, String filename, int X,int Y) {
        super(x, y, height, width, filename);
        this.X=X;
        this.Y=Y;
    }

    public void update(Camera camera){
        IV.setX(X- camera.getX());//on affiche l'enemie relativement a la position de la camera
     }


    public Rectangle2D getHitBox(){
        return (new Rectangle2D(X,Y,60,60));//renvoie le rectangle2D qui correpond a l'ennemi
    }

}
