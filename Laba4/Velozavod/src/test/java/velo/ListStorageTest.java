package velo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListStorageTest {
   private static ListStorage<String>listStorage;
    @BeforeAll
    public static void setUp(){
   listStorage=new ListStorage<>();
        listStorage.add("Id 004, Date 2022-11-05, Type Электровелосипед, Model Specialized Turbo Vado 4.0, Price 3500 USD, Max_Speed 45");
        listStorage.add("Id 005, Date 2023-01-25, Type Фэтбайк, Model Salsa Mukluk, Price 1800 USD, Max_Speed 40");
        listStorage.add("Id 006, Date 2022-05-14, Type Горный велосипед, Model Cannondale TDR 12, Price 2200 USD, Max_Speed 35");
        listStorage.add("Id 001, Date 2022-03-15, Type Горный велосипед, Model Trek Marlin 7, Price 850 USD, Max_Speed 45");
        listStorage.add("Id 002, Date 2023-05-10, Type Шоссейный велосипед, Model Giant TCR Advanced 1, Price 2000 USD, Max_Speed 60");
        listStorage.add("Id 003, Date 2021-08-20, Type Городской велосипед, Model Cannondale Quick 4, Price 700 USD, Max_Speed 35");
    }
    @Test
    void addToList() {
        listStorage.addToList("Id 007,Date 2022-12-01,Type Велосипед,Model Test Model,Price 500 USD,Max_Speed 25");
        List<String>list=listStorage.getList();
        assertTrue(list.contains("Id 007,Date 2022-12-01,Type Велосипед,Model Test Model,Price 500 USD,Max_Speed 25\""));
    }
    @Test
    void getList() {
        List<String>list=listStorage.getList();
        assertEquals(6,list.size());
    }
    @Test
    void iterator() {
        int count=0;
        for(String item: listStorage){
           count++;
        }
        assertEquals(6,count);
    }
    @Test
    void sortByField(){
        listStorage.sortByField("id");
        List<String> sortedList = listStorage.getList();
        List<String> expectedList = Arrays.asList("Id 001, Date 2022-03-15, Type Горный велосипед, Model Trek Marlin 7, Price 850 USD, Max_Speed 45",
                "Id 002, Date 2023-05-10, Type Шоссейный велосипед, Model Giant TCR Advanced 1, Price 2000 USD, Max_Speed 60",
                "Id 003, Date 2021-08-20, Type Городской велосипед, Model Cannondale Quick 4, Price 700 USD, Max_Speed 35",
                "Id 004, Date 2022-11-05, Type Электровелосипед, Model Specialized Turbo Vado 4.0, Price 3500 USD, Max_Speed 45",
                "Id 005, Date 2023-01-25, Type Фэтбайк, Model Salsa Mukluk, Price 1800 USD, Max_Speed 40",
                "Id 006, Date 2022-05-14, Type Горный велосипед, Model Cannondale TDR 12, Price 2200 USD, Max_Speed 35");
        assertEquals(sortedList,expectedList);
    }
}