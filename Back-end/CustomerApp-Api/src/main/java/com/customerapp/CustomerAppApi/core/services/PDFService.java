package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IPDFService;
import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.lowagie.text.DocumentException;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.text.DateFormat;
import java.util.List;

@Service
public class PDFService implements IPDFService {
    private final IRestaurantInfoService infoService;

    @Autowired
    public PDFService(IRestaurantInfoService infoService) {
        this.infoService = infoService;
    }

    @Override
    public Document createPDF(List<RestaurantOrderDto> restaurantOrdersDto) {
        StringBuilder buf = new StringBuilder();
        buf.append("<html>");

        // put in some style
        buf.append("<head><style language='text/css'>");
        buf.append("h3 { border: 1px solid #aaaaff; background: #ccccff; ");
        buf.append("padding: 1em; text-transform: capitalize; font-family: sansserif; font-weight: normal;}");
        buf.append("p { margin: 1em 1em 4em 3em; } p:first-letter { color: red; font-size: 150%; }");
        buf.append("h2 { background: #5555ff; color: white; border: 10px solid black; padding: 3em; font-size: 200%; }");
        buf.append("</style></head>");

        // generate the body
        buf.append("<body>");
        buf.append("<p><img src='100bottles.jpg'/></p>");
        for(int i=99; i>0; i--) {
            buf.append("<h3>").append(i).append(" bottles of beer on the wall, ").append(i).append(" bottles of beer!</h3>");
            buf.append("<p>Take one down and pass it around, ").append(i - 1).append(" bottles of beer on the wall</p>\n");
        }
        buf.append("<h2>No more bottles of beer on the wall, no more bottles of beer. ");
        buf.append("Go to the store and buy some more, 99 bottles of beer on the wall.</h2>");
        buf.append("</body>");
        buf.append("</html>");

        Document document = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new StringBufferInputStream(buf.toString()));

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(document, null);

            RestaurantInfo restaurantInfo = infoService.getRestaurantInfo();
            String outputFile = "docs/" + restaurantInfo.getName() + "-Rekening.pdf";
            OutputStream os = new FileOutputStream(outputFile);
            renderer.layout();
            renderer.createPDF(os);
            os.close();
        } catch(IOException | DocumentException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }
}
