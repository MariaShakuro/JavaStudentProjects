package velo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import velo.StructureOfVelo;
import static java.util.Collections.sort;

public class ListStorage<T> extends AbstractVelo<T> implements Iterable<T> {
    private  List <T> list = new ArrayList<>();

    public void add(T item) {
        list.add( item);
    }

    @Override
    public void addToList(T item) {
        list.add( item);
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
        return  list.iterator();
    }



}