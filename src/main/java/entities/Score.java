package entities;

import java.sql.Time;

public class Score {
    private Time finalTime;

    private int points;

    private int totalPoints;

    private String name;

    private String gameName;

    public Score(Time finalTime, int points, int totalPoints, String gameName) {
        this.finalTime = finalTime;
        this.points = points;
        this.totalPoints = totalPoints;
        this.gameName = gameName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Time finalTime) {
        this.finalTime = finalTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "finalTime=" + finalTime +
                ", points=" + points +
                ", totalPoints=" + totalPoints +
                ", name='" + name + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
