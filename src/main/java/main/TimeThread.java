package main;

import javax.swing.*;
import java.sql.Time;

public class TimeThread extends Thread{

    Time time;

    JLabel jTime;

    public TimeThread(Time time, JLabel jTime){
        this.time = time;
        this.jTime = jTime;
    }

    @Override
    public void run() {
        time.setTime(5000);
        while(time.getTime() != 0){
            time.setTime(time.getTime() - 1000);
            jTime.setText(time.toString().substring(3));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
