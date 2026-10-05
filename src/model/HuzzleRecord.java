package model;

public class HuzzleRecord implements Comparable<HuzzleRecord>{

    public  int seconds;
    public int minutes;
    public String heroUrl;
    public String playerName;
    public Integer totalSecs;

    public HuzzleRecord(int seconds, int minutes, String heroUrl, String playerName) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.heroUrl = heroUrl;
        this.playerName = playerName;
        this.totalSecs = seconds + (minutes* 60);
    }

    @Override
    public int compareTo(HuzzleRecord o) {
        return this.totalSecs.compareTo(o.totalSecs);
    }
}
