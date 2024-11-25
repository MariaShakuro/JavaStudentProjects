package main;
import velo.AbstractVelo;
import velo.MountainVelo;
import velo.RoadVelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile {

    public static List<AbstractVelo> readFile(String filepath) {
        List<AbstractVelo> bikes = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String line;
            Pattern pattern = Pattern.compile(
                    "Id\\s(\\d+),\\sDate\\s([\\d-]+),\\sType\\s([^,]+),\\sModel\\s([^,]+),\\sPrice\\s([\\d.]+)\\sUSD,\\sMax_Speed\\s([\\d.]+)"
            );
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                // Разбор строки и создание объекта AbstractVelo
                if (matcher.find()) {
                    int id = Integer.parseInt(matcher.group(1));
                    Date date = parseDate(matcher.group(2), dateFormat);
                    String type = matcher.group(3);
                    String model = matcher.group(4);
                    double price = Double.parseDouble(matcher.group(5));
                    double maxSpeed = Double.parseDouble(matcher.group(6));

                    AbstractVelo bike;
                    if ("Road".equalsIgnoreCase(type)) {
                        bike = new RoadVelo(id, date, type, model, price, maxSpeed);
                    } else if ("Mountain".equalsIgnoreCase(type)) {
                        bike = new MountainVelo(id, date, type, model, price, maxSpeed);
                    } else {
                        System.out.println("Некорректный тип: " + type + ". Строка пропущена: " + line);
                        continue;
                    }
                    bikes.add(bike);
                } else {
                    System.out.println("Некорректная строка пропущена: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + filepath);
        }
        return bikes;
    }

    private static Date parseDate(String dateStr, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Некорректная дата: " + dateStr);
            return null;
        }
    }
}

