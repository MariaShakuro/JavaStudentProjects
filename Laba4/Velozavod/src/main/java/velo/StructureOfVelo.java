package velo;

import main.EncryptionUtils;
import main.HashUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class StructureOfVelo<T> extends AbstractVelo<T> implements Comparator<StructureOfVelo> {
    private int id;
    private Date date;
    private String type,model;
    private double price,max_speed;

    @Override
    public int compare(StructureOfVelo o1, StructureOfVelo o2) {
        return 0;
    }


    public void set(int id,Date date,String type,String model,double price,double max_speed){
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
    public Date getDATE(){
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

   /* public String hashData(String data) throws NoSuchAlgorithmException, IOException {
        return HashUtils.hashData(data);
    }

    public String encrypt(String data) throws Exception {
        return EncryptionUtils.encrypt(data);
    }

    public String decrypt(String encryptedData) throws Exception {
        return EncryptionUtils.decrypt(encryptedData);
    }*/
    @Override
   public String toString(){
        return String.format("StructureOfVelo{id=%d,date=%s,type=%s,model=%s,price=%f,max_speed=%f}", id, date.toString(), type, model, price, max_speed);}
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
