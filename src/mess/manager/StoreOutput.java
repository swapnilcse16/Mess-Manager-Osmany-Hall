package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;




public class StoreOutput extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    
    ArrayList <String> items = new ArrayList<String>();
    ArrayList <Double> itemAmount = new ArrayList<>();
    ArrayList <Double> expenditure = new ArrayList<>();
    ArrayList <Double> avgPrice = new ArrayList<>();
    int selectedRow;
    Double lastPrice;
    Date date = new Date();
    DecimalFormat df = new DecimalFormat("####0.000");
    
    
    
    
    public StoreOutput() {
        conn = JConnection.ConnecrDb();
        selectedRow = -1;
        this.setTitle("STORE OUTPUT");
        
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
        
        tm = (DefaultTableModel) outputSheetTable.getModel();
        tm.setRowCount(0);
        itemNameUpdateEdt.setEditable(false);
        dateChooser.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        editor = (JTextFieldDateEditor) dateChooserUpdate.getDateEditor();
        editor.setEditable(false);
        this.setResizable(false);
        availableEdt.setEditable(false);
        inputEdt.requestFocusInWindow();
        
        try{
            psmt = conn.prepareStatement("SELECT itemname, available, AVGprice FROM store");
            rs = psmt.executeQuery();
            while(rs.next()){
                String iName = rs.getString(1);
                double iAmount = rs.getDouble(2);
                double aPrice = rs.getDouble(3);
                items.add(iName);
                itemAmount.add(iAmount);
                expenditure.add(0.0);
                itemNameCmb.addItem(iName);
                avgPrice.add(aPrice);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! Items are not available at this moment!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        okBtn = new javax.swing.JButton();
        inputEdt = new javax.swing.JTextField();
        availableEdt = new javax.swing.JTextField();
        itemNameCmb = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        statusUpdateCmb = new javax.swing.JComboBox<>();
        amountUpdate = new javax.swing.JTextField();
        btnupdate = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        itemNameUpdateEdt = new javax.swing.JTextField();
        alertLbl = new javax.swing.JLabel();
        dateChooserUpdate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputSheetTable = new javax.swing.JTable();
        btnsave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        statusCmb = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("ITEM NAME");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("GIVE INPUT(kg/pieces)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("AVAILABLE");

        okBtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        inputEdt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        inputEdt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inputEdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEdtActionPerformed(evt);
            }
        });

        availableEdt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        availableEdt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        itemNameCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        itemNameCmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemNameCmbMouseClicked(evt);
            }
        });
        itemNameCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameCmbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(okBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(itemNameCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputEdt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(availableEdt))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputEdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(availableEdt)
                    .addComponent(itemNameCmb, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("STATUS");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("ITEM NAME");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("AMOUNT");

        statusUpdateCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        statusUpdateCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner" }));

        amountUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        amountUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        amountUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountUpdateActionPerformed(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnupdate.setText("UPDATE");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        itemNameUpdateEdt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        alertLbl.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        alertLbl.setForeground(new java.awt.Color(204, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("OUT FOR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateChooserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(alertLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusUpdateCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemNameUpdateEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusUpdateCmb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemNameUpdateEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(alertLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("OUT FOR");

        outputSheetTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        outputSheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NAME", "OUTED AMOUNT", "STATUS", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
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
        outputSheetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outputSheetTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(outputSheetTable);

        btnsave.setBackground(new java.awt.Color(0, 0, 255));
        btnsave.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnsave.setText("SAVE & EXIT");
        btnsave.setActionCommand("");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("STATUS");

        statusCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        statusCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(statusCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    
    

    double avgPriceCalculator(String item){
        double avg = 0;
        int len = items.size();
        for(int i=0; i<len; i++){
            if(item.equals(items.get(i))){
                avg = avgPrice.get(i);
                break;
            }
        }
        return avg;
    }
    
    
    
    
    
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        int len = tm.getRowCount();
        if(len==0){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No Item-out Occured!" , 1);
        
            if(dialogResult != JOptionPane.YES_OPTION)  return;
            else{
                
                try{
                    conn.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                }
                
                setVisible(false);
                Menu m5=new Menu();
                m5.setVisible(true);
                return;
            }
        }
        
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to store-out out all these items?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        
        ArrayList <TableItems> tableItems = new ArrayList<>();
        int flag = 0;
        
        for(int tableIndex = 0; tableIndex<len; tableIndex++){
            flag = 0;
            String item = tm.getValueAt(tableIndex, 0).toString();
            String amount = tm.getValueAt(tableIndex,1).toString();
            Double Amount = 0.0;
            try{
               Amount = Double.parseDouble(amount);
            }catch(Exception e){}
            String status = tm.getValueAt(tableIndex, 2).toString();
            String DATE = tm.getValueAt(tableIndex,3).toString();
            
            int listLength = tableItems.size();
            
            for(int i=0; i<listLength; i++){
                TableItems temp = tableItems.get(i);
                if(item.equals(temp.item) && status.equals(temp.status) && DATE.equals(temp.DATE)){
                    flag = 1;
                    temp.amount = Double.valueOf(df.format(temp.amount + Amount));
                    tableItems.set(i, temp);
                    break;
                }
            }
            
            if(flag == 0){
                TableItems t = new TableItems(item,status,DATE,Double.valueOf(df.format(Amount)),avgPriceCalculator(item));
                tableItems.add(t);
            }
        }
        
        len = tableItems.size();
        for(int i=0; i<len; i++){
            TableItems t = tableItems.get(i);
            String DATE = t.DATE;
            SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); 
            
            try{
                Date date = dt.parse(DATE); 
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                DATE = formatter.format(date);
                
                psmt = conn.prepareStatement("INSERT INTO `storeoutput`(`itemname`, `outdate`, `state`, `amount`, `inputstate`, `avgprice`) VALUES(?,?,?,?,?,?)");
                
                psmt.setString(1, t.item);
                psmt.setString(2, DATE);
                psmt.setString(3,t.status);
                psmt.setDouble(4,t.amount);
                psmt.setString(5,"Stored Item");
                psmt.setDouble(6,t.avgPrice);
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! The items can't be stored out!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        
        len = items.size();
        for(int i=0; i<len; i++){
            Double e = expenditure.get(i);
            String item = items.get(i);
            try{
                psmt = conn.prepareStatement("SELECT available FROM store WHERE itemname = ?");
                psmt.setString(1, item);
                rs = psmt.executeQuery();
                rs.next();
                Double Available = rs.getDouble(1);
                //DecimalFormat df = new DecimalFormat("####0.000");
                Available -= e;
                Available = Double.valueOf(df.format(Available));
                psmt.close();
                rs.close();
                psmt = conn.prepareStatement("UPDATE `store` SET `available`=? WHERE itemname = ?");
                psmt.setDouble(1, Available);
                psmt.setString(2, item);
                psmt.execute();
                psmt.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Items are stored-out successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        setVisible(false);
        Menu m3=new Menu();
        m3.setVisible(true);
        
    }//GEN-LAST:event_btnsaveActionPerformed

    
    
    int rowCount(ResultSet rs){
        int count = 0;
        try{
            while(rs.next()){
                count++;
            }
        }catch(Exception e){
            
        }
        return count;
    }
    
    
    
    
    int checking(String item, String status, String DATE){
        int checking = 0;
        try{
                psmt = conn.prepareStatement("SELECT * FROM storeoutput where state = ? AND itemname = ? AND outdate = ?");
                psmt.setString(1, status);
                psmt.setString(2,item);
                psmt.setString(3,DATE);
                rs = psmt.executeQuery();
                checking = rowCount(rs);
                psmt.close();
                rs.close();
                return checking;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems at the time of checking!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                
            }
        return checking;
    }
    
    
    
    private void itemNameCmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemNameCmbMouseClicked
        
    }//GEN-LAST:event_itemNameCmbMouseClicked

    
    
    
    private void itemNameCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameCmbActionPerformed
        int index = itemNameCmb.getSelectedIndex();
        String item = itemNameCmb.getItemAt(index);
        Double newAvailable = Double.valueOf(df.format(itemAmount.get(index) - expenditure.get(index)));
        availableEdt.setText(newAvailable + "");
        ///System.out.println((itemAmount.get(index)*1.0 - expenditure.get(index)*1.0) + "");/////////////////////////
    }//GEN-LAST:event_itemNameCmbActionPerformed

    
    
    private int tableChecking(String item, String status, String givenDate, int selected){
        int flag = 0;
        int len = tm.getRowCount();
        
        for(int i=0; i<len; i++){
            if(i==selected) continue;
            String tItem = tm.getValueAt(i,0).toString();
            String tStatus = tm.getValueAt(i, 2).toString();
            String tDate = tm.getValueAt(i, 3).toString();
            
            SimpleDateFormat dt = new SimpleDateFormat("MMM d,yyyy"); ////////String ta je format e ache,sei format ta hobe
            try{
                Date date = dt.parse(tDate); 
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");   ////////je format e convert korte chai
                String DATE = formatter.format(date);
                
                if(DATE.equals(givenDate) && item.equals(tItem) && status.equals(tStatus)){
                    flag = 1;
                    //JOptionPane.showMessageDialog(null, "You have listed " + item + " before for " + tDate + "\nPlease update if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }catch(Exception e){}
        }
        
        return flag;
    }
    
    
    
    
    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        int lenCmb = itemNameCmb.getItemCount();
        if(lenCmb == 0){
            JOptionPane.showMessageDialog(null, "First Create a item from 'STORE INPUT'!", "No Item In The List!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        int index = itemNameCmb.getSelectedIndex();
        String item = itemNameCmb.getItemAt(index);
        String input = inputEdt.getText().toString().trim();
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        
        
        
        int tableCheck = tableChecking(item,statusCmb.getSelectedItem().toString(),DATE,-1);
        if(tableCheck == 1){
            JOptionPane.showMessageDialog(null, "You have listed " + item + " before for " + statusCmb.getSelectedItem().toString() + " for " +formatter2.format(date) + "\nPlease update if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*int checkResult = checking(item, statusCmb.getSelectedItem().toString(), DATE);
        if(checkResult!=0){
            JOptionPane.showMessageDialog(null, item + " for " + statusCmb.getSelectedItem().toString() + " is inserted previously for " + formatter2.format(date), "Can't Insert!", JOptionPane.ERROR_MESSAGE); 
            return;
        }*/
        
        try{
            Double Input = Double.parseDouble(input);
            Input = Double.valueOf(df.format(Input));
            Double available = Double.valueOf(itemAmount.get(index) - expenditure.get(index));
            
            if(Input<=0.0){
                JOptionPane.showMessageDialog(null, "Invalid input of " + item, "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(Input > available){
                JOptionPane.showMessageDialog(null, "You don't have enough storage of " + item, "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            Object o [] = {item, Input + "", statusCmb.getSelectedItem().toString(),formatter2.format(date)};
            tm.addRow(o);
            Double newExpenditure = Double.valueOf(df.format(expenditure.get(index) + Input));
            expenditure.set(index, newExpenditure);
            ///System.out.println("Input: " + Input + "Expenditure: " + expenditure.get(index) + "Store: " + itemAmount.get(index));/////////////////
            itemNameCmb.setSelectedIndex(index);
            
            /////////TABLE HANDLING\\\\\\\\\\\
            outputSheetTable.getSelectionModel().clearSelection();
            selectedRow = -1;
            statusUpdateCmb.setSelectedIndex(0);
            itemNameUpdateEdt.setText("");
            amountUpdate.setText("");
            alertLbl.setText("");
            inputEdt.setText("");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid input of " + item, "Alert", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_okBtnActionPerformed

    
    
    
    
    
    
    
    
    private void outputSheetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outputSheetTableMouseClicked
        selectedRow = outputSheetTable.getSelectedRow();
        String item = tm.getValueAt(selectedRow, 0).toString().trim();
        String amount = tm.getValueAt(selectedRow, 1).toString().trim();
        String status = tm.getValueAt(selectedRow, 2).toString().trim();
        String DATE = tm.getValueAt(selectedRow,3).toString().trim();
        Date date1 = null;
        try { 
            date1 = new SimpleDateFormat("MMM d,yyyy").parse(DATE);
        } catch (ParseException ex) {
            Logger.getLogger(StoreOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int statusIndex = -1;
        int index = -1;
        int len = items.size();
        
        for(int i=0; i<len; i++){
            if(item.equals(items.get(i))){
                index = i;
                break;
            }
        }
        
        if(status.charAt(0)=='B')   statusIndex = 0;
        else if(status.charAt(0)=='L')  statusIndex=1;
        else if(status.charAt(0) == 'D')    statusIndex=2;
        
        itemNameUpdateEdt.setText(item);
        statusUpdateCmb.setSelectedIndex(statusIndex);
        amountUpdate.setText(amount);
        lastPrice = Double.parseDouble(amount);
        
        Double tempAvailable = Double.valueOf(df.format(itemAmount.get(index) - expenditure.get(index) + lastPrice));
        
        alertLbl.setText("MAXIMUM AMOUNT WILL BE: " + tempAvailable + "");
        dateChooserUpdate.setDate(date1);
        //maxAmountEdt.setText((itemAmount.get(index) - expenditure.get(index) + lastPrice) + "");
    }//GEN-LAST:event_outputSheetTableMouseClicked

    
    
    
    
    
    
    
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No item selected!", "Alert", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        int lenCmb = itemNameCmb.getItemCount();
        if(lenCmb == 0){
            JOptionPane.showMessageDialog(null, "First Create a item from 'STORE INPUT'!", "No Item In The List!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String status = statusUpdateCmb.getSelectedItem().toString();
        String amount = amountUpdate.getText().toString().trim();
        String item = itemNameUpdateEdt.getText().toString();
        date = dateChooserUpdate.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        Format formatter2 = new SimpleDateFormat("MMM d,yyyy");
        
        
        
        int tableCheck = tableChecking(item, status, DATE,selectedRow);
        if(tableCheck ==1){
            JOptionPane.showMessageDialog(null, "You have listed " + item + " before for " + formatter2.format(date) + " for " +status+ " in another row.\nPlease update that row if required!", "Invalid Insertion!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*int checkResult = checking(item, status, DATE);
        if(checkResult!=0){
            JOptionPane.showMessageDialog(null, item + " for " + status + " is inserted previously for " + formatter2.format(date), "Can't Update!", JOptionPane.ERROR_MESSAGE); 
            return;
        }*/
        
        int index = -1;
        int len = items.size();
        for(int i=0; i<len; i++){
            if(item.equals(items.get(i))){
                index = i;
                break;
            }
        }
        
        
        Double available = Double.valueOf(df.format(itemAmount.get(index) - expenditure.get(index) + lastPrice)); 
        
        try{
            double Amount = Double.parseDouble(amount);
            if(Amount <= 0.0){
                JOptionPane.showMessageDialog(null, "Invalid amount of " + item, "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(Amount > available){
                JOptionPane.showMessageDialog(null, "Your maximum input will be  " + available, "Insufficient storage of " + item + "!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Double newExpenditure = Double.valueOf(df.format(expenditure.get(index) - lastPrice + Amount));
            expenditure.set(index, newExpenditure);
            int selectedIndex = itemNameCmb.getSelectedIndex();
            if(selectedIndex == index)  itemNameCmb.setSelectedIndex(index);
            
            tm.setValueAt(item, selectedRow, 0);
            tm.setValueAt(Amount + "", selectedRow, 1);
            tm.setValueAt(status, selectedRow, 2);
            tm.setValueAt(formatter2.format(date), selectedRow, 3);
            lastPrice = Amount;
            outputSheetTable.getSelectionModel().clearSelection();
            selectedRow = -1;
            statusUpdateCmb.setSelectedIndex(0);
            itemNameUpdateEdt.setText("");
            amountUpdate.setText("");
            alertLbl.setText("");
            //maxAmountEdt.setText((itemAmount.get(index) - expenditure.get(index) + lastPrice)+ "");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid amount of " + item, "Alert", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    
    
    
    
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "No item selected!", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String item = itemNameUpdateEdt.getText().toString().trim();
        String amount = tm.getValueAt(selectedRow, 1).toString();
        double Amount = Double.parseDouble(amount);
        int len = items.size();
        int index = -1;
        int mainIndex = itemNameCmb.getSelectedIndex();
        
        for(int i=0; i<len; i++){
            if(item.equals(items.get(i))){
                index = i;
                break;
            }
        }
        
        Double newExpenditure = Double.valueOf(df.format(expenditure.get(index) - Amount));
        expenditure.set(index, newExpenditure);
        tm.removeRow(selectedRow);
        selectedRow = -1;
        if(mainIndex == index)  itemNameCmb.setSelectedIndex(mainIndex);
        itemNameUpdateEdt.setText("");
        amountUpdate.setText("");
        statusUpdateCmb.setSelectedIndex(0);
        alertLbl.setText("");
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void inputEdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEdtActionPerformed
        okBtn.doClick();
        inputEdt.requestFocusInWindow();
    }//GEN-LAST:event_inputEdtActionPerformed

    private void amountUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountUpdateActionPerformed
        btnupdate.doClick();
        amountUpdate.requestFocusInWindow();
    }//GEN-LAST:event_amountUpdateActionPerformed

    
    
    
    
    
    
    
    
    
    void showAvailable(String item){
        try{
            psmt = conn.prepareStatement("SELECT available FROM store where itemname = ?");
            psmt.setString(1,item);
            rs = psmt.executeQuery();
            rs.next();
            availableEdt.setText(rs.getDouble(1) + "");
            psmt.close();
            rs.close();
            //System.out.println(itemNameCmb.getItemCount() + "");
        }catch(Exception e){
            //System.out.println("Problem");
        }
    }
    
    
    
    
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StoreOutput().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertLbl;
    private javax.swing.JTextField amountUpdate;
    private javax.swing.JTextField availableEdt;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooserUpdate;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField inputEdt;
    private javax.swing.JComboBox<String> itemNameCmb;
    private javax.swing.JTextField itemNameUpdateEdt;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JButton okBtn;
    private javax.swing.JTable outputSheetTable;
    private javax.swing.JComboBox<String> statusCmb;
    private javax.swing.JComboBox<String> statusUpdateCmb;
    // End of variables declaration//GEN-END:variables
}












class TableItems{
    public String item, status, DATE;
    public double amount,avgPrice;
    
    public TableItems(){
        
    }
    
    public TableItems(String item, String status,String DATE, double amount){
        this.item = item;
        this.status = status;
        this.DATE = DATE;
        this.amount = amount;
        this.avgPrice = 0;
    }
    
    public TableItems(String item, String status,String DATE, double amount, double avgPrice){
        this.item = item;
        this.status = status;
        this.DATE = DATE;
        this.amount = amount;
        this.avgPrice = avgPrice;
    }
}
