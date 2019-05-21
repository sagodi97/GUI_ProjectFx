package model;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HeroOption extends VBox {

    private ImageView circleImage;
    private ImageView heroImage;

    private String emptyCircle = "model/resources/gray_circle.png";
    private String chosenCircle = "model/resources/blue_boxTick.png";

    private boolean isTicked;

    private HERO hero;

    public HeroOption(HERO hero){
        circleImage = new ImageView(emptyCircle);
        isTicked = false;
        heroImage = new ImageView(hero.heroUrl);
        this.hero = hero;
        setAlignment(Pos.CENTER);
        this.setSpacing(20);
        getChildren().add(circleImage);
        getChildren().add(heroImage);

    }

    public HERO getHero(){
        return this.hero;
    }

    public boolean isTicked(){
        return isTicked;
    }

    public void updateIsTickedTo(boolean tick){
        this.isTicked = tick;
    }
}
