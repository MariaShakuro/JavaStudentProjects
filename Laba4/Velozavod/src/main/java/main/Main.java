package main;
import velo.*;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static java.lang.System.out;
import static main.XMLReader.readBikes;
import static main.XMLWriter.writeBikes;

public class Main {
    public static void main(String[] args)  {
        try{
            String data = "Some data";
            //Хэширование
            String hashedData = HashUtils.hashData(data);
            System.out.println("Hashed data: " + hashedData);
            //Шифрование
            String encryptedData = EncryptionUtils.encrypt(data);
            System.out.println("Encrypted data: " + encryptedData);
            //Дешифрование
            String decryptedData = EncryptionUtils.decrypt(encryptedData);
            System.out.println("Decrypted data: " + decryptedData);

            // Прочитать данные из исходного XML-файла
            List<StructureOfVelo<String>> bikes_= readBikes("Input.xml");
            // Прочитать данные из исходного Json-файла
            List<StructureOfVelo<String>> bikes = JSONReader.readBikesfromJSON("INPUT.json");

            // Добавить новый велосипед
            StructureOfVelo<String> newBike = new StructureOfVelo<>();
            Date date_=DateUtils.parseDate("2023-03-10");
            newBike.set(7, date_, "Шоссейный велосипед", "Giant TCR", 2000.0, 55.0);
            bikes.add(newBike);

            // Записать данные в новый XML-файл
            writeBikes("Output.xml", bikes_);
           // Записать данные в новый Json-файл
            JSONWriter.writeBikes("OUTPUT.json",bikes);

            ListStorage<String> listStorage = new ListStorage<>();
            MapStorage<String> mapStorage = new MapStorage<>();
            ReadFromFile read = new ReadFromFile("input.txt");
            read.readFile(listStorage, mapStorage);

            listStorage.sortByPrice();
            //Запись в файл
            WriteToFile write = new WriteToFile("output.txt");

            //Итераторный вывод List
            Iterator<String> listIterator = listStorage.iterator();
            StringBuilder listoutput=new StringBuilder();
            listoutput.append("List items\n");
            while(listIterator.hasNext()){
                String listValue=listIterator.next();
                listoutput.append(listValue).append("\n");
            }
            write.WriteToFile(listoutput.toString());

            //Итераторный вывод Map
            Iterator<Map.Entry<String, String>> mapIterator = mapStorage.iterator();
            StringBuilder mapoutput=new StringBuilder();
            mapoutput.append("Map items\n");
            while(mapIterator.hasNext()) {
                Map.Entry<String, String> entry = mapIterator.next();
                String mapValue = entry.getKey() + ":" + entry.getValue();
                mapoutput.append(mapValue).append("\n");
            }
            write.WriteToFile(mapoutput.toString());
        //Консольный ввод
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data for List and Map(id,date,type,model,price,max_speed):");
        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(",");
            if (parts.length == 6) {
                String id = parts[0].trim();
                String date = parts[1].trim();
                String type = parts[2].trim();
                String model = parts[3].trim();
                String price = parts[4].trim();
                String maxSpeed = parts[5].trim();

                String listEntry = String.format("\nList:Id %s, Date %s, Type %s, Model %s, Price %s, Max_Speed %s\n", id, date, type, model, price, maxSpeed);
                listStorage.add(listEntry);
                write.WriteToFile(listEntry);

                String mapEntry = String.format("Map:Id: %s, Date: %s, Type: %s, Model: %s, Price: %s, Max_Speed: %s\n", id, date, type, model, price, maxSpeed);
                mapStorage.put(id, mapEntry);
                write.WriteToFile(mapEntry);

                out.println("Сортировка по полю price");
                StringBuilder sortedOut=new StringBuilder();
                for(String sortedEntry:listStorage.getList()){
                    sortedOut.append(sortedEntry).append("\n");
                    out.println(sortedEntry);
                }
              //  write.WriteToFile(sortedOut.toString());
            } else {
                out.println("Invalid input");
            }
        }
    }catch(NoSuchElementException e){
        e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}