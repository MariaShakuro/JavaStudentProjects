package velo;

import java.util.Date;

public class Velo {
    private final int id;
    private final Date date;
    private final String type;
    private final String model;
    private final double price;
    private final double max_speed;

    public Velo(int id, Date date, String type, String model,
                double price, double max_speed, int id1, Date date1, String type1, String model1, double price1, double maxSpeed){

        this.id = id1;
        this.date = date1;
        this.type = type1;
        this.model = model1;
        this.price = price1;
        this.max_speed = maxSpeed;
    }
    public int getID(){return id;}
    public Date getDATE(){return date;}
    public String getTYPE(){return type;}
    public String getMODEL(){return model;}
    public double getPRICE(){return price;}
    public double getMAX_SPEED(){return max_speed;}
}
