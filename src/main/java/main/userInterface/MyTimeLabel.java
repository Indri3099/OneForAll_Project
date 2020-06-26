package main.userInterface;

import javax.swing.*;

public class MyTimeLabel extends JLabel implements Printer {
    @Override
    public void print(String s) {
        setText(s);
    }

    @Override
    public void println(String s) {
        print(s + "\n");
    }

    @Override
    public void clear() {
        setText("");
    }
}
