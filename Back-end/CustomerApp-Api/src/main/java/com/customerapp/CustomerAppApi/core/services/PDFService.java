package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IPDFService;
import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import com.customerapp.CustomerAppApi.core.interfaces.ITableService;
import com.customerapp.CustomerAppApi.models.BillProductDto;
import com.customerapp.CustomerAppApi.models.ProductOrderDto;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.lowagie.text.DocumentException;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.util.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.awt.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
public class PDFService implements IPDFService {
    private final IRestaurantInfoService infoService;
    private final ITableService tableService;

    @Autowired
    public PDFService(IRestaurantInfoService infoService, ITableService tableService) {
        this.infoService = infoService;
        this.tableService = tableService;
    }

    /*
    The resource URL is not working in the JAR
    If we try to access a file that is inside a JAR,
    It throws NoSuchFileException (linux), InvalidPathException (Windows)

    Resource URL Sample: file:java-io.jar!/json/file1.json
 */
    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    @Override
    public ResponseEntity<ByteArrayResource> createPDF(List<RestaurantOrderDto> restaurantOrdersDto, UUID restaurantTableId) {
        try {
            InputStream inputStream = getFileFromResourceAsStream("invoice.html");

            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String data = s.hasNext() ? s.next() : "";

            RestaurantInfo restaurantInfo = infoService.getRestaurantInfo();
            double subTotal = getSubTotal(restaurantOrdersDto);
            Color color = Color.decode(restaurantInfo.getPrimaryColor());
            Color colorLight = brighten(color, 0.25);

            data = data.replace("{{RESTAURANT_NAME}}", restaurantInfo.getName());
            data = data.replace("{{DATE}}", java.time.LocalDate.now().toString());
            data = data.replace("{{PAYMENT_DATE}}", java.time.LocalDate.now().toString());
            data = data.replace("{{INVOICE_NR}}", restaurantInfo.getId().toString());
            data = data.replace("{{TABLE_NR}}", Integer.toString(tableService.getRestaurantTableById(restaurantTableId).get().getTableNumber()));
            data = data.replace("{{LOGO}}", restaurantInfo.getRestaurantLogo());
            data = data.replace("{{CONTACT_NAME}}", restaurantInfo.getContactPersonName());
            data = data.replace("{{CONTACT_PHONE}}", restaurantInfo.getContactPersonPhone());
            data = data.replace("{{CONTACT_EMAIL}}", restaurantInfo.getContactPersonEmail());
            data = data.replace("{{ADDRESS}}", restaurantInfo.getAddress());
            data = data.replace("{{POSTAL_CODE}}", restaurantInfo.getPostalCode());
            data = data.replace("{{COUNTRY}}", restaurantInfo.getCountry());
            data = data.replace("{{CITY}}", restaurantInfo.getCity());
            data = data.replace("{{IBAN}}", restaurantInfo.getAccountNumber());
            data = data.replace("{{CONTACT_EMAIL}}", restaurantInfo.getContactPersonEmail());
            data = data.replace("{{SUBTOTAL}}", String.format("%.2f", subTotal - (subTotal / 100) * 9));
            data = data.replace("{{BTW_TOTAL}}", String.format("%.2f", (subTotal / 100) * 9));
            data = data.replace("{{TOTAL}}", String.format("%.2f", subTotal));
            data = data.replace("{{COLOR}}", "#" + String.format("%06X", 0xFFFFFF & color.getRGB()));
            data = data.replace("{{COLOR_LIGHT}}", "#" + String.format("%06X", 0xFFFFFF & colorLight.getRGB()));
            data = data.replace("{{TEXT_COLOR}}", "#" + String.format("%06X", 0xFFFFFF & getColor(color).getRGB()));
            data = data.replace("{{TEXT_COLOR_LIGHT}}", "#" + String.format("%06X", 0xFFFFFF & getColor(colorLight).getRGB()));
            data = data.replace("{{PRODUCTS}}", getTableRows(restaurantOrdersDto));
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(data)));
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
        } catch (IOException | DocumentException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableRows(List<RestaurantOrderDto> restaurantOrdersDto) {
        StringBuilder row = new StringBuilder();
        int amount = 1;
        List<BillProductDto> billProducts;
        for (RestaurantOrderDto order: restaurantOrdersDto) {
            billProducts = null;
            billProducts = new ArrayList<>();

            row.append("<tr class=\"product-row\"><td><div>")
                    .append("Order: ")
                    .append(restaurantOrdersDto.indexOf(order) + 1)
                    .append("</div><div class=\"text-opacity\"></div></td><td>")
                    .append("</td><td><div>&#x2713;</div></td><td class=\"text-right\">")
                    .append("</td><td class=\"text-right\">")
                    .append("</td></tr>");

            for (ProductOrderDto product : order.getProductOrders()) {
                if(billProducts.stream().anyMatch(p -> p.getProduct().getId().equals(product.getProduct().getId()))){
                    billProducts.stream().filter(p -> p.getProduct().getId().equals(product.getProduct().getId())).forEach(p -> {
                        p.setAmount(p.getAmount() + 1);
                    });
                } else {
                    billProducts.add(new BillProductDto(product.getProduct(), amount));
                }
            }
            for(BillProductDto billProduct : billProducts){
                row.append("<tr class=\"product-row\"><td><div>")
                        .append(billProduct.getProduct().getName())
                        .append("</div><div class=\"text-opacity\"></div></td><td>")
                        .append(billProduct.getAmount())
                        .append("</td><td><div>&#x2713;</div></td><td class=\"text-right\">")
                        .append(String.format("%.2f", billProduct.getProduct().getPrice()))
                        .append("</td><td class=\"text-right\">")
                        .append(String.format("%.2f", billProduct.getProduct().getPrice() * billProduct.getAmount()))
                        .append("</td></tr>");
            }
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

        if((red * 0.299 + green * 0.587 + blue * 0.114) > 150) return Color.BLACK;
        else return Color.WHITE;
    }

    private double getSubTotal(List<RestaurantOrderDto> restaurantOrdersDto) {
        double subTotal = 0.00;
        for (RestaurantOrderDto order: restaurantOrdersDto) subTotal += order.getSubTotal();
        return subTotal;
    }
}
