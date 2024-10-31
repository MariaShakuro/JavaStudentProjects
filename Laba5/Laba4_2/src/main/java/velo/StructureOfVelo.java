package velo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

class StructureOfVelo<T> extends AbstractVelo<T> implements Comparator<StructureOfVelo> {
    private int id,date;
    private String type,model;
    private double price,max_speed;

    public void set(int id,int date,String type,String model,double price,double max_speed){
        this.id=id;
        this.date=date;
        this.type=type;
        this.model=model;
        this.price=price;
        this.max_speed=max_speed;
    }

    public int getID(){
        return id;
    }
    public int getDATE(){
        return date;
    }
    public String getTYPE(){
        return type;
    }
    public String getMODEl(){
        return model;
    }
    public double getPRICE(){
        return price;
    }
    public double getMAX_SPEED(){
        return max_speed;
    }

    @Override
    public String toString(){
        return "StructureOfVelo{" + "id="+id+ ",date="+date+ ",type="+type+ ",model="+model+ ",price="+price+ ",max_speed="+max_speed+ "}";
    }
    @Override
    public int compare(StructureOfVelo o1, StructureOfVelo o2) {
        return Double.compare(o1.getPRICE(), o2.getPRICE());
    }


    @Override
    public void addToList(T item) {
        ListStorage.add(item);
    }

    @Override
    public void addToMap(String key, T item) {
        MapStorage.put(key,item);
    }

    @Override
    public List<T> getList() {
        return List.of();
    }

    @Override
    public Map<String, T> getMap() {
        return Map.of();
    }
}
