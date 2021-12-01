package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IPDFService;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class PDFService implements IPDFService {
    protected Font font10;
    protected Font font10b;
    protected Font font12;
    protected Font font12b;
    protected Font font14;
    protected BaseFont bfb;
    protected BaseFont bf;

    public PDFService() throws DocumentException, IOException {
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
            docWriter = PdfWriter.getInstance(document, new FileOutputStream("Restaurant-Bill.pdf"));
            document.addAuthor("Author");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("Restaurant name");
            document.addTitle("Invoice");
            document.setPageSize(PageSize.LETTER);
            document.open();
            PdfContentByte pdfContentByte = docWriter.getDirectContent();
            generateHeader(pdfContentByte);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) document.close();
            if (docWriter != null) docWriter.close();
        }

        return document;
    }

    private void generateHeader(PdfContentByte pdfContentByte)  {
        try {
            createHeadings(pdfContentByte,200,750,"Company Name");
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
