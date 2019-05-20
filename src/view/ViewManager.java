package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HuzzleButton;

public class ViewManager {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private final String BACKGROUNDIMG = "positive.png";


    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        renderBackground();
        renderButton();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void renderButton(){
        HuzzleButton boton = new HuzzleButton("SPOKO LOKO");
        mainPane.getChildren().add(boton);
        boton.setLayoutY(200);
        boton.setLayoutX(350);


    }

    private void renderBackground(){
        Image img = new Image("view/resources/img/" + BACKGROUNDIMG);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(bgImg));
    }



}
