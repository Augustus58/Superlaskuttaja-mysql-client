/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.logiikka;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import static com.itextpdf.text.Rectangle.BOTTOM;
import static com.itextpdf.text.Rectangle.LEFT;
import static com.itextpdf.text.Rectangle.RIGHT;
import static com.itextpdf.text.Rectangle.TOP;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;

/**
 *
 * @author Augustus58
 */
public class PdfExtractor {

    private final DataDeliver lataaja;
    private final JFileChooser chooser;
    private final DateFormat pvmFormaatti1;
    private final DateFormat pvmFormaatti2;
    private final DateFormat pvmFormaatti3;
    private final DateFormat pvmFormaatti4;

    public PdfExtractor(DataDeliver lataaja) {
        this.lataaja = lataaja;
        this.chooser = new JFileChooser();
        this.pvmFormaatti1 = new SimpleDateFormat("dd.MM.yyyy");
        this.pvmFormaatti2 = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        this.pvmFormaatti3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.pvmFormaatti4 = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void vieLaskuPdf(String laskunNumero) {
        try {
            String polku = "";
            String viiteTarkisteella;

            ResultSet rs = lataaja.getDbc().executeQuery("select distinct viiteTarkisteella\n"
                    + "from Lasku, Pankkiviivakoodi\n"
                    + "where laskunNumero = " + laskunNumero + "\n"
                    + "and Lasku.pankkiviivakoodi = Pankkiviivakoodi.pankkiviivakoodi\n"
                    + "");
            rs.first();
            viiteTarkisteella = rs.getString(1);

            rs = lataaja.getDbc().executeQuery("select pdfOletusvientipolku\n"
                    + "from Asetukset\n"
                    + "where asetuksetID = 1");
            if (rs.first()) {
                if (rs.getString(1) != null) {
                    polku = rs.getString(1);
                    chooser.setCurrentDirectory(new File(polku));
                }
                chooser.setSelectedFile(new File(viiteTarkisteella + ".pdf"));
            }
            int retrival = chooser.showSaveDialog(chooser);
            if (!polku.equals(chooser.getCurrentDirectory().getPath())) {
                lataaja.getDbc().executeUpdate("update Asetukset\n"
                        + "set pdfOletusvientipolku = '" + chooser.getCurrentDirectory().getPath() + "'\n"
                        + "where asetuksetID = 1");
            }
            Document document = new Document(PageSize.A4, 40, 20, 30, 30);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(chooser.getSelectedFile()));
                muodostaDokumentti(document, laskunNumero, writer);
                writer.close();
            } else {
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (HeadlessException headlessException) {
        } catch (FileNotFoundException fileNotFoundException) {
        } catch (DocumentException documentException) {
        } catch (ParseException parseException) {
        }
    }

    private void muodostaDokumentti(Document document, String laskunNumero, PdfWriter writer) throws DocumentException, SQLException, ParseException {
        document.open();
        //----------------------------------------------------------------------
        PdfPTable table1 = new PdfPTable(7);
        Font f1 = new Font(Font.FontFamily.HELVETICA, 10);
        Font f2 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f3 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font f4 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        ResultSet rs = lataaja.getDbc().executeQuery("select distinct Laskuttaja.yrityksenNimi, Laskuttaja.katuosoite,\n"
                + "Laskuttaja.postinumero, Laskuttaja.kaupunki, Lasku.paivays, Laskuttaja.alvTunniste,\n"
                + "Pankkiviivakoodi.erapaiva, Lasku.viivastyskorko, Pankkiviivakoodi.viiteTarkisteella,\n"
                + "T.nimi, Lasku.maksuehto, T.katuosoite, Laskuttaja.tilinumeronPankki,\n"
                + "T.postinumero, T.kaupunki, Laskuttaja.tilinumero, T.email, Laskuttaja.tilinumeronSwiftBic,\n"
                + "V.nimi, V.katuosoite, V.postinumero, V.kaupunki, V.email, Lasku.lisatiedot,\n"
                + "T.asiakasnumero, V.asiakasnumero, Laskuttaja.nimi, Laskuttaja.puhelinnumero,\n"
                + "Laskuttaja.sahkopostiOsoite, Pankkiviivakoodi.pankkiviivakoodi\n"
                + "from Lasku, Pankkiviivakoodi, Laskuttaja, Suorite, Asiakas T, Asiakas V\n"
                + "where Lasku.laskunNumero = " + laskunNumero + "\n"
                + "and Lasku.laskuttaja = Laskuttaja.yrityksenNimi\n"
                + "and Lasku.laskuttajanVersio = Laskuttaja.versio\n"
                + "and Lasku.pankkiviivakoodi = Pankkiviivakoodi.pankkiviivakoodi\n"
                + "and Lasku.laskunnumero = Suorite.lasku\n"
                + "and Suorite.tilaaja = T.asiakasnumero\n"
                + "and Suorite.tilaajanVersio = T.versio\n"
                + "and Suorite.vastaanottaja = V.asiakasnumero\n"
                + "and Suorite.vastaanottajanVersio = V.versio\n"
                + "");
        rs.first();
        table1.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
        table1.setLockedWidth(true);
        table1.setWidths(new int[]{370, 100, 73, 100, 73, 100, 179});
        PdfPCell cell;
        Paragraph p;

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph(rs.getString(1), f4);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("LASKU", f4);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        table1.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        p = new Paragraph(rs.getString(2), f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        table1.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph(rs.getString(3) + " " + rs.getString(4), f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("Päiväys", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph(pvmFormaatti1.format(pvmFormaatti3.parse(rs.getTimestamp(5).toString())), f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        table1.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("Laskun numero", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph(laskunNumero, f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        table1.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph("Alv-tunniste: " + rs.getString(6), f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("Eräpäivä", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph(pvmFormaatti1.format(pvmFormaatti4.parse(rs.getDate(7).toString())), f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        table1.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("Viivästyskorko", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph(Integer.toString(rs.getInt(8)) + ".0%", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        table1.addCell(cell);

        table1.completeRow();

        //Jos vastaanottaja on sama kuin tilaaja ei laiteta erikseen vastaanottajan tietoja näkyville.
        if (rs.getInt(25) == rs.getInt(26)) {
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(10), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Viitenumero", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(9), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(12), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Maksuehto", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(11), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(14) + " " + rs.getString(15), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Pankki", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(13), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(17), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Tilinumero", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(16), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Swift/BIC", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(18), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(" ", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph("Lisätiedot", f2);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(24), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(" ", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            document.add(table1);
        } else {
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph("Palvelun tilaaja", f2);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Viitenumero", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(9), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(10), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Maksuehto", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(11), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(12), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Pankki", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(13), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(14) + " " + rs.getString(15), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Tilinumero", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(16), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            p = new Paragraph(rs.getString(17), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph("Swift/BIC", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            p = new Paragraph(rs.getString(18), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph("Palvelun vastaanottaja", f2);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(19), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(20), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(21) + " " + rs.getString(22), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(23), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph("Lisätiedot", f2);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(rs.getString(24), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            p = new Paragraph(" ", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell);

            table1.completeRow();

            document.add(table1);
        }

        // Otetaan alemmas tulevia tietoja talteen.
        String[] alasTulevatTiedot = new String[]{
            rs.getString(9),
            rs.getString(1),
            rs.getString(27),
            rs.getString(13),
            rs.getString(2),
            rs.getString(28),
            rs.getString(16),
            rs.getString(3) + " " + rs.getString(4),
            rs.getString(29),
            rs.getString(18),
            rs.getString(30),};

        //Muodostetaan suoritteiden taulukko.
        PdfPTable table2 = new PdfPTable(7);
        table2.setWidths(new int[]{370, 100, 73, 100, 73, 100, 179});
        table2.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
        table2.setLockedWidth(true);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Kuvaus", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Määrä", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Yks.", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("à hinta", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Alv %", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Alv €", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph("Yhteensä", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(LEFT + BOTTOM + RIGHT + TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        table2.completeRow();

        rs = lataaja.getDbc().executeQuery("select distinct kuvaus, maara, maaranYksikot, aHintaVeroton, alvProsentti, ((alvProsentti / 100.0) * aHintaVeroton * maara) as alvEuroa,\n"
                + "((1.0 + alvProsentti / 100.0) * aHintaVeroton * maara) as yht\n"
                + "from Suorite\n"
                + "where lasku = " + laskunNumero + "\n"
                + "");

        while (rs.next()) {
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getString(1), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getBigDecimal(2, 2).toString(), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getString(3), f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getBigDecimal(4, 2).toString() + "€", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getString(5) + "%", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getBigDecimal(6, 2).toString() + "€", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(1);
            p = new Paragraph(rs.getBigDecimal(7, 2).toString() + "€", f1);
            p.setLeading(0, 1);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(LEFT + RIGHT);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorderWidth(0.2f);
            table2.addCell(cell);

            table2.completeRow();
        }

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        p = new Paragraph(" ", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(TOP);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table2.addCell(cell);

        table2.completeRow();

        rs = lataaja.getDbc().executeQuery("select distinct sum(aHintaVeroton * maara), sum((alvProsentti / 100.0) * aHintaVeroton * maara)\n"
                + "from Suorite\n"
                + "where lasku = " + laskunNumero + "\n"
                + "");

        rs.first();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph("Veroton hinta yhteensä", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(rs.getBigDecimal(1, 2).toString() + "€", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        table2.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph("Arvonlisävero yhteensä", f2);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(rs.getBigDecimal(2, 2).toString() + "€", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        table2.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(7);
        p = new Paragraph(" ", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        table2.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        p = new Paragraph("Yhteensä", f3);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        rs = lataaja.getDbc().executeQuery("select laskunSumma\n"
                + "from Lasku, Pankkiviivakoodi\n"
                + "where laskunNumero = " + laskunNumero + "\n"
                + "and Lasku.pankkiviivakoodi = Pankkiviivakoodi.pankkiviivakoodi\n"
                + "");

        rs.first();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(rs.getBigDecimal(1, 2).toString() + "€", f3);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell);

        table2.completeRow();

        document.add(table2);

        PdfPTable table3 = new PdfPTable(3);
        table3.setWidths(new int[]{1, 1, 1});
        table3.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
        table3.setLockedWidth(true);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph("Pyydämme käyttämään maksaessanne viitenumeroa: " + alasTulevatTiedot[0], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph(" ", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(BOTTOM);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.2f);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[1], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[2], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[3], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[4], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[5], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[6], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[7], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[8], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(1);
        p = new Paragraph(alasTulevatTiedot[9], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph(" ", f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        p = new Paragraph("Virtuaaliviivakoodi: " + alasTulevatTiedot[10], f1);
        p.setLeading(0, 1);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.addCell(cell);

        table3.completeRow();

        table3.writeSelectedRows(0, -1,
                document.left(document.leftMargin()),
                table3.getTotalHeight() + document.bottom(document.bottomMargin()),
                writer.getDirectContent());
        //----------------------------------------------------------------------
        document.close();

    }
}
