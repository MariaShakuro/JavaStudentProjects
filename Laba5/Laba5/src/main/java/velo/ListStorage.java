package velo;

import java.util.ArrayList;
import java.util.List;

public class ListStorage<T> {
    private List<T>storage=new ArrayList<>();
    public void add(T item){
        storage.add(item);
    }
    public List<T>getAll(){
        return storage;
    }
    public void clear(){
        storage.clear();
    }
}
