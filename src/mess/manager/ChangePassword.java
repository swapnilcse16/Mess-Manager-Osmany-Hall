package mess.manager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChangePassword extends javax.swing.JFrame {

    
    String prevPassword;
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    
    private void init(){
        conn = JConnection.ConnecrDb();
        this.setResizable(false);
        this.setTitle("CHANGE PASSWORD");
        try{
            psmt = conn.prepareStatement("SELECT * FROM sequence");
            rs = psmt.executeQuery();
            if(rs.next())   prevPassword = rs.getString(2);
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        JFrame frame = this;
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                try{
                    Login m = new Login();
                    m.setVisible(true);
                    frame.setVisible(false);
                    conn.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
    public ChangePassword() {
        initComponents();
        init();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        prevPassTxt = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        newPassTxt = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        rePassTxt = new javax.swing.JPasswordField();
        changePassBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("PREVIOUS  PASSWORD");

        prevPassTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("NEW  PASSWORD");

        newPassTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("RETYPE  PASSWORD");

        rePassTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rePassTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rePassTxtActionPerformed(evt);
            }
        });

        changePassBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        changePassBtn.setText("CHANGE  PASSWORD");
        changePassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changePassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rePassTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                        .addComponent(prevPassTxt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(newPassTxt, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(prevPassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(newPassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rePassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(changePassBtn)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changePassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassBtnActionPerformed
        String prevP = prevPassTxt.getText();
        String newP = newPassTxt.getText();
        String reP = rePassTxt.getText();
        
        if(prevP.equals("")){
            JOptionPane.showMessageDialog(null, "You must enter the previous password!", "Password Will Not Be Empty!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(newP.equals("")){
            JOptionPane.showMessageDialog(null, "You must enter a new password!", "Password Will Not Be Empty!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(reP.equals("")){
            JOptionPane.showMessageDialog(null, "You must retype the new password!", "Password Will Not Be Empty!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        if(!prevP.equals(prevPassword)){
            JOptionPane.showMessageDialog(null, "Previous password is not correct!", "Not Matched!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!newP.equals(reP)){
            JOptionPane.showMessageDialog(null, "Correctly retype the new password!", "Not Matched!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            psmt = conn.prepareStatement("UPDATE sequence SET password = ?");
            psmt.setString(1, newP);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Password is changed successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        try{
            conn.close();
        }catch(Exception e){}
        
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_changePassBtnActionPerformed

    private void rePassTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rePassTxtActionPerformed
        changePassBtn.doClick();
    }//GEN-LAST:event_rePassTxtActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePassBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPassTxt;
    private javax.swing.JPasswordField prevPassTxt;
    private javax.swing.JPasswordField rePassTxt;
    // End of variables declaration//GEN-END:variables
}
