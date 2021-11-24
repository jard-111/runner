// add any usefull package line

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        Group root = new Group();

        GameScene gameScene= new GameScene(root,800,400);
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }


}