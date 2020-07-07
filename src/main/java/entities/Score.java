package entities;

import java.sql.Time;

/**
 * Indica il punteggio raggiunto nella partita, tale punteggio viene salvato su DB attraverso un servizio REST <br>.
 * La classe implementa Comparable poiché i punteggi vengono mostrati in ordine decrescente (prima su Tempo impiegato , poi ,a parità di tempo,
 * su punteggio conseguito).
 */
public class Score implements Comparable{
    private final Time finalTime;

    private final int points;

    private final int totalPoints;

    private String name;

    private final String gameName;

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

    public int getPoints() {
        return points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getGameName() {
        return gameName;
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

    @Override
    public int compareTo(Object o) {
        if(o instanceof Score){
            if(this.getFinalTime().getTime() > ((Score) o).getFinalTime().getTime()){
                return 1;
            } else if(this.getFinalTime().getTime() < ((Score) o).getFinalTime().getTime()){
                return -1;
            } else {
                if(this.getPoints() > ((Score) o).getPoints()){
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
