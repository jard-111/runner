import javafx.scene.image.ImageView;

public class Desert {

    private ImageView iVDesert;

    public Desert(String filename){
        iVDesert =new ImageView(filename);
    }

    public ImageView getiVDesert() {
        return iVDesert;
    }
}