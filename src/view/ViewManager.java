package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HuzzleButton;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private final String BACKGROUNDIMG = "positive.png";
    private static final int MENU_START_X = (WIDTH / 3) + 10;
    private static final int MENU_START_Y = 350;

    List<HuzzleButton> menuButtons;


    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        renderBackground();
        renderLogo();
        menuButtons = new ArrayList<>();
        createButtons();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButton(HuzzleButton boton){
        boton.setLayoutX(MENU_START_X);
        boton.setLayoutY(MENU_START_Y + menuButtons.size() * 100);
        menuButtons.add(boton);
        mainPane.getChildren().add(boton);
    }

    private void createButtons(){
        createPlayButton();
        createHelpButton();
    }
    private void createPlayButton(){
        HuzzleButton boton = new HuzzleButton("PLAY");
        addMenuButton(boton);
    }

    private void createHelpButton(){
        HuzzleButton boton = new HuzzleButton("HELP");
        addMenuButton(boton);
    }

    private void renderBackground(){
        Image img = new Image("view/resources/img/" + BACKGROUNDIMG);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(bgImg));
    }

    private void renderLogo(){
        ImageView logo = new ImageView("view/resources/img/logo.png");
        logo.setLayoutX((WIDTH / 100)* 19);
        logo.setLayoutY(MENU_START_Y - 250);
        mainPane.getChildren().add(logo);
    }



}
