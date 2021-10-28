
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameScene extends Scene{

    //Camera camera;
    private ImageView leftIV;
    private ImageView rightIV;

    Heros hero=new Heros(200,200);
    Camera camera=new Camera(800,0);


    public GameScene(Group parent, double height, double width) {
        super(parent,height,width);

        staticThing left = new staticThing(camera.getX()%800,camera.getY(),400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        leftIV= left.getIV();
        leftIV.setX(0);
        leftIV.setY(0);

        staticThing right = new staticThing(0,0,400,800,"C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");
        rightIV= right.getIV();
        rightIV.setX(800-camera.getX()%800);
        rightIV.setY(0);

        parent.getChildren().add(leftIV);
        parent.getChildren().add(rightIV);
        parent.getChildren().add(hero.getIVanimated());
        timer.start();
    }

    public void update(){


    }

    AnimationTimer timer =new AnimationTimer() {
        @Override
        public void handle(long time) {
            hero.update();
            camera.update();
            update();
        }
    };


}
