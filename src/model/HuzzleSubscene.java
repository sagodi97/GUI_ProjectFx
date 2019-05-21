package model;

import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import global.Config;

public class HuzzleSubscene extends SubScene {

    private final static String BACKGROUNDIMG = "model/resources/img/blue_panel.png";
    private final static Config config = new Config();
    public boolean isHidden;

    public HuzzleSubscene(String testText) {
        super(new AnchorPane(), 635, 400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUNDIMG, 635, 400, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane pane = (AnchorPane) this.getRoot();
        pane.setBackground(new Background(image));
        isHidden = true;
        setLayoutY(250);
        setLayoutX(1997);
        Label testL = new Label();
        testL.setText(testText);
        testL.setFont(new Font(config.FONTPATH, 30));
        testL.setLayoutX(100);
        testL.setLayoutY(100);
        pane.getChildren().add(testL);
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
