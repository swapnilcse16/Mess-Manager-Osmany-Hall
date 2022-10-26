package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class ShowPayment extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    public ShowPayment() {
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
        
        this.setTitle("PAYMENT LIST");
        hallIdChk.setSelected(true);
        rollChk.setSelected(false);
        dateChooser.setDate(null);
        idTxt.setText("");
        idTxt.requestFocusInWindow();
        tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);
        Date date = new Date();
        dateChooser.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        totalTxt.setText("");
        dateTxt.setText("");
        
        int width[] = {100,100,100,100,150,100,100,100,100,100,100,100};
        for(int i=0; i<12; i++){
            int w = width[i];
            table.getColumnModel().getColumn(i).setPreferredWidth(w);
        }
        
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        idTxt = new javax.swing.JTextField();
        searchIdBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        searchDateBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        totalTxt = new javax.swing.JLabel();
        dateTxt = new javax.swing.JLabel();

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

        searchIdBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchIdBtn.setText("SEARCH");
        searchIdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIdBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("SEARCH  BY PAYMENT  DATE");

        dateChooser.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        searchDateBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchDateBtn.setText("SEARCH");
        searchDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDateBtnActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PAYMENT DATE", "RECORD DATE", "HALL ID", "ROLL", "NAME", "ROOM", "DEPARTMENT", "PREVIOUS DUE", "PREVIOUS ADVANCE", "PAID AMOUNT", " DUE", "ADVANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        totalTxt.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        totalTxt.setText("TOTAL:");

        dateTxt.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        dateTxt.setText("DATE:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(hallIdChk)
                        .addGap(28, 28, 28)
                        .addComponent(rollChk))
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchIdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchDateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalTxt)
                    .addComponent(dateTxt))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchIdBtn)
                    .addComponent(dateTxt))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchDateBtn)
                        .addComponent(totalTxt))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hallIdChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdChkActionPerformed
        boolean b = hallIdChk.isSelected();
        if(b)   rollChk.setSelected(false);
        else    rollChk.setSelected(true);
    }//GEN-LAST:event_hallIdChkActionPerformed

    private void rollChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollChkActionPerformed
        boolean b = rollChk.isSelected();
        if(b)   hallIdChk.setSelected(false);
        else    hallIdChk.setSelected(true);
    }//GEN-LAST:event_rollChkActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchIdBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    private void searchIdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIdBtnActionPerformed
        String id = idTxt.getText().toString().trim();
        int hallid = -1;
        boolean b = rollChk.isSelected();
        tm.setRowCount(0);
        totalTxt.setText("");
        dateTxt.setText("");
        
        if(b){
            try{
                psmt = conn.prepareStatement("SELECT hallid FROM stuinfo WHERE roll = ?");
                psmt.setString(1, id);
                rs = psmt.executeQuery();
                if(rs.next())   hallid = rs.getInt(1);
                psmt.close();
                rs.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(hallid == -1){
                JOptionPane.showMessageDialog(null, "This roll doesn't exist!", "Invalid Roll!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        
        else{
            try{
                hallid = Integer.parseInt(id);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Hall ID must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        ArrayList <PaymentContents> paymentContents = new ArrayList<>();
        
        try{
            psmt = conn.prepareStatement("Select paymentdate,recorddate,roll,name,roomno,dept,previousdue,paidamount,due from stuinfo JOIN paidbill USING (hallid) WHERE hallid =?");
            psmt.setInt(1, hallid);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                String pDate = rs.getString(1);
                String rDate = rs.getString(2);
                String roll = rs.getString(3);
                String name = rs.getString(4);
                String room = rs.getString(5);
                String dept = rs.getString(6);
                double prevDue = rs.getDouble(7);
                double paid = rs.getDouble(8);
                double due = rs.getDouble(9);
                
                PaymentContents p = new PaymentContents(pDate, rDate, hallid, roll, name, room, dept, prevDue, paid, due);
                paymentContents.add(p);
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = paymentContents.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No Record Found!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Collections.sort(paymentContents, new CustomComparatorDate());
        
        for(int i=0; i<len; i++){
            Object o[] = paymentContents.get(i).copy();
            tm.addRow(o);
        }
        
        //dateChooser.setDate(null);
    }//GEN-LAST:event_searchIdBtnActionPerformed

    
    private void setTotal(String DATE){
        int len = tm.getRowCount();
        Double total = 0.0;
        for(int i=0; i<len; i++)    total = total + Double.parseDouble(tm.getValueAt(i, 9).toString());
        totalTxt.setText("TOTAL PAYMENT: " + total + " Taka");
        
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATE); 
            Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
            DATE = formatter.format(date);
            dateTxt.setText("DATE: " + DATE);
        }catch(Exception e){}
    }
    
    
    
    private void searchDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDateBtnActionPerformed
        ArrayList <PaymentContents> paymentContents = new ArrayList<>();
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        tm.setRowCount(0);
        
        totalTxt.setText("");
        dateTxt.setText("");
        
        try{
            psmt = conn.prepareStatement("Select paymentdate,recorddate,roll,name,roomno,dept,previousdue,paidamount,due,hallid from stuinfo JOIN paidbill USING (hallid) WHERE paymentdate =?");
            psmt.setString(1, DATE);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                String pDate = rs.getString(1);
                String rDate = rs.getString(2);
                String roll = rs.getString(3);
                String name = rs.getString(4);
                String room = rs.getString(5);
                String dept = rs.getString(6);
                double prevDue = rs.getDouble(7);
                double paid = rs.getDouble(8);
                double due = rs.getDouble(9);
                int hallid = rs.getInt(10);
                
                PaymentContents p = new PaymentContents(pDate, rDate, hallid, roll, name, room, dept, prevDue, paid, due);
                paymentContents.add(p);
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = paymentContents.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No Record Found!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Collections.sort(paymentContents, new CustomComparatorDate());
        
        for(int i=0; i<len; i++){
            Object o[] = paymentContents.get(i).copy();
            tm.addRow(o);
        }
        
        idTxt.setText("");
        setTotal(DATE);
    }//GEN-LAST:event_searchDateBtnActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JButton searchDateBtn;
    private javax.swing.JButton searchIdBtn;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalTxt;
    // End of variables declaration//GEN-END:variables
}









class PaymentContents{
    String pDate,rDate;
    int hallId;
    String roll,name,roomno,dept;
    double  prevDue,paid,currentDue;

    public PaymentContents(String pDate, String rDate, int hallId, String roll, String name, String roomno, String dept, double prevDue, double paid, double cuurentDue) {
        this.pDate = pDate;
        this.rDate = rDate;
        this.hallId = hallId;
        this.roll = roll;
        this.name = name;
        this.roomno = roomno;
        this.dept = dept;
        this.prevDue = prevDue;
        this.paid = paid;
        this.currentDue = cuurentDue;
    }
    
    public Object[] copy(){
        Double prevAdvance = 0.0, curAdvance = 0.0;
        Double pDue = 0.0, cDue = 0.0;
        String payDate = "",recDate = "";
        
        if(prevDue < 0.0)   prevAdvance = -prevDue;
        else    pDue = prevDue;
        
        if(currentDue < 0.0)    curAdvance = - currentDue;
        else    cDue = currentDue;
        
        payDate = pDate;
        recDate = rDate;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(payDate); 
            Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
            payDate = formatter.format(date);
            
            date = dt.parse(recDate); 
            recDate = formatter.format(date);
        }catch(Exception e){}
        
        
        Object o[] = {payDate+"",recDate+"",hallId+"",roll,name,roomno,dept,pDue+"",prevAdvance+"",paid+"",cDue+"",curAdvance+""};
        return o;
    }
}








class CustomComparatorDate implements Comparator<PaymentContents> {

    @Override
    public int compare(PaymentContents r1, PaymentContents r2) {
        String date1 = r1.pDate;
        String date2 = r2.pDate;
        
        if(date1.equals(date2)){
            return r1.hallId - r2.hallId;
        }
        return date2.compareTo(date1);
   }
    
}
