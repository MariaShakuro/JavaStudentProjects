package velo;

public class PriceUtils {
    public static double extractPrice(String item) {
        // Предполагается, что цена - это часть строки, например, "Item: XYZ, Price: 100.0"
        String[] parts = item.split("Price: ");
        return Double.parseDouble(parts[1].trim());
    }
}
