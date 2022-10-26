/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mess.manager;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.text.Document;

/**
 *
 * @author bk biswas
 */
public class HeaderFooterPageEvent extends PdfPageEventHelper{
    PdfWriter writer;
    Document document;
    PdfPTable tbl;
    String otherRename;
    String specialRename;
    public HeaderFooterPageEvent(PdfWriter writer, Document document,PdfPTable tbl,String otherRename,String specialRename){
        this.document=document;
        this.otherRename=otherRename;
        this.specialRename=specialRename;
        this.tbl=tbl;
        this.writer=writer;
    }
    public void onStartPage() {
            tbl.addCell(new Phrase("SL",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("ROOM",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("HALL ID",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("ROLL",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("NAME",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("DEPARTMENT",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("MEAL CHARGE",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase(otherRename,FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase(specialRename,FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("PREVIOUS DUE",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("ADVANCE",FontFactory.getFont(FontFactory.COURIER,10)));
            tbl.addCell(new Phrase("TOTAL",FontFactory.getFont(FontFactory.COURIER,10)));
    }

}
