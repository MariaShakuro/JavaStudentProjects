package velo;

import java.util.Date;

public class ConcreteVelo extends AbstractVelo<ConcreteVelo>{
    public ConcreteVelo(int id, Date date,String type,String model,double price,double max_speed){
        super(id,date,type,model,price,max_speed);
    }
    @Override
    public String toString() {
        return "ConcreteVelo{" +
                "id=" + id +
        ", date=" + date +
        ", type=" + type +
                ",model=" + model +
                 ", price=" + price +
        ", max_speed=" + max_speed+
                "}";
    }
}
