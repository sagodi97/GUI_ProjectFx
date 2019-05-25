package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

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
    List<HeroOption> heroOptions;
    private HERO theChosenOne;
    int activeSubsceneIndex = -1;


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
        createScoresSubscene();      //0
        createHelpSubscene();        //1
        createHeroChooserSubscene(); //2

    }

    private void createHeroChooserSubscene(){
        HuzzleSubscene subescena = new HuzzleSubscene();
        HuzzleLabel label = new HuzzleLabel("CHOOSE YOUR PLAYER",28);
        label.setLayoutY(20);
        label.setLayoutX(160);
        subescena.pane.getChildren().add(label);
        addSubscene(subescena);
        subescena.pane.getChildren().add(createHeroOptions());
        subescena.pane.getChildren().add(createStartButton());

    }

    private HBox createHeroOptions(){
        HBox box = new HBox();
        box.setSpacing(80);
        heroOptions = new ArrayList<>();
        for (HERO hero:HERO.values()){
            HeroOption heroOption = new HeroOption(hero);
            heroOptions.add(heroOption);
            box.getChildren().add(heroOption);
            heroOption.setOnMouseClicked(event -> {
                for(HeroOption option: heroOptions){
                    option.updateIsTickedTo(false);
                }
                heroOption.updateIsTickedTo(true);
                theChosenOne = heroOption.getHero();
            });
        }
        box.setLayoutX(110);
        box.setLayoutY(110);
        box.setAlignment(Pos.CENTER);

    return box;
    }

    private void createHelpSubscene(){
        HuzzleSubscene subescena = new HuzzleSubscene();
        HuzzleLabel label = new HuzzleLabel("INSTRUCTIONS",28);
        label.setLayoutY(20);
        label.setLayoutX(160);
        subescena.pane.getChildren().add(label);
        addSubscene(subescena);
    }

    private void createScoresSubscene(){
        HuzzleSubscene subescena = new HuzzleSubscene();
        HuzzleLabel label = new HuzzleLabel("SCORE BOARD",28);
        label.setLayoutY(20);
        label.setLayoutX(160);
        subescena.pane.getChildren().add(label);
        addSubscene(subescena);
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
        botonPlay.setOnAction(event -> {
            subScenes.get(2).toggleSubscene();
            if(activeSubsceneIndex != -1 && activeSubsceneIndex != 2){
                subScenes.get(activeSubsceneIndex).toggleSubscene();
            }
            if(!subScenes.get(2).isHidden){
                activeSubsceneIndex = 2;
            }else {
                activeSubsceneIndex = -1;
            }
        });
    }

    private HuzzleButton createStartButton(){
        HuzzleButton boton = new HuzzleButton("START!");
        boton.setOnAction(event -> {
            //TODO Connect to GameViewManager.
            System.out.println("IT WORKS");
            GameViewManager newGame = new GameViewManager();
            if(this.theChosenOne == null){
                System.out.println("ELIGE TU HEROE PAPI");
            }else {
                newGame.createNewGame(this.theChosenOne, mainStage);
            }
        });
        boton.setLayoutX(400);
        boton.setLayoutY(320);
        return boton;
    }

    private void createScoresButton(){
        HuzzleButton botonPuntaje = new HuzzleButton("SCORES BOARD");
        addMenuButton(botonPuntaje);
        botonPuntaje.setOnAction(event -> {
            subScenes.get(0).toggleSubscene();
            if(activeSubsceneIndex != -1 && activeSubsceneIndex != 0){
                subScenes.get(activeSubsceneIndex).toggleSubscene();
            }
            if(!subScenes.get(0).isHidden){
                activeSubsceneIndex = 0;
            }else {
                activeSubsceneIndex = -1;
            }
        });
    }

    private void createHelpButton(){
        HuzzleButton botonAyuda = new HuzzleButton("HELP");
        addMenuButton(botonAyuda);
        botonAyuda.setOnAction(event -> {
            subScenes.get(1).toggleSubscene();
            if(activeSubsceneIndex != -1 && activeSubsceneIndex != 1){
                subScenes.get(activeSubsceneIndex).toggleSubscene();
            }
            if(!subScenes.get(1).isHidden){
                activeSubsceneIndex = 1;
            }else {
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
        logo.setLayoutX(350);
        logo.setLayoutY(50);
        mainPane.getChildren().add(logo);
    }





}
