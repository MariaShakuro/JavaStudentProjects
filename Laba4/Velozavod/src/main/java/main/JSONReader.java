package main;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import velo.DateDeserializer;
import velo.StructureOfVelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JSONReader {
    public static List<StructureOfVelo<String>> readBikesfromJSON(String filePath) throws FileNotFoundException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();

        Map<String, List<StructureOfVelo<String>>> jsonMap = gson.fromJson(new FileReader(filePath), new TypeToken<Map<String, List<StructureOfVelo<String>>>>() {}.getType());
        return jsonMap.get("bikes");

    }
}

