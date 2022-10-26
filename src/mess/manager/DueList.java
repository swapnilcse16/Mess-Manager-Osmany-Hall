package mess.manager;

import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DueList extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    ArrayList<Due> due;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    private void serialMaker(ArrayList<Due> d){
        int len = d.size();
        for(int i=1; i<=len; i++)   d.get(i-1).serial = i;
    }
    
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("DUE LIST");
        hallIdChk.setSelected(true);
        idTxt.requestFocusInWindow();
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        due = new ArrayList<>();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        try{
            psmt = conn.prepareStatement("SELECT hallid, roll,roomno,name,dept,totaldue FROM stuinfo JOIN totalbill USING(hallid)");
            rs = psmt.executeQuery();
            while(rs.next()){
                Due d = new Due(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4) , rs.getString(5), rs.getDouble(6));
                due.add(d);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        serialMaker(due);
        int len = due.size();
        for(int i=0; i<len; i++)    tm.addRow(due.get(i).copy());
        
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
    
    
    
    public DueList() {
        initComponents();
        init();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        idTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        searchBtn2 = new javax.swing.JButton();
        searchBtn3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hallIdChk.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdChk.setText("HALL  ID");
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

        searchBtn2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn2.setText("SORT  BY  HALL  ID");
        searchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn2ActionPerformed(evt);
            }
        });

        searchBtn3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn3.setText("SORT  BY  DUE");
        searchBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn3ActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SL NO", "HALL ID", "ROLL", "ROOM", "NAME", "DEPARTMENT", "ADVANCE", "DUE"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(hallIdChk)
                .addGap(18, 18, 18)
                .addComponent(rollChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(searchBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(searchBtn2)
                    .addComponent(searchBtn3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
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

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String id = idTxt.getText().toString().trim();
        idTxt.setText("");
        idTxt.requestFocusInWindow();
        int index = 2;
        if(hallIdChk.isSelected())  index = 1;
        
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

    private void searchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn2ActionPerformed
        idTxt.requestFocusInWindow();
        tm.setRowCount(0);
        
        Collections.sort(due, new CustomComparatorID());
        serialMaker(due);
        int len = due.size();
        for(int i=0; i<len; i++)    tm.addRow(due.get(i).copy());
    }//GEN-LAST:event_searchBtn2ActionPerformed

    private void searchBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn3ActionPerformed
        idTxt.requestFocusInWindow();
        tm.setRowCount(0);
        
        Collections.sort(due, new CustomComparatorDuezZz());
        serialMaker(due);
        int len = due.size();
        for(int i=0; i<len; i++)    tm.addRow(due.get(i).copy());
    }//GEN-LAST:event_searchBtn3ActionPerformed

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

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DueList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField idTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtn2;
    private javax.swing.JButton searchBtn3;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}





class Due{
    int serial,hallId;
    String roll,room,name,dept;
    Double due;

    public Due(int h, String roll, String room, String name, String dept, Double due) {
        hallId = h;
        this.roll = roll;
        this.room = room;
        this.name = name;
        this.dept = dept;
        this.due = due;
    }
    
    
    
    public Object[] copy(){
        Double ad = 0.0, d = 0.0;
        if(due < 0.0)   ad = -due;
        else    d = due;
        
        Object o[] = {serial+"", hallId+"",roll,room,name,dept,ad+"",d+""};
        return o;
    }
    
}





class CustomComparatorDuezZz implements Comparator<Due> {
    @Override
    public int compare(Due o1, Due o2) {
        if(Double.compare(o2.due,o1.due) > 0) return 1;
        return -1;
    }
}


class CustomComparatorID implements Comparator<Due> {
    @Override
    public int compare(Due o1, Due o2) {
        if(o2.hallId < o1.hallId) return 1;
        return -1;
    }
}
