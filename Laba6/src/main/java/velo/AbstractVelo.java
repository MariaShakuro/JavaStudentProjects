package velo;

import java.util.*;

public abstract class AbstractVelo<T> {
    // protected List<T> ListStorage=new ArrayList<>();
    //protected Map<String,T> MapStorage=new HashMap<>();
    protected int id;
    protected Date date;
    protected String type;
    protected String model;
    protected double price;
    protected double max_speed;
    public AbstractVelo(int id, Date date, String type, String model, double price, double max_speed) {
        this.id=id;
        this.date=date;
        this.type=type;
        this.model=model;
        this.price=price;
        this.max_speed=max_speed;
    }
    public void setID(int id){
        this.id=id;
    }
    public void setDATE(Date date){
        this.date=date;
    }
    public void setTYPE(String type){
        this.type=type;
    }
    public void setMODEL(String model){
        this.model=model;
    }
    public void setPRICE(double price){
        this.price=price;
    }
    public void setMAX_SPEED(double max_speed){
        this.max_speed=max_speed;
    }

    public int getID() {return id;}
    public Date getDATE(){return date;}
    public String getTYPE(){return type;}
    public String getMODEL(){return model;}
    public double getPRICE(){return price;}
    public double getMAX_SPEED(){return max_speed;}
    @Override
    public abstract String toString();


}

