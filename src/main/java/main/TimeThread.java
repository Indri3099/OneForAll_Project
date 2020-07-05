package main;

import main.userInterface.printer.Printer;

import java.sql.Time;

/**
 * Questo thread si occupa di aggiornare il tempo rimasto alla fine del gioco e di conseguenza l'oggetto Printer che si occupa
 * di mostrare all'utente il tempo rimasto.
 */
public class TimeThread extends Thread{

    Time time;

    Printer outTime;

    public TimeThread(Time time, Printer outTime){
        this.time = time;
        this.outTime = outTime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public void run() {
        while(time.getTime() != -3600000){
            time.setTime(time.getTime() - 1000);
            outTime.print(time.toString().substring(3));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
