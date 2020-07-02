/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.userInterface.dialogs;

import java.awt.*;

/**
 *
 * @author enrico
 */
public class DescriptionDialog extends javax.swing.JDialog {

    /**
     * Creates new form DescriptionDialog
     */
    public DescriptionDialog(java.awt.Frame parent, boolean modal, String description) {
        super(parent, modal);
        initComponents();
        jTextArea1.setText(description);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Descrizione");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        setMinimumSize(new Dimension(700,300));
        setPreferredSize(new Dimension(750,350));
        jTextArea1.setFont(jTextArea1.getFont().deriveFont((float) 18));
        jTextArea1.setEditable(false);
        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack();
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
