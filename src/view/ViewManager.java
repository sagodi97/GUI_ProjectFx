package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HuzzleButton;
import global.Config;
import model.HuzzleSubscene;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 1000;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private final String BACKGROUNDIMG = "positive.png";
    private static final int MENU_START_X = 50;
    private static final int MENU_START_Y = 300;

    List<HuzzleButton> menuButtons;
    List<HuzzleSubscene> subScenes;
    int activeSubsceneIndex;


    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        renderBackground();
        renderLogo();
        subScenes = new ArrayList<>();
        menuButtons = new ArrayList<>();
        createSubscenes();
        createButtons();

    }

    private void addSubscene(HuzzleSubscene subescena){
        subScenes.add(subescena);
        mainPane.getChildren().add(subescena);
    }
    private void createSubscenes(){
                                 //INDEX
        createScoresSubscene();  //0
        createHelpSubscene();    //1

    }

    private void createHelpSubscene(){
        HuzzleSubscene subescenaAyuda = new HuzzleSubscene("PAPO TO JEST AYUDA");
        addSubscene(subescenaAyuda);
    }

    private void createScoresSubscene(){
        HuzzleSubscene subescenaPuntajes = new HuzzleSubscene("PAPO TO JEST PUNTAJE");
        addSubscene(subescenaPuntajes);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButton(HuzzleButton boton){
        boton.setLayoutX(MENU_START_X);
        boton.setLayoutY(MENU_START_Y + menuButtons.size() * 65);
        menuButtons.add(boton);
        mainPane.getChildren().add(boton);
    }

    private void createButtons(){
        createPlayButton();
        createScoresButton();
        createHelpButton();
        createExitButton();
    }
    private void createPlayButton(){
        HuzzleButton botonPlay = new HuzzleButton("PLAY");
        addMenuButton(botonPlay);
    }

    private void createScoresButton(){
        HuzzleButton botonPuntaje = new HuzzleButton("SCORES BOARD");
        addMenuButton(botonPuntaje);
        botonPuntaje.setOnAction(event -> {
            subScenes.get(0).toggleSubscene();
            if(!subScenes.get(0).isHidden){
                if(activeSubsceneIndex == -1){
                    activeSubsceneIndex = 0;
                }else{
                    subScenes.get(activeSubsceneIndex).toggleSubscene();
                }
            }else{
                activeSubsceneIndex = -1;
            }
        });
    }

    private void createHelpButton(){
        HuzzleButton botonAyuda = new HuzzleButton("HELP");
        addMenuButton(botonAyuda);
        botonAyuda.setOnAction(event -> {
            subScenes.get(1).toggleSubscene();
            if(!subScenes.get(1).isHidden){
                if(activeSubsceneIndex == -1){
                    activeSubsceneIndex = 1;
                }else{
                    subScenes.get(activeSubsceneIndex).toggleSubscene();
                }
            }else{
                activeSubsceneIndex = -1;
            }
        });
    }

    private void createExitButton(){
        HuzzleButton botonSalir = new HuzzleButton("EXIT");
        addMenuButton(botonSalir);
        botonSalir.setOnAction(event -> {
            mainStage.close();
        });
    }



    private void renderBackground(){
        Image img = new Image("view/resources/img/" + BACKGROUNDIMG);
        BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(bgImg));
    }

    private void renderLogo(){
        ImageView logo = new ImageView("view/resources/img/logo.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);
        mainPane.getChildren().add(logo);
    }





}
