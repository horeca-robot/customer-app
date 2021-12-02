package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IPDFService;
import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

@Service
public class PDFService implements IPDFService {
    private final IRestaurantInfoService infoService;

    protected Font font10;
    protected Font font10b;
    protected Font font12;
    protected Font font12b;
    protected Font font14;
    protected BaseFont bfb;
    protected BaseFont bf;

    @Autowired
    public PDFService(IRestaurantInfoService infoService) throws DocumentException, IOException {
        this.infoService = infoService;
        bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        bfb = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        font10 = new Font(bf, 10);
        font10b = new Font(bfb, 10);
        font12 = new Font(bf, 12);
        font12b = new Font(bfb, 12);
        font14 = new Font(bf, 14);
    }

    @Override
    public Document createPDF(List<RestaurantOrderDto> restaurantOrdersDto) {
        Document document = null;
        PdfWriter docWriter = null;

        try {
            document = new Document();
            RestaurantInfo restaurantInfo = infoService.getRestaurantInfo();
            String path = "docs/" + restaurantInfo.getName() + "-Bill";
            docWriter = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.addAuthor(restaurantInfo.getContactPersonName());
            document.addCreationDate();
            document.addProducer();
            document.addCreator(restaurantInfo.getName());
            document.addTitle(restaurantInfo.getName() + " Bill");
            document.setPageSize(PageSize.LETTER);
            document.open();
            PdfContentByte pdfContentByte = docWriter.getDirectContent();
            generateLayout(document, pdfContentByte, restaurantInfo);
            generateHeader(pdfContentByte, restaurantInfo);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) document.close();
            if (docWriter != null) docWriter.close();
        }

        return document;
    }



    private void generateLayout(Document document, PdfContentByte pdfContentByte, RestaurantInfo restaurantInfo)  {
        try {
            pdfContentByte.setLineWidth(1f);

            pdfContentByte.rectangle(420,700,150,60);
            pdfContentByte.moveTo(420,720);
            pdfContentByte.lineTo(570,720);
            pdfContentByte.moveTo(420,740);
            pdfContentByte.lineTo(570,740);
            pdfContentByte.moveTo(480,700);
            pdfContentByte.lineTo(480,760);
            pdfContentByte.stroke();

            createHeadings(pdfContentByte,422,743,"Account No.");
            createHeadings(pdfContentByte,422,723,"Invoice No.");
            createHeadings(pdfContentByte,422,703,"Invoice Date");

            pdfContentByte.rectangle(20,50,550,600);
            pdfContentByte.moveTo(20,630);
            pdfContentByte.lineTo(570,630);
            pdfContentByte.moveTo(50,50);
            pdfContentByte.lineTo(50,650);
            pdfContentByte.moveTo(150,50);
            pdfContentByte.lineTo(150,650);
            pdfContentByte.moveTo(430,50);
            pdfContentByte.lineTo(430,650);
            pdfContentByte.moveTo(500,50);
            pdfContentByte.lineTo(500,650);
            pdfContentByte.stroke();

            createHeadings(pdfContentByte,22,633,"Qty");
            createHeadings(pdfContentByte,52,633,"Item Number");
            createHeadings(pdfContentByte,152,633,"Item Description");
            createHeadings(pdfContentByte,432,633,"Price");
            createHeadings(pdfContentByte,502,633,"Ext Price");

            Image companyLogo = Image.getInstance(restaurantInfo.getRestaurantLogo());
            companyLogo.setAbsolutePosition(25,700);
            companyLogo.scalePercent(25);
            document.add(companyLogo);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateHeader(PdfContentByte pdfContentByte, RestaurantInfo restaurantInfo)  {
        try {
            createHeadings(pdfContentByte,200,750,restaurantInfo.getName());
            createHeadings(pdfContentByte,200,735,"Address Line 1");
            createHeadings(pdfContentByte,200,720,"Address Line 2");
            createHeadings(pdfContentByte,200,705,"City, State - ZipCode");
            createHeadings(pdfContentByte,200,690,"Country");

            createHeadings(pdfContentByte,482,743,"ABC0001");
            createHeadings(pdfContentByte,482,723,"123456");
            createHeadings(pdfContentByte,482,703, DateFormat.getInstance().format(System.currentTimeMillis()));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void createHeadings(PdfContentByte pdfContentByte, float x, float y, String text){
        pdfContentByte.beginText();
        pdfContentByte.setFontAndSize(bfb, 8);
        pdfContentByte.setTextMatrix(x,y);
        pdfContentByte.showText(text.trim());
        pdfContentByte.endText();
    }
}
