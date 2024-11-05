package velo;
public class PriceUtils {
    public static double extractPrice(String item) {
        try {
            String[] parts = item.split("Price ");
            if (parts.length > 1) {
                String priceStr = parts[1].split(" ")[0].trim();
                return Double.parseDouble(priceStr.replace("USD","").trim());
            } else {
                throw new IllegalArgumentException("Price not found in the input string");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Failed to extract price from input: " + item);
            return 0.0;
        }
    }
}
