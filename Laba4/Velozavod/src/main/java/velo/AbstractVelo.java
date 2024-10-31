package velo;

import java.util.*;

abstract class AbstractVelo<T>  {
   protected List<T> ListStorage=new ArrayList<>();
   protected Map<String,T> MapStorage=new HashMap<>();
   public abstract void addToList(T item);
   public abstract void addToMap(String key,T item);
   public abstract List<T>getList();
   public abstract Map<String,T>getMap();
}
