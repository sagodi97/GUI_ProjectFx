package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HeroOption extends VBox {

    private ImageView circleImage;
    private Image rawHero;
    private ImageView heroImage;

    private String emptyCircle = "model/resources/img/unticked_circle.png";
    private String chosenCircle = "model/resources/img/blue_ticked_circle.png";

    private boolean isTicked;

    private HERO hero;

    public HeroOption(HERO hero){
        circleImage = new ImageView(emptyCircle);
        isTicked = false;
        rawHero = new Image(hero.heroUrl,100,100,true, true);
        heroImage = new ImageView(rawHero);

        this.hero = hero;
        setAlignment(Pos.CENTER);
        this.setSpacing(50);
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
        String diplayedCircle = this.isTicked ? chosenCircle:emptyCircle;
        circleImage.setImage(new Image(diplayedCircle));
    }
}
