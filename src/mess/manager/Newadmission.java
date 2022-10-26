package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Newadmission extends javax.swing.JFrame {
    
    
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement st = null;
    ResultSet rs = null;
    Date sysDate = null;
    

    public Newadmission() {
        initComponents();
        
        this.setTitle("NEW ADMISSION");
        conn = JConnection.ConnecrDb();
        setResizable(false);
        sysDate = new Date();
        edtdate.setDate(sysDate);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) edtdate.getDateEditor();
        editor.setEditable(false);
        
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
    
    
    
    public void mclose(){
        setVisible(false);
        Menu my=new Menu();
        my.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        IDENTITY = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtstdid = new javax.swing.JTextField();
        edtstdname = new javax.swing.JTextField();
        edtfathername = new javax.swing.JTextField();
        edtmothername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtdept = new javax.swing.JTextField();
        edtroom = new javax.swing.JTextField();
        ADDRESS = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edtpresentadd = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        edtpermanentadd = new javax.swing.JTextArea();
        OTHERINFO = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        edtdate = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        edtcontactno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbreligion = new javax.swing.JComboBox<>();
        cmbbloodgrp = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbgender = new javax.swing.JComboBox<>();
        btnok = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        IDENTITY.setBackground(new java.awt.Color(204, 153, 255));
        IDENTITY.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IDENTITY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic Light", 1, 14))); // NOI18N

        jLabel1.setText("STUDENT ROLL");

        jLabel2.setText("STUDENT NAME");

        jLabel3.setText("FATHER NAME");

        jLabel4.setText("MOTHER NAME");

        jLabel5.setText("DEPARTMENT");

        jLabel6.setText("ROOM NO");

        javax.swing.GroupLayout IDENTITYLayout = new javax.swing.GroupLayout(IDENTITY);
        IDENTITY.setLayout(IDENTITYLayout);
        IDENTITYLayout.setHorizontalGroup(
            IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IDENTITYLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IDENTITYLayout.createSequentialGroup()
                        .addComponent(edtdept, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(edtroom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))
                    .addGroup(IDENTITYLayout.createSequentialGroup()
                        .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtstdid, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtstdname, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtfathername, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtmothername, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))))
        );
        IDENTITYLayout.setVerticalGroup(
            IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IDENTITYLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtstdid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtstdname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtfathername, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtmothername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtroom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(IDENTITYLayout.createSequentialGroup()
                        .addGroup(IDENTITYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtdept, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );

        ADDRESS.setBackground(new java.awt.Color(204, 153, 255));
        ADDRESS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ADDRESS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Light", 1, 14))); // NOI18N

        jLabel7.setText("PRESENT ADDRESS");

        jLabel8.setText("PERMANENT ADDRESS");

        edtpresentadd.setColumns(20);
        edtpresentadd.setRows(5);
        jScrollPane1.setViewportView(edtpresentadd);

        edtpermanentadd.setColumns(20);
        edtpermanentadd.setRows(5);
        jScrollPane2.setViewportView(edtpermanentadd);

        javax.swing.GroupLayout ADDRESSLayout = new javax.swing.GroupLayout(ADDRESS);
        ADDRESS.setLayout(ADDRESSLayout);
        ADDRESSLayout.setHorizontalGroup(
            ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ADDRESSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addGroup(ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ADDRESSLayout.setVerticalGroup(
            ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ADDRESSLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ADDRESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        OTHERINFO.setBackground(new java.awt.Color(204, 153, 255));
        OTHERINFO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OTHER INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Light", 1, 14))); // NOI18N

        jLabel9.setText("DATE OF BIRTH");

        jLabel10.setText("CONTACT NO");

        jLabel11.setText("RELIGION");

        jLabel12.setText("BLOOD GROUP");

        cmbreligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISLAM", "HINDU", "CHRISTIAN", "BUDDHIST", "" }));

        cmbbloodgrp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "O+", "O-", "AB+", "AB-", "B+", "B-" }));

        jLabel13.setText("GENDER");

        cmbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cmbgender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbgenderItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout OTHERINFOLayout = new javax.swing.GroupLayout(OTHERINFO);
        OTHERINFO.setLayout(OTHERINFOLayout);
        OTHERINFOLayout.setHorizontalGroup(
            OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OTHERINFOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtdate, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(edtcontactno))
                .addGap(40, 40, 40)
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(37, 37, 37)
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbreligion, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbbloodgrp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbgender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OTHERINFOLayout.setVerticalGroup(
            OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OTHERINFOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OTHERINFOLayout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cmbreligion))
                        .addGap(24, 24, 24)
                        .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(cmbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(OTHERINFOLayout.createSequentialGroup()
                        .addComponent(edtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OTHERINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtcontactno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbbloodgrp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btncancel.setText("CANCEL");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        btnrefresh.setText("REFRESH");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IDENTITY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ADDRESS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(OTHERINFO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(IDENTITY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ADDRESS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OTHERINFO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnok)
                    .addComponent(btncancel)
                    .addComponent(btnrefresh))
                .addContainerGap())
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

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to save the student information?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
//for(int k=100; k<=700; k++){
        int hallid = 0;
        String s[] = new String[15];
        for(int i=0; i<15; i++) s[i]="";
        
        try {
        
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM sequence;");

            if (rs.next()) {
                hallid = rs.getInt(1);
            }
            
            st.close();
            rs.close();

    } catch (SQLException ex) {
            //System.out.println("Hall id Not available!");

    }
        //System.out.println(hallid + "");
        
        
        
        Date date =  edtdate.getDate();
        String dob = "";
        String entrydate = "";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        
        s[1] = edtstdid.getText().toString().trim();////////////////////CHANGEEE
        s[2] = edtstdname.getText().toString().trim();/////////////////CHANGEEE
        //s[1] = "201614" + k;//////CHANGE
        //s[2] = "Swapnil" + k;////CHANGE
        
        if(s[1].equals("")){
            JOptionPane.showMessageDialog(null, "You musst fillup the student's ID.", "Invalid Student ID!", JOptionPane.ERROR_MESSAGE);
            edtstdid.requestFocusInWindow();
            return;
        }
        
        if(s[2].equals("")){
            JOptionPane.showMessageDialog(null, "You musst fillup the student's name.", "Invalid Name!", JOptionPane.ERROR_MESSAGE);
            edtstdname.requestFocusInWindow();
            return;
        }
        
        try{
            psmt = conn.prepareStatement("SELECT name FROM stuinfo WHERE roll = ?");
            psmt.setString(1,s[1]);
            rs = psmt.executeQuery();
            int c = 0;
            String n = "";
            while(rs.next()){
                c++;
                n = rs.getString(1).toString();
            }
            if(c!=0){
                JOptionPane.showMessageDialog(null, "This student ID: " + s[1] + " is already assigned to " + n, "Invalid Student ID!", JOptionPane.ERROR_MESSAGE);
                edtstdid.requestFocusInWindow();
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        s[3] = edtfathername.getText().toString().trim();
        s[4] = edtmothername.getText().toString().trim();
        s[5] = edtdept.getText().toString().trim(); 
        s[7] = edtcontactno.getText().toString().trim();
        s[8] = cmbbloodgrp.getSelectedItem().toString().trim();
        s[9] = cmbgender.getSelectedItem().toString().trim();
        s[10] = cmbreligion.getSelectedItem().toString().trim();
        if(date != null)    s[11] = DateFormat.getDateInstance().format(date);
        s[12] = edtpermanentadd.getText().toString().trim();
        s[13] = edtpresentadd.getText().toString().trim();
        s[14] = edtroom.getText().toString().trim();/////////////////////CHANGE
        //s[14] = k + "";  ////////////CHANGE
        

        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(today));
            s[6] = DateFormat.getDateInstance().format(todayWithZeroTime);
        } catch (ParseException ex) {
            Logger.getLogger(Newadmission.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            psmt = conn.prepareStatement("INSERT INTO `totalbill`(`hallid`, `totaldue`) VALUES(?,?)");
            psmt.setInt(1,hallid);
            psmt.setDouble(2, 0.0);
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        try {
            
            psmt = conn.prepareStatement("INSERT INTO `stuinfo`(`hallid`, `roll`, `name`, `fname`, `mname`, `dept`, `entrydate`, `contno`, `bgrp`, `sex`, `rel`, `dob`, `peradd`, `presentadd`, `roomno`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psmt.setInt(1, hallid);
            for(int i=2; i<=15; i++)    psmt.setString(i, s[i-1]);
            psmt.execute();
            psmt = conn.prepareStatement("UPDATE `sequence` SET `hall_id_seq`=?");
            psmt.setInt(1, hallid + 1);
            psmt.execute();
            psmt.close();
            
            
            
            JOptionPane.showMessageDialog(null, "Admission of " + s[2] + " is completed successfully!", "Successfull!", JOptionPane.INFORMATION_MESSAGE); //////////////////////CHANGE
            refresh();
        } 
	catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Oops! Some Unknown error occured!", "Alert", JOptionPane.ERROR_MESSAGE); 
        }
        
        //System.out.println("Inserted: " + k);
//}        
    }//GEN-LAST:event_btnokActionPerformed

    
    
    
    
    private void cmbgenderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbgenderItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbgenderItemStateChanged

    
    
    
    
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    
    
    
    
    
    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        setVisible(false);
        Menu m6=new Menu();
        m6.setVisible(true);
    }//GEN-LAST:event_btncancelActionPerformed

    
    
    private void refresh(){
        edtstdid.setText("");
        edtstdname.setText("");
        edtfathername.setText("");
        edtmothername.setText("");
        edtdept.setText("");
        edtroom.setText("");
        edtpermanentadd.setText("");
        edtpresentadd.setText("");
        edtdate.setDate(sysDate);
        edtcontactno.setText("");
        cmbreligion.setSelectedIndex(0);
        cmbgender.setSelectedIndex(0);
        cmbbloodgrp.setSelectedIndex(0);
        edtstdid.requestFocusInWindow();
    }
    
    
    /*public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Newadmission().setVisible(true);
            }
        });
    }*/
    
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Newadmission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ADDRESS;
    private javax.swing.JPanel IDENTITY;
    private javax.swing.JPanel OTHERINFO;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnok;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JComboBox<String> cmbbloodgrp;
    private javax.swing.JComboBox<String> cmbgender;
    private javax.swing.JComboBox<String> cmbreligion;
    private javax.swing.JTextField edtcontactno;
    private com.toedter.calendar.JDateChooser edtdate;
    private javax.swing.JTextField edtdept;
    private javax.swing.JTextField edtfathername;
    private javax.swing.JTextField edtmothername;
    private javax.swing.JTextArea edtpermanentadd;
    private javax.swing.JTextArea edtpresentadd;
    private javax.swing.JTextField edtroom;
    private javax.swing.JTextField edtstdid;
    private javax.swing.JTextField edtstdname;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
