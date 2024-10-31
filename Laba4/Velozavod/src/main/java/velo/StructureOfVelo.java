package velo;

import com.google.gson.annotations.Expose;
import main.EncryptionUtils;
import main.HashUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StructureOfVelo<T> extends AbstractVelo<T> implements Comparator<StructureOfVelo> {
    private int id;
    private Date date;
    private String type,model;
    private double price,max_speed;

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
    //public void setDATE(Date date){this.date=date;}
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

    public String hashData(String data) throws NoSuchAlgorithmException, IOException {
        return HashUtils.hashData(data);
    }

    public String encrypt(String data) throws Exception {
        return EncryptionUtils.encrypt(data);
    }

    public String decrypt(String encryptedData) throws Exception {
        return EncryptionUtils.decrypt(encryptedData);
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
