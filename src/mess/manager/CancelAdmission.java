package mess.manager;

import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CancelAdmission extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement psmt = null;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel tm;
    
    
    
    
    
    
    
    
    
    
    public CancelAdmission() {
        initComponents();
        conn = JConnection.ConnecrDb();
        this.setResizable(false);
        
        hallIdChk.setSelected(true);
        this.setTitle("CANCEL ADMISSION");
        try{
            psmt = conn.prepareStatement("SELECT * FROM stuinfo");
            rs = psmt.executeQuery();
            tm = (DefaultTableModel)student_info.getModel();
            tm.setRowCount(0);
            
            
            while(rs.next()){
                Object o[] = {false,rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(6),rs.getString(15)};
                tm.addRow(o);
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private int insert(int hallid){
        int success = 0;
        
        try{
            //System.out.println("Hey!" + hallid);
            psmt = conn.prepareStatement("select * from stuinfo where hallid = ? ");
            psmt.setInt(1, hallid);
            rs = psmt.executeQuery();
            
            
            if(rs.next()){              
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date();
                Date todayWithZeroTime = formatter.parse(formatter.format(today));
                String outdate = DateFormat.getDateInstance().format(todayWithZeroTime);
                
                psmt = conn.prepareStatement("INSERT INTO previousstudents(`hallid`, `roll`, `name`, `fname`, `mname`, `dept`, `entrydate`, `contno`, `bgrp`, `sex`, `rel`, `dob`, `peradd`, `presentadd`, `roomno`, `outdate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                psmt.setInt(1, rs.getInt(1));
                //System.out.println(rs.getInt(1) + "");
                
                for(int i=2; i<=15; i++)    psmt.setString(i, rs.getString(i));
                psmt.setString(16, outdate);
                psmt.execute();
                success = 1;
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            success = 0;
            JOptionPane.showMessageDialog(null, "Oops! An unknown error occured!", "Alert", JOptionPane.ERROR_MESSAGE); 
        }
        
        return success;
    }

    
    
    

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        student_info = new javax.swing.JTable();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        idTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        student_info.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        student_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "SELECT", "HALL ID", "ROLL NO", "STUDENT NAME", "DEPARTMENT", "ROOM NO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        student_info.getTableHeader().setReorderingAllowed(false);
        student_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_infoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(student_info);

        hallIdChk.setText("HALL  ID");
        hallIdChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdChkActionPerformed(evt);
            }
        });

        rollChk.setText("ROLL");
        rollChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollChkActionPerformed(evt);
            }
        });

        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(hallIdChk)
                .addGap(18, 18, 18)
                .addComponent(rollChk)
                .addGap(18, 18, 18)
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ok, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    private void student_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_infoMouseClicked
        int selectedRow = student_info.getSelectedRow();
        ///student_info.getSelectionModel().clearSelection();
        boolean b = (boolean) tm.getValueAt(selectedRow, 0);
        if(b){
            tm.setValueAt(false, selectedRow, 0);
            return;
        }
        tm.setValueAt(true, selectedRow, 0); 
    }//GEN-LAST:event_student_infoMouseClicked

    
    
    
    
    
    
    
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        ArrayList <Integer> selection = new ArrayList<>();
        int len = tm.getRowCount();
        int flag = 0;
        
        for(int i=0; i<len; i++){
            boolean b = (boolean) tm.getValueAt(i, 0);
            if(b){
                selection.add(i);
                flag = 1;
            }
        }
        
        if(len == 0 || flag == 0){
            if(len == 0){JOptionPane.showMessageDialog(null, "No Student into the list!", "Invalid Selection", JOptionPane.ERROR_MESSAGE);}
            else{JOptionPane.showMessageDialog(null, "No Student Selected!", "Invalid Selection", JOptionPane.ERROR_MESSAGE);}
            return;
        }
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to cancel seat of " + selection.size() + " students?","Warning",1);
        if(dialogResult == JOptionPane.YES_OPTION){
            deleteStudent(selection, selection.size());
            return;
        }
    }//GEN-LAST:event_okActionPerformed

    
    
    
    
    private void deleteStudent(ArrayList<Integer>selection, int length){
        DefaultTableModel tm = (DefaultTableModel)student_info.getModel();
        for(int i=0; i<length; i++){
            int selectedRowIndex = selection.get(i);
            String hallId = tm.getValueAt(selectedRowIndex, 1).toString();
            int hallid = Integer.parseInt(hallId);
            
            int success = insert(hallid);
            if(success == 0){
                JOptionPane.showMessageDialog(null, "Oops! An unknown error occured!", "Alert", JOptionPane.ERROR_MESSAGE); 
                return;
            }
            
            try {
                psmt = conn.prepareStatement("DELETE FROM stuinfo WHERE hallid = ?");
                psmt.setInt(1, hallid);
                psmt.execute();
                psmt.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Students' seat can't be cancelled", "Error Occured!", JOptionPane.ERROR_MESSAGE); 
                return;
            }
            
            try{
                psmt = conn.prepareStatement("DELETE FROM `totalbill` WHERE hallid = ?");
                psmt.setInt(1, hallid);
                psmt.execute();
                psmt.close();
            }   catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            }
        }
              
        for(int i = 0; i<length; i++){
            int selectedRowIndex = selection.get(i) - i;
            tm.removeRow(selectedRowIndex);
        }
        
        JOptionPane.showMessageDialog(null, "Seat cancel completed!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    
    
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        setVisible(false);
        Menu m5=new Menu();
        m5.setVisible(true);
    }//GEN-LAST:event_exitActionPerformed

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
        searchBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String id = idTxt.getText().toString().trim();
        idTxt.setText("");
        idTxt.requestFocusInWindow();
        boolean b = hallIdChk.isSelected();
        int index = 2;
        if(b)   index = 1;
        int len = tm.getRowCount();
        
        for(int i=0; i<len; i++){
            String tId = tm.getValueAt(i, index).toString().trim();
            if(tId.equals(id)){
                student_info.getSelectionModel().setSelectionInterval(i, i);
                student_info.scrollRectToVisible(new Rectangle(student_info.getCellRect(i, 0, true)));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "The ID doesn't exist in the list!", "Invalid ID!", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_searchBtnActionPerformed

  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CancelAdmission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField idTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable student_info;
    // End of variables declaration//GEN-END:variables
}
