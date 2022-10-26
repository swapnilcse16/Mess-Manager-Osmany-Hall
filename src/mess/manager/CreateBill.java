package mess.manager;

import java.awt.Font;
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

public class CreateBill extends javax.swing.JFrame {

    String months[]={"None","January","February","March","April","May","June","July","August","September","October","November","December"};
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    DefaultTableModel tm = null;
    int selectedRow;
    String m,y;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    
    
    
    
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        this.setTitle("CREATE BILL");
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        bar.setVisible(true);
        bar.setStringPainted(true);
        bar.setValue(0);
        for(int i=0; i<13;i++){
            String m = months[i];
            monthCmb.addItem(m);
        }
        messBillTxt.setEditable(false);
        previousDueTxt.setEditable(false);
        advanceTxt.setEditable(false);
        nameTxt.setEditable(false);
        roomTxt.setEditable(false);
        totalTxt.setEditable(false);
        
        m = "None";
        y = "";
        this.setResizable(false);
        
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
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
        grandTotal1.setEditable(false);
        totalMessbill1.setEditable(false);
        totalOtherBill1.setEditable(false);
        totalSpecialBill1.setEditable(false);
        
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
    
    private void refreshRight(){
        nameLbl.setText("NAME:");
        roomLbl.setText("ROOM:");
        totalLbl.setText("TOTAL:");
        
        nameTxt.setText("");
        roomTxt.setText("");
        totalTxt.setText("");
        
        messBillTxt.setText("");
        othersChk.setSelected(false);
        othersTxt.setText("");
        specialTxt.setText("");
        
        previousDueTxt.setText("");
        advanceTxt.setText("");
        
        selectedRow = -1;
        table.getSelectionModel().clearSelection();
    }
    
    private void refresh(){
        tm.setRowCount(0);
        monthYearTxt.setText("");
        hallIdChk.setSelected(true);
        rollChk.setSelected(false);
        idTxt.setText("");
        
        refreshRight();
    }
    
    
    private void grandCalculator(){
        Double m = 0.0,o = 0.0, s =0.0, g = 0.0;
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            m += Double.parseDouble(tm.getValueAt(i, 4).toString());
            o += Double.parseDouble(tm.getValueAt(i, 5).toString());
            s += Double.parseDouble(tm.getValueAt(i, 6).toString());
            g += Double.parseDouble(tm.getValueAt(i, 9).toString());
        }
        
