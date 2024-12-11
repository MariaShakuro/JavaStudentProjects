package org.example.laba6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import velo.AbstractVelo;
import velo.ConcreteVelo;
import velo.SortVelo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button formatButton;

    @FXML
    private RadioButton hashBox;

    @FXML
    private TextArea inputTextField;

    @FXML
    private RadioButton jarBox;

    @FXML
    private CheckBox jsonInput;

    @FXML
    private CheckBox jsonOutput;

    @FXML
    private TextArea outputTextField;

    @FXML
    private RadioButton shifrBox;

    @FXML
    private CheckBox txtInput;

    @FXML
    private CheckBox txtOutput;

    @FXML
    private CheckBox xmlInput;

    @FXML
    private CheckBox xmlOutput;

    @FXML
    private RadioButton zipBox;

    @FXML
    private Button saveButton_1;
    @FXML
    private Button saveButton_2;
    @FXML
    private Button sortButton;

    @FXML
    private RadioButton sortDate;

    @FXML
    private RadioButton sortId;

    @FXML
    private RadioButton sortMax_speed;

    @FXML
    private RadioButton sortModel;

    @FXML
    private RadioButton sortPrice;

    @FXML
    private RadioButton sortType;

    @FXML
    private Button ttt;

    private List<AbstractVelo>veloList;
    private File inputFile;
    private FileChooser fileChooser;
    private String filePath = "C:/from_past_pc/Labs 2 grade/JavaStudentProjects/JavastudentProjects/Laba6/";


    private void writeDataToFile(List<ConcreteVelo> veloList) {
        Thread jsonThread = new Thread(new JsonWriterThread(veloList, "output.json"));
        Thread xmlThread = new Thread(new XmlWriterThread(veloList, "output.xml"));

        jsonThread.start();
        xmlThread.start();

        try {
            jsonThread.join();
            xmlThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSaveButtonAction() {
        String inputText = inputTextField.getText();
        List<ConcreteVelo> veloList = parseVeloList(inputText);
        writeDataToFile(veloList);
    }

    @FXML
    void initialize() {

        fileChooser = new FileChooser();

        txtInput.setOnAction(event -> handleFileInputSelection(txtInput, "txt"));
        xmlInput.setOnAction(event -> handleFileInputSelection(xmlInput, "xml"));
        jsonInput.setOnAction(event -> handleFileInputSelection(jsonInput, "json"));

        formatButton.setOnAction(event -> formatText());
        jarBox.setOnAction(event -> {
            if (jarBox.isSelected()) zipBox.setSelected(false);
        });
        zipBox.setOnAction(event -> {
            if (zipBox.isSelected()) jarBox.setSelected(false);
        });
        saveButton_1.setOnAction(event -> saveAsArchive());
        saveButton_2.setOnAction(event -> {
            try {
                saveHashAndEncrypt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        sortButton.setOnAction(event -> {
            try {
               sortList();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private boolean isJsonFormat(String data) {
        return data.trim().startsWith("{");
    }

    private boolean isXmlFormat(String data) {
        return data.trim().startsWith("<");
    }
    private List<ConcreteVelo> parseVeloListFromXml(String data) {
        List<ConcreteVelo> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(data)));

            NodeList nodeList = document.getElementsByTagName("bike");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String dateString = element.getElementsByTagName("date").item(0).getTextContent();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                String type = element.getElementsByTagName("type").item(0).getTextContent();
                String model = element.getElementsByTagName("model").item(0).getTextContent();
                double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent().replace(" USD", ""));
                double max_speed = Double.parseDouble(element.getElementsByTagName("max_speed").item(0).getTextContent());

                ConcreteVelo velo = new ConcreteVelo(id, date, type, model, price, max_speed);
                list.add(velo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<ConcreteVelo> parseVeloListFromJson(String data) {
        Gson gson = new Gson();
        try {
            JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonArray("bikes");
            Type listType = new TypeToken<List<ConcreteVelo>>() {}.getType();
            List<ConcreteVelo> list = gson.fromJson(jsonArray, listType);
            System.out.println("Parsed list from JSON: " + list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    private List<ConcreteVelo> parseVeloListFromString(String data) {
        List<ConcreteVelo> list = new ArrayList<>();
        String[] lines = data.split("\n");
        boolean skipHeaders = true;
        for (String line : lines) {
            if (skipHeaders) {
                skipHeaders = false;
                continue; // Пропускаем заголовок
            }
            ConcreteVelo velo = parseVelo(line);
            if (velo != null) {
                list.add(velo);
            }
        }
        System.out.println("Parsed list from String: " + list);
        return list;
    }


    private List<ConcreteVelo> parseVeloList(String data) {
        if (isJsonFormat(data)) {
            return parseVeloListFromJson(data);
        } else if (isXmlFormat(data)) {
            return parseVeloListFromXml(data);
        } else {
            return parseVeloListFromString(data);
        }
    }

    private void sortList() throws ParseException {
        String data = outputTextField.getText();
        System.out.println("Input data: " + data);

        List<ConcreteVelo> veloList;
        if (isJsonFormat(data)) {
            veloList = parseVeloListFromJson(data);
        } else if (isXmlFormat(data)) {
            veloList = parseVeloListFromXml(data);
        } else {
            veloList = parseVeloListFromString(data);
        }

        System.out.println("Parsed Velo List: " + veloList);

        if (sortDate.isSelected()) {
            SortVelo.sortDate(veloList);
            System.out.println("Sorted by Date: " + veloList);
        } else if (sortId.isSelected()) {
            SortVelo.sortId(veloList);
            System.out.println("Sorted by ID: " + veloList);
        } else if (sortMax_speed.isSelected()) {
            SortVelo.sortMax_speed(veloList);
            System.out.println("Sorted by Max Speed: " + veloList);
        } else if (sortModel.isSelected()) {
            SortVelo.sortModel(veloList);
            System.out.println("Sorted by Model: " + veloList);
        } else if (sortPrice.isSelected()) {
            SortVelo.sortPrice(veloList);
            System.out.println("Sorted by Price: " + veloList);
        } else if (sortType.isSelected()) {
            SortVelo.sortType(veloList);
            System.out.println("Sorted by Type: " + veloList);
        }

        displaySortedList(veloList);
        System.out.println("Displayed Sorted List");
    }

    private ConcreteVelo parseVelo(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) {
            return null;
        }
        try {
            int id = Integer.parseInt(parts[0].trim().replace("Id ", ""));
            String dateString = parts[1].trim().replace("Date ", "");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            String type = parts[2].trim().replace("Type ", "");
            String model = parts[3].trim().replace("Model ", "");
            double price = Double.parseDouble(parts[4].trim().replace("Price ", "").replace(" USD", ""));
            double max_speed = Double.parseDouble(parts[5].trim().replace("Max_Speed ", ""));

            return new ConcreteVelo(id, date, type, model, price, max_speed);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void displaySortedList(List<ConcreteVelo> veloList) {
        StringBuilder result = new StringBuilder();
        for (AbstractVelo velo : veloList) {
            result.append(velo.toString()).append("\n");
        }
        outputTextField.setText(result.toString());
    }

    private void handleFileInputSelection(CheckBox checkBox, String extension) {
        if (checkBox.isSelected()) {
            unselectOtherInputs(checkBox);
            inputFile = chooseFile(extension);
            if (inputFile != null) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get(inputFile.toURI())));
                    inputTextField.setText(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File chooseFile(String extension) {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extension.toUpperCase() + " files", "*." + extension));
        return fileChooser.showOpenDialog(new Stage());
    }

    private void unselectOtherInputs(CheckBox selectedCheckBox) {
        if (selectedCheckBox != txtInput) txtInput.setSelected(false);
        if (selectedCheckBox != xmlInput) xmlInput.setSelected(false);
        if (selectedCheckBox != jsonInput) jsonInput.setSelected(false);
    }

    private String generateXml(List<ConcreteVelo> veloList) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<factory>\n");
        for (ConcreteVelo velo : veloList) {
            xmlBuilder.append("    <bike>\n");
            xmlBuilder.append("        <id>").append(velo.getID()).append("</id>\n");
            xmlBuilder.append("        <date>").append(new SimpleDateFormat("yyyy-MM-dd").format(velo.getDATE())).append("</date>\n");
            xmlBuilder.append("        <model>").append(velo.getMODEL()).append("</model>\n");
            xmlBuilder.append("        <type>").append(velo.getTYPE()).append("</type>\n");
            xmlBuilder.append("        <price>").append(velo.getPRICE()).append(" USD</price>\n");
            xmlBuilder.append("        <max_speed>").append(velo.getMAX_SPEED()).append("</max_speed>\n");
            xmlBuilder.append("    </bike>\n");
        }
        xmlBuilder.append("</factory>");
        return xmlBuilder.toString();
    }

    private void formatText() {
        String inputText = inputTextField.getText();
        if (txtOutput.isSelected()) {
            outputTextField.setText(inputText);
            saveFormattedText(inputText, "txt");
        } else if (xmlOutput.isSelected()) {
            List<ConcreteVelo> veloList = parseVeloList(inputText);
            String formattedText = generateXml(veloList); outputTextField.setText(formattedText);
            saveFormattedText(formattedText, "xml");
        } else if (jsonOutput.isSelected()) {
            List<ConcreteVelo> veloList = parseVeloList(inputText);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String formattedText = gson.toJson(Map.of("bikes", veloList));
            outputTextField.setText(formattedText);
            saveFormattedText(formattedText, "json");
        }
    }


    private void saveFormattedText(String text, String extension) {
        try {
            Files.write(Paths.get(filePath + "_formatted." + extension), text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void saveAsArchive() {
        String fileExtension = txtOutput.isSelected() ? ".txt" : xmlOutput.isSelected() ? ".xml" : ".json";
        String fileToArchive = filePath + "output" + fileExtension;
        String destinationFolder = "C:/from_past_pc/Labs 2 grade/JavaStudentProjects/JavastudentProjects/Laba6/";
        // Создание папки, если она не существует
        File folder = new File(destinationFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String archiveFilePath;
        if (jarBox.isSelected()) {
            archiveFilePath = destinationFolder + new File(fileToArchive).getName() + ".jar";
            try {
                JarFile.jarFile(fileToArchive, archiveFilePath);
                outputTextField.setText("JAR архив создан в " + archiveFilePath);
            } catch (Exception e) {
                e.printStackTrace();
                outputTextField.setText("Ошибка с созданием JAR архива");
            }
        } else if (zipBox.isSelected()) {
            archiveFilePath = destinationFolder + new File(fileToArchive).getName() + ".zip";
            try {
                ZipFile.zipFile(fileToArchive, archiveFilePath);
                outputTextField.setText("ZIP архив создан в " + archiveFilePath);
            } catch (Exception e) {
                e.printStackTrace();
                outputTextField.setText("Ошибка с созданием ZIP архива");
            }
        }
    }


    private void saveHashAndEncrypt() throws Exception {
        String data = inputTextField.getText();
     if (shifrBox.isSelected()) {
         EncryptionUtils.encrypt(data);
     } else if (hashBox.isSelected()) {
          HashUtils.hashData(data);
     }

    }
}
