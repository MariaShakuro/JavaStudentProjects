package velo;

import java.util.Date;

public class Manual {
    private final int id;
    private final Date date;
    private final String type;
    private final String model;
    private final double price;
    private final double max_speed;

    public Manual(int id, Date date, String type, String model, double price, double maxSpeed) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.model = model;
        this.price = price;
        max_speed = maxSpeed;
    }
    @Override
    public String toString(){
        String info="";
        info += "Id:" + id + "\n";
        info += "Date of release:" + date + "\n";
        info +="Type of velo: " + type + "\n";
        info += "Model of velo:" + model + "\n";
        if(this.price != null){
            info += "Price of velo:" + "\n";
        }else  info += "Marked down velo:" + "\n";
        info += "Max speed of velo:" + max_speed + "\n";
       return info;
    }
}
