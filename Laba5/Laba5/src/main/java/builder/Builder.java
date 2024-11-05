package builder;

import java.util.Date;

public interface Builder {
 void setID(int id);
 void setDATE(Date date);
 void setTYPE(String type);
 void setMODEL(String model);
 void setPRICE(double price);
 void setMAX_SPEED(double max_speed);
}
