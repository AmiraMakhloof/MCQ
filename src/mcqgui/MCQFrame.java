package mcqgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class MCQFrame extends javax.swing.JFrame {

    Connection conn;
    Statement stmt;
    ResultSet resultSet;
    int a, b, score, s = 0;
    String q = null;
    String a1 = null;
    String a2 = null;
    String a3 = null;
    String ar = null;
    String name = null;

    public MCQFrame(String name) {
        initComponents();
        setTitle("Exams frame");
        setLocationRelativeTo(null);
//        ButtonGroup aaa = new ButtonGroup();
//        aaa.add(jrtAns1);
//        aaa.add(jrtAns2);
//        aaa.add(jrtAns3);
        buttonGroup1.add(jrtAns1);
        buttonGroup1.add(jrtAns2);
        buttonGroup1.add(jrtAns3);

        this.name = name;
        jlWelcome.setText("Welcome  " + name);

        initDB();
        selectQuestion();
        eventdriven();
    }

    public void initDB() {
        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded.!");
            // establish the connection
            // url , user , password
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/mcq", "root", "");
            System.out.println("connection established.!");
            // create staement to write sql code in it 
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println("error .. loading DB ");
        }

    }

    // repeat it 5 time when submit press 
    public void selectQuestion() {
        try {
            // random question
            a = (int) ((Math.random()) * 10.0);
            a++;
            System.out.println(a); // right and found in exams db
            String qu = "SELECT * FROM exams WHERE id =" + a;
            System.out.println("select");   // execute
            resultSet = stmt.executeQuery(qu);
            System.out.println("sssss");     /// it not execute 
            if (resultSet.next()) {
                System.out.println("aaaa");
                // get question and answers
                q = resultSet.getString("question");
                a1 = resultSet.getString("ans1");
                a2 = resultSet.getString("ans2");
                a3 = resultSet.getString("ans3");
                ar = resultSet.getString("rans");
                // put them on the frame
                jblQuestion.setText(q);
                jrtAns1.setText(a1);
                jrtAns2.setText(a2);
                jrtAns3.setText(a3);

                if (a1.equals(ar)) {
                    b = 1;
                }
                if (a2.equals(ar)) {
                    b = 2;
                }
                if (a3.equals(ar)) {
                    b = 3;
                }
            } else {
                System.out.println("eror1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MCQFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eventdriven() {

// button submit
        jbtSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

//                String select = buttonGroup1.getSelection().toString();
                
                if (jrtAns1.isSelected() && b == 1) {
                    score += 2;
                }
                // else if
                if (jrtAns2.isSelected() && b == 2) {
                    score += 2;
                }
                // else if
                if (jrtAns3.isSelected() && b == 3) {
                    score += 2;
                } 
                // try again if true take +1 only 
                // but why trying again by another question 
//                else {
//                    JOptionPane.showMessageDialog(null, "try again");
//                    jrtAns1.setSelected(false);
//                    jrtAns2.setSelected(false);
//                    jrtAns3.setSelected(false);
//                    eventdriven();
//                    if (jrtAns1.isSelected() && b == 1) {
//                        score ++;
//                    } else if (jrtAns2.isSelected() && b == 2) {
//                        score ++;
//                    } else if (jrtAns3.isSelected() && b == 3) {
//                        score ++;
//                    }
//                }
                // to define if i answer 5 question or not 
                if (s == 4) {
                    // we finish the question
                    hide();
                    // move to final frame with the score
                    new FinalFrame(score, name).setVisible(true);
                }
                s++;
                // ask another question 
                jrtAns1.setSelected(true);
                selectQuestion();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jrtAns1 = new javax.swing.JRadioButton();
        jblQuestion = new javax.swing.JLabel();
        jlWelcome = new javax.swing.JLabel();
        jbtSubmit = new javax.swing.JButton();
        jrtAns3 = new javax.swing.JRadioButton();
        jrtAns2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jrtAns1.setText("ans1");

        jblQuestion.setText("question 1");

        jlWelcome.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jlWelcome.setText("welcome.. ");

        jbtSubmit.setText("submit");

        jrtAns3.setText("ans3");

        jrtAns2.setText("ans2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jrtAns1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrtAns2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jrtAns3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jbtSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jrtAns1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrtAns2)
                .addGap(18, 18, 18)
                .addComponent(jrtAns3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MCQFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MCQFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MCQFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MCQFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MCQFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jblQuestion;
    private javax.swing.JButton jbtSubmit;
    private javax.swing.JLabel jlWelcome;
    private javax.swing.JRadioButton jrtAns1;
    private javax.swing.JRadioButton jrtAns2;
    private javax.swing.JRadioButton jrtAns3;
    // End of variables declaration//GEN-END:variables
}
