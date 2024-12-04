package org.example.laba6;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import velo.AbstractVelo;
import velo.RoadVelo;
import velo.MountainVelo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JSONReader {

    public static List<AbstractVelo> readBikesfromJSON(String filePath) throws FileNotFoundException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();

        Map<String, List<JsonObject>> jsonMap;
        try {
            jsonMap = gson.fromJson(new FileReader(filePath), new TypeToken<Map<String, List<JsonObject>>>() {}.getType());
        } catch (JsonSyntaxException | JsonIOException e) {
            System.out.println("Ошибка чтения JSON: " + e.getMessage());
            return new ArrayList<>();
        }

        List<AbstractVelo> bikes = new ArrayList<>();
        if (jsonMap != null && jsonMap.containsKey("bikes")) {
            List<JsonObject> jsonBikes = jsonMap.get("bikes");

            for (JsonObject jsonBike : jsonBikes) {
                int id = jsonBike.get("id").getAsInt();
                Date date = gson.fromJson(jsonBike.get("date"), Date.class);
                String type = jsonBike.get("type").getAsString();
                String model = jsonBike.get("model").getAsString();
                double price = jsonBike.get("price").getAsDouble();
                double max_speed = jsonBike.get("max_speed").getAsDouble();

                AbstractVelo bike;
                if ("Road".equalsIgnoreCase(type) || "Шоссейный велосипед".equalsIgnoreCase(type)) {
                    bike = new RoadVelo(id, date, type, model, price, max_speed);
                } else if ("Mountain".equalsIgnoreCase(type) || "Горный велосипед".equalsIgnoreCase(type)) {
                    bike = new MountainVelo(id, date, type, model, price, max_speed);
                } else {
                    System.out.println("Неизвестный тип: " + type);
                    continue;
                }
                bikes.add(bike);
            }
        } else {
            System.out.println("Нет информации о байках в JSON.");
        }

        return bikes;
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return dateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                throw new JsonParseException("Невозможно разобрать дату: " + json.getAsString(), e);
            }
        }
    }
}
