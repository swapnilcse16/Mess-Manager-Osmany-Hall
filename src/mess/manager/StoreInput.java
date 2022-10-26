package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
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

public class StoreInput extends javax.swing.JFrame {
    
    
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    int selectedRow;
    DecimalFormat df = new DecimalFormat("####0.000");
    
    
    boolean letterChecker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        
        //for(int i=0; i<len; i++){
            int ascii = (int)c[0];
            if(!((ascii>=65 && ascii<=90) || (ascii>=97 && ascii<=122)))    return false;
        //}
        
        return true;
    }
    

    
        
    
    
    
    
    public StoreInput() {
        
        conn = JConnection.ConnecrDb();
        selectedRow = -1;
        this.setTitle("STORE INPUT");
        
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
        
        tm = (DefaultTableModel)inputSheetTable.getModel();
        tm.setRowCount(0);
        Date date = new Date();
        dateChooser.setDate(date);
        dateChooserUpdate.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        editor = (JTextFieldDateEditor) dateChooserUpdate.getDateEditor();
        editor.setEditable(false);
        this.setResizable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        inputSheetTable.setDefaultRenderer(Object.class, centerRenderer);
        
        try{
            psmt = conn.prepareStatement("SELECT itemname FROM store");
            rs = psmt.executeQuery();
             
            while(rs.next()){
                String item = rs.getString(1);
                cmbitemname.addItem(item);
                itemUpdate.addItem(item);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No item found!", "An Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        edtquantity.requestFocusInWindow();
        
        
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbitemname = new javax.swing.JComboBox<>();
        btncreateitem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        edtquantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edtprice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnok = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        priceUpdate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        quantityUpdate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        itemUpdate = new javax.swing.JComboBox<>();
        dateChooserUpdate = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputSheetTable = new javax.swing.JTable();
        save = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("STORE INPUT");

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("DATE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("ITEM NAME");

        cmbitemname.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cmbitemname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbitemnameMouseClicked(evt);
            }
        });
        cmbitemname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbitemnameActionPerformed(evt);
            }
        });

        btncreateitem.setText("CREATE");
        btncreateitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateitemActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("QUANTITY");

        edtquantity.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        edtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtquantityActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("KG");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("PRICE");

        edtprice.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        edtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtpriceActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("TAKA");

        btnok.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(cmbitemname, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtquantity)
                            .addComponent(edtprice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(btncreateitem)
                            .addComponent(jLabel4)))
                    .addComponent(btnok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbitemname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncreateitem))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtprice)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnok)
                .addGap(23, 23, 23))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("NAME");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("QUANTITY");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("PRICE");

        priceUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        priceUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceUpdateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("TAKA");

        btnupdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnupdate.setText("UPDATE");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        quantityUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        quantityUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityUpdateActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText("KG");

        itemUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        itemUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUpdateActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("DATE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quantityUpdate)
                            .addComponent(itemUpdate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(priceUpdate)
                            .addComponent(dateChooserUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))))
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        inputSheetTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        inputSheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ITEM NAME", "QUANTITY", "PRICE", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inputSheetTable.setToolTipText("");
        inputSheetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputSheetTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inputSheetTable);

        save.setBackground(new java.awt.Color(0, 153, 204));
        save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save.setText("SAVE  &  EXIT");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("INPUT DETAILS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    
    
    
    
    
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        inputSheetTable.getSelectionModel().clearSelection();
        selectedRow = -1;
        
        int len = tm.getRowCount();
        
        if(len == 0){
            //JOptionPane.showMessageDialog(null, "First enter an item!", "No Item Inserted", JOptionPane.ERROR_MESSAGE); 
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No Item Inserted!" , 1);
        
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
        
        
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to insert all these items?" , "Alert!" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        ArrayList <InputStore> inputStore = new ArrayList<>();
        int flag = 0;
        
        for(int i=0; i<len; i++){
            flag = 0;
            String item = tm.getValueAt(i, 0).toString().trim();
            String quantity = tm.getValueAt(i, 1).toString().trim();
            String price = tm.getValueAt(i, 2).toString().trim();
            String DATE = tm.getValueAt(i, 3).toString().trim();
            Double Quantity = 0.0, Price = 0.0;
            Double Avai = 0.0, AvgP = 0.0;
            
            SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
            try{
                Date date = dt.parse(DATE); 
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
                DATE = formatter.format(date);
                Quantity = Double.parseDouble(quantity);
                Price = Double.parseDouble(price);
                
                psmt = conn.prepareStatement("SELECT available,AVGprice FROM store WHERE itemname = ?");
                psmt.setString(1, item);
                rs = psmt.executeQuery();
                
                if(rs.next()){
                    Double Available = rs.getDouble(1);
                    Double AVGprice = rs.getDouble(2);
                    //System.out.println("Available: " + Available + " AVGprice: " + AVGprice);
                    Double x  = Available * AVGprice + Price;
                    Available += Quantity;
                    Available = Double.valueOf(df.format(Available));
                    AVGprice = x/Available;
                    AVGprice = Double.valueOf(df.format(AVGprice));
                    
                    psmt = conn.prepareStatement("UPDATE `store` SET `available`=?,`AVGprice`=? WHERE itemname = ?");
                    psmt.setDouble(1, Available);
                    psmt.setDouble(2, AVGprice);
                    psmt.setString(3,item);
                    psmt.execute();
                    Avai = Available;
                    AvgP = AVGprice;
                }
                psmt.close();
                rs.close();
                
            }catch(Exception e){JOptionPane.showMessageDialog(null, "Oops! Item-amount can't be updated!", "An Unknown Error Occured!", JOptionPane.ERROR_MESSAGE); return; }
            
            int listLength = inputStore.size();
            for(int l=0; l<listLength; l++){
                InputStore temp = inputStore.get(l);
                if(item.equals(temp.item) && DATE.equals(temp.DATE)){
                    inputStore.get(l).update(Quantity, Price);
                    flag = 1;
                    break;
                }
            }
            
            if(flag == 0){
                InputStore iStore = new InputStore(item, DATE, Quantity, Price, Avai, AvgP);
                inputStore.add(iStore);
            }
        }//////////////////for loop endsssss
       
        
        
        
        
        int listLength = inputStore.size();
        for(int i=0; i<listLength; i++){
            InputStore temp = inputStore.get(i);

            try{
                psmt = conn.prepareStatement("INSERT INTO `storeinput`(`itemname`, `curdate`, `amount`, `price`,available,avgprice) VALUES (?,?,?,?,?,?)");
                psmt.setString(1, temp.item);
                psmt.setString(2,temp.DATE);
                psmt.setDouble(3, temp.Quantity);
                psmt.setDouble(4, temp.Price);
                psmt.setDouble(5, temp.Available);
                psmt.setDouble(6, temp.AvgPrice);
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! Item-amount can't be inserted!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
        }
        
        JOptionPane.showMessageDialog(null, "Items have been stored correctly!", "Successful!", JOptionPane.INFORMATION_MESSAGE); 
        
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
        Menu m5=new Menu();
        m5.setVisible(true);
    }//GEN-LAST:event_saveActionPerformed

    
    
    
    
    String upperCaseMaker(String s){
        int len = s.length();
        char[] c = s.toCharArray();
        int temp = (int)c[0] - 32;
        c[0] = (char)temp;
        
        return new String(c);
    }
    
    
    
    
    
    
    
    
    
    private void btncreateitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateitemActionPerformed
        String itemName = null;
        itemName = JOptionPane.showInputDialog("Item Name");
        
        if(itemName == null){
            JOptionPane.showMessageDialog(null, "No Item Entered!", "Alert!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        itemName = itemName.trim();
        if(itemName.equals("")){
            JOptionPane.showMessageDialog(null, "No Item Entered!", "Alert!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(!letterChecker(itemName)){
            JOptionPane.showMessageDialog(null, "Item name will contain only letter!", "Invalid Item Name!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        itemName = itemName.toLowerCase();
        String upperCaseItemName = upperCaseMaker(itemName);
        
        int len = cmbitemname.getItemCount();
        
        for(int i =0; i<len; i++){
            String comboItem = cmbitemname.getItemAt(i);
            comboItem = comboItem.toLowerCase();
            if(itemName.equals(comboItem)){
                JOptionPane.showMessageDialog(null, upperCaseItemName + " is entered before!", "Alert!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try{
            psmt = conn.prepareStatement("INSERT INTO `store`(`itemname`, `available`,AVGprice) VALUES (?,?,?)");
            psmt.setString(1,upperCaseItemName);
            psmt.setDouble(2, 0.0);
            psmt.setDouble(3, 0.0);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! The item can't be created!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Item is created successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        
        
        cmbitemname.addItem(upperCaseItemName);
        itemUpdate.addItem(upperCaseItemName);
    }//GEN-LAST:event_btncreateitemActionPerformed

    
    
    
    
    private int tableChecking(String item, String giVenDate, int selected){
        int flag = 0;
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            if(i==selected)  continue;
            String tItem = tm.getValueAt(i, 0).toString();
            String tDate = tm.getValueAt(i, 3).toString();
            
            SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
            try{
                Date date = dt.parse(tDate); 
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
                String DATE = formatter.format(date);
                
                if(DATE.equals(giVenDate) && item.equals(tItem)){
                    flag = 1;
                    //JOptionPane.showMessageDialog(null, "You have listed " + item + " before for " + tDate + "\nPlease update if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }catch(Exception e){}
        }
        
        return flag;
    }
    
    
    
    
    
    
    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        int len = cmbitemname.getItemCount();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "Insert an item first!", "No Item!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        String item = cmbitemname.getSelectedItem().toString();
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        String quantity = edtquantity.getText().toString().trim();
        String price = edtprice.getText().toString().trim();
        String DATE = formatter.format(date);
        Double Price = 0.0, Quantity = 0.0;
        int count = 0;
        
        ///////////////Present in table or not
        int check = tableChecking(item, DATE, -1);
        if(check == 1){
            JOptionPane.showMessageDialog(null, "You have listed " + item + " before for " + formatter2.format(date) + "\nPlease update if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        /*try {
            psmt = conn.prepareStatement("SELECT amount FROM storeinput WHERE curdate = ? AND itemname = ?");
            psmt.setString(1,DATE);
            psmt.setString(2,item);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                count++;
                break;
            }
            if(count != 0){
                JOptionPane.showMessageDialog(null, item + " is inserted previously for " + formatter2.format(date), "Can't Insert!", JOptionPane.ERROR_MESSAGE); 
                psmt.close();
                rs.close();
                return;
            }
            
            psmt.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Oops! There is some problem! ", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE); 
                return;
        }*/
        
        try{
            Quantity = Double.parseDouble(quantity);
            Quantity = Double.valueOf(df.format(Quantity));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You inserted an invalid quantity", "Invalid QUANTITY!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        try{
            Price = Double.parseDouble(price);
            Price = Double.valueOf(df.format(Price));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You inserted an invalid price", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(Quantity <= 0.0){
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0", "Invalid QUANTITY!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(Price <= 0.0){
            JOptionPane.showMessageDialog(null, "Price must be greater than 0", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        Object o[] = {item,Quantity+"",Price+"",formatter2.format(date)};
        tm.addRow(o);
        inputSheetTable.getSelectionModel().clearSelection();
        selectedRow = -1;
        
        edtquantity.setText("");
        edtprice.setText("");
    }//GEN-LAST:event_btnokActionPerformed

    
    
    
    
    
    
    
    
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No item selected!", "Alert", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        double Quantity = 0.0, Price = 0.0;
        String ItemName = itemUpdate.getSelectedItem().toString();
        String quantity = quantityUpdate.getText();
        String price = priceUpdate.getText();
        Date date = dateChooserUpdate.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        int count = 0;
        
        int check = tableChecking(ItemName, DATE, selectedRow);
        if(check == 1){
            JOptionPane.showMessageDialog(null, "You have listed " + ItemName + " before for " + formatter2.format(date) + " in another row.\nPlease update that row if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*try {
            psmt = conn.prepareStatement("SELECT amount FROM storeinput WHERE curdate = ? AND itemname = ?");
            psmt.setString(1,DATE);
            psmt.setString(2,ItemName);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                count++;
                break;
            }
            if(count != 0){
                JOptionPane.showMessageDialog(null, ItemName + " is inserted previously for " + formatter2.format(date), "Can't Insert!", JOptionPane.ERROR_MESSAGE); 
                psmt.close();
                rs.close();
                return;
            }
            
            psmt.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Some problem occured!", "Unwanted problem", JOptionPane.ERROR_MESSAGE); 
                return;
        }*/
        
        
        
        
        try{
            Quantity = Double.parseDouble(quantity);
            Quantity = Double.valueOf(df.format(Quantity));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You inserted an invalid quantity", "Invalid QUANTITY!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        try{
            Price = Double.parseDouble(price);
            Price = Double.valueOf(df.format(Price));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You inserted an invalid price", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(Quantity <=0 ){
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0", "Invalid QUANTITY!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(Price <=0){
            JOptionPane.showMessageDialog(null, "Price must be greater than 0", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        tm.setValueAt(ItemName, selectedRow, 0);
        tm.setValueAt(Quantity+"", selectedRow, 1);
        tm.setValueAt(Price+"", selectedRow, 2);
        tm.setValueAt(formatter2.format(date), selectedRow, 3);
        
        quantityUpdate.setText("");
        priceUpdate.setText("");
        
        inputSheetTable.getSelectionModel().clearSelection();
        selectedRow = -1;
    }//GEN-LAST:event_btnupdateActionPerformed

    
    
    
    
    private void inputSheetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputSheetTableMouseClicked
        selectedRow = inputSheetTable.getSelectedRow();
        String itemName = tm.getValueAt(selectedRow, 0).toString().trim();
        String quantity = tm.getValueAt(selectedRow, 1).toString().trim();
        String price = tm.getValueAt(selectedRow, 2).toString().trim();
        String DATE = tm.getValueAt(selectedRow, 3).toString().trim();
        
        int len = itemUpdate.getItemCount();

        for(int i=0; i<len; i++){
            if(itemName.equals(itemUpdate.getItemAt(i))){
                itemUpdate.setSelectedIndex(i);
                break;
            }
        }
        
        quantityUpdate.setText(quantity);
        priceUpdate.setText(price);
        
        SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATE); 
            dateChooserUpdate.setDate(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Problem!", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_inputSheetTableMouseClicked

    
    
    
    
    
    
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No item selected!", "Alert", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        tm.removeRow(selectedRow);
        selectedRow = -1;
        itemUpdate.setSelectedIndex(0);
        quantityUpdate.setText("");
        priceUpdate.setText("");
        inputSheetTable.getSelectionModel().clearSelection();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void edtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtpriceActionPerformed
        btnok.doClick();
        edtquantity.requestFocusInWindow();
    }//GEN-LAST:event_edtpriceActionPerformed

    private void priceUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceUpdateActionPerformed
        btnupdate.doClick();
        quantityUpdate.requestFocusInWindow();
    }//GEN-LAST:event_priceUpdateActionPerformed

    private void cmbitemnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbitemnameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbitemnameMouseClicked

    private void cmbitemnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbitemnameActionPerformed
        edtquantity.requestFocusInWindow();
    }//GEN-LAST:event_cmbitemnameActionPerformed

    private void itemUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUpdateActionPerformed
        quantityUpdate.requestFocusInWindow();
    }//GEN-LAST:event_itemUpdateActionPerformed

    private void quantityUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityUpdateActionPerformed
        btnupdate.doClick();
        quantityUpdate.requestFocusInWindow();
    }//GEN-LAST:event_quantityUpdateActionPerformed

    private void edtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtquantityActionPerformed
        btnok.doClick();
        edtquantity.requestFocusInWindow();
    }//GEN-LAST:event_edtquantityActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StoreInput().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncreateitem;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnok;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cmbitemname;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooserUpdate;
    private javax.swing.JTextField edtprice;
    private javax.swing.JTextField edtquantity;
    private javax.swing.JTable inputSheetTable;
    private javax.swing.JComboBox<String> itemUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField priceUpdate;
    private javax.swing.JTextField quantityUpdate;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}





class InputStore{
    String item , DATE;
    Double Quantity, Price;
    Double Available,AvgPrice;
    
    public InputStore(String i, String D, Double Q, Double P, Double Av, Double Ap){
        item = i;
        DATE = D;
        Quantity = Q;
        Price = P;
        Available = Av;
        AvgPrice = Ap;
    }
    
    public void update(Double Q, Double P){
        Quantity = Quantity + Q;
        Price = Price + P;
    }
}