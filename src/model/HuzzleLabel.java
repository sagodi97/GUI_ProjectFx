package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import global.Config;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class HuzzleLabel extends Label {

    private final static  String BACKGROUND_IMG = "model/resources/img/blue_Spanel.png";
    Config config = new Config();

    public HuzzleLabel(String text, int size){
        setPrefWidth(300);
        setPrefHeight(50);
        setPadding(new Insets(5,5,5,5));
        setFont(config.FONT(size));
        setText(text);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMG, 300, 60, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(image));
        setAlignment(Pos.CENTER);

    }
}
