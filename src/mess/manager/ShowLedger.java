package mess.manager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ShowLedger extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm1,tm2;
    String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    DecimalFormat df = new DecimalFormat("####0.00");
    
    
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("SHOW LEDGER");
        tm1 = (DefaultTableModel)table1.getModel();
        tm2 = (DefaultTableModel)table2.getModel();
        
        tm1.setRowCount(0);
        tm2.setRowCount(0);
        
        for(int i=0; i<12; i++) monthCmb.addItem(months[i]);
        
        refresh();
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy");
        String DATE = formatter.format(date);
        yearTxt.setText(DATE);
        
        try{
            psmt = conn.prepareStatement("SELECT itemname FROM store");
            rs = psmt.executeQuery();
            while(rs.next())    itemCmb.addItem(rs.getString(1));
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
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
    
    
    private void refresh(){
        itemNameLbl.setText("");
        monthYearTxt.setText("");
        availableLbl.setText("");
        avgTxt.setText("");
        availablePrice.setText("");
    }
    
    
    
    public ShowLedger() {
        initComponents();
        init();
    }

    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        monthCmb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        yearTxt = new javax.swing.JTextField();
        itemCmb = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        storedItemSearchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        monthYearTxt = new javax.swing.JLabel();
        itemNameLbl = new javax.swing.JLabel();
        availableLbl = new javax.swing.JLabel();
        avgTxt = new javax.swing.JLabel();
        availablePrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("SELECT MONTH");

        monthCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("SELECT YEAR");

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        itemCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        itemCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                itemCmbItemStateChanged(evt);
            }
        });
        itemCmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemCmbMouseClicked(evt);
            }
        });
        itemCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCmbActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("STORED ITEM");

        storedItemSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        storedItemSearchBtn.setText("SEARCH");
        storedItemSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storedItemSearchBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("INPUT  LIST");

        table1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DATE", "IN QUANTITY", "IN PRICE", "AVAILABLE", "AVG PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        table2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DATE", "STATE", "QUANTITY", "AVG PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("OUTPUT  LIST");

        monthYearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        monthYearTxt.setText("Item name: Rice");

        itemNameLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        itemNameLbl.setText("Item name: Rice");

        availableLbl.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        availableLbl.setText("Item name: Rice");

        avgTxt.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        avgTxt.setText("Item name: Rice");

        availablePrice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        availablePrice.setText("Item name: Rice");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(170, 170, 170))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(monthCmb, 0, 134, Short.MAX_VALUE)
                                        .addComponent(itemCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(storedItemSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monthYearTxt)
                            .addComponent(itemNameLbl)
                            .addComponent(availableLbl)
                            .addComponent(avgTxt)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(202, 202, 202)
                                .addComponent(availablePrice)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemNameLbl))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(storedItemSearchBtn))
                        .addGap(18, 18, 18)
                        .addComponent(avgTxt)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(availablePrice)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(monthYearTxt)
                        .addGap(18, 18, 18)
                        .addComponent(availableLbl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
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

    private void storedItemSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storedItemSearchBtnActionPerformed
        tm1.setRowCount(0);
        tm2.setRowCount(0);
        
        int index = monthCmb.getSelectedIndex();
        String month = monthCmb.getSelectedItem().toString().trim();
        String year = yearTxt.getText().toString().trim();
        String item = itemCmb.getSelectedItem().toString().trim();
        int y = 0;
        
        
        
        refresh();
        
        try{
            y = Integer.parseInt(year);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You entered an invalid year!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        index++;
        String day = index + "";
        if(index <10)   day = "0"+day;
        
        String DATE = year + "-" + day+"%";
        //System.out.println(DATE + " " + item);
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM storeinput WHERE curdate LIKE ? AND itemname = ?");
            psmt.setString(1,DATE);
            psmt.setString(2,item);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                String dateDB = rs.getString(2);
                Double quantity = rs.getDouble(3);
                Double price = rs.getDouble(4);
                Double available = Double.valueOf(df.format(rs.getDouble(5)));
                Double avgPrice = Double.valueOf(df.format(rs.getDouble(6)));
                
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
                try{
                    Date date = dt.parse(dateDB); 
                    Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
                    dateDB = formatter.format(date);
                }catch(Exception e){}
                
                Object o[] = {dateDB+"",quantity+"",price+"",Double.valueOf(df.format(available))+"",Double.valueOf(df.format(avgPrice))+""};
                tm1.addRow(o);
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        
        showStoreOutput(index,month,year,item);/////////////////////////////////
        //double total = 0.0;
        //for(int i=0; i<tm2.getRowCount(); i++)  total += Double.parseDouble(tm2.getValueAt(i, 2).toString());
        //System.out.println(total + "");
        if(true)    return;
        
        
        /*int days[] = new int[12];
        days[0] = 31;
        days[1] = 28 + leapYear(year);
        days[2] = 31;
        days[3] = 30;
        days[4] = 31;
        days[5] = 30;
        days[6] = 31;
        days[7] = 31;
        days[8] = 30;
        days[9] = 31;
        days[10] = 30;
        days[11] = 31;
        
        int noOfDay = days[index-1];
        double stateAmount[] = new double[3];
        double stateAvg[] = new double[3];
        String state[] = {"Breakfast","Lunch","Dinner"};
        for(int k=1; k<=noOfDay;k++){
            DATE = dateMaker(k, index, year);
            try{
                psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE itemname =? AND outdate =? AND state=? AND inputstate='Stored Item'");
                for(int i=0; i<3; i++){
                    stateAmount[i] = 0.0;
                    stateAvg[i] = 0.0;
                    
                    psmt.setString(1, item);
                    psmt.setString(2, DATE);
                    psmt.setString(3, state[i]);
                    rs = psmt.executeQuery();
                    
                    if(rs.next()){
                        stateAmount[i] = rs.getDouble(1);
                        stateAvg[i] = rs.getDouble(2);
                    }
                }
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
                try{
                    Date date = dt.parse(DATE); 
                    Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
                    DATE = formatter.format(date);
                }catch(Exception e){}
                
                Double total = stateAmount[0] + stateAmount[1] + stateAmount[2];
                if(total!=0){
                    Object o[] = {DATE,stateAmount[0]+"",stateAmount[1]+"",stateAmount[2]+"",total+"",stateAvg[0]+"",stateAvg[1]+"",stateAvg[2]+""};
                    tm2.addRow(o);
                }
                psmt.close();
                rs.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }*/
        
        itemNameLbl.setText("Item Name: " + item);
        monthYearTxt.setText(monthCmb.getItemAt(index-1) + ", " + year);
        
        try{
            psmt = conn.prepareStatement("SELECT available, AVGprice FROM store where itemname = ?");
            psmt.setString(1, item);
            rs = psmt.executeQuery();
            if(rs.next()){
                availableLbl.setText("Currently Available Quantity: " + rs.getDouble(1));
                avgTxt.setText("Currently Average Price: " + rs.getDouble(2));
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_storedItemSearchBtnActionPerformed

    private void itemCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCmbActionPerformed
        
    }//GEN-LAST:event_itemCmbActionPerformed

    private void itemCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemCmbItemStateChanged
        //storedItemSearchBtn.doClick();
    }//GEN-LAST:event_itemCmbItemStateChanged

    private void itemCmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemCmbMouseClicked
        //storedItemSearchBtn.doClick();
    }//GEN-LAST:event_itemCmbMouseClicked

    
    
    
    
    private void showStoreOutput(int index,String month,String year,String item){
        int days[] = new int[12];
        days[0] = 31;
        days[1] = 28 + leapYear(year);
        days[2] = 31;
        days[3] = 30;
        days[4] = 31;
        days[5] = 30;
        days[6] = 31;
        days[7] = 31;
        days[8] = 30;
        days[9] = 31;
        days[10] = 30;
        days[11] = 31;
        String DATE = "";
        
        int noOfDay = days[index-1];
        //double stateAmount[] = new double[3];
        //double stateAvg[] = new double[3];
        String state[] = {"Breakfast","Lunch","Dinner"};
        for(int k=1; k<=noOfDay;k++){
            DATE = dateMaker(k, index, year);
            
             try{
                psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE itemname =? AND outdate =? AND state=? AND inputstate='Stored Item'");
                for(int i=0; i<3; i++){
                    //stateAmount[i] = 0.0;
                    //stateAvg[i] = 0.0;
                    psmt.setString(1, item);
                    psmt.setString(2, DATE);
                    psmt.setString(3, state[i]);
                    rs = psmt.executeQuery();
                    String DATE2 = "";
                    try{
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
                        Date date = dt.parse(DATE); 
                        Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
                        DATE2 = formatter.format(date);
                    }catch(Exception e){}
                    
                    while(rs.next()){
                        Object o[] = {DATE2,state[i], rs.getDouble(1)+"",rs.getDouble(2)+""};
                        tm2.addRow(o);
                    }
                }
                
                psmt.close();
                rs.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        itemNameLbl.setText("Item Name: " + item);
        monthYearTxt.setText(monthCmb.getItemAt(index-1) + ", " + year);
        
        try{
            psmt = conn.prepareStatement("SELECT available, AVGprice FROM store where itemname = ?");
            psmt.setString(1, item);
            rs = psmt.executeQuery();
            if(rs.next()){
                availableLbl.setText("Currently Available Quantity: " + Double.valueOf(df.format(rs.getDouble(1))));
                if(Double.valueOf(df.format(rs.getDouble(1))) == 0.0)   avgTxt.setText("Currently Average Price: 0.0 Taka");
                else    avgTxt.setText("Currently Average Price: " + Double.valueOf(df.format(rs.getDouble(2))) + " Taka");
                availablePrice.setText("Current Total Price: " + Double.valueOf(df.format(rs.getDouble(1) * rs.getDouble(2))) + " Taka");
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(tm1.getRowCount() + tm2.getRowCount() == 0)  JOptionPane.showMessageDialog(null, "No ledger is available for this month!", "No Record Found!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    private int leapYear(String year){
        int y = Integer.parseInt(year);
        if(  ((y % 4 == 0) && (y % 100 != 0)) ||    (y % 400 == 0)      )  return 1;
        return 0;
    }
    
    
    private String dateMaker(int day, int month, String year){
        String DAY = day + "";
        if(day <10) DAY = "0"+DAY;
        
        String MONTH = month+"";
        if(month<10)    MONTH="0"+MONTH;
        
        return  year + "-"+MONTH+"-"+DAY;
    }
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowLedger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableLbl;
    private javax.swing.JLabel availablePrice;
    private javax.swing.JLabel avgTxt;
    private javax.swing.JComboBox<String> itemCmb;
    private javax.swing.JLabel itemNameLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthYearTxt;
    private javax.swing.JButton storedItemSearchBtn;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
