package main;

import entities.Score;
import userInterface.GUI;
import userInterface.dialogs.EndingDialog;

import javax.swing.*;

/**
 * Il runner ha come solo scopo quello di far partire l'intera applicazione. <br>
 * Al termine del thread timer (quando il tempo è esaurito) viene mostrato un messaggio e aperta la endDialog.
 */
public class Runner {
    public static void main(String[] args) {

        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        GUI gui = new GUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        synchronized (GUI.getTimer()) {
            try {
                GUI.getTimer().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(gui, "Tempo esaurito", "TEMPO", JOptionPane.INFORMATION_MESSAGE);
        JDialog ending = new EndingDialog(gui, true, new Score(gui.getGame().getTotalTime(), gui.getGame().getActualPoints(), gui.getGame().getPointGoal(), gui.getGame().getName()), gui.getGame().getLose());
        ending.setVisible(true);
        gui.dispose();

    }
}
