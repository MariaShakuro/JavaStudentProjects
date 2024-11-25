package main;

import com.google.gson.*;
import velo.AbstractVelo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONWriter {

    public static void writeBikes(String filePath, List<AbstractVelo> bikes) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(AbstractVelo.class, new AbstractVeloSerializer());
        Gson gson = gsonBuilder.create();

        Map<String, List<AbstractVelo>> jsonMap = new HashMap<>();
        jsonMap.put("bikes", bikes);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(jsonMap, writer);
            System.out.println("Данные успешно записаны в " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка записи JSON: " + e.getMessage());
        }
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public JsonElement serialize(Date src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(dateFormat.format(src));
        }
    }

    private static class AbstractVeloSerializer implements JsonSerializer<AbstractVelo> {
        @Override
        public JsonElement serialize(AbstractVelo src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.getID());
            jsonObject.addProperty("date", new SimpleDateFormat("yyyy-MM-dd").format(src.getDATE()));
            jsonObject.addProperty("type", src.getTYPE());
            jsonObject.addProperty("model", src.getMODEL());
            jsonObject.addProperty("price", src.getPRICE());
            jsonObject.addProperty("max_speed", src.getMAX_SPEED());
            return jsonObject;
        }
    }
}
