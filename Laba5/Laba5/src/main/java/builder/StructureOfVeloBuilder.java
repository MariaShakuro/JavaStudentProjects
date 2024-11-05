package builder;
import velo.*;

import java.util.Date;

public class StructureOfVeloBuilder implements Builder{
    private int id;
    private Date date;
    private String type,model;
    private double price,max_speed;

    @Override
    public void setID(int id){this.id=id;}
    @Override
    public void setDATE(Date date){this.date=date;}
    @Override
    public void setTYPE(String type){this.type=type;}
    @Override
    public void setMODEL(String model){this.model=model;}
    @Override
    public void setPRICE(double price){this.price=price;}
    @Override
    public void setMAX_SPEED(double max_speed){this.max_speed=max_speed;}

    public Velo getResult(){
        return new Velo(id,date,type,model,price,max_speed);
    }
}
