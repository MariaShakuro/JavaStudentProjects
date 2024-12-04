package org.example.laba6;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import velo.AbstractVelo;
import velo.RoadVelo;
import velo.MountainVelo;
import velo.DateUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader {
    public static List<AbstractVelo> readBikes(String filePath) {
        List<AbstractVelo> bikes = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("bike");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    try {
                        int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                        Date date = DateUtils.parseDate(eElement.getElementsByTagName("date").item(0).getTextContent());
                        String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                        String model = eElement.getElementsByTagName("model").item(0).getTextContent();
                        double price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent().replaceAll("[^\\d.]", ""));
                        double maxSpeed = Double.parseDouble(eElement.getElementsByTagName("max_speed").item(0).getTextContent());

                        AbstractVelo bike;
                        if ("Road".equalsIgnoreCase(type)) {
                            bike = new RoadVelo(id, date, type, model, price, maxSpeed);
                        } else if ("Mountain".equalsIgnoreCase(type)) {
                            bike = new MountainVelo(id, date, type, model, price, maxSpeed);
                        } else {
                            System.out.println("Некорректный тип: " + type + ". Строка пропущена: " + eElement.getTextContent());
                            continue;
                        }
                        bikes.add(bike);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.println("Некорректные данные для байка. Пропущено: " + eElement.getTextContent());
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return bikes;
    }
}


