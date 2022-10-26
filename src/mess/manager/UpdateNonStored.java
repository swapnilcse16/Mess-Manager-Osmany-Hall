package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Rectangle;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class UpdateNonStored extends javax.swing.JFrame {

    
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    ArrayList <NonStored> nonStored;
    int selectedRow;
    DecimalFormat df = new DecimalFormat("####0.000");
    ArrayList <Integer> deletedSerial;
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("VIEW & UPDATE NON STORED ITEM");
        selectedRow = -1;
        nonStored = new ArrayList<>();
        deletedSerial = new ArrayList<>();
        this.setResizable(false);
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        dateTxt.setEditable(false);
        Date date = new Date();
        dateChooser.setDate(date);
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
        
        
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM storeoutput WHERE inputstate!= 'Stored Item'");
            rs = psmt.executeQuery();
            while(rs.next()){
                NonStored n = new NonStored(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6));
                nonStored.add(n);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        Collections.sort(nonStored, new CustomComparatorNonStoredDate());
        int len = nonStored.size();
        
        for(int i=0; i<len; i++)    tm.addRow(nonStored.get(i).copy());
        
    }
    
    
    private void refresh(){
        dateTxt.setText("");
        itemTxt.setText("");
        stateCmb.setSelectedIndex(0);
        quantityTxt.setText("");
        priceTxt.setText("");
    }
    
    
    private void set(){
        dateTxt.setText(tm.getValueAt(selectedRow,0).toString().trim());
        itemTxt.setText(tm.getValueAt(selectedRow,1).toString().trim());
        String tState = tm.getValueAt(selectedRow,2).toString().trim();
        for(int i=0; i<3; i++){
            if(tState.equals(stateCmb.getItemAt(i))){
                stateCmb.setSelectedIndex(i);
                break;
            }
        }
        quantityTxt.setText(tm.getValueAt(selectedRow,3).toString().trim());
        priceTxt.setText(tm.getValueAt(selectedRow,4).toString().trim());
    }
    
    
    public UpdateNonStored() {
        initComponents();
        init();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        searchBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateTxt = new javax.swing.JTextField();
        itemTxt = new javax.swing.JTextField();
        quantityTxt = new javax.swing.JTextField();
        priceTxt = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        stateCmb = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();
        saveAndExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("SEARCH BY DATE");

        dateChooser.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("DATE:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("ITEM NAME:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("STATE:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("QUANTITY:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("TOTAL PRICE:");

        dateTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        itemTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        quantityTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        priceTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        priceTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTxtActionPerformed(evt);
            }
        });

        updateBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Item", "State", "Quantity", "Price", "Average Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        stateCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        stateCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner" }));

        deleteBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        saveAndExit.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        saveAndExit.setText("SAVE  AND  EXIT");
        saveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceTxt)
                            .addComponent(quantityTxt)
                            .addComponent(dateTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(itemTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stateCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                        .addComponent(saveAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBtn)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(itemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stateCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn)
                    .addComponent(saveAndExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void priceTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTxtActionPerformed
        updateBtn.doClick();
    }//GEN-LAST:event_priceTxtActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        selectedRow = -1;
        selectedRow = table.getSelectedRow();
        refresh();
        set();
    }//GEN-LAST:event_tableMouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("MMM d,yyyy");
        String DATE = formatter.format(date);
        refresh();
        
        selectedRow = -1;
        table.getSelectionModel().clearSelection();
        
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            String tDate = tm.getValueAt(i,0).toString().trim();
            if(tDate.equals(DATE)){
                selectedRow = i;
                table.getSelectionModel().setSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                set();
                break;
            }
        }
        
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "There is no non-stored item at " + DATE, "No Record Found!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "First Select a item!", "No Item Selected!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String item = itemTxt.getText().trim();
        if(item.equals("")){
            JOptionPane.showMessageDialog(null, "You must enter an item name!", "Invalid Item Name!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!letterChecker(item)){
            JOptionPane.showMessageDialog(null, "First letter of item name will contain only letter!", "Invalid Item Name!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        item = item.toLowerCase();
        item = upperCaseMaker(item);
        
        
        String state = stateCmb.getSelectedItem().toString();
        String quantity = quantityTxt.getText().toString().trim();
        String price = priceTxt.getText().toString().trim();
        Double Quantity = 0.0, Price = 0.0;
        
        try{
            Quantity = Double.parseDouble(quantity);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Quantity must be a number!", "Invalid Quantity!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            Price = Double.parseDouble(price);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Price must be a number!", "Invalid Price!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Quantity <= 0){
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0!", "Invalid Quantity!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Price <=0){
            JOptionPane.showMessageDialog(null, "Price must be greater than 0!", "Invalid Price!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        Double Avg = Double.valueOf(df.format(Price/Quantity));
        nonStored.get(selectedRow).itemname = item;
        nonStored.get(selectedRow).amount = Quantity;
        nonStored.get(selectedRow).avgprice = Avg;
        nonStored.get(selectedRow).state = state;
        nonStored.get(selectedRow).price = Price;
        nonStored.get(selectedRow).change = 1;
        
        tm.setValueAt(item, selectedRow, 1);
        tm.setValueAt(state, selectedRow, 2);
        tm.setValueAt(Quantity + "", selectedRow, 3);
        tm.setValueAt(Price + "", selectedRow, 4);
        tm.setValueAt(Avg+"", selectedRow, 5);
        
        selectedRow = -1;
        table.getSelectionModel().clearSelection();
        refresh();
        
        /*for(int i=0; i<nonStored.size(); i++){
            NonStored t = nonStored.get(i);
            System.out.println(t.itemname + " " + t.state + " " + t.amount + " " + t.price + " " + t.avgprice);
        }*/
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "First Select a item!", "No Item Selected!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        deletedSerial.add(nonStored.get(selectedRow).serialNo);
        nonStored.remove(selectedRow);
        tm.removeRow(selectedRow);
        
        selectedRow = -1;
        table.getSelectionModel().clearSelection();
        refresh();
        
        /*for(int i=0; i<nonStored.size(); i++){
            NonStored t = nonStored.get(i);
            System.out.println(t.itemname + " " + t.state + " " + t.amount + " " + t.price + " " + t.avgprice);
        }*/
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void saveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndExitActionPerformed
        int lenD = deletedSerial.size();
        int lenT = nonStored.size();
        
        if(lenD == 0 && lenT==0){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No Item to update!" , 1);
        
            if(dialogResult != JOptionPane.YES_OPTION)  return;
            else{
                try{
                    conn.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                }
                
                
                setVisible(false);
                Menu m5=new Menu();
                m5.setVisible(true);
                return;
            }
        }
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to update the items?" , "Alert!" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        
        for(int i=0; i<lenD; i++){
            String serial = deletedSerial.get(i) + "+%";
            //System.out.println(serial);
            try{
                psmt = conn.prepareStatement("DELETE FROM storeoutput WHERE inputstate LIKE ?");
                psmt.setString(1,serial);
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        for(int i=0; i<lenT; i++){
            String serial = nonStored.get(i).serialNo + "+%";
            int change = nonStored.get(i).change;
            
            if(change == 1){
                try{
                    psmt = conn.prepareStatement("UPDATE storeoutput SET itemname = ?, state=?, amount=?, inputstate=?, avgprice=? WHERE inputstate LIKE ?");
                    psmt.setString(1, nonStored.get(i).itemname);
                    psmt.setString(2,nonStored.get(i).state);
                    psmt.setDouble(3, nonStored.get(i).amount);
                    psmt.setString(4, nonStored.get(i).serialNo + "+" + nonStored.get(i).price + "");
                    psmt.setDouble(5, Double.valueOf(df.format(nonStored.get(i).price/nonStored.get(i).amount)));
                    psmt.setString(6, serial);
                    psmt.execute();
                    psmt.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } 
        }
        JOptionPane.showMessageDialog(null, "Items are updated successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }


        setVisible(false);
        Menu m5=new Menu();
        m5.setVisible(true);
        return;
    }//GEN-LAST:event_saveAndExitActionPerformed

    
    
    
    
    
    
    boolean letterChecker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        
        //for(int i=0; i<len; i++){
            int ascii = (int)c[0];
            if(!((ascii>=65 && ascii<=90) || (ascii>=97 && ascii<=122) || ascii == 32))    return false;
        //}
        
        return true;
    }
    
    
    String upperCaseMaker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        int temp = (int)c[0] - 32;
        c[0] = (char)temp;
        
        return new String(c);
    }
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateNonStored().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField dateTxt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField itemTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JTextField quantityTxt;
    private javax.swing.JButton saveAndExit;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> stateCmb;
    private javax.swing.JTable table;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}






class NonStored{
    String itemname, outdate,state;
    Double amount;
    int serialNo;
    double avgprice;
    int change;
    double price;

    public NonStored(String itemname, String outdate, String state, Double amount, String serial, double avgprice) {
        this.itemname = itemname;
        this.outdate = outdate;
        this.state = state;
        this.amount = amount;
        this.change = 0;
        try{
            this.serialNo = Integer.parseInt(serialMaker(serial));
            this.price = Double.parseDouble(quantityMaker(serial));
        }catch(Exception e){}
        this.avgprice = avgprice;
    }
    
    
    
    String serialMaker(String serial){
        String newSerial = "";
        int len = serial.length();
        for(int i=0; i<len; i++){
            char c = serial.charAt(i);
            if(c == '+')    break;
            newSerial = newSerial + c + "";
        }
        return newSerial;
    }
    
    
    String quantityMaker(String serial){
        int len = serial.length();
        int index = 0;
        for(int i=0; i<len; i++){
            char c = serial.charAt(i);
            if(c=='+'){
                index = i+1;
                break;
            }
        }
        String newQuantity = "";
        for(int i=index; i<len; i++){
            char c = serial.charAt(i);
            newQuantity = newQuantity + c + "";
        }
        
        return newQuantity;
    }
    
    
    public Object[] copy(){
        String DATE = outdate;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATE); 
            Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
            DATE = formatter.format(date);
        }catch(Exception e){}
        
        Object o[] = {DATE,itemname,state,amount+"",price+"",avgprice+""};
        return o;
    }
}





class CustomComparatorNonStoredDate implements Comparator<NonStored> {
    
    public int compare(NonStored o1, NonStored o2) {
        return o2.outdate.compareTo(o1.outdate);
    }
}