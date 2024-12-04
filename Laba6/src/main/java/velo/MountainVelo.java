package velo;

import java.util.Date;

public class MountainVelo extends AbstractVelo{
    public MountainVelo(int id, Date date, String type, String model, double price, double max_speed){
        super(id,date,type,model,price,max_speed);
    }
    @Override
    public String toString() {
        return "MountainVelo [id=" + getID() + ", date=" + getDATE() + ", type=" + getTYPE() + ",model=" + getMODEL() + ",price=" + getPRICE() + ",max_speed=" + getMAX_SPEED() + "]";
    }
}
