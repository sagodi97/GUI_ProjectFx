package model.resources;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

import global.Config;

public class HuzzleLabel extends Label {

    Config config = new Config();

    public HuzzleLabel(String text){
        setPrefWidth(100);
        setPrefHeight(400);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setFont(config.FONT());
    }

}
