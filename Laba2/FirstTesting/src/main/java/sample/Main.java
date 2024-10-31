package sample;
import java.util.Formatter;
import java.util.Scanner;
import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        out.print("Введите x:");
        double x = sc.nextDouble();
        out.print("Введите k:");
        try {
            int k = sc.nextInt();
            double E = Math.pow(10, -k);

            if (k <= 0) {
                throw new Exception("К может быть только натуральным");
            }

            TaylorSeries taylorSeries = new TaylorSeries();
            double tailorResult = taylorSeries.taylor_sinh(x, E);
            double formulaResult = Math.sinh(x);

            double difference = formulaResult - tailorResult;
            out.println("Отличие двух рядов на:" + difference);

            Formatter formatter = new Formatter();
            formatter.format("Приближенное значение: %." + (k) + "f%n", tailorResult);
            formatter.format("Точное значение: %." + (k) + "f%n", formulaResult);

            int intValue = (int) tailorResult;
            formatter.format("Восьмеричное значение: %o%n", intValue);
            formatter.format("Шестнадцатеричное значение: %x%n", intValue);

            formatter.format("Форматированное значение: %+0#20." + (k) + "f%n", tailorResult);

            out.print(formatter.toString());
            formatter.close();
            out.close();
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}