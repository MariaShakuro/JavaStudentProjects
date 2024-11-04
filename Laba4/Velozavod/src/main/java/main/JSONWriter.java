package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import velo.StructureOfVelo;
import velo.StructureOfVeloSerializer;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWriter{
    public static void writeBikes(String filePath, List<StructureOfVelo<String>> bikes) {
       // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().registerTypeAdapter(StructureOfVelo.class,
                new StructureOfVeloSerializer()).setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(bikes, writer);
            System.out.println("JSON file has been successfully written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



























