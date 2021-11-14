// add any usefull package line

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    //Heros hero=new Heros(100,200, "C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\heros.png");
    //Desert desert=new Desert("C:\\Users\\caincain\\Desktop\\ENSEA\\2A\\java\\runner\\img\\desert.png");



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