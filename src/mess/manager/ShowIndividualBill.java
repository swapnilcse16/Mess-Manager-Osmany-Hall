package mess.manager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShowIndividualBill extends javax.swing.JFrame {
    
    String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    
    public ShowIndividualBill() {
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
    
    
    
    
    
    void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("INDIVIDUAL BILL");
        for(int i=0; i<12; i++) monthsCmb.addItem(months[i]);
        tm = (DefaultTableModel)table1.getModel();
        refresh();
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy");
        String DATE = formatter.format(date);
        yearTxt.setText(DATE);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table1.setDefaultRenderer(Object.class, centerRenderer);
        this.setResizable(false);
        hallIdChk.setSelected(true);
        
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
        nameLbl.setText("");
        roomLbl.setText("");
        grandTotal.setText("");
        tm.setRowCount(0);
    }
    
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        monthsCmb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yearTxt = new javax.swing.JTextField();
        hallIdTxt = new javax.swing.JTextField();
        hallIdBtn = new javax.swing.JButton();
        nameLbl = new javax.swing.JLabel();
        roomLbl = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "BREAKFAST", "LUNCH", "DINNER", "BREAKFAST BILL", "LUNCH BILL", "DINNER BILL", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("MONTH");

        monthsCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("YEAR");

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        hallIdTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdTxtActionPerformed(evt);
            }
        });

        hallIdBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdBtn.setText("SEARCH");
        hallIdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdBtnActionPerformed(evt);
            }
        });

        nameLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameLbl.setText("NAME: Swapnil Biswas");

        roomLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        roomLbl.setText("ROOM: B3-18");

        grandTotal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        grandTotal.setText("GRAND TOTAL: 3400/-");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "BREAKFAST", "LUNCH", "DINNER", "BREAKFAST BILL", "LUNCH BILL", "DINNER BILL", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table1);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(monthsCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hallIdChk)
                        .addGap(18, 18, 18)
                        .addComponent(rollChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hallIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hallIdBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(145, 279, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roomLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(156, 156, 156))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthsCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(hallIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLbl)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roomLbl)
                        .addGap(18, 18, 18)
                        .addComponent(grandTotal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(hallIdBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void setInfo(String name, String roll, String roomno){
        nameLbl.setText("NAME: "+name);
        //rollTxt.setText(roll);
        roomLbl.setText("ROOM: "+ roomno);
    }
    
    
    
    private void setTable(String month, String year,int hallId){
         BillManager b = new BillManager(month,year,hallId);
         ArrayList <MessBill> messBill = b.showBill();
         int len = messBill.size();
         Double grand = 0.0;
         
         for(int i=0; i<len; i++){
             Object o[] = messBill.get(i).copyShowBill();
             grand = grand + messBill.get(i).mealCharge;
             tm.addRow(o);
         }
         
         grandTotal.setText("GRAND TOTAL: " + java.lang.Math.ceil(grand) + "");
    }
    
    
    private void hallIdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdBtnActionPerformed
        refresh();
        String h = hallIdTxt.getText().toString().trim();
        int hallId = -1;
        
        if(rollChk.isSelected()){
            try{
                psmt = conn.prepareStatement("SELECT hallid from stuinfo WHERE roll = ?");
                psmt.setString(1, h);
                rs = psmt.executeQuery();
                while(rs.next())    hallId = rs.getInt(1);
                if(hallId==-1){
                    JOptionPane.showMessageDialog(null, "The roll no is not available!", "Invalid Roll!", JOptionPane.ERROR_MESSAGE);
                    psmt.close();
                    rs.close();
                    return;
                }
                psmt.close();
                rs.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        
        try{
            if(!rollChk.isSelected())    hallId = Integer.parseInt(h);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hall ID must be a number!", "Invalid ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int c = 0;
        String name = "",roll="",roomno="";
        try{
            psmt = conn.prepareStatement("SELECT name,roll,roomno FROM stuinfo WHERE hallid = ?");
            psmt.setInt(1,hallId);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                c++;
                name = rs.getString(1);
                roll = rs.getString(2);
                roomno = rs.getString(3);
            }
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(c==0){
            JOptionPane.showMessageDialog(null, "There is no student with this Hall ID!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        setInfo(name, roll, roomno);
        String month = monthsCmb.getSelectedItem().toString();
        String year = yearTxt.getText().toString().trim();
        
        try{
            c = Integer.parseInt(year);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "The year you entered is invalid!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(c < 1900){
            JOptionPane.showMessageDialog(null, "The year you entered is invalid!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        setTable(month, year, hallId);
    }//GEN-LAST:event_hallIdBtnActionPerformed

    private void hallIdChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdChkActionPerformed
        boolean checked = hallIdChk.isSelected();
        if(checked){
            rollChk.setSelected(false);
        }
        else    rollChk.setSelected(true);
    }//GEN-LAST:event_hallIdChkActionPerformed

    private void rollChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollChkActionPerformed
        boolean checked = rollChk.isSelected();
        if(checked) hallIdChk.setSelected(false);
        else    hallIdChk.setSelected(true);
    }//GEN-LAST:event_rollChkActionPerformed

    private void hallIdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdTxtActionPerformed
        hallIdBtn.doClick();
    }//GEN-LAST:event_hallIdTxtActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowIndividualBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel grandTotal;
    private javax.swing.JButton hallIdBtn;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField hallIdTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> monthsCmb;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JLabel roomLbl;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
