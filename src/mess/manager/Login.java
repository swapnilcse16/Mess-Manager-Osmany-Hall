package mess.manager;

import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement st = null;
    ResultSet rs = null;
    String PASSWORD = "";
    
    
    private void tempOut(){
        Double sum = 0.0;
        for(int i=26; i<=31; i++){
            Double total = 0.0;
            String DATE = "2019-01-"+i;
            try{
                psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE outdate = ?");
                psmt.setString(1, DATE);
                rs = psmt.executeQuery();

                while(rs.next()){
                    total = total + rs.getDouble(1)*rs.getDouble(2);
                }
                psmt.close();
                rs.close();
                System.out.println(total + "");
            }catch(Exception e){System.out.println("Hello");}
            sum += total;
        }
        
        System.out.println("Sum: " + sum);
    }
    
    
    
    private void stuinfoMigrate(){
        Connection c = JConnection.secondDb();
        
        try{
            psmt = conn.prepareStatement("DELETE FROM stuinfo");
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            System.out.println("DELETE PROBLEM");
        }
        
        try{
            psmt = conn.prepareStatement("DELETE FROM totalbill");
            psmt.execute();
            psmt.close();
        }catch(Exception e){
            System.out.println("DELETE BILL PROBLEM");
        }
        
        
        
        try{
            psmt = c.prepareStatement("SELECT * FROM stuinfo order by (hallid)");
            rs = psmt.executeQuery();
            
            while(rs.next()){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `stuinfo`(`hallid`, `roll`, `name`, `fname`, `mname`, `dept`, `entrydate`, `contno`, `bgrp`, `sex`, `rel`, `dob`, `peradd`, `presentadd`, `roomno`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, rs.getInt(1));
                for(int i=2; i<=15; i++)    ps.setString(i, rs.getString(i));
                ps.execute();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO `totalbill`(`hallid`, `totaldue`) VALUES(?,?)");
                ps.setInt(1,rs.getInt(1));
                ps.setDouble(2, 0.0);
                ps.execute();
                ps.close();
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            System.out.println("Insertion problem");
        }
        
        System.out.println("DONE!");
    }
    
    
    public void offerInsert(){
        for(int i=7; i<=25; i++){
            String s = "2019-05-";
            if(i<=9)    s = s + "0" + i;
            else    s = s + i;
            
            try{
                
                psmt = conn.prepareStatement("INSERT INTO offerinfo(offerdate,bf,lunch,dinner) VALUES(?,-20.0,0.0,0.0)");
                psmt.setString(1,s);
                psmt.execute();
            }catch(Exception e){
                
            }
        }
    }
    
    
    
   public void func() {
       String s1[]=new String [31];
       String s2[]=new String [31];
       for(int i=26;i<=31;i++){
           s1[i-26]="2018-12-"+i;
       }
       for(int i=1;i<=25;i++){
           String tmp=i+"";
           if(i<=9)tmp="0"+tmp;
           s1[i+5]="2019-01-"+tmp;
       }
       //for(int i=0; i<31; i++)  System.out.println(s1[i] + "");
       for(int i=1; i<=31; i++){
           String DATE = "2019-01-";
           if(i<=9) DATE = DATE + "0" + i;
           else DATE = DATE + i + "";
           
           try{
           psmt = conn.prepareStatement("UPDATE storeoutput SET outdate = ? WHERE outdate = ?");
           psmt.setString(1, s1[i-1]);
           psmt.setString(2, DATE);
           psmt.execute();
           psmt.close();
           psmt = conn.prepareStatement("UPDATE paradestate SET curdate = ? WHERE curdate = ?");
           psmt.setString(1, s1[i-1]);
           psmt.setString(2, DATE);
           psmt.execute();
           psmt.close();
       }catch(Exception e){
               System.out.println("Hello");
       }
       }
   }
   
   
   private void changeDate(){
       ArrayList<String> curDate = new ArrayList<>();
       ArrayList<String> newDate = new ArrayList<>();
       int c = 1;
       
       for(int i=26; ;i++){
           String cd = "";
           String nd = "";
           
           if(i>=26)    cd = "2019-01-" + i;
           else if(i>=1 && i<=9) cd = "2019-02-0"+i;
           else cd = "2019-02-" + i;
           
           if(i==31)    i=0;
           
           if(c <=9)    nd = "2019-02-0" + c;
           else nd = "2019-02-"+c;
           
           curDate.add(cd);
           newDate.add(nd);
           c++;
           if(i==22)    break;
       }
       
       int len = curDate.size();
       for(int i=0; i<len; i++){
           System.out.println(curDate.get(i) + "   " + newDate.get(i));
           try{
               psmt = conn.prepareStatement("UPDATE paradestate SET curdate = ? WHERE curdate = ?");
               psmt.setString(1, curDate.get(i));
               psmt.setString(2, newDate.get(i));
               psmt.execute();
               
               psmt = conn.prepareStatement("UPDATE storeoutput SET outdate = ? WHERE outdate = ?");
               psmt.setString(1, curDate.get(i));
               psmt.setString(2, newDate.get(i));
               psmt.execute();
           }catch(Exception e){
               System.out.println("Problem!");
           }
       }
       System.out.println("DONE!");
   }
   
   
   private void setUpdate(){
       try{
           psmt = conn.prepareStatement("SELECT hallid,previous FROM messbill WHERE month = 'March'");
           rs = psmt.executeQuery();
           
           while(rs.next()){
               PreparedStatement ps = conn.prepareStatement("UPDATE totalbill SET totaldue = ? WHERE hallid = ?");
               ps.setDouble(1, rs.getDouble(2));
               ps.setInt(2, rs.getInt(1));
               ps.execute();
               ps.close();
           }
           psmt.close();
           rs.close();
           conn.close();
       }catch(Exception e){
           System.out.println("Problem");
       }
   }
    
    
    public Login() {
        initComponents();
        conn = JConnection.ConnecrDb();
        this.setResizable(false);
        
        this.setTitle("LOGIN");
        ///BillManager b = new BillManager("September", "2009");\\\\
        
        try {
            psmt = conn.prepareStatement("SELECT * FROM sequence");
            rs = psmt.executeQuery();
            if(rs.next()){
                PASSWORD = rs.getString(2);
                //System.out.println(PASSWORD + "");
            }
            
            psmt.close();
            rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE); 
        }
        
//        BillManager b = new BillManager();
//        String curDate = "2018-12-30";
//        int a[] = b.dateExtract(curDate);
//        int curMonth = a[1];
//        
//        for(int i=1; i<=800; i++){
//            String next = b.nextDate(curDate);
//            a = b.dateExtract(next);
//            if(a[1]!=curMonth){
//                curMonth = a[1];
//                System.out.println("");
//                System.out.println("");
//            }
//            
//            System.out.println(next);
//            curDate = next;
//        }
        //stuinfoMigrate();
        //tempOut();
        //func();
        //System.out.println(Menu.setPath);
        //changeDate();
        //setUpdate();
        //offerInsert();
    }
    
    
    
    
    
    

    
    
    
    
    
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(107, 128, 133));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mess/manager/newpackage/rsz_162649_539921699384959_1443320062_n.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Login");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setText("USERNAME");

        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("PASSWORD");

        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("CHANGE PASSWORD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mess/manager/newpackage/rsz_mist_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(112, 112, 112))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(password)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String Uname = username.getText().toString();
        String pass = password.getText().toString();
        
        if(!Uname.equals("admin")){
            JOptionPane.showMessageDialog(null, "Username not correct!", "Incorrect Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!pass.equals(PASSWORD)){
            JOptionPane.showMessageDialog(null, "Password not correct!", "Incorrect Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            
        }
        
        //JOptionPane.showMessageDialog(null,  "Problem!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        setVisible(false);
        Menu m5=new Menu();
        m5.setVisible(true);
        //JOptionPane.showMessageDialog(null,  "Problem!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_passwordActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            conn.close();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        ChangePassword c = new ChangePassword();
        this.setVisible(false);
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

  
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
