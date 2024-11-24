package velo;

import java.util.Date;

public class RoadVelo extends AbstractVelo{
    public RoadVelo(int id,Date date,String type,String model,double price,double max_speed){
        super(id,date,type,model,price,max_speed);
    }
    @Override
    public String toString() {
        return "RoadVelo [id=" + getID() + ", date=" + getDATE() + ", type=" + getTYPE() + ",model=" + getMODEL() + ",price=" + getPRICE() + ",max_speed=" + getMAX_SPEED() + "]";
    }
}
