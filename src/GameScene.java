
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.transform.Translate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class GameScene extends Scene{

    private final ImageView leftIV;
    private final ImageView rightIV;
    private final ImageView gameOverIV;
    private final ImageView victoryIV;
    private final int cameraInitialX=0;//sert a recommencer une partie
    private final int heroInitialX=100;
    private final int heroInitialY=100;
    private final Button restart;
    private final Button startButton;
    private String syspath = System.getProperty("user.dir"); // ceci permet de ne pas avoir de bug liés aux paths. (file: n'arive pas a trouver les mp3...)

    private int vitesse=6;
    private final int vie=3;//vie donnee au personnage en debut de partie
    private int vieRestante=vie;//vie du personnage lors d'une partie

    private boolean jeu=false;//sert a bloquer le jeu pendant les ecrans de debut/fin



    //creation de classes
    Camera camera=new Camera(cameraInitialX,0);
    Heros hero;
    ArrayList<Foe> ennemis = new ArrayList<>();
    ArrayList<staticThing> vies=new ArrayList<>();


    //importation des musiques
    Media doomMedia = new Media(new File(syspath+"\\music\\doom.mp3").toURI().toString());
    MediaPlayer doomPlayer = new MediaPlayer(doomMedia);
    Media rickMedia = new Media(new File(syspath+"\\music\\never-gonna-give-you-up.mp3").toURI().toString());
    MediaPlayer rickPlayer = new MediaPlayer(rickMedia);



    public GameScene(Group parent, double height, double width) {

        super(parent,height,width);

        // Creation des differentes classes
        hero=new Heros(heroInitialX,250,"file:img/heros.png");

        staticThing victory = new staticThing(0,0,400,800,"file:img/victory.png");
        victoryIV= victory.getIV();
        victoryIV.setX(0);
        victoryIV.setY(0);
        victoryIV.setVisible(false);

        staticThing gameOver = new staticThing(0,0,400,800,"file:img/gameOver.jpg");
        gameOverIV= gameOver.getIV();
        gameOverIV.setX(0);
        gameOverIV.setY(0);
        gameOverIV.setVisible(false);

        staticThing left = new staticThing(0,0,400,800,"file:img/desert.png");
        leftIV= left.getIV();
        leftIV.setX(-camera.getX()%800);
        leftIV.setY(0);

        staticThing right = new staticThing(0,0,400,800,"file:img/desert.png");
        rightIV= right.getIV();
        rightIV.setX(800-camera.getX()%800);
        rightIV.setY(0);

        restart = new Button("restart");
        restart.getTransforms().add(new Translate(350,300));
        restart.setMinSize(100,50);
        restart.setVisible(false);
        restart.setDisable(true);

        startButton = new Button("Start");
        startButton.setMinSize(800,400);

        for (int i=0;i<vie;i++){
            vies.add(new staticThing(0,0,50,50,"file:img/coeur.png"));
            vies.get(i).getIV().setX(i*50);
        }
        createFoes();

        //lancement de la musique
        doomPlayer.play();

        //ajout des imageView au Group
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


        //declanchement su timer
        timer.start();
    }


    public void update() {
        leftIV.setX(-camera.getX()%800);
        rightIV.setX(800-camera.getX()%800);
        hero.getIVanimated().setX(hero.getX()-camera.getX());
        camera.physique(hero);

        if (vieRestante==0){//le joueur est mort
            doomPlayer.stop();
            rickPlayer.play();
            reset(gameOverIV);
        }

        if(hero.getX()>2000){//le joueur est venu a bout du parcour
            reset(victoryIV);
        }

        setOnMouseClicked((e) -> hero.jump());//un click sur la souris declanche un saut
    }

    public void reset(ImageView startRestart){
        jeu=false;
        startRestart.setVisible(true);
        restart.setVisible(true);
        restart.setDisable(false);
        restart.setOnAction(value ->  {//si on appuie sur restart, on enleve l'ecran de fin et on remet en place le decord de jeu a sa position initiale
            doomPlayer.play();
            rickPlayer.stop();
            vieRestante=vie;
            for (int i=0;i<vie;i++){
                vies.get(i).getIV().setVisible(true);
            }
            hero.setPositions(heroInitialX,heroInitialY);
            camera.setX(cameraInitialX);
            hero.resetInvincibility();
            startRestart.setVisible(false);
            jeu=true;
            restart.setVisible(false);
            restart.setDisable(true);
        });
    }



    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long time) {
                if (jeu) {//cela permet de ne pas mettre a jour le jeu si on est si l'ecran de debut ou de fin
                    hero.update(vitesse);
                    for (Foe ennemi : ennemis) {
                        ennemi.update(camera);
                    }
                    if (hero.checkCollision(ennemis)) {
                        vies.get(vieRestante - 1).getIV().setVisible(false);
                        vieRestante -= 1;
                    }
                    update();

                }
                startButton.setOnAction(value ->  {//si on appuie sur jouer, le boutton disparait et le jeu se lance
                    startButton.setDisable(true);
                    startButton.setVisible(false);
                    jeu=true;
                });

        }
    };


    //on cree la liste des ennemis. Il n'y a pas d'aleatoire, il faut creer le parcour a l'avance en rajoutant des ennemis dans cette liste
    private void createFoes(){
        ennemis.add(new Foe(0,0,60,60,"file:img/trash.png",1000,290));
        ennemis.add(new Foe(0,0,60,60,"file:img/trash.png",1450,290));
        ennemis.add(new Foe(0,0,60,60,"file:img/trash.png",1500,290));
        ennemis.add(new Foe(0,0,60,60,"file:img/trash.png",1800,290));

        for (Foe ennemi : ennemis) {
            ennemi.getIV().setY(290);
        }

    }

}
