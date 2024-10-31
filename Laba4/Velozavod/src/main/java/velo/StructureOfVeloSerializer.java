package velo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StructureOfVeloSerializer implements JsonSerializer<StructureOfVelo> {
    @Override
    public JsonElement serialize(StructureOfVelo structureOfVelo, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", structureOfVelo.getID());
        jsonObject.addProperty("date", String.valueOf(structureOfVelo.getDATE()));
        jsonObject.addProperty("type", structureOfVelo.getTYPE());
        jsonObject.addProperty("model", structureOfVelo.getMODEl());
        jsonObject.addProperty("price", structureOfVelo.getPRICE());
        jsonObject.addProperty("max_speed", structureOfVelo.getMAX_SPEED());
        return jsonObject;
    }
}
