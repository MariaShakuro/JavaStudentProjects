package main;

import velo.StructureOfVelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import velo.StructureOfVelo;
public class WriteToFile {
    public static void  WriteToFile(String filePath, List<StructureOfVelo<String>> bikes){
       try(BufferedWriter out=new BufferedWriter(new FileWriter(filePath))){
        for(StructureOfVelo<String> bike : bikes){
   out.write(bikeToString(bike));
   out.newLine();
        }
           System.out.println("File has been successfully written!");
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    public static void writeEntry(String filePath, StructureOfVelo<String> bike) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true))) {
            out.write(bikeToString(bike));
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static  String bikeToString(StructureOfVelo<String> bike){
        return String.format("Id %d, Date %s, Type %s, Model %s, Price %.2f USD, Max_Speed %.2f",
                bike.getID(), bike.getDATE().toString(), bike.getTYPE(), bike.getMODEl(), bike.getPRICE(), bike.getMAX_SPEED());
    }
}
