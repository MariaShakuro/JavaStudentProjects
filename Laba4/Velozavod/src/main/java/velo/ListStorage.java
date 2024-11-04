package velo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListStorage<T> extends AbstractVelo<T> implements Iterable<T> {
    private List<T> list = new ArrayList<>();

    public void add(T item) {
        list.add(item);
    }

    @Override
    public void addToList(T item) {
        list.add(item);
    }

    @Override
    public void addToMap(String key, T item) {
    }

    @Override
    public List<T> getList() {
        return new ArrayList<>(list);//copy  of list
    }

    @Override
    public Map<String, T> getMap() {
        return Map.of();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    // Функция сортировки
    public void sortByField(String field) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                String str1 = (String) o1;
                String str2 = (String) o2;

                // Используем регулярное выражение для корректного парсинга строки
                Pattern pattern = Pattern.compile(
                        "Id\\s(\\d+), Date\\s([\\d-]+), Type\\s([^,]+), Model\\s([^,]+), Price\\s([\\d.]+)\\sUSD, Max_Speed\\s([\\d.]+)");
                Matcher matcher1 = pattern.matcher(str1);
                Matcher matcher2 = pattern.matcher(str2);

                if (matcher1.find() && matcher2.find()) {
                    int id1 = Integer.parseInt(matcher1.group(1));
                    int id2 = Integer.parseInt(matcher2.group(1));

                    Date date1 = DateUtils.parseDate(matcher1.group(2));
                    Date date2 = DateUtils.parseDate(matcher2.group(2));

                    String type1 = matcher1.group(3);
                    String type2 = matcher2.group(3);

                    String model1 = matcher1.group(4);
                    String model2 = matcher2.group(4);

                    double price1 = Double.parseDouble(matcher1.group(5));
                    double price2 = Double.parseDouble(matcher2.group(5));

                    double maxSpeed1 = Double.parseDouble(matcher1.group(6));
                    double maxSpeed2 = Double.parseDouble(matcher2.group(6));

                    switch (field.toLowerCase()) {
                        case "id":
                            return Integer.compare(id1, id2);
                        case "date":
                            return date1.compareTo(date2);
                        case "type":
                            return type1.compareTo(type2);
                        case "model":
                            return model1.compareTo(model2);
                        case "price":
                            return Double.compare(price1, price2);
                        case "max_speed":
                            return Double.compare(maxSpeed1, maxSpeed2);
                        default:
                            return 0;
                    }
                }
                return 0;
            }
        });
    }

}