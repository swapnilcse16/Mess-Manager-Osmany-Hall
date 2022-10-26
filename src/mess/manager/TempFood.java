package mess.manager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TempFood extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    int HALLID, PRESENT;
    String YEAR, MONTH;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    
    private void migrate(){
        try{
            psmt = conn.prepareStatement("SELECT special, hallid FROM messbill");
            rs = psmt.executeQuery();
            while(rs.next()){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `tempfood`(`hallid`, `month`, `year`, `bill`) VALUES(?,?,?,?)");
                ps.setInt(1, rs.getInt(2));
                ps.setString(2, "January");
                ps.setString(3, "2019");
                ps.setDouble(4, rs.getDouble(1));
                ps.execute();
                ps.close();
                
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            
        }
    }
    
    
    private void refresh(){
        monthLbl.setText("");
        hallIdLbl1.setText("");
        rollLbl1.setText("");
        nameLbl1.setText("");
        roomLbl1.setText("");
        HALLID = -1;
        YEAR = "";
        MONTH = "";
        //hallIdChk.setSelected(true);
        PRESENT = 0;
        //migrate();
    }
    
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int i=0; i<12; i++) monthCmb.addItem(months[i]);
        refresh();
        idTxt.requestFocusInWindow();
        this.setResizable(false);
        this.setTitle("TEMPORARY ITEMS");
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
    
    
    
    
    public TempFood() {
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
        jLabel1 = new javax.swing.JLabel();
        monthCmb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yearTxt = new javax.swing.JTextField();
        hallIdLbl = new javax.swing.JLabel();
        nameLbl = new javax.swing.JLabel();
        rollLbl = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        priceTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        monthLbl = new javax.swing.JLabel();
        roomLbl = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        quantityTxt = new javax.swing.JTextField();
        avgTxt = new javax.swing.JTextField();
        setBtn = new javax.swing.JButton();
        roomLbl1 = new javax.swing.JLabel();
        rollLbl1 = new javax.swing.JLabel();
        nameLbl1 = new javax.swing.JLabel();
        hallIdLbl1 = new javax.swing.JLabel();

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

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("MONTH");

        monthCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("YEAR");

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        yearTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTxtActionPerformed(evt);
            }
        });

        hallIdLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdLbl.setText("HALL ID:");

        nameLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameLbl.setText("NAME:");

        rollLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollLbl.setText("ROLL:");

        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        priceTxt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        priceTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTxtActionPerformed(evt);
            }
        });

        monthLbl.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        monthLbl.setText("MONTH: JANUARY");

        roomLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        roomLbl.setText("ROOM:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("PRICE:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("QUANTITY");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("AVG PRICE");

        quantityTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        quantityTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTxtActionPerformed(evt);
            }
        });

        avgTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        avgTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avgTxtActionPerformed(evt);
            }
        });

        setBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        setBtn.setText("SET");
        setBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBtnActionPerformed(evt);
            }
        });

        roomLbl1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        roomLbl1.setText("HALL ID:");

        rollLbl1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rollLbl1.setText("HALL ID:");

        nameLbl1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        nameLbl1.setText("HALL ID:");

        hallIdLbl1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        hallIdLbl1.setText("HALL ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(priceTxt))
                                .addComponent(saveBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(roomLbl)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rollLbl1)
                                    .addComponent(roomLbl1)
                                    .addComponent(nameLbl1))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(nameLbl, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rollLbl, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(hallIdChk)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rollChk)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(monthCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(yearTxt)))
                                    .addComponent(searchBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(hallIdLbl)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hallIdLbl1)))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(monthLbl)
                                    .addComponent(setBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(avgTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 29, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avgTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtn)
                    .addComponent(setBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hallIdLbl)
                    .addComponent(hallIdLbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(nameLbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rollLbl)
                    .addComponent(rollLbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomLbl)
                    .addComponent(roomLbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(saveBtn)
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
        String year = yearTxt.getText().trim();
        String month = monthCmb.getSelectedItem().toString().trim();
        String id = idTxt.getText().toString().trim();
        refresh();
        
        try{
            int y = Integer.parseInt(year);
            if(y < 2019){
                JOptionPane.showMessageDialog(null, "Year must be greater than 2018!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Year must be a number!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        String sql = "";
        boolean b = hallIdChk.isSelected();
        if(b)   sql = "SELECT hallid,roll,name,roomno FROM stuinfo WHERE hallid = ?";
        else    sql = "SELECT hallid,roll,name,roomno FROM stuinfo WHERE roll = ?";
        
        int h = -1;
        
        try{
            h = Integer.parseInt(id);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hall Id must be a number!", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String roll = "", name="", room="";
        try{
            psmt = conn.prepareStatement(sql);
            if(b)   psmt.setInt(1, h);
            else    psmt.setString(1, id);
            rs = psmt.executeQuery();
            while(rs.next()){
                HALLID = rs.getInt(1);
                roll = rs.getString(2);
                name = rs.getString(3);
                room = rs.getString(4);
                YEAR = year;
                MONTH = month;
                quantityTxt.setText("");
                quantityTxt.requestFocusInWindow();
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(HALLID == -1){
            JOptionPane.showMessageDialog(null, "The ID doesn't exist!", "ID Not Found!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        nameLbl1.setText(name);
        hallIdLbl1.setText("" + HALLID);
        rollLbl1.setText(roll);
        roomLbl1.setText(room);
        monthLbl.setText(MONTH + ", " + YEAR);
        
        String pr = "";
        try{
            psmt = conn.prepareStatement("SELECT bill FROM tempfood WHERE hallid = ? AND month = ? AND year = ?");
            psmt.setInt(1, HALLID);
            psmt.setString(2, MONTH);
            psmt.setString(3, YEAR);
            rs = psmt.executeQuery();
            while(rs.next()){
                pr = pr + rs.getDouble(1);
                PRESENT = 1;
                
            }
            psmt.close();
            rs.close();
            priceTxt.setText(pr);
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_idTxtActionPerformed

    private void yearTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_yearTxtActionPerformed

    private void quantityTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTxtActionPerformed
        setBtn.doClick();
    }//GEN-LAST:event_quantityTxtActionPerformed

    private void avgTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avgTxtActionPerformed
        setBtn.doClick();
    }//GEN-LAST:event_avgTxtActionPerformed

    private void priceTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTxtActionPerformed
        saveBtn.doClick();
    }//GEN-LAST:event_priceTxtActionPerformed

    private void setBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBtnActionPerformed
        if(HALLID == -1){
            JOptionPane.showMessageDialog(null, "First select a student!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String quantity = quantityTxt.getText().trim();
        String price = avgTxt.getText().trim();
        Double Quantity = 0.0, Price = 0.0;
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
        
        if(Quantity < 0.0){
            JOptionPane.showMessageDialog(null, "Quantity must be positive", "Invalid QUANTITY!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        if(Price < 0.0){
            JOptionPane.showMessageDialog(null, "Price must be positive", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        priceTxt.setText(""+Double.valueOf(df.format(Quantity*Price)));
        priceTxt.requestFocusInWindow();
    }//GEN-LAST:event_setBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try{
            psmt = conn.prepareStatement("SELECT * FROM messbill where month = ? AND year = ?");
            psmt.setString(1, MONTH);
            psmt.setString(2, YEAR);
            rs = psmt.executeQuery();
            int c = 0;
            while(rs.next()){
                c++;
                if(c>0) break;
            }
            psmt.close();
            rs.close();
            if(c>0){
                JOptionPane.showMessageDialog(null, "Bill of " + MONTH + ", " + YEAR+ " is already created!", "Can't update!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e){
            
        }
        
        
        String price = priceTxt.getText().trim();
        Double Price = 0.0;
        try{
            Price = Double.parseDouble(price);
            Price = Double.valueOf(df.format(Price));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You inserted an invalid price", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        if(Price < 0.0){
            JOptionPane.showMessageDialog(null, "Price must be positive", "Invalid PRICE!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        Price = Double.valueOf(df.format(Price));
        String sql = "";
        if(PRESENT == 1)    sql = "UPDATE `tempfood` SET `bill`=? WHERE hallid = ? AND month = ? AND year = ?";
        else    sql = "INSERT INTO `tempfood`(`bill`,  `hallid`,  `month`, `year`) VALUES(?,?,?,?)";
        try{
            psmt = conn.prepareStatement(sql);
            psmt.setDouble(1, Price);
            psmt.setInt(2, HALLID);
            psmt.setString(3, MONTH);
            psmt.setString(4, YEAR);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        refresh();
        priceTxt.setText("");
        idTxt.setText("");
        idTxt.requestFocusInWindow();
        JOptionPane.showMessageDialog(null, "Completed suceessfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_saveBtnActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TempFood().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField avgTxt;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JLabel hallIdLbl;
    private javax.swing.JLabel hallIdLbl1;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthLbl;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JLabel nameLbl1;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JTextField quantityTxt;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JLabel rollLbl;
    private javax.swing.JLabel rollLbl1;
    private javax.swing.JLabel roomLbl;
    private javax.swing.JLabel roomLbl1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton setBtn;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
