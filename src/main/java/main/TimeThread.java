package main;

import main.userInterface.printer.Printer;

import java.sql.Time;

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
//        time.setTime(5000);
        while(time.getTime() != -3600000){
//            System.out.println(time.getTime() + " " + time);
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
