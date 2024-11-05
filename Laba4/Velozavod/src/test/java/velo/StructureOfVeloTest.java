package velo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructureOfVeloTest {
    private StructureOfVelo<String> velo;
    private int id;
    private Date date;
    private String type;
    private String model;
    private double price;
    private double max_speed;
    @BeforeEach
    public void setUp(){
        velo=new StructureOfVelo<>();
        id=11;
        date=new Date();
        type="ddfdf";
        model="ggggggg";
        price=20.4;
        max_speed=23.0;
        velo.set(id,date,type,model,price,max_speed);
    }

    @Test
    void getID(){
      assertEquals(id,velo.getID(),"Need to be equals in id");
    }
    @Test
    void getDATE(){
      assertEquals(date,velo.getDATE(),"Need to be equals in date");
    }
    @Test
    void getTYPE(){
        assertEquals(type,velo.getTYPE(),"Need to be equals in type");
    }
    @Test
    void getMODEL(){
        assertEquals(model,velo.getMODEl(),"Need to be equals in model");
    }
    @Test
    void getPRICE(){
        assertEquals(price,velo.getPRICE(),"Need to be equals in price");
    }
    @Test
    void getMAX_SPEED(){
        assertEquals(max_speed,velo.getMAX_SPEED(),"Need to be equals in max speed");
    }
}
