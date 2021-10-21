
import javafx.geometry.Rectangle2D;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameScene extends Camera{

    Camera camera;
    staticThing left;
    staticThing right;

    public GameScene(Group g){
        left=new staticThing(800,400,"desert.png");
        g.getChildren().add(left);
        camera= new Camera(1500,0);
        render();
    }
    void render(){
        double offset=camera.getX()%left.getW();
        left.getBackView().setViewPort(new Rectangle2D(offset,0,left.getW()-offset,left.getL());
        //on recommence avec right


    }

}