        totalMessbill1.setText(Double.valueOf(df.format(m)) + "");
        totalOtherBill1.setText(Double.valueOf(df.format(o)) + "");
        totalSpecialBill1.setText(Double.valueOf(df.format(s)) + "");
        grandTotal1.setText(Double.valueOf(df.format(g)) + "");
    }
    
    
    public CreateBill() {
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
        jLabel1 = new javax.swing.JLabel();
        monthCmb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yearTxt = new javax.swing.JTextField();
        idTxt = new javax.swing.JTextField();
        showBillBtn = new javax.swing.JButton();
        hallIdChk = new javax.swing.JCheckBox();
        rollChk = new javax.swing.JCheckBox();
        searchBtn = new javax.swing.JButton();
        nameLbl = new javax.swing.JLabel();
        roomLbl = new javax.swing.JLabel();
        mBillLbl = new javax.swing.JLabel();
        messBillTxt = new javax.swing.JTextField();
        othersTxt = new javax.swing.JTextField();
        specialTxt = new javax.swing.JTextField();
        specialLbl = new javax.swing.JLabel();
        prevLbl = new javax.swing.JLabel();
        previousDueTxt = new javax.swing.JTextField();
        advanceLbl = new javax.swing.JLabel();
        advanceTxt = new javax.swing.JTextField();
        totalLbl = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        othersChk = new javax.swing.JCheckBox();
        saveExitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        monthYearTxt = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();
        nameTxt = new javax.swing.JTextField();
        roomTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();
        totalMessbill = new javax.swing.JLabel();
        totalOtherBill = new javax.swing.JLabel();
        totalSpecialBill = new javax.swing.JLabel();
        grandTotalLbl = new javax.swing.JLabel();
        totalMessbill1 = new javax.swing.JTextField();
        totalSpecialBill1 = new javax.swing.JTextField();
        totalOtherBill1 = new javax.swing.JTextField();
        grandTotal1 = new javax.swing.JTextField();
        startDate = new com.toedter.calendar.JDateChooser();
        endDate = new com.toedter.calendar.JDateChooser();
        showBillByDate = new javax.swing.JButton();
        start = new javax.swing.JLabel();
        end = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        idTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        showBillBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        showBillBtn.setText("SHOW BILL");
        showBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBillBtnActionPerformed(evt);
            }
        });

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

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        nameLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameLbl.setText("NAME");

        roomLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        roomLbl.setText("ROOM");

        mBillLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        mBillLbl.setText("MESS BILL");

        messBillTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        othersTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        othersTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersTxtActionPerformed(evt);
            }
        });

        specialTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        specialTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialTxtActionPerformed(evt);
            }
        });

        specialLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        specialLbl.setText("SPECIAL");

        prevLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        prevLbl.setText("PREVIOUS DUE");

        previousDueTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        advanceLbl.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        advanceLbl.setText("ADVANCE");

        advanceTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        totalLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        totalLbl.setText("TOTAL");

        updateBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        othersChk.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        othersChk.setText("OTHERS");

        saveExitBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        saveExitBtn.setText("SAVE  &  EXIT");
        saveExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveExitBtnActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hall ID", "Roll", "Room", "Name", "Meal Charge", "Others", "Special", "Previous Due", "Advance", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        monthYearTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        monthYearTxt.setText("NAME");

        nameTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        roomTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        totalTxt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        totalMessbill.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalMessbill.setText("TOTAL MESSBILL:");

        totalOtherBill.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalOtherBill.setText("TOTAL OTHER BILL:");

        totalSpecialBill.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalSpecialBill.setText("TOTAL SPECIAL BILL:");

        grandTotalLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        grandTotalLbl.setText("GRAND TOTAL:");

        totalMessbill1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        totalSpecialBill1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        totalOtherBill1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        grandTotal1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        startDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startDateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startDateMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startDateMousePressed(evt);
            }
        });
        startDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                startDatePropertyChange(evt);
            }
        });
        startDate.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                startDateVetoableChange(evt);
            }
        });

        endDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endDateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                endDateMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                endDateMousePressed(evt);
            }
        });
        endDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                endDatePropertyChange(evt);
            }
        });
        endDate.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                endDateVetoableChange(evt);
            }
        });

        showBillByDate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        showBillByDate.setText("SHOW BILL");
        showBillByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBillByDateActionPerformed(evt);
            }
        });

        start.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        start.setText("FROM");

        end.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        end.setText("TO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(showBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(31, 31, 31)
                                .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(hallIdChk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(rollChk))
                            .addComponent(idTxt)
                            .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(monthYearTxt)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grandTotalLbl)
                            .addComponent(start)
                            .addComponent(end))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grandTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(showBillByDate, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(mBillLbl)
                                .addComponent(prevLbl))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(messBillTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(previousDueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(nameLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nameTxt)))
                    .addComponent(saveExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(othersChk)
                                    .addComponent(advanceLbl))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(othersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(advanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(roomLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(specialLbl)
                                    .addComponent(totalLbl))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(specialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalOtherBill)
                            .addComponent(totalSpecialBill)
                            .addComponent(totalMessbill))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalSpecialBill1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(totalOtherBill1)
                            .addComponent(totalMessbill1))))
                .addGap(82, 82, 82))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hallIdChk)
                    .addComponent(rollChk)
                    .addComponent(nameLbl)
                    .addComponent(roomLbl)
                    .addComponent(totalLbl)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mBillLbl)
                    .addComponent(specialLbl)
                    .addComponent(specialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(othersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messBillTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(othersChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showBillBtn)
                    .addComponent(searchBtn)
                    .addComponent(prevLbl)
                    .addComponent(previousDueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(advanceLbl)
                    .addComponent(advanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(monthYearTxt)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grandTotalLbl)
                            .addComponent(grandTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(start))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(end))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showBillByDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalMessbill)
                            .addComponent(totalMessbill1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalOtherBill)
                            .addComponent(totalOtherBill1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalSpecialBill1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalSpecialBill))
                                .addGap(134, 134, 134))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(saveExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void showBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBillBtnActionPerformed
        String month = monthCmb.getSelectedItem().toString().trim();
        String year = yearTxt.getText().toString().trim();
        refresh();
        //visibility(true);
        
        if(month.equals("None")){
            JOptionPane.showMessageDialog(null, "No month is selected!", "Select A Month!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            int d = Integer.parseInt(year);
            if(d < 2000){
                JOptionPane.showMessageDialog(null, "Please enter a valid year!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a valid year!", "Invalid Year!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        try{
            psmt = conn.prepareStatement("SELECT * FROM messbill WHERE year = ? AND month = ?");
            psmt.setString(1,year);
            psmt.setString(2, month);
            rs = psmt.executeQuery();
            int c = 0;
            
            while(rs.next()){
                c++;
                if(c==1)    break;
            }
            
            if(c!=0){
                JOptionPane.showMessageDialog(null, "The Bill Has Already Been Created For " + month +","+year, "Invalid!", JOptionPane.ERROR_MESSAGE);
                psmt.close();
                rs.close();
                return;
            }
            
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList <Integer> hallIds = new ArrayList<>();
        ArrayList <Double> dues = new ArrayList<>();
        ArrayList <String> room = new ArrayList<>();
        ArrayList <String> roll = new ArrayList<>();
        ArrayList <String> name = new ArrayList<>();
        
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,roll,name FROM stuinfo");
            rs = psmt.executeQuery();
            while(rs.next()){
                hallIds.add(rs.getInt(1));
                room.add(rs.getString(2));
                roll.add(rs.getString(3));
                name.add(rs.getString(4));
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = hallIds.size();
        int interval = len/100;
        if(interval == 0)   interval = 1;
        
        try{
            for(int i=0; i<len; i++){
                int h = hallIds.get(i);
                psmt = conn.prepareStatement("SELECT totaldue FROM totalbill WHERE hallid = ?");
                psmt.setInt(1,h);
                
                rs = psmt.executeQuery();
                if(rs.next())   dues.add(rs.getDouble(1));
                
                psmt.close();
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        monthYearTxt.setText(month + "," + year);
        for(int i=0; i<len; i++){
            int hallId = hallIds.get(i);
            Double due = dues.get(i);
            String r = roll.get(i);
            String roomno = room.get(i);
            String n = name.get(i);
            BillManager b = new BillManager(month,year,hallId);
            Double mealCharge[] = totalBillCalculator(b.showBill(),month,year,hallId);
            
            if(i%interval==0){
                bar.setVisible(true);
                if(i/interval == 100)   bar.setValue(99);
                else    bar.setValue(i/interval);
                bar.update(bar.getGraphics());
            }
                
            if(i==len-1){
                    //psmt.executeBatch();
                bar.setVisible(true);
                bar.setValue(100);
                bar.update(bar.getGraphics());
            }
            
            if(due >=0){
                Object o[] = {hallId + "",r,roomno,n,mealCharge[0]+"","0.0",mealCharge[1] + "",due+"","0.0",java.lang.Math.ceil(mealCharge[0]+ mealCharge[1] +due)+""};
                tm.addRow(o);
            }
            
            else{
                Object o[] = {hallId + "",r,roomno,n,mealCharge[0]+"","0.0",mealCharge[1] + "","0.0",(-due)+"",java.lang.Math.ceil(mealCharge[0]+ mealCharge[1] +due)+""};
                tm.addRow(o);
            }
        }
        
        m = month;
        y = year;
        bar.setValue(0);
        Double total = 0.0;
        for(int i=0; i<tm.getRowCount(); i++)   total = total + Double.parseDouble(tm.getValueAt(i,9).toString());
        grandCalculator();
        //System.out.println(total + "");
    }//GEN-LAST:event_showBillBtnActionPerformed

    private void hallIdChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallIdChkActionPerformed
        boolean b = hallIdChk.isSelected();
        if(b)   rollChk.setSelected(false);
        else    rollChk.setSelected(true);
    }//GEN-LAST:event_hallIdChkActionPerformed

    private void rollChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollChkActionPerformed
        boolean b = rollChk.isSelected();
        if(b)   hallIdChk.setSelected(false);
        else    hallIdChk.setSelected(true);
    }//GEN-LAST:event_rollChkActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        int len = tm.getRowCount();
        refreshRight();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "Select a month & year, and then press 'SHOW BILL' button", "Show A Bill First!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = idTxt.getText().toString().trim();
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Hall ID / Roll will not be null!", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean b = hallIdChk.isSelected();
        if(b){
            for(int i=0; i<len; i++){
                String tHallId = tm.getValueAt(i, 0).toString().trim();
                if(tHallId.equals(id)){
                    selectedRow = i;
                    table.getSelectionModel().setSelectionInterval(i, i);
                    table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                    updateSet();
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(null, "The Hall Id doesn't exist in the list!", "Invalid Hall ID!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            String tRoll = tm.getValueAt(i, 1).toString().trim();
            if(tRoll.equals(id)){
                selectedRow = i;
                table.getSelectionModel().setSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
                updateSet();
                return;
            }
        }
        idTxt.requestFocusInWindow();
        JOptionPane.showMessageDialog(null, "The Roll doesn't exist in the list!", "Invalid Roll!", JOptionPane.ERROR_MESSAGE);
        return;
        
    }//GEN-LAST:event_searchBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        selectedRow = table.getSelectedRow();
        updateSet();
        
    }//GEN-LAST:event_tableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        int len = tm.getRowCount();
        if( len == 0){
            JOptionPane.showMessageDialog(null, "Select a month & year, and then press 'SHOW BILL' button", "Show A Bill First!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        if(selectedRow == -1 && othersChk.isSelected()==true){
            String other = othersTxt.getText().toString().trim(); 
            Double Other = 0.0;
            try{
                Other = Double.parseDouble(other);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "This amount must be a number", "Invalid Amount!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(Other < 0.0){
                JOptionPane.showMessageDialog(null, "This amount must be greater or equal to 0!", "Invalid Amount!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            for(int i=0; i<len; i++)    tm.setValueAt(other, i, 5);
            totalCalculator();
            othersChk.setSelected(false);
            othersTxt.setText("");
            grandCalculator();
            return;
        }
        
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Select a row first!", "No Row Selected!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String other = othersTxt.getText().toString().trim();
        String special = specialTxt.getText().toString().trim();
        Double Other = 0.0, Special = 0.0;
        
        try{
            Other = Double.parseDouble(other);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "This amount must be a number", "Invalid Amount!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        try{
            Special = Double.parseDouble(special);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "This amount must be a number", "Invalid Amount!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(Other < 0.0){
            JOptionPane.showMessageDialog(null, "This amount must be greater or equal to 0!", "Invalid Amount!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        tm.setValueAt(other, selectedRow, 5);
        tm.setValueAt(special, selectedRow, 6);
        
        boolean b = othersChk.isSelected();
        if(b){
            for(int i=0; i<len; i++)    tm.setValueAt(other, i, 5);
        }
        
        refreshRight();
        totalCalculator();
        idTxt.requestFocusInWindow();
        grandCalculator();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void saveExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveExitBtnActionPerformed
        int len = tm.getRowCount();
        if(len == 0){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?" , "No Bill Is Created!" , 1);
        
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
        
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure want to create bill of " + m + "," + y + "\nYou will not be able to update it later!" , "Warning" , 1);
        if(dialogResult != JOptionPane.YES_OPTION)  return;
        
        
        
        if(m.equals("None") || m.equals("") || y.equals("")){
            JOptionPane.showMessageDialog(null, "Select a month & year, and then press 'SHOW BILL' button", "Show A Bill First!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int interval = len/100;
        if(interval == 0)   interval = 1;
        
        for(int i=0; i<len; i++){
            
            if(i%interval==0){
                bar.setVisible(true);
                bar.setValue(i/interval);
                bar.update(bar.getGraphics());
            }
                
            if(i==len-1){
                    //psmt.executeBatch();
                bar.setVisible(true);
                bar.setValue(100);
                bar.update(bar.getGraphics());
            }
            
            
            
            String h = tm.getValueAt(i,0).toString().trim();
            String me = tm.getValueAt(i,4).toString().trim();
            String o = tm.getValueAt(i,5).toString().trim();
            String s = tm.getValueAt(i,6).toString().trim();
            String p = tm.getValueAt(i,7).toString().trim();
            String a = tm.getValueAt(i,8).toString().trim();
            String t = tm.getValueAt(i, 9).toString().trim();
            
            Double M = 0.0, O = 0.0, S =0.0, P = 0.0, A = 0.0, T = 0.0;
            Integer H = 0;
            
            try{
                H = Integer.parseInt(h);
                M = Double.parseDouble(me);
                O = Double.parseDouble(o);
                S = Double.parseDouble(s);
                P = Double.parseDouble(p);
                A = Double.parseDouble(a);
                T = Double.parseDouble(t);
                
                if(A!= 0.0)   P = -A;
            }catch(Exception e){}
            
            try{
                psmt = conn.prepareStatement("INSERT INTO `messbill`(`hallid`, `year`, `month`, `mealcharge`, `other`, `special`, `previous`) VALUES(?,?,?,?,?,?,?)");
                psmt.setInt(1,H);
                psmt.setString(2, y);
                psmt.setString(3, m);
                psmt.setDouble(4,M);
                psmt.setDouble(5, O);
                psmt.setDouble(6, S);
                psmt.setDouble(7, P);
                psmt.execute();
                psmt.close();
                
                psmt = conn.prepareStatement("UPDATE `totalbill` SET `totaldue`=? WHERE hallid = ?");
                psmt.setDouble(1, T);
                psmt.setInt(2, H);
                psmt.execute();
                psmt.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }//////////////for loop endsss
        
        try{
            conn.close();
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(null, "Mess bill of " + m + "," + y + " is created successfully!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        
        Menu m1 = new Menu();
        m1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_saveExitBtnActionPerformed

    private void yearTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTxtActionPerformed
        showBillBtn.doClick();
    }//GEN-LAST:event_yearTxtActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        searchBtn.doClick();
        
    }//GEN-LAST:event_idTxtActionPerformed

    private void othersTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersTxtActionPerformed
        updateBtn.doClick();
    }//GEN-LAST:event_othersTxtActionPerformed

    private void specialTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialTxtActionPerformed
        updateBtn.doClick();
    }//GEN-LAST:event_specialTxtActionPerformed

    private void startDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startDateMouseClicked

    }//GEN-LAST:event_startDateMouseClicked

    private void startDateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startDateMouseEntered
        //System.out.println("Hello");
    }//GEN-LAST:event_startDateMouseEntered

    private void startDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startDateMousePressed

    }//GEN-LAST:event_startDateMousePressed

    private void startDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_startDatePropertyChange
        //if(SEARCHCLICKED==1)    okAction();
    }//GEN-LAST:event_startDatePropertyChange

    private void startDateVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_startDateVetoableChange
        //System.out.println("HELLO");
    }//GEN-LAST:event_startDateVetoableChange

    private void endDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endDateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateMouseClicked

    private void endDateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endDateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateMouseEntered

    private void endDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endDateMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateMousePressed

    private void endDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_endDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_endDatePropertyChange

    private void endDateVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_endDateVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateVetoableChange

    private int dateCompare(int a[], int b[]){
        if(a[0] > b[0]) return 1;
        if(a[0] < b[0]) return -1;
        if(a[1] > b[1]) return 1;
        if(a[1] < b[1]) return -1;
        if(a[2] > b[2]) return 1;
        if(a[2] < b[2]) return -1;
        return 0;
    }
    
    private void showBillByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBillByDateActionPerformed
        Date d1 = startDate.getDate();
        Date d2 = endDate.getDate();
        if(d1 == null || d2 == null){
            JOptionPane.showMessageDialog(null, "Select the dates", "Invalid Date Selection!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DATE1 = formatter.format(d1);
        String DATE2 = formatter.format(d2);
        BillManager temp = new BillManager();
        int x = dateCompare(temp.dateExtract(DATE1), temp.dateExtract(DATE2));
        if(x == 1){
            JOptionPane.showMessageDialog(null, "Start Date Must Be Less Than End Date!", "Invalid Date Selection!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        refresh();
        //visibility(true);
        
        
        ArrayList <Integer> hallIds = new ArrayList<>();
        ArrayList <Double> dues = new ArrayList<>();
        ArrayList <String> room = new ArrayList<>();
        ArrayList <String> roll = new ArrayList<>();
        ArrayList <String> name = new ArrayList<>();
        
        try{
            psmt = conn.prepareStatement("SELECT hallid,roomno,roll,name FROM stuinfo");
            rs = psmt.executeQuery();
            while(rs.next()){
                hallIds.add(rs.getInt(1));
                room.add(rs.getString(2));
                roll.add(rs.getString(3));
                name.add(rs.getString(4));
            }
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int len = hallIds.size();
        int interval = len/100;
        if(interval == 0)   interval = 1;
        
        try{
            for(int i=0; i<len; i++){
                int h = hallIds.get(i);
                psmt = conn.prepareStatement("SELECT totaldue FROM totalbill WHERE hallid = ?");
                psmt.setInt(1,h);
                
                rs = psmt.executeQuery();
                if(rs.next())   dues.add(rs.getDouble(1));
                
                psmt.close();
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i=0; i<len; i++){
            int hallId = hallIds.get(i);
            Double due = dues.get(i);
            String r = roll.get(i);
            String roomno = room.get(i);
            String n = name.get(i);
            //BillManager b = new BillManager();
            Double mealCharge[] = totalBillCalculator(temp.showBill(hallId,DATE1, DATE2));
            
            if(i%interval==0){
                bar.setVisible(true);
                if(i/interval == 100)   bar.setValue(99);
                else    bar.setValue(i/interval);
                bar.update(bar.getGraphics());
            }
                
            if(i==len-1){
                    //psmt.executeBatch();
                bar.setVisible(true);
                bar.setValue(100);
                bar.update(bar.getGraphics());
            }
            
            if(due >=0){
                Object o[] = {hallId + "",r,roomno,n,mealCharge[0]+"","0.0",mealCharge[1] + "",due+"","0.0",java.lang.Math.ceil(mealCharge[0]+ mealCharge[1] +due)+""};
                tm.addRow(o);
            }
            
            else{
                Object o[] = {hallId + "",r,roomno,n,mealCharge[0]+"","0.0",mealCharge[1] + "","0.0",(-due)+"",java.lang.Math.ceil(mealCharge[0]+ mealCharge[1] +due)+""};
                tm.addRow(o);
            }
        }
        
        bar.setValue(0);
        Double total = 0.0;
        for(int i=0; i<tm.getRowCount(); i++)   total = total + Double.parseDouble(tm.getValueAt(i,9).toString());
        grandCalculator();
    }//GEN-LAST:event_showBillByDateActionPerformed

    
    
    
    
    private void totalCalculator(){
        int len = tm.getRowCount();
        for(int i=0; i<len; i++){
            String messBill = tm.getValueAt(i,4).toString().trim();
            String other = tm.getValueAt(i,5).toString().trim();
            String special = tm.getValueAt(i,6).toString().trim();
            String previous = tm.getValueAt(i,7).toString().trim();
            String advance = tm.getValueAt(i,8).toString().trim();
            
            try{
                Double MessBill = Double.parseDouble(messBill);
                Double Other = Double.parseDouble(other);
                Double Special = Double.parseDouble(special);
                Double Previous = Double.parseDouble(previous);
                Double Advance = Double.parseDouble(advance);
                Double total = MessBill + Other + Special + Previous - Advance;
                total = java.lang.Math.ceil(total);
                tm.setValueAt(total + "", i, 9);
            }catch(Exception e){}
        }
    }
    
    
    
    
    private void updateSet(){
        int r = selectedRow;
        nameTxt.setText(tm.getValueAt(r, 3).toString().trim().toUpperCase());
        roomTxt.setText(tm.getValueAt(r, 2).toString().trim());
        totalTxt.setText(tm.getValueAt(r, 9).toString().trim());
        messBillTxt.setText(tm.getValueAt(r, 4).toString().trim());
        othersTxt.setText(tm.getValueAt(r, 5).toString().trim());
        specialTxt.setText(tm.getValueAt(r, 6).toString().trim());
        previousDueTxt.setText(tm.getValueAt(r, 7).toString().trim());
        advanceTxt.setText(tm.getValueAt(r, 8).toString().trim());
    }
    
    
    private Double[] totalBillCalculator(ArrayList<MessBill> m, String month, String year, int hallId){
        int len = m.size();
        Double sum = 0.0;
        Double offerSum = 0.0;
        
        for(int i=0; i<len; i++){
            sum = sum + m.get(i).mealCharge;
            offerSum = offerSum + m.get(i).offer;
            //System.out.println(m.get(i).mealCharge + "");
        }
        
        try{
            psmt = conn.prepareStatement("SELECT bill FROM tempfood WHERE month = ? AND year = ? AND hallid = ?");
            psmt.setString(1, month);
            psmt.setString(2, year);
            psmt.setInt(3, hallId);
            rs = psmt.executeQuery();
            while(rs.next())    sum += rs.getDouble(1);
            psmt.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
        }
        
        Double p[] = new Double[2];
        p[0] = Double.valueOf(df.format(sum));
        p[1] = Double.valueOf(df.format(offerSum));
        return p;
    }
    
    private Double[] totalBillCalculator(ArrayList<MessBill> m){
        int len = m.size();
        Double sum = 0.0;
        Double offerSum = 0.0;
        
        for(int i=0; i<len; i++){
            sum = sum + m.get(i).mealCharge;
            offerSum = offerSum + m.get(i).offer;
            //System.out.println(m.get(i).mealCharge + "");
        }
        //System.out.println(offerSum);
        
        Double p[] = new Double[2];
        p[0] = Double.valueOf(df.format(sum));
        p[1] = Double.valueOf(df.format(offerSum));
        return p;
    }
    
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel advanceLbl;
    private javax.swing.JTextField advanceTxt;
    private javax.swing.JProgressBar bar;
    private javax.swing.JLabel end;
    private com.toedter.calendar.JDateChooser endDate;
    private javax.swing.JTextField grandTotal1;
    private javax.swing.JLabel grandTotalLbl;
    private javax.swing.JCheckBox hallIdChk;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mBillLbl;
    private javax.swing.JTextField messBillTxt;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthYearTxt;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JCheckBox othersChk;
    private javax.swing.JTextField othersTxt;
    private javax.swing.JLabel prevLbl;
    private javax.swing.JTextField previousDueTxt;
    private javax.swing.JCheckBox rollChk;
    private javax.swing.JLabel roomLbl;
    private javax.swing.JTextField roomTxt;
    private javax.swing.JButton saveExitBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton showBillBtn;
    private javax.swing.JButton showBillByDate;
    private javax.swing.JLabel specialLbl;
    private javax.swing.JTextField specialTxt;
    private javax.swing.JLabel start;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JLabel totalMessbill;
    private javax.swing.JTextField totalMessbill1;
    private javax.swing.JLabel totalOtherBill;
    private javax.swing.JTextField totalOtherBill1;
    private javax.swing.JLabel totalSpecialBill;
    private javax.swing.JTextField totalSpecialBill1;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}
