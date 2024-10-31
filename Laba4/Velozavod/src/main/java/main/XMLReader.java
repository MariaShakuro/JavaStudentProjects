package main;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import velo.DateUtils;
import velo.StructureOfVelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader {
    public static List<StructureOfVelo<String>>readBikes(String filePath) {
        List<StructureOfVelo<String>> bikes_ = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(xmlFile);
            //В случае отступов,оно всё нормализует и не будет непредвиденных ошибок
            doc.getDocumentElement().normalize();
            //Получение узлов и итерация по ним
            NodeList nList = doc.getElementsByTagName("bike");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                    Date date = DateUtils.parseDate(eElement.getElementsByTagName("date").item(0).getTextContent());
                    String model = eElement.getElementsByTagName("model").item(0).getTextContent();
                    String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                    String priceStr = eElement.getElementsByTagName("price").item(0).getTextContent();
                    double price = Double.parseDouble(priceStr.replaceAll("[^\\d.]", ""));
                    double maxSpeed = Double.parseDouble(eElement.getElementsByTagName("max_speed").item(0).getTextContent());
                    StructureOfVelo<String> bike = new StructureOfVelo<>();
                    bike.set(id, date, type, model, price, maxSpeed);
                    bikes_.add(bike);
                }
            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return bikes_;
    }
}
