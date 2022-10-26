package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Payment extends javax.swing.JFrame {
    
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("PAYMENT");
        psmt = null;
        rs = null;
        this.setResizable(false);
        hallIdChk.setSelected(true);
        rollChk.setSelected(false);
        Date date = null;
        dateChooser.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        idTxt.requestFocusInWindow();
        
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
        nameTxt1.setText("");
        hallIdTxt1.setText("");
        rollTxt1.setText("");
        roomTxt1.setText("");
        dueTxt1.setText("");
        advanceTxt1.setText("");
        dateChooser.setDate(null);
        paidTxt.setText("");
        paidTxt.requestFocusInWindow();
    }
    
    
    
    
    private void setInfo(int hallId){
        try{
            psmt = conn.prepareStatement("SELECT name,roll,roomno FROM stuinfo WHERE hallid = ?");
            psmt.setInt(1,hallId);
            rs = psmt.executeQuery();
            
            if(rs.next()){
                nameTxt1.setText(rs.getString(1) + "");
                hallIdTxt1.setText(hallId + "");
                rollTxt1.setText(rs.getString(2));
                roomTxt1.setText(rs.getString(3));
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        
        try{
            psmt = conn.prepareStatement("SELECT totaldue from totalbill WHERE hallid = ?");
            psmt.setInt(1, hallId);
            rs = psmt.executeQuery();
            
            if(rs.next()){
                Double due = rs.getDouble(1);
                if(due >= 0.0){
                    dueTxt1.setText(due + "");
                    advanceTxt1.setText("0.0");
                }
                else{
                    advanceTxt1.setText((-due) + "");
                    dueTxt1.setText("0.0");
                }    
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    
    
    

    public Payment() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            initComponents();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StoreInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StoreInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StoreInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtdate = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        idTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        nameLbl = new javax.swing.JLabel();
        hallIdLbl = new javax.swing.JLabel();
        rollLbl = new javax.swing.JLabel();
        roomLbl = new javax.swing.JLabel();
        dueLbl = new javax.swing.JLabel();
        advanceLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        paidTxt = new javax.swing.JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();
        exitBtn = new javax.swing.JButton();
        nameTxt1 = new javax.swing.JLabel();
        hallIdTxt1 = new javax.swing.JLabel();
        rollTxt1 = new javax.swing.JLabel();
        roomTxt1 = new javax.swing.JLabel();
        dueTxt1 = new javax.swing.JLabel();
        advanceTxt1 = new javax.swing.JLabel();
        dueCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hallIdChk.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdChk.setText("HALL ID");
        hallIdChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdChkActionPerformed(evt);
            }
        });

        rollChk.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollChk.setText("ROLL");
        rollChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollChkActionPerformed(evt);
            }
        });

        idTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        nameLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameLbl.setText("NAME:");

        hallIdLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdLbl.setText("HALL ID:");

        rollLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollLbl.setText("ROLL:");

        roomLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        roomLbl.setText("ROOM:");

        dueLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        dueLbl.setText("DUE:");

        advanceLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        advanceLbl.setText("ADVANCE:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("PAID AMOUNT:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("PAYMENT DATE:");

        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        paidTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        dateChooser.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        exitBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        nameTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameTxt1.setForeground(new java.awt.Color(0, 0, 153));

        hallIdTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdTxt1.setForeground(new java.awt.Color(0, 0, 153));

        rollTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollTxt1.setForeground(new java.awt.Color(0, 0, 153));

        roomTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        roomTxt1.setForeground(new java.awt.Color(0, 0, 153));

        dueTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        dueTxt1.setForeground(new java.awt.Color(0, 0, 153));

        advanceTxt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        advanceTxt1.setForeground(new java.awt.Color(0, 0, 153));

        dueCheck.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        dueCheck.setText("DUE");
        dueCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(hallIdChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rollChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLbl)
                            .addComponent(advanceLbl)
                            .addComponent(hallIdLbl)
                            .addComponent(rollLbl)
                            .addComponent(roomLbl)
                            .addComponent(dueLbl)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(exitBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paidTxt)
                            .addComponent(nameTxt1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hallIdTxt1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rollTxt1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomTxt1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dueTxt1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(advanceTxt1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(dueCheck)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(nameTxt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdLbl)
                    .addComponent(hallIdTxt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rollLbl)
                    .addComponent(rollTxt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomLbl)
                    .addComponent(roomTxt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueLbl)
                    .addComponent(dueTxt1))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advanceLbl)
                    .addComponent(advanceTxt1))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paidTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dueCheck)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(exitBtn))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hallIdChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdChkActionPerformed
        boolean b= hallIdChk.isSelected();
        if(b)   rollChk.setSelected(false);
        else    rollChk.setSelected(true);
    }//GEN-LAST:event_hallIdChkActionPerformed

    private void rollChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollChkActionPerformed
        boolean b = rollChk.isSelected();
        if(b)   hallIdChk.setSelected(false);
        else    hallIdChk.setSelected(true);
    }//GEN-LAST:event_rollChkActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        refresh();
        String id = idTxt.getText().toString().trim();
        int hallId = -1;
        int given = 0;
        boolean b = hallIdChk.isSelected();
        String sql = "";
        
        if(b)   sql = "SELECT hallid FROM stuinfo WHERE hallid = ?";
        else    sql = "SELECT hallid from stuinfo WHERE roll = ?";
        
        if(b){
            try{
                given = Integer.parseInt(id);
            }catch(Exception e){
                idTxt.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Hall id must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try{
            psmt = conn.prepareStatement(sql);
            if(b)   psmt.setInt(1, given);  
            else    psmt.setString(1, id);
            rs = psmt.executeQuery();
            if(rs.next())   hallId = rs.getInt(1);
            psmt.close();
            rs.close();
        }catch(Exception e){
            idTxt.requestFocusInWindow();
            JOptionPane.showMessageDialog(null, "Hall id must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        if(hallId!=-1)  setInfo(hallId);
        else    JOptionPane.showMessageDialog(null, "This ID doesn't exist!", "Invalid!", JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_searchBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String amount = paidTxt.getText().toString().trim();
        Double Amount = 0.0;
        int HallId = -1;
        String hallId = hallIdTxt1.getText().toString().trim();
        Date date = dateChooser.getDate();
        
        try{
            HallId = Integer.parseInt(hallId);
        }catch(Exception e){
            idTxt.requestFocusInWindow();
            JOptionPane.showMessageDialog(null, "First insert a valid Hall ID or Roll!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            int c = Integer.parseInt(amount);
            //Amount = Double.parseDouble(amount);
        }catch(Exception e){
            paidTxt.requestFocusInWindow();
            JOptionPane.showMessageDialog(null, "The paid amount must be a number!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Amount = Integer.parseInt(amount) * 1.0;
        if(Amount <= 0.0){
            JOptionPane.showMessageDialog(null, "The paid amount must be a positive number!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(date == null){
            JOptionPane.showMessageDialog(null, "Select a date of payment!!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean b = dueCheck.isSelected();
        if(b){
            JOptionPane.showMessageDialog(null, "This will be counted as student's due!", "Alert!", JOptionPane.INFORMATION_MESSAGE);
            Amount = -Amount;
        }
        
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        String DATE = formatter.format(date);
        
        
        
        
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to update the payment?\nYou will not be able to modify it later!" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        Double prev = 0.0;
        
        try{
            psmt = conn.prepareStatement("SELECT totaldue FROM totalbill WHERE hallid = ?");
            psmt.setInt(1, HallId);
            rs = psmt.executeQuery();
            if(rs.next())   prev = rs.getDouble(1);
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            psmt = conn.prepareStatement("UPDATE `totalbill` SET `totaldue`=? WHERE hallid = ?");
            psmt.setDouble(1, Double.valueOf(df.format(prev - Amount)));
            psmt.setInt(2, HallId);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        try{
            Date d = new Date();
            psmt = conn.prepareStatement("INSERT INTO `paidbill`(`hallid`, `paidamount`, `due`, `previousdue`, `paymentdate`, `recorddate`) VALUES (?,?,?,?,?,?)");
            psmt.setInt(1,HallId);
            psmt.setDouble(2, Amount);
            psmt.setDouble(3, prev - Amount);
            psmt.setDouble(4, prev);
            psmt.setString(5, DATE);
            psmt.setString(6, formatter2.format(d));
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Payment completed successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        refresh();
        idTxt.requestFocusInWindow();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        try{
            conn.close();
        }catch(Exception e){
            
        }
        
        Menu m = new Menu();
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    private void dueCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueCheckActionPerformed
        paidTxt.requestFocusInWindow();
    }//GEN-LAST:event_dueCheckActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel advanceLbl;
    private javax.swing.JLabel advanceTxt1;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JCheckBox dueCheck;
    private javax.swing.JLabel dueLbl;
    private javax.swing.JLabel dueTxt1;
    private com.toedter.calendar.JDateChooser edtdate;
    private javax.swing.JButton exitBtn;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JLabel hallIdLbl;
    private javax.swing.JLabel hallIdTxt1;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JLabel nameTxt1;
    private javax.swing.JTextField paidTxt;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JLabel rollLbl;
    private javax.swing.JLabel rollTxt1;
    private javax.swing.JLabel roomLbl;
    private javax.swing.JLabel roomTxt1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}
