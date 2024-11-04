package main;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import velo.StructureOfVelo;
import javax.xml.transform.OutputKeys;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


public class XMLWriter {

    public static void writeBikes(String filePath, List<StructureOfVelo<String>> bikes) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создание нового документа
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("factory");
            doc.appendChild(rootElement);

            // Заполнение документа данными о велосипедах
            for (StructureOfVelo<String> bike : bikes) {
                Element bikeElement = doc.createElement("bike");
                rootElement.appendChild(bikeElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(bike.getID())));
                bikeElement.appendChild(id);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(String.valueOf(bike.getDATE())));
                bikeElement.appendChild(date);

                Element model = doc.createElement("model");
                model.appendChild(doc.createTextNode(bike.getMODEl()));
                bikeElement.appendChild(model);

                Element type = doc.createElement("type");
                type.appendChild(doc.createTextNode(bike.getTYPE()));
                bikeElement.appendChild(type);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(bike.getPRICE())));
                bikeElement.appendChild(price);

                Element maxSpeed = doc.createElement("max_speed");
                maxSpeed.appendChild(doc.createTextNode(String.valueOf(bike.getMAX_SPEED())));
                bikeElement.appendChild(maxSpeed);
            }

            // Запись документа в XML-файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("XML-файл успешно записан!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

