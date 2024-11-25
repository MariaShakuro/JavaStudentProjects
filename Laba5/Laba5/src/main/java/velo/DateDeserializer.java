package velo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try{
            String dateStr = jsonElement.getAsString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dateStr);
        }catch(ParseException e){
            throw new JsonParseException(e);
        }
    }
}

