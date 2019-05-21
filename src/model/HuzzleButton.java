package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import global.Config;

public class HuzzleButton extends Button {
    private final Config config = new Config();
    private final String BUTTON_SPOKO = "-fx-background-color: transparent; -fx-background-image: url('model/resources/img/blue_button02.jpg');";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('model/resources/img/blue_button03.jpg');";

    public HuzzleButton(String text){
        initListener();
        setText(text);
        setFont(config.FONT(23));
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_SPOKO);
    }

    private void setButtonPressed(){
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 5);
    }

    private void setButtonReleased(){
        setStyle(BUTTON_SPOKO);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 5);
    }

    private void initListener(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressed();
                }
            }
        });
        //I'm leaving both the lambda and non lambda ways of doing for self-educational purposes.
        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonReleased();
            }
        });

        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
        });
        setOnMouseExited(event -> {
            setEffect(null);
        });
    }
}
