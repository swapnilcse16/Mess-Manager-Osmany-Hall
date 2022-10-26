package mess.manager;

import java.util.Comparator;
import java.util.Collections;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;



public class NewJFrame extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement psmt;
    ResultSet rs;
    DefaultTableModel tm;
    String otherRename, specialRename;
    ArrayList <TableContents> tableContents = new ArrayList<>();
    String MONTH,YEAR;
    DecimalFormat df = new DecimalFormat("####0.00");
    
    private void init(){
        conn = JConnection.ConnecrDb();
        psmt = null;
        rs = null;
        tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        otherRename = "OTHERS";
        specialRename = "SPECIAL";
        MONTH = "";
        YEAR = "";
        refreshTotal();
        this.setTitle("PRINT BILL");
        this.setResizable(false);
        
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
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(150);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
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
    
    
    
    
    public NewJFrame() {
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
        monthYearTxt = new javax.swing.JLabel();
        createPdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        sortByRoom = new javax.swing.JButton();
        sortByDue = new javax.swing.JButton();
        totalMessbill = new javax.swing.JLabel();
        totalOtherBill = new javax.swing.JLabel();
        totalSpecialBill = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("MONTH");

        monthCmb.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        monthCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCmbActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("YEAR");

        yearTxt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        yearTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTxtActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchBtn.setText("SEARCH");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        monthYearTxt.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        monthYearTxt.setText("JANUARY, 2019");

        createPdf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createPdf.setText("CREATE   PDF");
        createPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPdfActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SL", "ROOM NO", "HALL ID", "ROLL NO", "NAME", "DEPARTMENT", "MESS BILL", "OTHERS", "SPECIAL", "PREVIOUS DUE", "ADVANCE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("RENAME \"OTHERS\" COLUMN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setText("RENAME \"SPECIAL\" COLUMN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        sortByRoom.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        sortByRoom.setText("SORT BY ROOM NO");
        sortByRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByRoomActionPerformed(evt);
            }
        });

        sortByDue.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        sortByDue.setText("SORT BY DUE");
        sortByDue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByDueActionPerformed(evt);
            }
        });

        totalMessbill.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        totalMessbill.setText("TOTAL MEALCHARGE: ");

        totalOtherBill.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        totalOtherBill.setText("TOTAL OTHER BILL:");

        totalSpecialBill.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        totalSpecialBill.setText("TOTAL SPECIAL BILL:");

        grandTotal.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        grandTotal.setText("GRAND TOTAL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchBtn)
                                .addGap(328, 328, 328)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalSpecialBill)
                                    .addComponent(totalOtherBill)
                                    .addComponent(totalMessbill)
                                    .addComponent(grandTotal))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(monthYearTxt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sortByRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortByDue, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(monthCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(yearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(createPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthYearTxt))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(totalMessbill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalOtherBill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalSpecialBill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grandTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortByDue)
                    .addComponent(sortByRoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void refreshTotal(){
        grandTotal.setText("");
        totalMessbill.setText("");
        totalOtherBill.setText("");
        totalSpecialBill.setText("");
    }
    
    
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        refreshTotal();
        tm.setRowCount(0);
        monthYearTxt.setText("");
        int length = tableContents.size();
        MONTH = "";
        YEAR = "";
        
        for(int i=0; i<length; i++) tableContents.remove(0);
        
        String month = monthCmb.getSelectedItem().toString().trim();
        String year = yearTxt.getText().toString().trim();
        MONTH = month;
        YEAR = year;
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
        monthYearTxt.setText(month + ", " + year);
        
        Double m = 0.0, o =0.0, s = 0.0, g =0.0;
        try{
            psmt = conn.prepareStatement("SELECT roomno,hallid,roll,name,dept,mealcharge,other,special,previous FROM messbill JOIN stuinfo USING(hallid) WHERE year = ? AND month = ? ");
            psmt.setString(1, year);
            psmt.setString(2, month);
            rs = psmt.executeQuery();

            while(rs.next()){
                TableContents t = new TableContents(rs.getString(1).trim(), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9));
                m += rs.getDouble(6);
                o += rs.getDouble(7);
                s += rs.getDouble(8);
                g = g + java.lang.Math.ceil(rs.getDouble(6) + rs.getDouble(7) + rs.getDouble(8) + rs.getDouble(9));
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
        //System.out.println("Hello");
        grandTotal.setText("GRAND TOTAL: " + Double.valueOf(df.format(g)) + " TAKA");
        totalMessbill.setText("TOTAL MEAL CHARGE: " + Double.valueOf(df.format(m)) + " TAKA");
        totalOtherBill.setText("TOTAL OTHER BILL: " + Double.valueOf(df.format(o)) + " TAKA");
        totalSpecialBill.setText("TOTAL SPECIAL BILL: " + Double.valueOf(df.format(s)) + " TAKA");
        tm.setRowCount(0);
        sortByRoomFunc();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void createPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPdfActionPerformed
        int len = tm.getRowCount();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "First select a bill to print!", "No Bill To Print!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        String path="";
        JFileChooser j=new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        /*int x=j.showSaveDialog(this);
        if(x==JFileChooser.APPROVE_OPTION){
            path=j.getSelectedFile().getPath();
        }*/
        //path="C:\\Users\\RAZ\\hell.pdf";
        //path = "E:\\Presentation\\";
        //path = "E:\\";
        Document doc= new Document(PageSize.A4.rotate());
        

        
    
        try {
            Font font=new Font(Font.SANS_SERIF, 12, Font.BOLD);
            PdfWriter pd=PdfWriter.getInstance(doc, new FileOutputStream(path+MONTH + YEAR + "Messbill.pdf"));
             doc.open();
            Font font1=new Font(Font.SANS_SERIF, 14, Font.BOLD);

            Paragraph pr=new Paragraph("Osmany Hall Mess Bill\nMonth: " + MONTH + ", Year: " + YEAR+"\n\n",FontFactory.getFont(FontFactory.HELVETICA,20));
            pr.setAlignment(Element.ALIGN_CENTER);
            doc.add(pr );
            PdfPTable tbl=new PdfPTable(new float[] {2,3,3,5,10,6,4,4,4,4,4,4});
            tbl.setWidthPercentage(100);
            
            tbl.addCell(new Phrase("SL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("ROOM",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("HALL ID",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("ROLL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("NAME",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("DEPARTMENT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("MEAL CHARGE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase(otherRename,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase(specialRename,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("PREVIOUS DUE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("ADVANCE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            tbl.addCell(new Phrase("TOTAL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            /*tbl.addCell("SL");
            tbl.addCell("ROOM");
            tbl.addCell("HALL ID");
            tbl.addCell("ROLL");
            tbl.addCell("NAME");
            tbl.addCell("MEAL CHARGE");
            tbl.addCell("OTHER");
            tbl.addCell("SPECIAL");
            tbl.addCell("PREVIOUS DUE");
            tbl.addCell("ADVANCE");
            tbl.addCell("TOTAL");*/
            
            int interval = len/100;
            if(interval == 0)   interval = 1;
            int next = 28;
            
            for(int i=0;i<len;i++){
                String m1=tm.getValueAt(i, 0).toString();
                String m2=tm.getValueAt(i, 1).toString();
                String m3=tm.getValueAt(i, 2).toString();
                String m4=tm.getValueAt(i, 3).toString();
                String m5=tm.getValueAt(i, 4).toString();
                String m6=tm.getValueAt(i, 5).toString();
                String m7=tm.getValueAt(i, 6).toString();
                String m8=tm.getValueAt(i, 7).toString();
                String m9=tm.getValueAt(i, 8).toString();
                String m10=tm.getValueAt(i, 9).toString();
                String m11 = tm.getValueAt(i, 10).toString();
                String m12 = tm.getValueAt(i, 11).toString();
       
                //Chunk chunk = new Chunk(m1, largeBold);
                tbl.addCell(new Phrase(weightSetter(m1, 4),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(weightSetter(m2, 8),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(weightSetter(m3, 5),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(weightSetter(m4, 11),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(weightSetter(m5, 20),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(weightSetter(m6, 11),FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(m7,FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(m8,FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(m9,FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(m10,FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD)));
                tbl.addCell(new Phrase(m11,FontFactory.getFont(FontFactory.HELVETICA,10)));
                tbl.addCell(new Phrase(m12,FontFactory.getFont(FontFactory.HELVETICA,10)));
                /*tbl.addCell(m1);
                tbl.addCell(m2);
                tbl.addCell(m3);
                tbl.addCell(m4);
                tbl.addCell(m5);
                tbl.addCell(m6);
                tbl.addCell(m7);
                tbl.addCell(m8);
                tbl.addCell(m9);
                tbl.addCell(m10);
                tbl.addCell(m11);*/
                //System.out.println(pd.getPageNumber() + "");
                
                if(i == next){
                    tbl.addCell(new Phrase("SL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("ROOM",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("HALL ID",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("ROLL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("NAME",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("DEPARTMENT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("MEAL CHARGE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase(otherRename,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase(specialRename,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("PREVIOUS DUE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("ADVANCE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    tbl.addCell(new Phrase("TOTAL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
                    next = next + 35;
                }
                
            }
            doc.add(tbl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
       doc.close();
       JOptionPane.showMessageDialog(null, "Pdf is created successfully!", "COMPLETED!", JOptionPane.INFORMATION_MESSAGE); 
        
        
    }//GEN-LAST:event_createPdfActionPerformed

    
    
    private String weightSetter(String string, int weight){
        String s = "";
        int len = weight;
        if(weight > string.length())    len = string.length();
        for(int i=0; i<len; i++) s = s+ string.charAt(i);
        if(string.length() > weight)    s = s + "..";
        return s;
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String x = null;
        x = JOptionPane.showInputDialog("Rename 'OTHERS'");
        if(x==null) return;
        
        x = x.trim();
        if(x.equals("")) return;
        otherRename = x;
        changeColumnName(otherRename, 7);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String x = null;
        x = JOptionPane.showInputDialog("Rename 'SPECIAL'");
        if(x==null) return;
        
        x = x.trim();
        if(x.equals(""))    return;
        specialRename = x;
        changeColumnName(specialRename, 8);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    
    
    
    private void sortByRoomFunc(){
        int len = tableContents.size();
        tm.setRowCount(0);
        try{
            Collections.sort(tableContents, new CustomComparatorRooms());
        }catch(Exception e){
            System.out.println("Problem");
        }
        reArrange();
        //reverse(tableContents);
        serialMaker(tableContents);
        
        for(int i=0; i<len; i++){
            Object o[] = tableContents.get(i).copy();
            tm.addRow(o);
        }
    }
    
    
    
    
    
    private void reArrange(){
        ArrayList<TableContents> number = new ArrayList<>();
        ArrayList<TableContents> string = new ArrayList<>();
        ArrayList<TableContents> NULL = new ArrayList<>();
        int len = tableContents.size();
        
        for(int i=0; i<len; i++){
            int flag = 0;
            TableContents t = tableContents.get(0);
            try{
                Integer.parseInt(t.roomno);
                flag = 1;
            }catch(Exception e){
                
            }
            
            if(flag == 1)   number.add(t);
            else if(t.roomno.equals(""))    NULL.add(t);
            else    string.add(t);
            
            tableContents.remove(0);
        }
        
        Collections.sort(number, new CustomComparatorIntegerRoom());
        len = number.size();
        for(int i=0; i<len; i++)    tableContents.add(number.get(i));
        len = string.size();
        for(int i=0; i<len; i++)    tableContents.add(string.get(i));
        len = NULL.size();
        for(int i=0; i<len; i++)    tableContents.add(NULL.get(i));
    }
    
    
    
    
    
    
    
    
    
    private void sortByRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByRoomActionPerformed
        int len = tm.getRowCount();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "First search for a month & year!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        sortByRoomFunc();
    }//GEN-LAST:event_sortByRoomActionPerformed

    
    private void reverse(ArrayList<TableContents> t){
        int len = t.size();
        ArrayList <TableContents> temp = new ArrayList<>();
        
        for(int i=0; i<len; i++)    temp.add(t.get(i));
        for(int i=len-1,j=0; i>=0; i--,j++) t.set(j, temp.get(i));
    }
    
    private void serialMaker(ArrayList<TableContents> t){
        int len = t.size();
        for(int i=1; i<=len; i++)   t.get(i-1).serial = i;
    }
    
    
    
    
    
    
    
    
    
    private void sortByDueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByDueActionPerformed
        int len = tm.getRowCount();
        if(len == 0){
            JOptionPane.showMessageDialog(null, "First search for a month & year!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        tm.setRowCount(0);
        Collections.sort(tableContents, new CustomComparatorDues());
        //reverse(tableContents);
        serialMaker(tableContents);
        
        for(int i=0; i<len; i++){
            Object o[] = tableContents.get(i).copy();
            tm.addRow(o);
        }
    }//GEN-LAST:event_sortByDueActionPerformed

    private void yearTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTxtActionPerformed
        searchBtn.doClick();
    }//GEN-LAST:event_yearTxtActionPerformed

    private void monthCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCmbActionPerformed
        yearTxt.requestFocusInWindow();
    }//GEN-LAST:event_monthCmbActionPerformed

    
    
    
    
    
    
    
    private void changeColumnName(String header, int columnNo){
        JTableHeader head= table.getTableHeader();
        TableColumnModel colMod = head.getColumnModel();
        TableColumn tabCol = colMod.getColumn(columnNo);
        tabCol.setHeaderValue(header);
        head.repaint();
    }
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
}
        });
    }

    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPdf;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> monthCmb;
    private javax.swing.JLabel monthYearTxt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton sortByDue;
    private javax.swing.JButton sortByRoom;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalMessbill;
    private javax.swing.JLabel totalOtherBill;
    private javax.swing.JLabel totalSpecialBill;
    private javax.swing.JTextField yearTxt;
    // End of variables declaration//GEN-END:variables
}




/*class CustomComparatorRoom implements Comparator<TableContents> {
    @Override
    public int compare(TableContents r1, TableContents r2) {
        String room1 = r1.roomno.toUpperCase().trim();
        String room2 = r2.roomno.toUpperCase().trim();
        
        if(room1.equals("") ){
            return -1;
        }
        else if(room2.equals("") ){
            return 1;
        }
        else if(room1.charAt(0)>='0' && room1.charAt(0)<='9' && room2.charAt(0)>='0' && room2.charAt(0)<='9'){
            int ro1=Integer.parseInt(room1);
            int ro2=Integer.parseInt(room2);
            if(ro1>ro2)return -1;
            else return 1;
        }
        else if(room1.charAt(0)>='0' && room1.charAt(0)<='9' && room2.charAt(0)<='0' && room2.charAt(0)>='9'){
            return -1;
        }
        else if(room1.charAt(0)<='0' && room1.charAt(0)>='9' && room2.charAt(0)>='0' && room2.charAt(0)<='9'){
            return 1;
        }
        else{
            return room2.compareTo(room1);
        }
        //ascending order

   //descending order
   //return StudentName2.compareTo(StudentName1);
   } 
}*/







class CustomComparatorRooms implements Comparator<TableContents> {
    @Override
    public int compare(TableContents r1, TableContents r2) {
        String room1 = r1.roomno.toUpperCase().trim();
        String room2 = r2.roomno.toUpperCase().trim();
        
        if(room1.equals("") ){
            return -1;
        }
        else if(room2.equals("") ){
            return -1;
        }
        else{
            return room2.compareTo(room1);
        }
        //ascending order

   //descending order
   //return StudentName2.compareTo(StudentName1);
   } 
}







class CustomComparatorDues implements Comparator<TableContents> {
    
    public int compare(TableContents o1, TableContents o2) {
        
        if( Double.compare(o2.total,o1.total) > 0 )   return 1;
        return -1;
    }   
}






class CustomComparatorIntegerRoom implements Comparator<TableContents> {
    
    public int compare(TableContents o1, TableContents o2) {
        
        if( Integer.parseInt(o2.roomno) >= Integer.parseInt(o1.roomno) )   return 1;
        return -1;
    }   
}

