package mess.manager;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

public class PrintBill extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    Document doc;
    
    
    private void init(){
        conn = JConnection.ConnecrDb();
        
        
        this.setTitle("PRINT BILL");
        psmt = null;
        rs = null;
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
        
        
        String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int i=0; i<12; i++) monthCmb.addItem(months[i]);
        monthYearTxt.setText("");
        
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
    
    
    
    
    public PrintBill() {
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
        searchBtn = new javax.swing.JButton();
        createPdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        monthYearTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("MONTH");

        monthCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("YEAR");

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        createPdf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createPdf.setText("CREATE   PDF");
        createPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPdfActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SL", "ROOM NO", "HALL ID", "ROLL NO", "NAME", "MESS BILL", "OTHERS", "SPECIAL", "PREVIOUS DUE", "ADVANCE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        monthYearTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        monthYearTxt.setText("JANUARY, 2019");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createPdf, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(monthCmb, 0, 134, Short.MAX_VALUE)
                            .addComponent(yearTxt))))
                .addGap(18, 18, 18)
                .addComponent(searchBtn)
                .addGap(200, 200, 200)
                .addComponent(monthYearTxt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(monthYearTxt))
                .addGap(18, 18, 18)
                .addComponent(createPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
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

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        tm.setRowCount(0);
        monthYearTxt.setText("");
        
        ArrayList <TableContents> tableContents = new ArrayList<>();
        String month = monthCmb.getSelectedItem().toString().trim();
        String year = yearTxt.getText().toString().trim();
        int y = 0;
        
        try{
            y = Integer.parseInt(year);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter a valid year!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if( y<2000 || y >3000){
            JOptionPane.showMessageDialog(null, "Enter a valid year!", "Invalid!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int len = 0;
        try{
            psmt = conn.prepareStatement("SELECT roomno,hallid,roll,name,mealcharge,other,special,previous FROM messbill JOIN stuinfo USING(hallid) WHERE year = ? AND month = ?");
            psmt.setString(1, year);
            psmt.setString(2, month);
            rs = psmt.executeQuery();
            
            while(rs.next()){
                TableContents t = new TableContents(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9));
                tableContents.add(t);
                len++;
            }
            
            psmt.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(len == 0){
            JOptionPane.showMessageDialog(null, "Bill of " + month + ", " + year+ " is not created!", "Bill Not Created!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        tm.setRowCount(0);
        for(int i=0; i<len; i++){
            TableContents t = tableContents.get(i);
            Object o [] = t.copy();
            tm.addRow(o);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    
    
    
    
    
    
    
    
    private void createPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPdfActionPerformed
        
    }//GEN-LAST:event_createPdfActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new PrintBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPdf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthYearTxt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}





class TableContents{
    int serial;
    String roomno;
    int hallId;
    String rollno,name,dept;
    public Double messbill,other,special,previousdue,total;

    public TableContents(String roomno, int hallId, String rollno, String name, String dept, Double messbill, Double other, Double special, Double previousdue) {
        this.roomno = roomno;
        this.hallId = hallId;
        this.rollno = rollno;
        this.name = name;
        this.messbill = messbill;
        this.other = other;
        this.special = special;
        this.previousdue = previousdue;
        this.total = java.lang.Math.ceil(messbill + other + special + previousdue);
        this.dept = dept;
        this.serial = -1;
    }
    
    public Object[] copy(){
        double prev = 0.0, advance = 0.0;
        if(previousdue >= 0.0) prev = previousdue;
        else    advance = -previousdue;
        
        String TOTAL = "";
        if(total >= 0.0)    TOTAL = TOTAL + total + "";
        else    TOTAL = TOTAL + "("+ (-total) + ")";
        Object o[] = {serial + "",roomno,hallId+"",rollno,name,dept,messbill+"",other+"",special+"",prev+"",advance+"",TOTAL};
        return o;
    }
    
}