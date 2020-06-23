package main;

public class TimeUnit {
    private int minutes;

    private int seconds;

    public TimeUnit(int minutes, int seconds) {
        if (minutes <= 0 && seconds <= 0) {
            this.minutes = 1;
            this.seconds = 0;
        } else if (minutes < 0) {
            this.minutes = 1;
            this.seconds = seconds;
        } else if (seconds < 0) {
            this.minutes = minutes;
            this.seconds = 0;
        } else if (seconds > 59) {
            this.minutes = minutes;
        }

        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void update(boolean inc) {
        int d;
        if (inc) {
            d = 1;
        } else {
            d = -1;
        }

        if (seconds + d == 60 || seconds + d == 0) {
            minutes += d;
            seconds = 0;
        } else {
            seconds += d;
        }

        if(minutes < 0){
            minutes = 0;
        }
    }
}
