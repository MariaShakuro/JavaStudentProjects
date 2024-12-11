package velo;

import java.util.List;

public class SortVelo {

    private static List<AbstractVelo> list;

    public static void sortId(List<ConcreteVelo> list) {
        list.sort((a, b) -> Integer.compare(a.getID(), b.getID()));
    }

    public static void sortDate(List<ConcreteVelo> list) {
        list.sort((a, b) -> a.getDATE().compareTo(b.getDATE()));
    }

    public static void sortType(List<ConcreteVelo> list) {
        list.sort((a, b) -> a.getTYPE().compareTo(b.getTYPE()));
    }

    public static void sortModel(List<ConcreteVelo> list) {
        list.sort((a, b) -> a.getMODEL().compareTo(b.getMODEL()));
    }

    public static void sortPrice(List<ConcreteVelo> list) {
        list.sort((a, b) -> Double.compare(a.getPRICE(), b.getPRICE()));
    }

    public static void sortMax_speed(List<ConcreteVelo> list) {
        list.sort((a, b) -> Double.compare(a.getMAX_SPEED(), b.getMAX_SPEED()));
    }
}
