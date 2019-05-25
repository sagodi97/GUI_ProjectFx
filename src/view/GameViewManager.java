package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HERO;
import model.HuzzleLabel;
import model.HuzzleMaker;

import java.util.ArrayList;
import java.util.List;

class GameViewManager {

    private BorderPane borderPane;
    private Pane headerPane;
    private GridPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private HERO chosenHero;
    private int puzzleSize;


    private String imageUrl = "https://scontent-waw1-1.cdninstagram.com/vp/f78fda0e6a6d9a78c6b36a3e94662fe4/5D7A8730/t51.2885-15/e35/53246943_360348081233825_3550596113467512414_n.jpg?_nc_ht=scontent-waw1-1.cdninstagram.com";
    private Image pepe= new Image(imageUrl,750,750,false,true);


    private HuzzleMaker arquitecto;
    private  Pane testingG;

    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;

    GameViewManager(){
        arquitecto = new HuzzleMaker();

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #FFFFFF;");

        gamePane = new GridPane();
        gamePane.setGridLinesVisible(true);
        gamePane.setPadding(new Insets(10,10,10,10));
        gamePane.setHgap(5);
        gamePane.setVgap(5);

        testingG = new AnchorPane();

        headerPane = new AnchorPane();
        HBox panelBar = new HBox();
        HuzzleLabel counter = new HuzzleLabel("TIEMPO", 18);
        HuzzleLabel info = new HuzzleLabel("INFO, PLAYER, I.T.D", 18);
        panelBar.setSpacing(30);
        panelBar.getChildren().add(counter);
        panelBar.getChildren().add(info);
        headerPane.getChildren().add(panelBar);

        HBox puzzleArea = new HBox();
        puzzleArea.setSpacing(50);
        puzzleArea.getChildren().add(gamePane);
        puzzleArea.getChildren().add(testingG);

        borderPane.setTop(headerPane);
        borderPane.setCenter(puzzleArea);


        gameScene = new Scene(borderPane, WIDTH, HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    void createNewGame(HERO theOneAndOnly, Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();
        this.gameStage.show();
        this.chosenHero = theOneAndOnly;
        this.puzzleSize = 4;

        ImageView solution = new ImageView(pepe);
        solution.setLayoutY(10);
        testingG.getChildren().add(solution);
        List<ImageView> img = arquitecto.hacemeCuadritos(pepe, puzzleSize);
        int row = 0;
        int col = 0;
        for (int i = 0; i < (img.size() - 1); i++){
            GridPane.setConstraints(img.get(i),row,col);
            gamePane.getChildren().add(img.get(i));
            if ((i + 1) % puzzleSize == 0){
                row++;
                col = 0;
            }else {
                col++;
            }
        }
    }
}
