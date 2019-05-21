package model;

public enum HERO {
    ONE("model/resources/img/ewa.png"),
    TWO(("model/resources/img/werner.png")),
    THREE(("model/resources/img/tomaszewski.png"));

    String heroUrl;

    private HERO(String heroUrl){
        this.heroUrl = heroUrl;
    }
}
