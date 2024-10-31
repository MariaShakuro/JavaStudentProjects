package velo;

import java.io.IOException;
import java.util.*;

public class ListStorage<T> extends AbstractVelo<T> implements Iterable<T>{
   private List<T> list=new ArrayList<>();

   public void add(T item){
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
    // Функция сортировки по цене
    public void sortByPrice() {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                // Предполагается, что T - это строка с полем "Price"
                double price1 = PriceUtils.extractPrice((String) o1);
                double price2 = PriceUtils.extractPrice((String) o2);
                return Double.compare(price1, price2);
            }
        });
    }

}
