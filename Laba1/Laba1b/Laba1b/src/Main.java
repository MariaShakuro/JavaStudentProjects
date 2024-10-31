import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(System.out));
        out.write("Введите x:");
        out.flush();
        Scanner scanner=new Scanner(in);
        BigDecimal x = scanner.nextBigDecimal();
        out.write("Введите k:");
        out.flush();
        try {
            BigInteger k = scanner.nextBigInteger();
            if (k.compareTo(BigInteger.ZERO) <= 0) {
                throw new Exception("К может быть только натуральным");
            }

          //  BigDecimal E = BigDecimal.TEN.pow((k.intValue());
            BigDecimal E = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k.intValue()));
            TaylorSeries taylorSeries = new TaylorSeries();
            BigDecimal tailorResult = taylorSeries.taylor_sinh(x, E);
            double formulaResult = Math.sinh(x.doubleValue());

            BigDecimal difference =
                    BigDecimal.valueOf(formulaResult).subtract(tailorResult);
            out.write("Отличие двух рядов на:" + difference);
            out.newLine();
            out.flush();

            Formatter formatter = new Formatter(out);
            formatter.format("Приближенное значение: %." + (k.intValue()) + "f%n", tailorResult);
            formatter.flush();
            formatter.format("Точное значение: %." + (k.intValue()) + "f%n", formulaResult);
            formatter.flush();

            BigInteger intValue =tailorResult.toBigInteger();
            formatter.format("Восьмеричное значение: %o%n", intValue);
            formatter.flush();
            formatter.format("Шестнадцатеричное значение: %x%n", intValue);
            formatter.flush();

            formatter.format("Форматированное значение: %+0#20." + (k.intValue()) + "f%n", tailorResult);
            formatter.flush();

            out.write(formatter.toString());
            out.flush();

        } catch (Exception e) {
            out.write("problem somewhere: " + e.getMessage());
            out.newLine();
            out.flush();
        }
    }
}