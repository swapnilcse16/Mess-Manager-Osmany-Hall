
package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OfferInfo extends javax.swing.JFrame {

    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    
    public void init(){
        conn = JConnection.ConnecrDb();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        this.setResizable(false);
        this.setTitle("OFFER INFO");
        date.setEditable(false);
        refresh();
        
        JFrame frame = this;
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                try{
                    Menu m = new Menu();
                    m.setVisible(true);
                    frame.setVisible(false);
                    conn.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void refresh(){
        dateChooser.setDate(null);
        date.setText("");
        bf.setText("");
        lunch.setText("");
        dinner.setText("");
        bf.setEditable(false);
        lunch.setEditable(false);
        dinner.setEditable(false);
    }
    
    public OfferInfo() {
        initComponents();
        init();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        ok = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        date = new javax.swing.JTextField();
        bf = new javax.swing.JTextField();
        lunch = new javax.swing.JTextField();
        dinner = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Taka = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dateChooser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateChooserKeyPressed(evt);
            }
        });
        dateChooser.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                dateChooserVetoableChange(evt);
            }
        });

        ok.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("DATE");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("DATE");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("BREAKFAST");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("LUNCH");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("DINNER");

        save.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        bf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfActionPerformed(evt);
            }
        });

        lunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunchActionPerformed(evt);
            }
        });

        dinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dinnerActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Taka");

        Taka.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Taka.setText("Taka");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Taka");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bf, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lunch, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Taka))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dinner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Taka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(save)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        Date dt = dateChooser.getDate();
        if(dt == null){
            JOptionPane.showMessageDialog(null, "Select a date first!", "Invalid Date!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(dt);
        
        Double b = 0.0, l = 0.0, d = 0.0;
        try{
            psmt = conn.prepareStatement("SELECT * FROM offerinfo WHERE offerdate = ?");
            psmt.setString(1, DATE);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                b = rs.getDouble(2);
                l = rs.getDouble(3);
                d = rs.getDouble(4);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        bf.setText(b + "");
        lunch.setText(l + "");
        dinner.setText(d + "");
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        DATE = formatter2.format(dt);
        date.setText(DATE);
        
        bf.setEditable(true);
        lunch.setEditable(true);
        dinner.setEditable(true);
        bf.requestFocusInWindow();
    }//GEN-LAST:event_okActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String b,l,d, dt, DATE = "";
        SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
        int UPDATE = 0;
        Double B = 0.0, L = 0.0, D = 0.0;
        
        dt = date.getText().toString().trim();
        b = bf.getText().toString().trim();
        l = lunch.getText().toString().trim();
        d = dinner.getText().toString().trim();
        
        try{
            B = Double.parseDouble(b);
            L = Double.parseDouble(l);
            D = Double.parseDouble(d);
            
            Date x = df.parse(dt); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
            DATE = formatter.format(x);
            System.out.println(DATE);
            
            psmt = conn.prepareStatement("SELECT * FROM offerinfo WHERE offerdate = ?");
            psmt.setString(1, DATE);
            rs = psmt.executeQuery();
            
            while(rs.next())    UPDATE++;
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Inputs are incorrect or you haven't chosen a date!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "";
        if(UPDATE==0)   sql = "INSERT INTO offerinfo (bf,lunch,dinner,offerdate) VALUES(?,?,?,?)";
        else    sql = "UPDATE offerinfo SET bf = ? , lunch = ? , dinner = ? WHERE offerdate = ?";
        refresh();
        
        try{
            psmt = conn.prepareStatement(sql);
            psmt.setDouble(1, B);
            psmt.setDouble(2, L);
            psmt.setDouble(3, D);
            psmt.setString(4, DATE);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_saveActionPerformed

    private void bfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfActionPerformed
        save.doClick();
    }//GEN-LAST:event_bfActionPerformed

    private void lunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchActionPerformed
        save.doClick();
    }//GEN-LAST:event_lunchActionPerformed

    private void dinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dinnerActionPerformed
        save.doClick();
    }//GEN-LAST:event_dinnerActionPerformed

    private void dateChooserVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_dateChooserVetoableChange
        
    }//GEN-LAST:event_dateChooserVetoableChange

    private void dateChooserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateChooserKeyPressed
        
    }//GEN-LAST:event_dateChooserKeyPressed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OfferInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Taka;
    private javax.swing.JTextField bf;
    private javax.swing.JTextField date;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField dinner;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lunch;
    private javax.swing.JButton ok;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
