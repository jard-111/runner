// add any usefull package line

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    Heros hero=new Heros(100,200,"heros.png");
    Desert desert=new Desert("desert.png");

    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.getChildren().add(desert.getiVDesert());
        root.getChildren().add(hero.getiVHeros());
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}