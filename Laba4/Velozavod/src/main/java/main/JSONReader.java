package main;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import velo.DateDeserializer;
import velo.StructureOfVelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JSONReader {
    public static List<StructureOfVelo<String>> readBikesfromJSON(String filePath) throws FileNotFoundException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();

        Map<String, List<StructureOfVelo<String>>> jsonMap = gson.fromJson(new FileReader(filePath), new TypeToken<Map<String, List<StructureOfVelo<String>>>>() {}.getType());

        try {
            jsonMap = gson.fromJson(new FileReader(filePath), new TypeToken<Map<String, List<StructureOfVelo<String>>>>() {}.getType());
        } catch (JsonSyntaxException | JsonIOException e) {
            System.out.println("Ошибка чтения JSON: " + e.getMessage());
            return new ArrayList<>();
        }

        if (jsonMap != null && jsonMap.containsKey("bikes")) {
            return jsonMap.get("bikes");
        } else {
            System.out.println("Нет информации о байках в JSON.");
            return new ArrayList<>();
        }
    }
}

