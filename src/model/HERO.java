package model;

public enum HERO {
    ONE("model/resources/img/player_green.png"),
    TWO(("model/resources/img/player_red.png")),
    THREE(("model/resources/img/player_yellow.png"));

    String heroUrl;

    private HERO(String heroUrl){
        this.heroUrl = heroUrl;
    }
}
