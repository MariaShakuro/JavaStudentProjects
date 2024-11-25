package main;

import velo.AbstractVelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    public static <T extends AbstractVelo<?>> void writeToFile(String filePath, List<T> bikes) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            for (T bike : bikes) {
                out.write(bikeToString(bike));
                out.newLine();
            }
            System.out.println("File has been successfully written!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends AbstractVelo<?>> void writeEntry(String filePath, T bike) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true))) {
            out.write(bikeToString(bike));
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T extends AbstractVelo<?>> String bikeToString(T bike) {
        return String.format("Id %d, Date %s, Type %s, Model %s, Price %.2f USD, Max_Speed %.2f",
                bike.getID(), bike.getDATE().toString(), bike.getTYPE(), bike.getMODEL(), bike.getPRICE(), bike.getMAX_SPEED());
    }
}


