package velo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapStorage<T> extends AbstractVelo<T> implements Iterable<Map.Entry<String,T>>{
   private Map<String,T>map=new HashMap<>();
   public void put(String key,T item){
       map.put(key,item);
   }
    @Override
    public void addToList(T item) {

    }

    @Override
    public void addToMap(String key, T item) {
    map.put(key,item);
    }

    @Override
    public List<T> getList() {
        return List.of();
    }

    @Override
    public Map<String, T> getMap() {
        return Map.of();
    }

    @Override
    public Iterator<Map.Entry<String,T>> iterator() {
        return map.entrySet().iterator();
    }
}
