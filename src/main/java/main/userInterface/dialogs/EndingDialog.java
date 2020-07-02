package main.userInterface.dialogs;

import entities.Score;
import main.scoreRest.RestHandling;

/**
 *
 * @author enrico
 */
public class EndingDialog extends javax.swing.JDialog {

    private Score score;

    private String phrase;

    private String gameName;
    /**
     * Creates new form EndingDialog
     */
    public EndingDialog(java.awt.Frame parent, boolean modal, Score score, String phrase) {
        super(parent, modal);
        this.score = score;
        this.phrase = phrase;
        initComponents();
        init();
    }

    private void init(){
        jTextArea1.setText(phrase);
        score.getFinalTime().setHours(0);
        jTextArea1.append("\nPunteggio : " + score.getPoints() + "/" + score.getTotalPoints() + "\nTempo impiegato : " + score.getFinalTime());
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNameSave = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("The End");
        setMinimumSize(new java.awt.Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(500, 300));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Se desideri salvare il tuo punteggio inserisci un nome e salva!");
        jPanel1.add(jLabel2, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(jTextFieldNameSave, java.awt.BorderLayout.CENTER);

        jButtonSave.setText("salva");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSave, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);
        setLocationRelativeTo(null);
        pack();
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(!jTextFieldNameSave.getText().equals("")){
            score.setName(jTextFieldNameSave.getText());
            RestHandling.saveScore(score);
            this.dispose();
        }
    }


    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldNameSave;
}
