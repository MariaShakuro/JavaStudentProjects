package velo;

import java.util.Date;

public abstract class AbstractVeloBuilder<T extends AbstractVelo, B extends AbstractVeloBuilder<T, B>> {
    protected int id;
    protected Date date;
    protected String type;
    protected String model;
    protected double price;
    protected double max_speed;

    public B setID(int id) {
        this.id = id;
        return (B) this;
    }

    public B setDATE(Date date) {
        this.date = date;
        return (B) this;
    }

    public B setTYPE(String type) {
        this.type = type;
        return (B) this;
    }

    public B setMODEL(String model) {
        this.model = model;
        return (B) this;
    }

    public B setPRICE(double price) {
        this.price = price;
        return (B) this;
    }

    public B setMAX_SPEED(double max_speed) {
        this.max_speed = max_speed;
        return (B) this;
    }

    public abstract T build();
}
