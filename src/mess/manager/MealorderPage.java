package mess.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class MealorderPage extends javax.swing.JFrame{

    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    DefaultTableModel tmLeft = null;
    DefaultTableModel tmRight = null;
    int selectedLeft , selectedRight;
    ArrayList <MealStatus> left,right;
    String DATESAVED;
    int PREVIOUS;
    int SEARCHCLICKED;
    
    
    
    private void totalCalculator(){
        int len = right.size();
        offPerson.setText("Total Off: " + len);
        len = left.size();
        onPerson.setText("Total On: " + len);
        int bf =0, lunch =0, dinner = 0;
        try{
            for(int i=0; i<len; i++){
                MealStatus m = left.get(i);
                bf = bf + Integer.parseInt(m.breakfast);
                lunch = lunch + Integer.parseInt(m.lunch);
                dinner = dinner + Integer.parseInt(m.dinner);
            }
            
            totalBreakfast.setText("Total Breakfast: " + bf);
            totalDinner.setText   ("Total Dinner: " + dinner);
            totalLunch.setText    ("Total Lunch: " + lunch);
        }catch(Exception e){
            
        }
    }
    
    
   
    
    private void init(){
        conn=JConnection.ConnecrDb();
        
        monthYear.setText("");
        SEARCHCLICKED = 0;
        this.setTitle("DAILY MEAL SHEET");
        PREVIOUS = 0;
        Date date = new Date();
        dateChooser.setDate(date);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        setResizable(false);
        offAllBreakfast.setText("<html>OFF ALL<br/>BREAKFAST</html>");
        offAllLunch.setText("<html>OFF ALL<br/>LUNCH</html>");
        offAllDinner.setText("<html>OFF ALL<br/>DINNER</html>");
        tmLeft = (DefaultTableModel)leftTable.getModel();
        tmRight = (DefaultTableModel)rightTable.getModel();
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        selectedLeft = -1;
        selectedRight = -1;
        left = new ArrayList<>();
        right = new ArrayList<>();
        
        
        
        leftTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        leftTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        leftTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        leftTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        leftTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        leftTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        leftTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        
        
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
        
        
        //okBtn.doClick();
    }
    
    
    
    
    
    
    private int databaseParade(){
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        DATESAVED = formatter.format(date);
        int c = 0;
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM paradestate WHERE curdate = ?");
            psmt.setString(1, DATESAVED);
            rs = psmt.executeQuery();
            while(rs.next()){
                c++;
                if(c>0) break; 
            }
            
            psmt.close();
            rs.close();
            if(c==0)    return 0;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        PREVIOUS = 1;
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,name FROM stuinfo");
            rs = psmt.executeQuery();
            
            while(rs.next()){
                int hallId = rs.getInt(1);
                String room = rs.getString(2);
                String name = rs.getString(3);
                String bf = "";
                String lunch = "";
                String dinner = "";
                PreparedStatement ps1 = conn.prepareStatement("SELECT bf,lunch,dinner from paradestate WHERE hallid = ? AND curdate=?");
                ps1.setInt(1, hallId);
                ps1.setString(2, DATESAVED);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next()){
                    bf = rs1.getString(1);
                    lunch = rs1.getString(2);
                    dinner = rs1.getString(3);
                }
                ps1.close();
                rs1.close();
                
                if(bf.equals(""))   bf="1";
                if(lunch.equals("")) lunch="1";
                if(dinner.equals("")) dinner = "1";
                
                MealStatus m = new MealStatus(false, hallId+"" , room, name, bf, lunch, dinner);
                if(m.breakfast.equals("0") && m.lunch.equals("0") && m.dinner.equals("0")) right.add(m);
                else    left.add(m);
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        /*try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,name,bf,lunch,dinner FROM stuinfo JOIN paradestate USING(hallid) WHERE curdate=?");
            psmt.setString(1, DATESAVED);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                MealStatus m = new MealStatus(false, rs.getInt(1)+"",rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                c++;
                PREVIOUS = 1;
                if(m.breakfast.equals("0") && m.lunch.equals("0") && m.dinner.equals("0")){
                    right.add(m);
                }
                else    left.add(m);
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }*/
        
        int len = left.size();
        if(len>1)   Collections.sort(left, new CustomComparator());
        len = right.size();
        if(len>1)   Collections.sort(right, new CustomComparator());
        
        for(int i=0; i<len; i++)    tmRight.addRow(right.get(i).copy2());
        len = left.size();
        for(int i=0; i<len; i++)    tmLeft.addRow(left.get(i).copy());
        
        return c;
    }
    
    
    
    public MealorderPage() {
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
        totalCalculator();
        SEARCHCLICKED = 1;
        //leftTable.getSelectionModel().setSelectionInterval(100, 100);
        //leftTable.scrollRectToVisible(new Rectangle(leftTable.getCellRect(100, 0, true)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        leftTable = new javax.swing.JTable();
        saveAndExit = new javax.swing.JButton();
        btnOn = new javax.swing.JButton();
        btnOff = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        rightTable = new javax.swing.JTable();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        offSelected = new javax.swing.JButton();
        onSelected = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        hallIdUpdate = new javax.swing.JLabel();
        nameUpdate = new javax.swing.JLabel();
        breakfastUpdate = new javax.swing.JTextField();
        lunchUpdate = new javax.swing.JTextField();
        dinnerUpdate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        searchBarLeft = new javax.swing.JTextField();
        searchLeft = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        searchBarRight = new javax.swing.JTextField();
        searchRight = new javax.swing.JButton();
        onPerson = new javax.swing.JLabel();
        offPerson = new javax.swing.JLabel();
        totalBreakfast = new javax.swing.JLabel();
        totalLunch = new javax.swing.JLabel();
        totalDinner = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        offAllBreakfast = new javax.swing.JButton();
        offAllLunch = new javax.swing.JButton();
        offAllDinner = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        monthYear = new javax.swing.JLabel();
        guestChk = new javax.swing.JCheckBox();
        countLeft = new javax.swing.JLabel();
        countRight = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        leftTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "Hall ID", "Room", "Name", "Breakfast", "Lunch", "Dinner"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leftTable.getTableHeader().setReorderingAllowed(false);
        leftTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leftTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(leftTable);

        saveAndExit.setText("SAVE  &  EXIT");
        saveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndExitActionPerformed(evt);
            }
        });

        btnOn.setText("On All");
        btnOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnActionPerformed(evt);
            }
        });

        btnOff.setText("Off All");
        btnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOffActionPerformed(evt);
            }
        });

        rightTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rightTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "SELECT", "Hall ID", "Room", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        rightTable.getTableHeader().setReorderingAllowed(false);
        rightTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rightTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(rightTable);

        dateChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateChooserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateChooserMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dateChooserMousePressed(evt);
            }
        });
        dateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserPropertyChange(evt);
            }
        });
        dateChooser.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                dateChooserVetoableChange(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Update");

        jLabel2.setText("Hall ID");

        jLabel3.setText("Breakfast");

        jLabel4.setText("Lunch");

        jLabel5.setText("Dinner");

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        offSelected.setText("Off Selected");
        offSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offSelectedActionPerformed(evt);
            }
        });

        onSelected.setText("On Selected");
        onSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSelectedActionPerformed(evt);
            }
        });

        jLabel6.setText("Name");

        breakfastUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breakfastUpdateActionPerformed(evt);
            }
        });

        lunchUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunchUpdateActionPerformed(evt);
            }
        });

        dinnerUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dinnerUpdateActionPerformed(evt);
            }
        });

        jLabel7.setText("HALL ID");

        searchBarLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarLeftActionPerformed(evt);
            }
        });

        searchLeft.setText("SEARCH");
        searchLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLeftActionPerformed(evt);
            }
        });

        jLabel8.setText("DATE");

        jLabel9.setText("HALL ID");

        searchBarRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarRightActionPerformed(evt);
            }
        });

        searchRight.setText("SEARCH");
        searchRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRightActionPerformed(evt);
            }
        });

        onPerson.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        onPerson.setText("jLabel13");

        offPerson.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        offPerson.setText("jLabel14");

        totalBreakfast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalBreakfast.setText("jLabel15");

        totalLunch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalLunch.setText("jLabel16");

        totalDinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalDinner.setText("jLabel17");

        jProgressBar1.setStringPainted(true);

        offAllBreakfast.setText("OFF ALL BREAKFAST");
        offAllBreakfast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offAllBreakfastActionPerformed(evt);
            }
        });

        offAllLunch.setText("jButton2");
        offAllLunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offAllLunchActionPerformed(evt);
            }
        });

        offAllDinner.setText("jButton3");
        offAllDinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offAllDinnerActionPerformed(evt);
            }
        });

        okBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        monthYear.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        monthYear.setText("jLabel10");

        guestChk.setText("GUEST");
        guestChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestChkActionPerformed(evt);
            }
        });

        countLeft.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        countLeft.setText("0");

        countRight.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        countRight.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(offSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(onPerson))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guestChk)
                            .addComponent(searchLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(255, 255, 255)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBarRight, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchRight))
                            .addComponent(offPerson)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hallIdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(165, 165, 165)
                                .addComponent(countLeft)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(breakfastUpdate))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lunchUpdate))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dinnerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(offAllBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(offAllLunch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(43, 43, 43)
                                .addComponent(nameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalLunch)
                            .addComponent(totalBreakfast)
                            .addComponent(totalDinner))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(onSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(offAllDinner, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(monthYear)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(countRight)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(onPerson)
                                    .addComponent(offPerson)))
                            .addComponent(okBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(guestChk)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(searchBarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLeft)
                    .addComponent(jLabel9)
                    .addComponent(searchBarRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchRight)
                    .addComponent(offSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(onSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countLeft)
                    .addComponent(countRight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalBreakfast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalLunch)
                            .addComponent(monthYear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalDinner)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hallIdUpdate)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(nameUpdate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(breakfastUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lunchUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dinnerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 7, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saveAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(offAllBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(offAllLunch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(offAllDinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void saveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndExitActionPerformed
        if(tmLeft.getRowCount() + tmRight.getRowCount() == 0){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No mealorder is made!" , 1);
        
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
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to save the meal sheet?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        Date date = dateChooser.getDate();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = formatter.format(date);
        DATE = DATESAVED;
        int c = 0;
        
        /*try{
            psmt = conn.prepareStatement("SELECT hallid FROM paradestate where curdate = ?");
            psmt.setString(1, DATE);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                c++;
                if(c>0)
                    break;
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems at the time of checking date!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(c!=0){
            String DATE2 = "";
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
            try{
                Date date2 = dt.parse(DATE); 
                Format formatter2 = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
                DATE2 = formatter2.format(date2);
            }catch(Exception e){}
            JOptionPane.showMessageDialog(null, "Meal sheet of " + DATE2 + " is updated already!", "Select Another Date!", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        
        if(PREVIOUS==1){
            try{
                psmt = conn.prepareStatement("DELETE FROM paradestate where curdate = ?");
                psmt.setString(1, DATESAVED);
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        int len = right.size();
        for(int i=0; i<len; i++){
            left.add(right.get(i));
        }
        Collections.sort(left, new CustomComparator());
        jProgressBar1.setVisible(true);
        jProgressBar1.setValue(0);
        jProgressBar1.update(jProgressBar1.getGraphics());
        try{
            //////////////Start
            
            len = left.size();
            psmt = conn.prepareStatement("INSERT INTO `paradestate`(`hallid`, `curdate`, `bf`, `lunch`, `dinner`) VALUES(?,?,?,?,?)");
            int interval=len/100;
            if(len < 100){
                interval = 1;
            }
            for(int i=0; i<len; i++){
                MealStatus m = left.get(i);
                psmt.setInt(1, m.HallId);
                psmt.setString(2, DATE);
                psmt.setInt(3, Integer.parseInt(m.breakfast));
                psmt.setInt(4, Integer.parseInt(m.lunch));
                psmt.setInt(5, Integer.parseInt(m.dinner));
                
                if(i%interval==0){
                    jProgressBar1.setVisible(true);
                    if(i/interval == 100)  jProgressBar1.setValue(99);
                    else    jProgressBar1.setValue(i/interval);
                    jProgressBar1.update(jProgressBar1.getGraphics());
                }
                
                if(i==len-1){
                    //psmt.executeBatch();
                    jProgressBar1.setVisible(true);
                    jProgressBar1.setValue(100);
                    jProgressBar1.update(jProgressBar1.getGraphics());
                }
                psmt.execute();
                //psmt.close();
            }
            
            psmt.close();
            //jProgressBar1.setVisible(false);
            ///////end
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Meal sheet update is failed! ...", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Meal sheet is saved successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems in time of closing connection!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        Menu m = new Menu();
        m.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_saveAndExitActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if(selectedLeft == -1){
            JOptionPane.showMessageDialog(null, "For updating, click on the name of a student", "No Student Selected!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String bf = breakfastUpdate.getText().toString().trim();
        String lunch = lunchUpdate.getText().toString().trim();
        String dinner = dinnerUpdate.getText().toString().trim();
        int Bf = 0, Lunch =0, Dinner = 0;
        try{
            Bf = Integer.parseInt(bf);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You enterd an invalid breakfast amount!", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            //searchBarLeft.requestFocusInWindow();
            return;
        }
        
        try{
            Lunch = Integer.parseInt(lunch);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You enterd an invalid lunch amount!", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            //searchBarLeft.requestFocusInWindow();
            return;
        }
        
        try{
            Dinner = Integer.parseInt(dinner);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "You enterd an invalid dinner amount!", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Bf<0){
            JOptionPane.showMessageDialog(null, "Breakfast amount will not be negative", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Lunch<0){
            JOptionPane.showMessageDialog(null, "Lunch amount will not be negative", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Dinner < 0){
            JOptionPane.showMessageDialog(null, "Dinner amount will not be negative", "Invalid Number!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!(Bf==0 && Lunch==0 && Dinner==0)){
            tmLeft.setValueAt(Bf+"",selectedLeft,4);
            tmLeft.setValueAt(Lunch +"",selectedLeft,5);
            tmLeft.setValueAt(Dinner + "",selectedLeft,6);
            left.get(selectedLeft).breakfast = bf;
            left.get(selectedLeft).lunch = lunch;
            left.get(selectedLeft).dinner = dinner;
        }
        
        else{
            int dialogResult = JOptionPane.showConfirmDialog (null, "This will be counted as meal-off!" , "Do you want to off the meal?" , 1);        
            if(dialogResult != JOptionPane.YES_OPTION)  return;
            left.get(selectedLeft).breakfast = "0";
            left.get(selectedLeft).lunch = "0";
            left.get(selectedLeft).dinner = "0";
            left.get(selectedLeft).select = false;
            
            MealStatus m = left.get(selectedLeft);
            right.add(m);
            left.remove(selectedLeft);
            selectedLeft = -1;
            selectedRight = -1;
            refresh();
            int lenx=left.size();
            if(lenx>1)  Collections.sort(left, new CustomComparator());
            lenx=right.size();
            if(lenx>1)  Collections.sort(right, new CustomComparator());
            
            tmLeft.setRowCount(0);
            tmRight.setRowCount(0);
            
            int len = left.size();
            for(int i=0; i<len; i++){
                Object o[] = left.get(i).copy();
                tmLeft.addRow(o);
            }
            len = right.size();
            for(int i=0; i<len; i++){
                Object o[] = right.get(i).copy2();
                tmRight.addRow(o);
            }
        }
        totalCalculator();
        searchBarLeft.requestFocusInWindow();
    }//GEN-LAST:event_updateBtnActionPerformed

    
    
    private void refresh(){
        hallIdUpdate.setText("");
        nameUpdate.setText("");
        breakfastUpdate.setText("");
        lunchUpdate.setText("");
        dinnerUpdate.setText("");
    }
    
    
    private void btnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOffActionPerformed
        int len = left.size();
        countLeft.setText("0");
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No student in the list!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0; i<len; i++){
            left.get(0).select = false;
            left.get(0).breakfast = "0";
            left.get(0).lunch = "0";
            left.get(0).dinner = "0";
            right.add(left.get(0));
            left.remove(0);    
        }
        
        Collections.sort(right, new CustomComparator());
        selectedLeft =-1;
        selectedRight = -1;
        refresh();
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        len = right.size();
        for(int i=0; i<len; i++){
            Object o[] = right.get(i).copy2();
            tmRight.addRow(o);
        }
        totalCalculator();
    }//GEN-LAST:event_btnOffActionPerformed

    
    
    
    
    
    private void btnOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnActionPerformed
        int len = right.size();
        countRight.setText("0");
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No student in the list!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0; i<len; i++){
            right.get(0).select = false;
            right.get(0).breakfast = "1";
            right.get(0).lunch = "1";
            right.get(0).dinner = "1";
            left.add(right.get(0));
            right.remove(0);    
        }
        
        Collections.sort(left, new CustomComparator());
        selectedLeft =-1;
        selectedRight = -1;
        refresh();
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        len = left.size();
        for(int i=0; i<len; i++){
            Object o[] = left.get(i).copy();
            tmLeft.addRow(o);
        }
        
        totalCalculator();
    }//GEN-LAST:event_btnOnActionPerformed

    
    
    
    
    
    private void leftTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftTableMouseClicked
        int selectedRow = leftTable.getSelectedRow();
        int selectedColumn = leftTable.getSelectedColumn();
        
        if(selectedColumn == 0){
            searchBarLeft.setText("");
            searchBarLeft.requestFocusInWindow();
            boolean b = (boolean)tmLeft.getValueAt(selectedRow, 0);
            refresh();
            selectedLeft = -1;
            if(b){
                tmLeft.setValueAt(false,selectedRow,0);
                left.get(selectedRow).select = false;
            }
            else{
                tmLeft.setValueAt(true,selectedRow,0);
                left.get(selectedRow).select = true;
            }
            click();
        }
        
        else{
            breakfastUpdate.requestFocusInWindow();
            selectedLeft = selectedRow;
            updateSet();
        }
    }//GEN-LAST:event_leftTableMouseClicked

    
    
    private void updateSet(){
        hallIdUpdate.setText(tmLeft.getValueAt(selectedLeft,1).toString().trim());
            breakfastUpdate.setText(tmLeft.getValueAt(selectedLeft,4).toString().trim());
            lunchUpdate.setText(tmLeft.getValueAt(selectedLeft,5).toString().trim());
            dinnerUpdate.setText(tmLeft.getValueAt(selectedLeft,6).toString().trim());
            nameUpdate.setText(tmLeft.getValueAt(selectedLeft,3).toString().trim());
    }
    
    
    
    
    
    private void click(){
        int len = tmLeft.getRowCount();
        int count = 0;
        for(int i=0; i<len; i++){
            boolean b = (boolean)tmLeft.getValueAt(i, 0);
            if(b)   count++;
        }
        
        countLeft.setText(count + "");
        len = tmRight.getRowCount();
        count = 0;
        
        for(int i=0; i<len; i++){
            boolean b = (boolean)tmRight.getValueAt(i, 0);
            if(b)   count++;
        }
        
        countRight.setText(count + "");
    }
    
    
    
    
    private void guestEasy(int HallId){
        int len = right.size();
        int index = -1;
        for(int i=0; i<len; i++){
            MealStatus m = right.get(i);
            if(m.HallId == HallId){
                index = i;
                break;
            }
        }
        
        if(index!=-1){
            MealStatus m = right.get(index);
            right.remove(index);
            tmRight.removeRow(index);
            m.breakfast = "1";
            m.lunch = "1";
            m.dinner = "1";
            left.add(m);
            Collections.sort(left, new CustomComparator());
            tmLeft.setRowCount(0);
            len = left.size();
            for(int i=0; i<len; i++)    tmLeft.addRow(left.get(i).copy());
        }
        
        len = left.size();
        for(int i=0; i<len; i++){
            MealStatus m = left.get(i);
            if(m.HallId == HallId){
                breakfastUpdate.requestFocusInWindow();
                selectedLeft = i;
                updateSet();
                leftTable.getSelectionModel().setSelectionInterval(i, i);
                leftTable.scrollRectToVisible(new Rectangle(leftTable.getCellRect(i, 0, true)));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "There is no such Hall ID in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    
    private void searchLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchLeftActionPerformed
        String hallId = searchBarLeft.getText().toString().trim();
        searchBarLeft.setText("");
        int HallId = 0;
        try{
            HallId = Integer.parseInt(hallId);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hall ID must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            //searchBarLeft.setText("");
            searchBarLeft.requestFocusInWindow();
            return;
        }
        
        boolean g = guestChk.isSelected();
        if(g){
            guestEasy(HallId);
            searchBarLeft.setText("");
            //searchBarLeft.requestFocusInWindow();
            totalCalculator();
            return;
        }
        
        int len = left.size();
        for(int i=0; i<len; i++){
            MealStatus m = left.get(i);
            if(m.HallId == HallId){
                selectedLeft = -1;
                refresh();
                leftTable.getSelectionModel().setSelectionInterval(i, i);
                leftTable.scrollRectToVisible(new Rectangle(leftTable.getCellRect(i, 0, true)));
                //breakfastUpdate.requestFocusInWindow();
                boolean b = m.select;
                left.get(i).select = !b;
                tmLeft.setValueAt(!b, i, 0);
                click();
                searchBarLeft.setText("");
                //updateSet();
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "There is no such Hall ID in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
        //searchBarLeft.setText("");
        searchBarLeft.requestFocusInWindow();
        return;
    }//GEN-LAST:event_searchLeftActionPerformed

    private void offSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offSelectedActionPerformed
        int len = left.size();
        countLeft.setText("0");
        ArrayList <Integer> selection = new ArrayList<>();
        for(int i=0; i<len; i++){
            MealStatus m = left.get(i);
            if(m.select){
                m.select = false;
                m.breakfast = "0";
                m.lunch = "0";
                m.dinner = "0";
                right.add(m);
                selection.add(i);
            }
        }

        len = selection.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No student selected!", "Invalid Selection!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            int index = selection.get(i);
            left.remove(index - i);
        }
        len=left.size();
        if(len>1)   Collections.sort(left, new CustomComparator());
        len=right.size();
        if(len>1)   Collections.sort(right, new CustomComparator());
        
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        selectedLeft = -1;
        selectedRight = -1;
        refresh();
        
        len = left.size();
        for(int i=0; i<len; i++){
            Object o[] = left.get(i).copy();
            tmLeft.addRow(o);
        }
        
        len = right.size();
        for(int i=0; i<len; i++){
            Object o[] = right.get(i).copy2();
            tmRight.addRow(o);
        }
        totalCalculator();
    }//GEN-LAST:event_offSelectedActionPerformed

    private void rightTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightTableMouseClicked
        int selectedRow = rightTable.getSelectedRow();
        int selectedColumn = rightTable.getSelectedColumn();
        
        if(selectedColumn == 0){
            searchBarRight.setText("");
            searchBarRight.requestFocusInWindow();
            boolean b = right.get(selectedRow).select;
            if(b){
                tmRight.setValueAt(false, selectedRow, 0);
                right.get(selectedRow).select = false;
            }
            else{
                tmRight.setValueAt(true, selectedRow, 0);
                right.get(selectedRow).select = true;
            }
            click();
        }
    }//GEN-LAST:event_rightTableMouseClicked

    private void onSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSelectedActionPerformed
        int len = right.size();
        countRight.setText("0");
        ArrayList <Integer> selection = new ArrayList<>();
        for(int i=0; i<len; i++){
            MealStatus m = right.get(i);
            if(m.select){    
                m.select = false;
                m.breakfast = "1";
                m.lunch = "1";
                m.dinner = "1";
                left.add(m);
                selection.add(i);
            }
        }
        len = selection.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "No student selected!", "Invalid Selection!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            int index = selection.get(i);
            right.remove(index - i);
        }
        
        selectedLeft = -1;
        selectedRight = -1;
        len=left.size();
        if(len>1)   Collections.sort(left, new CustomComparator());
        len=right.size();
        if(len>1)   Collections.sort(right, new CustomComparator());
        refresh();
        
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        len = left.size();
        for(int i=0; i<len; i++){
            Object o[] = left.get(i).copy();
            tmLeft.addRow(o);
        }
        
        len = right.size();
        for(int i=0; i<len; i++){
            Object o[]= right.get(i).copy2();
            tmRight.addRow(o);
        }
        totalCalculator();
    }//GEN-LAST:event_onSelectedActionPerformed

    private void searchRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRightActionPerformed
        String hallId = searchBarRight.getText().toString().trim();
        int HallId =0;
        searchBarRight.setText("");
        
        try{
            HallId = Integer.parseInt(hallId);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hall ID must be a number!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            //searchBarLeft.setText("");
            searchBarRight.requestFocusInWindow();
            return;
        }
        
        int len = right.size();
        for(int i=0; i<len; i++){
            MealStatus m = right.get(i);
            if(m.HallId == HallId){
                rightTable.getSelectionModel().setSelectionInterval(i, i);
                rightTable.scrollRectToVisible(new Rectangle(rightTable.getCellRect(i, 0, true)));
                boolean b = m.select;
                right.get(i).select = !b;
                tmRight.setValueAt(!b, i, 0);
                click();
                searchBarRight.requestFocusInWindow();
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "There is no such Hall ID in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
        //searchBarLeft.setText("");
        searchBarRight.requestFocusInWindow();
        return;
    }//GEN-LAST:event_searchRightActionPerformed

    private void check(){
        int len = left.size();
        ArrayList <Integer> selection = new ArrayList<>();
        for(int i=0; i<len; i++){
            MealStatus m = left.get(i);
            if(m.breakfast.equals("0") && m.lunch.equals("0") && m.dinner.equals("0")){
                selection.add(i);
                right.add(m);
            }
        }
        
        
        len = selection.size();
        for(int i=0; i<len; i++){
            int index = selection.get(i);
            left.remove(index - i);
        }
        
        len = left.size();
        if(len>1)  Collections.sort(left,new CustomComparator());
        len = right.size();
        if(len>1)  Collections.sort(right, new CustomComparator());
        
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        len = left.size();
        for(int i=0; i<len; i++){
            Object o[] = left.get(i).copy();
            tmLeft.addRow(o);
        }
        
        len = right.size();
        for(int i=0; i<len; i++){
            Object o[] = right.get(i).copy2();
            tmRight.addRow(o);
        }
        
        totalCalculator();
    }
    
    
    private void offAllBreakfastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offAllBreakfastActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to off all students' breakfast?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        selectedLeft = -1;
        leftTable.getSelectionModel().clearSelection();
        refresh();
        
        int len = left.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "All breakfast is already off!", "Alert!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            left.get(i).breakfast = "0";
        }
        
        check();
    }//GEN-LAST:event_offAllBreakfastActionPerformed

    private void offAllLunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offAllLunchActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to off all students' lunch?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        selectedLeft = -1;
        leftTable.getSelectionModel().clearSelection();
        refresh();
        
        int len = left.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "All lunch is already off!", "Alert!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            left.get(i).lunch = "0";
        }
        
        check();
    }//GEN-LAST:event_offAllLunchActionPerformed

    private void offAllDinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offAllDinnerActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to off all students' dinner?" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        selectedLeft = -1;
        leftTable.getSelectionModel().clearSelection();
        refresh();
        
        int len = left.size();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "All dinner is already off!", "Alert!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            left.get(i).dinner = "0";
        }
        
        check();
    }//GEN-LAST:event_offAllDinnerActionPerformed

    private void searchBarLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarLeftActionPerformed
        searchLeft.doClick();
    }//GEN-LAST:event_searchBarLeftActionPerformed

    private void searchBarRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarRightActionPerformed
        searchRight.doClick();
    }//GEN-LAST:event_searchBarRightActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        searchBarLeft.requestFocusInWindow();
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        countLeft.setText("0");
        countRight.setText("0");
        
        PREVIOUS = 0;
        int lenx = left.size();
        for(int i=0; i<lenx; i++)    left.remove(0);
        lenx = right.size();
        for(int i=0; i<lenx; i++)    right.remove(0);
        
        int chk = databaseParade();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(DATESAVED); 
            Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
            String DATE = formatter.format(date);
            monthYear.setText(DATE);
        }catch(Exception e){}
        if(chk!=0)  {
            totalCalculator();
            return;
        }
        
        
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,name FROM stuinfo");
            rs = psmt.executeQuery();
            
            while(rs.next()){
                Object o[] = {false, rs.getInt(1) + "", rs.getString(2), rs.getString(3), "1", "1","1" };
                MealStatus m = new MealStatus(false, rs.getInt(1) + "", rs.getString(2), rs.getString(3), "1", "1","1");
                //tmLeft.addRow(o);
                left.add(m);
            }
            
            Collections.sort(left,new CustomComparator());
            int len = left.size();
            /*for(int i=0; i<len; i++){
                MealStatus m = left.get(i);
                //System.out.println(m.HallId +" " + m.name);
            }
            */
            for(int i=0; i<len; i++){
                Object o[] = left.get(i).copy();
                tmLeft.addRow(o);
            }
            
            psmt.close();
            rs.close();
            totalCalculator();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
    }//GEN-LAST:event_okBtnActionPerformed

    
    
    
    private void okAction(){
        tmLeft.setRowCount(0);
        tmRight.setRowCount(0);
        
        PREVIOUS = 0;
        int lenx = left.size();
        for(int i=0; i<lenx; i++)    left.remove(0);
        lenx = right.size();
        for(int i=0; i<lenx; i++)    right.remove(0);
        
        int chk = databaseParade();
        if(chk!=0)  {
            totalCalculator();
            return;
        }
        
        
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,name FROM stuinfo");
            rs = psmt.executeQuery();
            
            while(rs.next()){
                Object o[] = {false, rs.getInt(1) + "", rs.getString(2), rs.getString(3), "1", "1","1" };
                MealStatus m = new MealStatus(false, rs.getInt(1) + "", rs.getString(2), rs.getString(3), "1", "1","1");
                //tmLeft.addRow(o);
                left.add(m);
            }
            
            Collections.sort(left,new CustomComparator());
            int len = left.size();
            /*for(int i=0; i<len; i++){
                MealStatus m = left.get(i);
                //System.out.println(m.HallId +" " + m.name);
            }
            */
            for(int i=0; i<len; i++){
                Object o[] = left.get(i).copy();
                tmLeft.addRow(o);
            }
            
            psmt.close();
            rs.close();
            totalCalculator();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    
    
    private void breakfastUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breakfastUpdateActionPerformed
        lunchUpdate.requestFocusInWindow();
    }//GEN-LAST:event_breakfastUpdateActionPerformed

    private void lunchUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchUpdateActionPerformed
        dinnerUpdate.requestFocusInWindow();
    }//GEN-LAST:event_lunchUpdateActionPerformed

    private void dinnerUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dinnerUpdateActionPerformed
        updateBtn.doClick();
    }//GEN-LAST:event_dinnerUpdateActionPerformed

    private void dateChooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooserMouseClicked
        
    }//GEN-LAST:event_dateChooserMouseClicked

    private void dateChooserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooserMousePressed
        
    }//GEN-LAST:event_dateChooserMousePressed

    private void dateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserPropertyChange
        //if(SEARCHCLICKED==1)    okAction();
    }//GEN-LAST:event_dateChooserPropertyChange

    private void dateChooserVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_dateChooserVetoableChange
        //System.out.println("HELLO");
    }//GEN-LAST:event_dateChooserVetoableChange

    private void dateChooserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooserMouseEntered
        //System.out.println("Hello");
    }//GEN-LAST:event_dateChooserMouseEntered

    private void guestChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestChkActionPerformed
        searchBarLeft.requestFocusInWindow();
    }//GEN-LAST:event_guestChkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MealorderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MealorderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MealorderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MealorderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(() -> {
            new MealorderPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField breakfastUpdate;
    private javax.swing.JButton btnOff;
    private javax.swing.JButton btnOn;
    private javax.swing.JLabel countLeft;
    private javax.swing.JLabel countRight;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField dinnerUpdate;
    private javax.swing.JCheckBox guestChk;
    private javax.swing.JLabel hallIdUpdate;
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
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable leftTable;
    private javax.swing.JTextField lunchUpdate;
    private javax.swing.JLabel monthYear;
    private javax.swing.JLabel nameUpdate;
    private javax.swing.JButton offAllBreakfast;
    private javax.swing.JButton offAllDinner;
    private javax.swing.JButton offAllLunch;
    private javax.swing.JLabel offPerson;
    private javax.swing.JButton offSelected;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel onPerson;
    private javax.swing.JButton onSelected;
    private javax.swing.JTable rightTable;
    private javax.swing.JButton saveAndExit;
    private javax.swing.JTextField searchBarLeft;
    private javax.swing.JTextField searchBarRight;
    private javax.swing.JButton searchLeft;
    private javax.swing.JButton searchRight;
    private javax.swing.JLabel totalBreakfast;
    private javax.swing.JLabel totalDinner;
    private javax.swing.JLabel totalLunch;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}






class MealStatus{
    boolean select;
    String room,name,breakfast,lunch,dinner;
    int HallId;
    
    public MealStatus(boolean select, String hallId, String room, String name, String breakfast, String lunch, String dinner){
        this.select = select;
        this.HallId = Integer.parseInt(hallId.trim());
        this.room = room;
        this.name = name;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
    
    public Object[] copy(){
        Object o[] = {select,HallId+"",room,name,breakfast,lunch,dinner};
        return o;
    }
    
    public Object[] copy2(){
        Object o[] = {select,HallId+"",room,name};
        return o;
    }
}




class CustomComparator implements Comparator<MealStatus> {
    @Override
    public int compare(MealStatus o1, MealStatus o2) {
        if(o2.HallId < o1.HallId)   return 1;
        return -1;
    }
}




