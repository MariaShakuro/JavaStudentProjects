package velo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortVelo {
    private static List<StructureOfVelo> list;

    public static void sortId(List<StructureOfVelo> list) {
        list.sort((a, b) -> Integer.compare(a.getID(), b.getID()));
    }

    public static void sortDate(List<StructureOfVelo> list) {
        list.sort((a, b) -> a.getDATE().compareTo(b.getDATE()));
    }

    public static void sortType(List<StructureOfVelo> list) {
        list.sort((a, b) -> a.getTYPE().compareTo(b.getTYPE()));
    }

    public static void sortModel(List<StructureOfVelo> list) {
        list.sort((a, b) -> a.getMODEl().compareTo(b.getMODEl()));
    }

    public static void sortPrice(List<StructureOfVelo> list) {
        list.sort((a, b) -> Double.compare(a.getPRICE(), b.getPRICE()));
    }

    public static void sortMax_speed(List<StructureOfVelo> list) {
        list.sort((a, b) -> Double.compare(a.getMAX_SPEED(), b.getMAX_SPEED()));
    }
}
