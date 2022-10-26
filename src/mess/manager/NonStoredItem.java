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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class NonStoredItem extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    int selectedRow;
    int serialNo;
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("NON STORED ITEM ENTRY");
        selectedRow = -1;
        tm = (DefaultTableModel)outputSheetTable.getModel();
        Date date = new Date();
        dateChooser.setDate(date);
        dateChooserUpdate.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        editor = (JTextFieldDateEditor) dateChooserUpdate.getDateEditor();
        editor.setEditable(false);
        this.setResizable(false);
        tm.setRowCount(0);
        
        
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM sequence");
            rs = psmt.executeQuery();
            if(rs.next())   serialNo = rs.getInt(3);
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
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
    
    
    
    
    
    private int databaseChecking(String item, String givenDate, String stat){
        String DATE = givenDate;
        SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATE); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
            DATE = formatter.format(date);
        }catch(Exception e){}
        
        int count = 0;
        
        try{
            psmt = conn.prepareStatement("SELECT itemname,outdate,state from storeoutput where itemname = ? AND outdate = ? AND state =?");
            psmt.setString(1,item);
            psmt.setString(2, DATE);
            psmt.setString(3, stat);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                count++;
                if(count > 0)   break;
            }
            
            //System.out.println("dItem: " + rs.getString(1) + " tDate: " + rs.getString(2) + " tStat: " + rs.getString(3));
            //System.out.println("sItem: " + item + " sDate: " + DATE + " sState: " + stat);
            psmt.close();
            rs.close();
            return count;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    
    
    
    
    
    private int tableChecking(String item, String givenDate, String stat, int row){
        String DATE = givenDate;
        /*SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATE); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
            DATE = formatter.format(date);
        }catch(Exception e){}*/
        
        int len = tm.getRowCount();
        int flag = 0;
        for(int i=0; i<len; i++){
            if(i==row)  continue;
            String tItem = tm.getValueAt(i,0).toString().trim();
            String tDate = tm.getValueAt(i,4).toString().trim();
            String tStat = tm.getValueAt(i,3).toString().trim();
            
            if(item.equals(tItem) && tDate.equals(DATE) && tStat.equals(stat)){
                flag = 1;
                //System.out.println("tItem: " + tItem + " tDate: " + tDate + " tStat: " + tStat);
                //System.out.println("sItem: " + item + " sDate: " + DATE + " sState: " + stat);
                break;
            }
        }
        
        return flag;
    }
    
    
    
    
    
    String upperCaseMaker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        int temp = (int)c[0] - 32;
        c[0] = (char)temp;
        
        return new String(c);
    }
    
    
    
    boolean letterChecker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        
        //for(int i=0; i<len; i++){
            int ascii = (int)c[0];
            if(!((ascii>=65 && ascii<=90) || (ascii>=97 && ascii<=122) || ascii == 32))    return false;
        //}
        
        return true;
    }
    
    
    
    

    public NonStoredItem() {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        quantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        itemName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        statusUpdate = new javax.swing.JComboBox<>();
        itemNameUpdate = new javax.swing.JTextField();
        quantityUpdate = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        dateChooserUpdate = new com.toedter.calendar.JDateChooser();
        priceUpdate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputSheetTable = new javax.swing.JTable();
        saveAndExit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setText("ITEM NAME");

        jLabel3.setText("PRICE");

        ok.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        jLabel4.setText("QUANTITY(kg/pieces)");

        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });

        itemName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quantity)
                            .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(itemName))))
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ok)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel5.setText("OUT FOR");

        jLabel6.setText("STATUS");

        jLabel7.setText("ITEM NAME");

        jLabel8.setText("PRICE");

        statusUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner" }));

        itemNameUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        quantityUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        priceUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("QUANTITY(kg/pieces)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusUpdate, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemNameUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(quantityUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooserUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(priceUpdate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setText("DATE");

        outputSheetTable.setBorder(new javax.swing.border.MatteBorder(null));
        outputSheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NAME", "QUANTITY", "PRICE", "STATUS", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outputSheetTable.getTableHeader().setReorderingAllowed(false);
        outputSheetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outputSheetTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(outputSheetTable);

        saveAndExit.setBackground(new java.awt.Color(204, 153, 255));
        saveAndExit.setText("SAVE & EXIT");
        saveAndExit.setActionCommand("");
        saveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndExitActionPerformed(evt);
            }
        });

        jLabel9.setText("STATUS");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(saveAndExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
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

    private void saveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndExitActionPerformed
        int len = tm.getRowCount();
        if(len == 0){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No Item In The List!" , 1);
            if(dialogResult == JOptionPane.YES_OPTION){
                try{
                    conn.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems at the time of closing connection!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                }
                
                Menu m = new Menu();
                this.setVisible(false);
                m.setVisible(true);
            }
            return;
        }
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to insert all these items?" , "Warning" , 1);        
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        
        for(int i=0; i<len; i++){
            String item = tm.getValueAt(i, 0).toString();
            String quant = tm.getValueAt(i, 1).toString();
            String pric = tm.getValueAt(i, 2).toString();
            String stat = tm.getValueAt(i, 3).toString();
            String DATE = tm.getValueAt(i, 4).toString();
            Double Quantity=0.0,Price=0.0;
            String serial = serialNo + "";
            serialNo++;
            
            try{
                Quantity = Double.parseDouble(quant);
                Price = Double.parseDouble(pric);
                SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy");
                Date date = dt.parse(DATE); 
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
                DATE = formatter.format(date);
            }catch(Exception e){}
            
            try{
                DecimalFormat df = new DecimalFormat("####0.000");
                psmt = conn.prepareStatement("INSERT INTO `storeoutput`(`itemname`, `outdate`, `state`, `amount`, `inputstate`, `avgprice`) VALUES(?,?,?,?,?,?)");
                psmt.setString(1, item);
                psmt.setString(2, DATE);
                psmt.setString(3, stat);
                psmt.setDouble(4, Quantity);
                psmt.setString(5, serial + "+" + Price);
                psmt.setDouble(6, Double.valueOf(df.format(Price/Quantity)));
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!\nItems can't be inserted!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        try{
            psmt = conn.prepareStatement("UPDATE sequence SET non_stored_seq = ?");
            psmt.setInt(1, serialNo);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Items are inserted successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems at the time of closing the connection!!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        Menu m = new Menu();
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_saveAndExitActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        ok.doClick();
    }//GEN-LAST:event_priceActionPerformed

    private void itemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        itemName.requestFocusInWindow();
        String item = itemName.getText().toString().trim();
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
        String quant = quantity.getText().trim();
        String pri = price.getText().trim();
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("MMM d,yyyy");
        String DATE = formatter.format(date);
        String stat = status.getSelectedItem().toString().trim();
        
        /*int result = databaseChecking(item, DATE, stat);
        if(result == -1)    return;
        if(result > 0){
            JOptionPane.showMessageDialog(null, item + " for " + stat + " is previously allocated for " + DATE, "Previously Inserted!", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        
        int result = tableChecking(item, DATE, stat, -1);
        if(result!=0){
            JOptionPane.showMessageDialog(null, "You have inserted " + item + " for " + stat + " for " + DATE + "\nPlease update table if necessary", "Previously Inserted!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Double Quantity = 0.0, Price =0.0;
        try{
            Quantity = Double.parseDouble(quant);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Quantity must be a number!", "Invalid Quantity!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            Price = Double.parseDouble(pri);
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
        
        Object o[] = {item, Quantity + "", Price+"", stat, DATE};
        tm.addRow(o);
        selectedRow = -1;
        outputSheetTable.getSelectionModel().clearSelection();
        
        itemName.setText("");
        //status.setSelectedIndex(0);
        quantity.setText("");
        price.setText("");
        
        itemNameUpdate.setText("");
        statusUpdate.setSelectedIndex(0);
        quantityUpdate.setText("");
        priceUpdate.setText("");
    }//GEN-LAST:event_okActionPerformed

    private void outputSheetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outputSheetTableMouseClicked
        selectedRow = outputSheetTable.getSelectedRow();
        String titem = tm.getValueAt(selectedRow, 0).toString().trim();
        String tquant = tm.getValueAt(selectedRow, 1).toString().trim();
        String tpric = tm.getValueAt(selectedRow, 2).toString().trim();
        String tstat = tm.getValueAt(selectedRow, 3).toString().trim();
        String DATE = tm.getValueAt(selectedRow, 4).toString().trim();
        
        Date date = null;
        SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
        try{
            date = dt.parse(DATE); 
        }catch(Exception e){}
        
        itemNameUpdate.setText(titem);
        for(int i=0; i<3; i++){
            if(tstat.equals(statusUpdate.getItemAt(i))){
                statusUpdate.setSelectedIndex(i);
                break;
            }
        }
        
        quantityUpdate.setText(tquant);
        priceUpdate.setText(tpric);
        dateChooserUpdate.setDate(date);
    }//GEN-LAST:event_outputSheetTableMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        itemNameUpdate.requestFocusInWindow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No item selected!", "Invalid Update!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Date date = dateChooserUpdate.getDate();
        String item = itemNameUpdate.getText().toString().trim();
        if(item.equals("")){
            JOptionPane.showMessageDialog(null, "You must enter an item name!", "Invalid Item Name!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!letterChecker(item)){
            JOptionPane.showMessageDialog(null, "First name of item name will contain only letter!", "Invalid Item Name!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        item = item.toLowerCase();
        item = upperCaseMaker(item);
        String quant = quantityUpdate.getText().toString().trim();
        String pric = priceUpdate.getText().toString().trim();
        String stat = statusUpdate.getSelectedItem().toString().trim();
        Format formatter = new SimpleDateFormat("MMM d,yyyy");
        String DATE = formatter.format(date);
        
        /*int result = databaseChecking(item, DATE, stat);
        if(result == -1)    return;
        if(result > 0){
            JOptionPane.showMessageDialog(null, item + " for " + stat + " is previously allocated for " + DATE, "Previously Inserted!", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        
        int result = tableChecking(item, DATE, stat, selectedRow);
        if(result!=0){
            JOptionPane.showMessageDialog(null, "You have inserted " + item + " for " + stat + " for " + DATE + "\nPlease update table if necessary", "Previously Inserted!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Double Quantity = 0.0, Price =0.0;
        try{
            Quantity = Double.parseDouble(quant);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Quantity must be a number!", "Invalid Quantity!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            Price = Double.parseDouble(pric);
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
        
        tm.setValueAt(item, selectedRow, 0);
        tm.setValueAt(Quantity + "",selectedRow,1);
        tm.setValueAt(Price + "", selectedRow, 2);
        tm.setValueAt(stat, selectedRow, 3);
        tm.setValueAt(DATE + "", selectedRow, 4);
        selectedRow = -1;
        outputSheetTable.getSelectionModel().clearSelection();
        
        itemNameUpdate.setText("");
        statusUpdate.setSelectedIndex(0);
        quantityUpdate.setText("");
        priceUpdate.setText("");
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No row selected to delete!", "No Row Selected!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        tm.removeRow(selectedRow);
        selectedRow = -1;
        outputSheetTable.getSelectionModel().clearSelection();
        
        itemNameUpdate.setText("");
        statusUpdate.setSelectedIndex(0);
        quantityUpdate.setText("");
        priceUpdate.setText("");
        
    }//GEN-LAST:event_deleteActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NonStoredItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooserUpdate;
    private javax.swing.JButton delete;
    private javax.swing.JTextField itemName;
    private javax.swing.JTextField itemNameUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok;
    private javax.swing.JTable outputSheetTable;
    private javax.swing.JTextField price;
    private javax.swing.JTextField priceUpdate;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField quantityUpdate;
    private javax.swing.JButton saveAndExit;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JComboBox<String> statusUpdate;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
