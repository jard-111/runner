
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class GameScene extends Scene{

    //Camera camera;
    private ImageView leftIV;
    private ImageView rightIV;
    private ImageView vieIV;
    private ImageView gameOverIV;
    private int vitesse=3;
    private int vie=3;

    private boolean jeu=true;


    Heros hero=new Heros(10,250,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\heros.png");
    Camera camera=new Camera(0,0);
    ArrayList<Foe> ennemis = new ArrayList<>();
    ArrayList<staticThing> vies=new ArrayList<>();


    public GameScene(Group parent, double height, double width) {

        super(parent,height,width);

        staticThing gameOver = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\gameOver.jpg");
        gameOverIV= gameOver.getIV();
        gameOverIV.setX(0);
        gameOverIV.setY(400);

        staticThing left = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        leftIV= left.getIV();
        leftIV.setX(-camera.getX()%800);
        leftIV.setY(0);

        staticThing right = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        rightIV= right.getIV();
        rightIV.setX(800-camera.getX()%800);
        rightIV.setY(0);

        for (int i=0;i<vie;i++){
            vies.add(new staticThing(0,0,50,50,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\coeur.png"));
            vies.get(i).getIV().setX(i*50);
        }
        createFoes();

        parent.getChildren().add(leftIV);
        parent.getChildren().add(rightIV);
        for (int i=0;i<vie;i++){
            parent.getChildren().add(vies.get(i).getIV());
        }
        parent.getChildren().add(hero.getIVanimated());
        for (Foe ennemi : ennemis) {
            parent.getChildren().add(ennemi.getIV());
        }
        parent.getChildren().add(gameOverIV);
        timer.start();
    }

    public void update(){
        leftIV.setX(-camera.getX()%800);
        rightIV.setX(800-camera.getX()%800);
        hero.getIVanimated().setX(hero.getX()-camera.getX());
        camera.physique(hero);

        for (int i=0;i<vie;i++){
            vies.get(i).getIV().setX(i*50);
        }
        if (vie==0){
            jeu=false;
            gameOverIV.setY(0);
        }

        setOnMouseClicked((e) -> hero.jump());
    }

    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (jeu) {
                hero.update(vitesse);
                camera.update();
                for (Foe ennemi : ennemis) {
                    ennemi.update(camera);
                }
                if (hero.checkCollision(ennemis)) {
                    vie -= 1;
                    vies.remove(0);
                }
                update();
            }
        }
    };

    private void createFoes(){
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1000,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1200,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1500,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1800,290));

        for (Foe ennemi : ennemis) {
            ennemi.getIV().setY(290);
        }

    }

}
