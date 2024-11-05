package velo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateUtilsTest {
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private static Date expectedDate;
    @BeforeAll
    public static void SetUp(){
        try{
           expectedDate=dateFormat.parse("2022-01-01");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void parseDate(){
    String dateS="2022-01-01";
    Date parseDate=DateUtils.parseDate(dateS);
    assertNotNull(parseDate,"Cannot be null");
    assertEquals(expectedDate,parseDate,"Should be equals");
    }
}
