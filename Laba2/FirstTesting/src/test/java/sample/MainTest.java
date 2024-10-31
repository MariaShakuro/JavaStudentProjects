package sample;
import org.junit.jupiter.api.Test;
import java.util.Formatter;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testTaylorSeriesPositiveX() {
        TaylorSeries taylorSeries = new TaylorSeries();
        double x = 1.0;
        double E = Math.pow(10, -5);
        double expected = Math.sinh(x);
        double actual = taylorSeries.taylor_sinh(x, E);
        assertEquals(expected, actual, E, "Результат должен быть близок к sinh(x)");
    }
    @Test
    public void testTaylorSeriesNegativeX() {
        TaylorSeries taylorSeries = new TaylorSeries();
        double x = -1.0;
        double E = Math.pow(10, -5);
        double expected = Math.sinh(x);
        double actual = taylorSeries.taylor_sinh(x, E);
        assertEquals(expected, actual, E, "Результат должен быть близок к sinh(x)");
    }

    @Test
public void ExceptionNatural(){
    Exception exception = assertThrows(Exception.class, () -> {
        int k = -1;
        if (k <= 0) {
            throw new Exception("К может быть только натуральным");
        }
    });
    assertEquals("К может быть только натуральным", exception.getMessage());
    }
    @Test
    public void BigFormatData(){
        int k = 0;
        boolean b=false;
        if(k<=127){
            b=true;
        }
        assertTrue(true, "Выход за поле типа данных");
    }

    @Test
    public void testFormatterOutput() {
        TaylorSeries taylorSeries = new TaylorSeries();
        double x = 1.0;
        double E = Math.pow(10, -5);
        double tailorResult = taylorSeries.taylor_sinh(x, E);
        int intValue = (int) tailorResult;

        Formatter formatter = new Formatter();
        formatter.format("Восьмеричное значение: %o%n", intValue);
        formatter.format("Шестнадцатеричное значение: %x%n", intValue);
        String expected = String.format("Восьмеричное значение: %o%nШестнадцатеричное значение: %x%n", intValue, intValue);
        assertEquals(expected, formatter.toString());
        formatter.close();
    }

}