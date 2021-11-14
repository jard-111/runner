
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.control.Button;


public class GameScene extends Scene{

    //Camera camera;
    private final ImageView leftIV;
    private final ImageView rightIV;
    private final ImageView gameOverIV;
    private final ImageView victoryIV;
    private final int cameraInitialX=0;
    private final int heroInitialX=100;
    private final Button restart;
    private final Button startButton;

    private int vitesse=6;
    private final int vie=3;
    private int vieRestante=vie;

    private boolean jeu=false;



    Camera camera=new Camera(cameraInitialX,0);
    Heros hero;
    ArrayList<Foe> ennemis = new ArrayList<>();
    ArrayList<staticThing> vies=new ArrayList<>();


    public GameScene(Group parent, double height, double width) {

        super(parent,height,width);

        hero=new Heros(heroInitialX,250,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\heros.png");


        staticThing victory = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\victory.png");
        victoryIV= victory.getIV();
        victoryIV.setX(0);
        victoryIV.setY(0);
        victoryIV.setVisible(false);

        staticThing gameOver = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\gameOver.jpg");
        gameOverIV= gameOver.getIV();
        gameOverIV.setX(0);
        gameOverIV.setY(0);
        gameOverIV.setVisible(false);

        staticThing left = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        leftIV= left.getIV();
        leftIV.setX(-camera.getX()%800);
        leftIV.setY(0);

        staticThing right = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        rightIV= right.getIV();
        rightIV.setX(800-camera.getX()%800);
        rightIV.setY(0);

        restart = new Button("restart");
        restart.setMinSize(800,50);
        restart.setVisible(false);
        restart.setDisable(true);

        startButton = new Button("Start");
        startButton.setMinSize(800,400);

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
        parent.getChildren().add(victoryIV);
        parent.getChildren().add(restart);
        parent.getChildren().add(startButton);

        timer.start();
    }

    public void update() {
        leftIV.setX(-camera.getX()%800);
        rightIV.setX(800-camera.getX()%800);
        hero.getIVanimated().setX(hero.getX()-camera.getX());
        camera.physique(hero);

        if (vieRestante==0){
            reset(gameOverIV);
        }

        if(hero.getX()>2000){
            reset(victoryIV);
        }

        setOnMouseClicked((e) -> hero.jump());
    }

    public void reset(ImageView debutFin){
        jeu=false;
        debutFin.setVisible(true);
        restart.setVisible(true);
        restart.setDisable(false);
        restart.setOnAction(value ->  {
            vieRestante=vie;
            for (int i=0;i<vie;i++){
                vies.get(i).getIV().setVisible(true);
            }
            hero.setX(heroInitialX);
            camera.setX(cameraInitialX);
            for (Foe ennemi : ennemis) {
                ennemi.resetcollision();
            }
            debutFin.setVisible(false);
            jeu=true;
            restart.setVisible(false);
            restart.setDisable(true);
        });
    }



    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long time) {
                if (jeu) {
                    vitesse = 6;
                    hero.update(vitesse);
                    camera.update();
                    for (Foe ennemi : ennemis) {
                        ennemi.update(camera);
                    }
                    if (hero.checkCollision(ennemis)) {
                        vies.get(vieRestante - 1).getIV().setVisible(false);
                        vieRestante -= 1;
                    }
                    update();

                }
                startButton.setOnAction(value ->  {
                    startButton.setDisable(true);
                    startButton.setVisible(false);
                    jeu=true;
                });

        }
    };

    private void createFoes(){
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1000,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1450,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1500,290));
        ennemis.add(new Foe(0,0,60,60,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\trash.png",1800,290));

        for (Foe ennemi : ennemis) {
            ennemi.getIV().setY(290);
        }

    }

}
