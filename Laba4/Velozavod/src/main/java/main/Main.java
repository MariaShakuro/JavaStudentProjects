package main;
import velo.*;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static java.lang.System.out;
import static main.XMLReader.readBikes;

public class  Main {
    private static List<StructureOfVelo> list;
    public static void main(String[] args)  {
        list = new ArrayList<>();
        try{
            out.println("Выберите формат ввода данных:\n1 - JSON\n2 - XML\n3 - TXT");
            Scanner in=new Scanner(System.in);
            int inputChoice = in.nextInt(); in.nextLine();
            String inputFormat = "";
            switch (inputChoice) {
                case 1:
                    inputFormat = "JSON";
                    break;
                    case 2:
                        inputFormat = "XML";
                        break;
                        case 3: inputFormat = "TXT";
                        break;
                        default:
                            System.out.println("Неверный выбор");
                            return;
            }

            ListStorage<String> listStorage = new ListStorage<>();
            MapStorage<String> mapStorage = new MapStorage<>();
            List<StructureOfVelo<String>> bikes = null;

            // Прочитать данные из исходного файла в зависимости от формата
            if (inputFormat.equals("JSON")) {
                bikes = JSONReader.readBikesfromJSON("input.json");
                listStorage = convertListToStorage(bikes);
            } else if (inputFormat.equals("XML")) {
               bikes= readBikes("input.xml");
                listStorage = convertListToStorage(bikes);
            } else {
               // ReadFromFile read = new ReadFromFile("input.txt");
                bikes =ReadFromFile.readFile("input.txt");
                listStorage = convertListToStorage(bikes);
            }


            // Перебор всех элементов для хэширования, шифрования и дешифрования
            for (StructureOfVelo<String> bike : bikes) {
                String data = bike.toString();
                // Хэширование
                String hashedData = HashUtils.hashData(data);
                System.out.println("Hashed data: " + hashedData);
                // Шифрование
                String encryptedData = EncryptionUtils.encrypt(data);
                System.out.println("Encrypted data: " + encryptedData);
                // Дешифрование
                String decryptedData = EncryptionUtils.decrypt(encryptedData);
                System.out.println("Decrypted data: " + decryptedData);
            }


            out.println("Выберите формат вывода данных:\n1 - JSON\n2 - XML\n3 - TXT");
            int choice=in.nextInt();
            in.nextLine();
            String format ="";
            String outputFilePath = "";
            switch (choice) {
                case 1:
                    format = "JSON";
                    outputFilePath = "output.json";
                    break;
                    case 2:
                        format = "XML";
                        outputFilePath = "output.xml";
                        break;
                        case 3:
                            format = "TXT";
                            outputFilePath = "output.txt";
                            break;
                            default:
                                System.out.println("Неверный выбор");
                                return;
            }
            // Прочитать данные из исходного файла в зависимости от формата
            if (format.equals("JSON")) JSONWriter.writeBikes(outputFilePath, bikes);
            else if (format.equals("XML")) XMLWriter.writeBikes(outputFilePath, bikes);
            else {
                 WriteToFile.WriteToFile(outputFilePath,bikes);
            }

            //Итераторный вывод List
            Iterator<String> listIterator = listStorage.iterator();
            StringBuilder listOutput=new StringBuilder();
            listOutput.append("List items\n");
            while(listIterator.hasNext()){
                String listValue=listIterator.next();
                listOutput.append(listValue).append("\n");
            }
           // WriteToFile.WriteToFile(outputFilePath,bikes);
           // write.WriteToFile(listOutput.toString());

            //Итераторный вывод Map
            Iterator<Map.Entry<String, String>> mapIterator = mapStorage.iterator();
            StringBuilder mapoutput=new StringBuilder();
            mapoutput.append("Map items\n");
            while(mapIterator.hasNext()) {
                Map.Entry<String, String> entry = mapIterator.next();
                String mapValue = entry.getKey() + ":" + entry.getValue();
                mapoutput.append(mapValue).append("\n");
            }
            // write.WriteToFile(mapoutput.toString());

            out.println("Выберите поле для сортировки:1-id,2-date,3-type,4-model,5-price,6-max_speed");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    SortVelo.sortId(list);
                    break;
                case 2:
                    SortVelo.sortDate(list);
                    break;
                case 3:
                    SortVelo.sortType(list);
                    break;
                case 4:
                    SortVelo.sortModel(list);
                    break;
                case 5:
                    SortVelo.sortPrice(list);
                    break;
                case 6:
                    SortVelo.sortMax_speed(list);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }

            // Выводим отсортированный список
            System.out.println("Отсортированный список:");
            for (StructureOfVelo velo : list) {
                System.out.println(velo);
            }


            //  write.WriteToFile(sortedOut.toString());

            // Архивация файлов
            ZipFile.zipFile(outputFilePath, "output.zip");
            JarFile.jarFile(outputFilePath, "output.jar");

        //Консольный ввод
       System.out.println("Enter data for List and Map(id,date,type,model,price,max_speed):");
        while (true) {
            String input = in.nextLine();
            String[] parts = input.split(",");
            if (parts.length == 6) {
                StructureOfVelo<String> newBike = new StructureOfVelo<>();
                Date date = DateUtils.parseDate(parts[1].trim());
                newBike.set(Integer.parseInt(parts[0].trim()), date, parts[2].trim(), parts[3].trim(), Double.parseDouble(parts[4].trim()), Double.parseDouble(parts[5].trim()));
                bikes.add(newBike);
                if (format.equals("JSON")) {
                    JSONWriter.writeBikes(outputFilePath, bikes);
                } else if (format.equals("XML")) {
                    XMLWriter.writeBikes(outputFilePath, bikes);
                } else {
                    WriteToFile.writeEntry(outputFilePath, newBike);
                }
                //String mapEntry = String.format("Map:Id: %s, Date: %s, Type: %s, Model: %s, Price: %s, Max_Speed: %s\n", id, date, type, model, price, maxSpeed);
               // mapStorage.put(id, mapEntry);
           } else {
                out.println("Invalid input");
            }
        }
        }
    catch(NoSuchElementException e){
        e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 private static ListStorage<String> convertListToStorage(List<StructureOfVelo<String>> bikes) {
        ListStorage<String> storage = new ListStorage<>();
  for (StructureOfVelo<String> bike : bikes) {
      storage.add(bike.toString());
  }
  return storage;
    }
    private static List<StructureOfVelo<String>> convertStorageToList(ListStorage<String> storage) {
        List<StructureOfVelo<String>> bikes = new ArrayList<>();
        for (String item : storage.getList()) {
            // Используем регулярное выражение для корректного парсинга
            String[] parts = item.split("[:=,]+");
            if (parts.length == 12) {
                StructureOfVelo<String> bike = new StructureOfVelo<>();
                Date date = DateUtils.parseDate(parts[4].trim());
                bike.set(Integer.parseInt(parts[2].trim()), date, parts[6].trim(), parts[8].trim(), Double.parseDouble(parts[10].trim()), Double.parseDouble(parts[12].trim()));
                bikes.add(bike);
            }
        }
        return bikes;
    }
}
