package mess.manager;

import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewMessBillOnly extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    public ViewMessBillOnly() {
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

    
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        hallIdChk.setSelected(true);
        monthYearTxt.setText("");
        yearTxt.requestFocusInWindow();
        this.setTitle("VIEW MEAL CHARGE");
        bar.setVisible(true);
        bar.setStringPainted(true);
        bar.setValue(0);
        String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int i=0; i<12; i++) monthCmb.addItem(months[i]);
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        
        
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
    
    
    
    
    private void totalCalculator(String month, String year){
        int len = tm.getRowCount();
        Double total = 0.0;
        for(int i=0; i<len; i++){
            String x = tm.getValueAt(i, 4).toString();
            Double X = Double.parseDouble(x);
            total += X;
            //System.out.println(X + " " + total);
        }
        
        totalBill.setText("TOTAL: " + Double.valueOf(df.format(total)) + " Taka");
        
        total = 0.0;
        try{
            psmt = conn.prepareStatement("SELECT sum(special) FROM messbill WHERE month = ? AND year = ?");
            psmt.setString(1, month);
            psmt.setString(2, year);
            rs = psmt.executeQuery();
            while(rs.next())    total = total + rs.getDouble(1);
            psmt.close();
            rs.close();
            specialBill.setText("SPECIAL COST: " + Double.valueOf(df.format(total)) + " Taka");
        }catch(Exception e){
            
        }
        
    }
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        monthCmb = new javax.swing.JComboBox<>();
        yearTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bar = new javax.swing.JProgressBar();
        idTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        monthYearTxt = new javax.swing.JLabel();
        totalBill = new javax.swing.JLabel();
        specialBill = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("MONTH");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("YEAR");

        monthCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        monthCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCmbActionPerformed(evt);
            }
        });

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        yearTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTxtActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "HALL ID", "ROLL", "ROOM", "NAME", "MEAL CHARGE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

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

        monthYearTxt.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        monthYearTxt.setText("MONTH, YEAR");

        totalBill.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        totalBill.setText("...");

        specialBill.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        specialBill.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(monthCmb, 0, 113, Short.MAX_VALUE)
                            .addComponent(yearTxt))))
                .addGap(107, 107, 107)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthYearTxt)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(hallIdChk)
                                .addGap(18, 18, 18)
                                .addComponent(rollChk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalBill)
                            .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(specialBill))))
                .addContainerGap(187, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(rollChk)
                    .addComponent(hallIdChk))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthYearTxt)
                    .addComponent(totalBill))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(specialBill)
                    .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
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

    
    private Double totalBillCalculator(ArrayList<MessBill> m, String month, String year, int hallId){
        int len = m.size();
        Double sum = 0.0;
        
        for(int i=0; i<len; i++){
            sum = sum + m.get(i).mealCharge;
            //System.out.println(m.get(i).mealCharge + "");
        }
        
        try{
            psmt = conn.prepareStatement("SELECT bill FROM tempfood WHERE month = ? AND year = ? AND hallid = ?");
            psmt.setString(1, month);
            psmt.setString(2, year);
            psmt.setInt(3, hallId);
            rs = psmt.executeQuery();
            while(rs.next())    sum += rs.getDouble(1);
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        return Double.valueOf(df.format(sum));
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String month = monthCmb.getSelectedItem().toString().trim();
        String year = yearTxt.getText().toString().trim();
        tm.setRowCount(0);
        monthYearTxt.setText("...");
        
        try{
            int d = Integer.parseInt(year);
            if(d < 2000){
                JOptionPane.showMessageDialog(null, "Please enter a valid year!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a valid year!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        monthYearTxt.setText(month + ", " + year);
        idTxt.requestFocusInWindow();
        ///System.out.println("Hello");
        
        ArrayList <Integer> hallIds = new ArrayList<>();
        ArrayList <String> room = new ArrayList<>();
        ArrayList <String> roll = new ArrayList<>();
        ArrayList <String> name = new ArrayList<>();
        
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,roll,name FROM stuinfo");
            rs = psmt.executeQuery();
            while(rs.next()){
                hallIds.add(rs.getInt(1));
                room.add(rs.getString(2));
                roll.add(rs.getString(3));
                name.add(rs.getString(4));
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = hallIds.size();
        int interval = len/100;
        if(interval == 0)   interval = 1;
        
        
        
        //monthYearTxt.setText(month + "," + year);
        for(int i=0; i<len; i++){
            int hallId = hallIds.get(i);
            String r = roll.get(i);
            String roomno = room.get(i);
            String n = name.get(i);
            BillManager b = new BillManager(month,year,hallId);
            Double mealCharge = totalBillCalculator(b.showBill(),month,year,hallId);
            
            if(i%interval==0){
                bar.setVisible(true);
                if(i/interval == 100)   bar.setValue(99);
                else    bar.setValue(i/interval);
                bar.update(bar.getGraphics());
            }
                
            if(i==len-1){
                    //psmt.executeBatch();
                bar.setVisible(true);
                bar.setValue(100);
                bar.update(bar.getGraphics());
            }
            
            
            Object o[] = {hallId + "",r,roomno,n,Double.valueOf(df.format(mealCharge))+""};
            tm.addRow(o);
            bar.setValue(0);
            
        }
        
        totalCalculator(month,year);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void yearTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTxtActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_yearTxtActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    private void hallIdChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdChkActionPerformed
        boolean b = hallIdChk.isSelected();
        if(b)   rollChk.setSelected(false);
        else    rollChk.setSelected(true);
        idTxt.requestFocusInWindow();
    }//GEN-LAST:event_hallIdChkActionPerformed

    private void rollChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollChkActionPerformed
        boolean b = rollChk.isSelected();
        if(b)   hallIdChk.setSelected(false);
        else    hallIdChk.setSelected(true);
        idTxt.requestFocusInWindow();
    }//GEN-LAST:event_rollChkActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String id = idTxt.getText().toString().trim();
        idTxt.setText("");
        idTxt.requestFocusInWindow();
        int index = 1;
        if(hallIdChk.isSelected())  index = 0;
        
        int len = tm.getRowCount();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No student in the list!", "Empty List!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            String tId = tm.getValueAt(i, index).toString().trim();
            if(tId.equals(id)){
                table.getSelectionModel().setSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "ID is not in the list!", "ID Not Found!", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void monthCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCmbActionPerformed
        yearTxt.requestFocusInWindow();
    }//GEN-LAST:event_monthCmbActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMessBillOnly().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthYearTxt;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel specialBill;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalBill;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
