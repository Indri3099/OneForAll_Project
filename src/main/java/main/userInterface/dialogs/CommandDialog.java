/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.userInterface.dialogs;

import entities.command.Command;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author enrico
 */
public class CommandDialog extends javax.swing.JDialog {

    List<Command> commands;
    
    public CommandDialog(java.awt.Frame parent, boolean modal, List<Command> commands) {
        super(parent, modal);
        this.commands = commands;
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new Dimension(500,500));
        setMinimumSize(new Dimension(500,300));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comandi");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Elenco comandi disponibili:");
        jLabel1.setFont(jLabel1.getFont().deriveFont((float)18));
        jTextArea1.setFont(jTextArea1.getFont().deriveFont((float)18));
        jTextArea1.setEditable(false);
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        setLocationRelativeTo(null);

        for(Command c : commands){
            jTextArea1.append(c.getName() + "\n");
        }
        pack();
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
