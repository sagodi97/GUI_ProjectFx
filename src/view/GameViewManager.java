package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.HERO;
import model.HuzzleMaker;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private HERO chosenHero;

    private static final int HEIGHT = 700;
    private static final int WIDTH = 1000;

    GameViewManager(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, WIDTH, HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(HERO theOneAndOnly, Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();
        this.gameStage.show();
        this.chosenHero = theOneAndOnly;
    }

    private void createNEwCroppedImage(){
        String url = "https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/52696152_3101371956545050_1058515284467384320_n.jpg?_nc_cat=106&_nc_ht=scontent-waw1-1.xx&oh=8c24a9b52ddb989774034e77bb4d2ef9&oe=5D94A896";
        Image pepe= new Image(url);
        HuzzleMaker arquitecto = new HuzzleMaker();
        ImageView [][] originalGrid = arquitecto.hacemeCuadritos(pepe, 2,2,gamePane);

    }

    private void createNewGrid(){
       GridPane cleanBoard = new GridPane();
    }
}
