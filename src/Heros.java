import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Heros extends AnimatedThing{

    private int pesanteur=20;
    private double vy=0;

    public Heros(double x, double y, String filename) {
        super(x, y, filename);
        IVanimated.setViewport(new Rectangle2D(0, 0, 83, 100));
    }

    public void update(int vitesse) {

        majY();
        viewPort();

        this.X += vitesse;

    }

    public boolean checkCollision(ArrayList<Foe> ennemis){
        for(Foe ennemi : ennemis) {
            if (ennemi.getHitBox().intersects(new Rectangle2D(X+30, Y, 20, 85)) & !ennemi.isCollision()) {
                System.out.println("collision");
                ennemi.setCollision(true);
                return true;
            }
        }
        return false;
    }

    private void majY(){
        if (Y < 250) {
            attitude = 2;
            vy += pesanteur;
        }
        if (Y + vy * 0.016 >= 250) {
            Y = 250;
            attitude = 1;
        } else {
            Y += vy * 0.016;
        }
        this.IVanimated.setY(Y);
    }

    private void viewPort(){
        switch (attitude) {
            case 1:
                if (periode == maxperiode) {
                    IVanimated.setViewport(new Rectangle2D(84 * index, 0, 83, 100));
                    if (index == maxindex) {
                        index = 0;
                    } else index += 1;
                    periode = 0;
                } else periode += 1;
                break;
            case 2:
                if(vy<=0) {
                    IVanimated.setViewport(new Rectangle2D(0, 162, 83, 100));
                }
                if(vy>0) {
                    IVanimated.setViewport(new Rectangle2D(83, 162, 83, 100));
                }

        }
    }


    public void jump(){
        if (attitude !=2) vy=-500;
    }
}
