import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Heros extends AnimatedThing{

    private int pesanteur=20;//acceleration "negative" due a la pesenteur
    private double vy=0;//vitesse en Y du hero
    private int invincibility=0;//entier qui rend le hero invincible si il est superieur a 0

    public Heros(double x, double y, String filename) {
        super(x, y, filename);
        IVanimated.setViewport(new Rectangle2D(0, 0, 83, 100));
    }

    public void resetInvincibility() {
        this.invincibility = 0;
    }

    //met a jour les caracteristiques du hero pendant le jeu
    public void update(int vitesse) {
        majY();
        viewPort();
        if(invincibility>0) invincibility-=1;

        this.X += vitesse;
    }

    // verifie que le hero n'a pas heurte un ennemi
    public boolean checkCollision(ArrayList<Foe> ennemis){
        for(Foe ennemi : ennemis) {
            if (ennemi.getHitBox().intersects(new Rectangle2D(X+30, Y, 20, 85)) & invincibility==0) {
                System.out.println("collision");
                invincibility=32;//rend le hero invincible pendant 0,5s si il heurte un ennemi
                return true;
            }
        }
        return false;
    }

    //Applique la gravite au hero si celui-ci se trouve dans les airs
    private void majY(){
        if (Y < 250) {
            attitude = 2;
            vy += pesanteur;
        }
        if (Y + vy * 0.016 >= 250) {//le hero est "pose sur le sol" si la gravite le fait descendre trop bas
            Y = 250;
            attitude = 1;
        } else {
            Y += vy * 0.016;
        }
        this.IVanimated.setY(Y);
    }

    //affichage dynamique du hero
    private void viewPort(){
        if (invincibility%8<4) {//si le hero est invincible, celui-ci clignotte
            IVanimated.setVisible(true);
            switch (attitude) {
                case 1://le hero cours
                    if (periode == maxperiode) {//l'affichage du hero se fait a une frequence de (60Hz/maxperiode)
                        IVanimated.setViewport(new Rectangle2D(84 * index, 0, 83, 100));
                        if (index == maxindex) {
                            index = 0;
                        } else index += 1;
                        periode = 0;
                    } else periode += 1;
                    break;
                case 2://le heros en en train de sauter
                    if (vy <= 0) {
                        IVanimated.setViewport(new Rectangle2D(0, 162, 83, 100));
                    }
                    if (vy > 0) {
                        IVanimated.setViewport(new Rectangle2D(83, 162, 83, 100));
                    }

            }
        }
        else{
            IVanimated.setVisible(false);
        }
    }

    //un saut donne au hero une vitesse instantanee dirigee vers le haut.
    public void jump(){
        if (attitude !=2) vy=-500;//on ne peut pas sauter dans les airs
    }
}
