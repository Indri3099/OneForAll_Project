/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.dialogs;

import entities.Score;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Viene mostrata la classifica degli score fatti per il gioco in questione
 * @author enrico
 */
public class RankingDialog extends javax.swing.JDialog {

    /**
     * Creates new form RankingDialog
     */
    
    List<Score> scores;
    
    String gameName;
    
    public RankingDialog(java.awt.Frame parent, boolean modal, List<Score> scores, String gameName) {
        super(parent, modal);
        this.scores = scores;
        this.gameName = gameName;
        initComponents();
        init();
    }

    private void init(){
        DefaultTableModel model = (DefaultTableModel) jTableRanking.getModel();

        for(Score score : scores){
            if(score.getGameName().equals(gameName)){
                model.addRow(new Object[]{score.getName(), score.getPoints() + "/" + score.getTotalPoints(), score.getFinalTime().toString().substring(3)});
            }
        }
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRanking = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ranking");
        setPreferredSize(new Dimension(400,150));
        jTableRanking.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTableRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Points", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableRanking);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack();
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRanking;
}
