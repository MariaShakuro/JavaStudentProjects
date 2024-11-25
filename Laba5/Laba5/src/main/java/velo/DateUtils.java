package velo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public static Date parseDate(String dateSting){
        try{
            return dateFormat.parse(dateSting);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}

