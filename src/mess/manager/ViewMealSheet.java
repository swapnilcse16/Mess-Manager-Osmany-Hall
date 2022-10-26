
package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class ViewMealSheet extends javax.swing.JFrame {
    
    
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    DecimalFormat df = new DecimalFormat("####0.00");
    String state[] = {"Breakfast", "Lunch", "Dinner"};
    Double price[] = new Double[3];
    String mealItem[] = new String[3];
    Double total[] = new Double[3];
    ArrayList <Double> temp = new ArrayList<>();
    
    
    void visibility(boolean b){
        hallIdLbl.setVisible(b);
        hallIdedt.setVisible(b);
        hallIdBtn.setVisible(b);
        rollLbl.setVisible(b);
        rollEdt.setVisible(b);
        rollBtn.setVisible(b);
    }
    
    
    void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("VIEW DAILY MEAL SHEET");
        tm = (DefaultTableModel)table.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        Date date = new Date();
        dateChooser.setDate(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        this.setResizable(false);
        tm.setRowCount(0);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        
        refresh();
        //visibility(false);
        
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
    
    
    
    
    void refresh(){
        tm.setRowCount(0);
        breakfastCost1.setText("");
        lunchCost1.setText("");
        dinnerCost1.setText("");
        totalBreakfastsLbl1.setText("");
        totalLuchLbl1.setText("");
        totalDinnerLbl1.setText("");
        //bItems.setText("");
        //lItems.setText("");
        //dItems.setText("");
        hallIdedt.setText("");
        rollEdt.setText("");
        breakfastCost1.setEditable(false);
        lunchCost1.setEditable(false);
        dinnerCost1.setEditable(false);
        totalBreakfastsLbl1.setEditable(false);
        totalDinnerLbl1.setEditable(false);
        totalLuchLbl1.setEditable(false);
        for(int i=0; i<3; i++){
            price[i] = 0.0;
            mealItem[i] = "";
        }
    }
    
    
    

    public ViewMealSheet() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        hallIdLbl = new javax.swing.JLabel();
        hallIdedt = new javax.swing.JTextField();
        hallIdBtn = new javax.swing.JButton();
        rollLbl = new javax.swing.JLabel();
        rollEdt = new javax.swing.JTextField();
        rollBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        breakfastCost = new javax.swing.JLabel();
        lunchCost = new javax.swing.JLabel();
        dinnerCost = new javax.swing.JLabel();
        totalBreakfastsLbl = new javax.swing.JLabel();
        totalLuchLbl = new javax.swing.JLabel();
        totalDinnerLbl = new javax.swing.JLabel();
        breakfastCost1 = new javax.swing.JTextField();
        lunchCost1 = new javax.swing.JTextField();
        dinnerCost1 = new javax.swing.JTextField();
        totalDinnerLbl1 = new javax.swing.JTextField();
        totalLuchLbl1 = new javax.swing.JTextField();
        totalBreakfastsLbl1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("DATE");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dateChooser.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        hallIdLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdLbl.setText("HALL ID");

        hallIdedt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hallIdedt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdedtActionPerformed(evt);
            }
        });

        hallIdBtn.setText("SEARCH");
        hallIdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallIdBtnActionPerformed(evt);
            }
        });

        rollLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollLbl.setText("ROLL");

        rollEdt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rollEdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollEdtActionPerformed(evt);
            }
        });

        rollBtn.setText("SEARCH");
        rollBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollBtnActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hall id", "Roll", "Room", "Name", "Breakfast", "Lunch", "Dinner", "Breakfast Cost", "Lunch Cost", "Dinner Cost", "Total Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table);

        breakfastCost.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        breakfastCost.setText("BREAKFAST COST:");

        lunchCost.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lunchCost.setText("LUNCH COST:");

        dinnerCost.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        dinnerCost.setText("DINNER COST:");

        totalBreakfastsLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalBreakfastsLbl.setText("TOTAL BREAKFAST:");

        totalLuchLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalLuchLbl.setText("TOTAL LUNCH:");

        totalDinnerLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalDinnerLbl.setText("TOTAL DINNER:");

        breakfastCost1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        lunchCost1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        dinnerCost1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        totalDinnerLbl1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        totalLuchLbl1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        totalBreakfastsLbl1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setText("VIEW  DINNER  ITEMS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setText("VIEW  BREAKFAST  ITEMS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setText("VIEW  LUNCH  ITEMS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hallIdLbl)
                            .addComponent(rollLbl))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rollEdt)
                    .addComponent(hallIdedt)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(hallIdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rollBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(breakfastCost, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(totalBreakfastsLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(breakfastCost1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalBreakfastsLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lunchCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalLuchLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lunchCost1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLuchLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dinnerCost)
                            .addComponent(totalDinnerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalDinnerLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dinnerCost1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dinnerCost)
                                    .addComponent(dinnerCost1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalDinnerLbl)
                                    .addComponent(totalLuchLbl)
                                    .addComponent(totalBreakfastsLbl)
                                    .addComponent(totalDinnerLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalLuchLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalBreakfastsLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(breakfastCost)
                                .addComponent(lunchCost)
                                .addComponent(breakfastCost1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lunchCost1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hallIdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hallIdedt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hallIdLbl))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rollBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rollEdt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rollLbl)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2)
                                    .addComponent(jButton4))))
                        .addGap(29, 29, 29)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        int count = 0;
        int totalBf = 0, totalLunch = 0, totalDinner = 0;
        ArrayList <Parade> parade = new ArrayList<>();
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM paradestate WHERE curdate = ?");
            psmt.setString(1,DATE);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                int hallid = rs.getInt(1);
                int bf = rs.getInt(3);
                int lunch = rs.getInt(4);
                int dinner = rs.getInt(5);
                count++;
                
                totalBf += bf;
                totalLunch += lunch;
                totalDinner += dinner;
                
                Parade p = new Parade(hallid,bf,lunch,dinner);
                parade.add(p);
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(count==0){
            JOptionPane.showMessageDialog(null, "Meal order of chosen date is not updated!\nPlease choose another date!", "No record found!", JOptionPane.ERROR_MESSAGE);
            refresh();
            return;
        }
        
        refresh();
        
        
        for(int i=0; i<3; i++){
            price[i] = 0.0;
            total[i] = 0.0;
            mealItem[i] = "";
            try{
                //double totalQuantity = 0.0, totalPrice = 0.0;
                psmt = conn.prepareStatement("SELECT itemname,amount,avgprice FROM storeoutput WHERE outdate = ? AND state = ?");
                psmt.setString(1, DATE);
                psmt.setString(2, state[i]);
                rs = psmt.executeQuery();
                int number = 1;
                
                while(rs.next()){
                    String it = rs.getString(1);
                    double qu = Double.valueOf(df.format(rs.getDouble(2)));
                    double av = Double.valueOf(df.format(rs.getDouble(3)));
                    
                    mealItem[i] = mealItem[i] + number + ". " + it + "(" + qu + "x" + av+"/-)  = " +Double.valueOf(df.format(qu * av)) + "\n";
                    price[i] = price[i] + qu * av;
                    total[i] += qu * av;
                    number++;
                }
                
                psmt.close();
                rs.close();
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        tm.setRowCount(0);
        try{
            count = parade.size();
            for(int i=0; i<count; i++){
                int hallId = parade.get(i).hallId;
                psmt = conn.prepareStatement("SELECT roll,roomno,name from stuinfo WHERE hallid = ?");
                psmt.setInt(1, hallId);
                rs = psmt.executeQuery();
                
                if(rs.next()){
                    String roll = rs.getString(1);
                    String roomno = rs.getString(2);
                    String name = rs.getString(3);
                    
                    parade.get(i).roll = roll;
                    parade.get(i).room = roomno;
                    parade.get(i).name = name;
                }
                
                Double d1 = totalBf * 1.0;
                Double m1 = parade.get(i).breakfast * 1.0;
                if(totalBf == 0)    parade.get(i).breakfastBill = 0.0;
                else    parade.get(i).breakfastBill = Double.valueOf(df.format((m1*price[0])/d1));
                
                d1 = totalLunch * 1.0;
                m1 = parade.get(i).lunch * 1.0;
                if(totalLunch == 0) parade.get(i).lunchBill = 0.0;
                else    parade.get(i).lunchBill = Double.valueOf(df.format((m1*price[1])/d1));
                
                d1 = totalDinner * 1.0;
                m1 = parade.get(i).dinner * 1.0;
                if(totalDinner == 0)    parade.get(i).dinnerBill = 0.0;
                else    parade.get(i).dinnerBill = Double.valueOf(df.format((m1*price[2])/d1));
                
                psmt.close();
                rs.close();
                
                Object o[] = parade.get(i).copy();
                tm.addRow(o);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //bItems.setText("Breakfast Items:  " + mealItem[0]);
        //lItems.setText("Lunch Items:       "+ mealItem[1]);
        //dItems.setText("Dinner Items:      " + mealItem[2]);
        
        totalBreakfastsLbl1.setText("" + totalBf+"");
        totalLuchLbl1.setText(""+totalLunch+"");
        totalDinnerLbl1.setText(""+totalDinner + "");
        
        if(totalBf == 0)    breakfastCost1.setText("0");
        else    breakfastCost1.setText("" + Double.valueOf(df.format(price[0]/totalBf)));
        if(totalLunch == 0) lunchCost1.setText("0");
        else    lunchCost1.setText    ("" + Double.valueOf(df.format(price[1]/totalLunch)));
        if(totalDinner == 0)    dinnerCost1.setText("0");
        else    dinnerCost1.setText   ("" + Double.valueOf(df.format(price[2]/totalDinner)));
        
        
        ///////////////////CHANGE\\\\\\\\\\\\\\\\\\\\\\\\\\\
        /*Double total = 0.0;
        for(int i=0; i<tm.getRowCount(); i++)   total = total + Double.parseDouble(tm.getValueAt(i, 10).toString().trim());
        System.out.println(total + "");
        temp.add(total);*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void hallIdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdBtnActionPerformed
        String hallId = hallIdedt.getText().toString().trim();
        if(hallId.equals("")){
            JOptionPane.showMessageDialog(null, "Hall ID must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            String tHallId = tm.getValueAt(i,0).toString().trim();
            if(tHallId.equals(hallId)){
                table.getSelectionModel().setSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "The Hall ID doesn't exist in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_hallIdBtnActionPerformed

    private void rollBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollBtnActionPerformed
        String roll = rollEdt.getText().toString().trim();
        if(roll.equals("")){
            JOptionPane.showMessageDialog(null, "Roll No will not be empty!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            String tRoll = tm.getValueAt(i,1).toString().trim();
            if(tRoll.equals(roll)){
                table.getSelectionModel().setSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "The Roll No doesn't exist in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_rollBtnActionPerformed

    private void hallIdedtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdedtActionPerformed
        hallIdBtn.doClick();
    }//GEN-LAST:event_hallIdedtActionPerformed

    private void rollEdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollEdtActionPerformed
        rollBtn.doClick();
    }//GEN-LAST:event_rollEdtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        showUpdate(0);
        
        
        /////////////////////CHANGE\\\\\\\\\\\\\\\\\\\\\\\\\
        /*Double total = 0.0;
        for(int i=0; i<temp.size(); i++)    total = total + temp.get(i);
        System.out.println(total + "");*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showUpdate(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showUpdate(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    private void showUpdate(int i){
        if(mealItem[i].equals(""))  JOptionPane.showMessageDialog(null, "Nothing to show!", state[i], JOptionPane.INFORMATION_MESSAGE);
        else    JOptionPane.showMessageDialog(null, mealItem[i] + "\nTOTAL PRICE: " + Double.valueOf(df.format(total[i])), state[i], JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMealSheet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel breakfastCost;
    private javax.swing.JTextField breakfastCost1;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel dinnerCost;
    private javax.swing.JTextField dinnerCost1;
    private javax.swing.JButton hallIdBtn;
    private javax.swing.JLabel hallIdLbl;
    private javax.swing.JTextField hallIdedt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lunchCost;
    private javax.swing.JTextField lunchCost1;
    private javax.swing.JButton rollBtn;
    private javax.swing.JTextField rollEdt;
    private javax.swing.JLabel rollLbl;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalBreakfastsLbl;
    private javax.swing.JTextField totalBreakfastsLbl1;
    private javax.swing.JLabel totalDinnerLbl;
    private javax.swing.JTextField totalDinnerLbl1;
    private javax.swing.JLabel totalLuchLbl;
    private javax.swing.JTextField totalLuchLbl1;
    // End of variables declaration//GEN-END:variables
}








class Parade{
    int hallId,breakfast,lunch,dinner;
    Double breakfastBill,lunchBill,dinnerBill;
    String roll,room,name;
    DecimalFormat df = new DecimalFormat("####0.00");

    public Parade(int hallId, int breakfast, int lunch, int dinner) {
        this.hallId = hallId;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.breakfastBill = 0.0;
        this.lunchBill = 0.0;
        this.dinnerBill = 0.0;
        this.roll = "";
        this.room = "";
        this.name = "";
    }
    
    public Object[] copy(){
        Object o[] = {hallId + "", roll, room,name,breakfast + "", lunch + "" ,dinner + "",Double.valueOf(df.format(breakfastBill))+"",Double.valueOf(df.format(lunchBill))+"",Double.valueOf(df.format(dinnerBill))+"",Double.valueOf(df.format(breakfastBill + lunchBill + dinnerBill))  + ""};
        return o;
    }
}
