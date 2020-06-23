/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.userInterface;

import javax.swing.JTextArea;

/**
 *
 * @author enrico
 */
public class MyTextArea extends JTextArea implements Printer{

    @Override
    public void print(String s) {
        this.append(s);
    }

    @Override
    public void println(String s) {
        print(s + "\n");
    }


}
