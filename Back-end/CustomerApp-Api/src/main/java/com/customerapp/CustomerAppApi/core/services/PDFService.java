package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IPDFService;
import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import com.customerapp.CustomerAppApi.models.ProductOrderDto;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.itextpdf.text.pdf.qrcode.ByteArray;
import com.lowagie.text.DocumentException;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class PDFService implements IPDFService {
    private final IRestaurantInfoService infoService;

    @Autowired
    public PDFService(IRestaurantInfoService infoService) {
        this.infoService = infoService;
    }

    @Override
    public ResponseEntity<ByteArrayResource> createPDF(List<RestaurantOrderDto> restaurantOrdersDto, UUID restaurantTableId) {
        Document document = null;
        try {
            URL resource = PDFService.class.getResource("/invoice.html");
            RestaurantInfo restaurantInfo = infoService.getRestaurantInfo();
            Path file = Paths.get(resource.toURI());
            String data = Files.readString(file);
            double subTotal = getSubTotal(restaurantOrdersDto);
            Color color = Color.decode(restaurantInfo.getPrimaryColor());
            Color colorLight = brighten(color, 0.25);
            Color colorLightest = brighten(colorLight, 0.25);

            data = data.replace("{{RESTAURANT_NAME}}", restaurantInfo.getName());
            data = data.replace("{{DATE}}", java.time.LocalDate.now().toString());
            data = data.replace("{{PAYMENT_DATE}}", java.time.LocalDate.now().toString());
            data = data.replace("{{INVOICE_NR}}", restaurantInfo.getId().toString());
            data = data.replace("{{TABLE_NR}}", restaurantTableId.toString());
            data = data.replace("{{LOGO}}", restaurantInfo.getRestaurantLogo());
            data = data.replace("{{CONTACT_NAME}}", restaurantInfo.getContactPersonName());
            data = data.replace("{{CONTACT_PHONE}}", restaurantInfo.getContactPersonPhone());
            data = data.replace("{{CONTACT_EMAIL}}", restaurantInfo.getContactPersonEmail());
            data = data.replace("{{SUBTOTAL}}", String.format("%.2f", subTotal - (subTotal / 100) * 9));
            data = data.replace("{{BTW_TOTAL}}", String.format("%.2f", (subTotal / 100) * 9));
            data = data.replace("{{TOTAL}}", String.format("%.2f", subTotal));
            data = data.replace("{{COLOR}}", "#" + String.format("%06X", 0xFFFFFF & color.getRGB()));
            data = data.replace("{{COLOR_LIGHT}}", "#" + String.format("%06X", 0xFFFFFF & colorLight.getRGB()));
            data = data.replace("{{COLOR_LIGHTEST}}", "#" + String.format("%06X", 0xFFFFFF & colorLightest.getRGB()));
            data = data.replace("{{TEXT_COLOR}}", "#" + String.format("%06X", 0xFFFFFF & getColor(color).getRGB()));
            data = data.replace("{{TEXT_COLOR_LIGHT}}", "#" + String.format("%06X", 0xFFFFFF & getColor(colorLight).getRGB()));
            data = data.replace("{{PRODUCTS}}", getTableRows(restaurantOrdersDto));
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(data)));
            document.getDocumentElement().normalize();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(document, null);
            renderer.layout();
            renderer.createPDF(baos);
            byte[] byteArray = baos.toByteArray();
            baos.close();

            ByteArrayResource bar = new ByteArrayResource(byteArray);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition",
                    "attachment; filename=\"Rekening_" + restaurantInfo.getName() + ".pdf\"");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(byteArray.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bar);


        } catch(IOException | DocumentException | SAXException | ParserConfigurationException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableRows(List<RestaurantOrderDto> restaurantOrdersDto) {
        StringBuilder row = new StringBuilder();
        for (RestaurantOrderDto order: restaurantOrdersDto) {
            for (ProductOrderDto product: order.getProductOrders()) row.append("<tr class=\"product-row\"><td><div>").append(product.getProduct().getName()).append("</div><div class=\"text-opacity\"></div></td><td>").append(1).append("</td><td><div>&#x2713;</div></td><td class=\"text-right\">").append(product.getProduct().getPrice()).append(",-</td><td class=\"text-right\">").append(product.getProduct().getPrice()).append(",-</td></tr>");
        }
        return row.toString();
    }

    private static Color brighten(Color color, double fraction) {
        int red = (int) Math.round(Math.min(255, color.getRed() + 255 * fraction));
        int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * fraction));
        int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * fraction));

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);
    }

    private Color getColor(Color color) {
        int colorRange = color.getRGB();

        int red   = (colorRange >>> 16) & 0xFF;
        int green = (colorRange >>> 8) & 0xFF;
        int blue  = (colorRange) & 0xFF;

        float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;

        if (luminance >= 0.5f) return Color.white;
        else return Color.black;
    }

    private double getSubTotal(List<RestaurantOrderDto> restaurantOrdersDto) {
        double subTotal = 0.00;
        for (RestaurantOrderDto order: restaurantOrdersDto) subTotal += order.getSubTotal();
        return subTotal;
    }
}
