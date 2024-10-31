package main;
import velo.ListStorage;
import velo.MapStorage;
import velo.ReadFromFile;
import velo.WriteToFile;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        ListStorage<String> listStorage = new ListStorage<>();
        MapStorage<String> mapStorage = new MapStorage<>();
        //Чтение данных из файла
        ReadFromFile read = new ReadFromFile("input.txt");
        read.readFile(listStorage, mapStorage);
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
        System.out.println("Enter data (id,date,type,model,price,max_speed):");
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
                listStorage.sortByPrice();
            } else {
                out.println("Invalid input");
            }
        }
    }
    }