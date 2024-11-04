package main;

import velo.DateUtils;
import velo.StructureOfVelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile {
    public static List<StructureOfVelo<String>> readFile(String filepath)throws FileNotFoundException{
        List<StructureOfVelo<String>> bikes = new ArrayList<>();
        try(BufferedReader in=new BufferedReader(new FileReader(filepath))){
           String line;
            Pattern pattern=Pattern.compile(
                    "Id\\s(\\d+),Date\\s([\\d-]+),Type\\s([^,]+),Model\\s([^,]+),Price\\s([\\d.]+)\\sUSD,Max_Speed\\s(\\d+)"
            );
           while((line =in.readLine())!=null){
               Matcher matcher=pattern.matcher(line);
               // Разбор строки и создание объекта StructureOfVelo
               if (matcher.find()) {
                   int id = Integer.parseInt(matcher.group(1));
                   Date date = DateUtils.parseDate(matcher.group(2));
                   String type = matcher.group(3);
                   String model = matcher.group(4);
                   double price = Double.parseDouble(matcher.group(5));
                   double max_speed = Double.parseDouble(matcher.group(6));

                   StructureOfVelo<String> bike = new StructureOfVelo<>();
                   bike.set(id, date, type, model, price, max_speed);
                   bikes.add(bike);
               }
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bikes;
    }
}
