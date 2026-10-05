package model;

public enum HERO {
    EWA("model/resources/img/ewa.png", "Ewa T."),
    WERNER("model/resources/img/werner.png","T. Werner"),
    TOMASZEWSKI("model/resources/img/tomaszewski.png", "M. Tomaszewski.");

    public String heroUrl;
    public String heroName;

    HERO(String heroUrl, String heroName){
        this.heroUrl = heroUrl;
        this.heroName = heroName;
    }
}
