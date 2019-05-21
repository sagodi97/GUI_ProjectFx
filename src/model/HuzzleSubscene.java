package model;

import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import global.Config;

public class HuzzleSubscene extends SubScene {

    private final static String BACKGROUND_IMG = "model/resources/img/blue_panel.png";
    private final static Config config = new Config();
    public boolean isHidden;

    public AnchorPane pane;

    public HuzzleSubscene() {
        super(new AnchorPane(), 635, 400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMG, 635, 400, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        pane = (AnchorPane) this.getRoot();
        pane.setBackground(new Background(image));
        isHidden = true;
        setLayoutY(250);
        setLayoutX(1997);
    }

    public void toggleSubscene(){
        if (isHidden){
            setLayoutX(300);
            isHidden = false;
        }else{
            setLayoutX(1997);
            isHidden = true;
        }
    }
}
