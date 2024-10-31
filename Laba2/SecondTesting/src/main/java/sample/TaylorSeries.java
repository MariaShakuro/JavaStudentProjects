package sample;

import java.math.BigDecimal;
import java.math.MathContext;

class TaylorSeries {
    public BigDecimal taylor_sinh(BigDecimal x, BigDecimal E) {
        BigDecimal sum = x;
        BigDecimal term = x;
        int n = 1;
        MathContext mc=new MathContext(50);

        while (term.abs().compareTo(E)>=0) {
            BigDecimal denominator = BigDecimal.valueOf((2 * n) * (2 * n + 1));
            if (denominator.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("Division by zero");
            }
            term =term.multiply(x,mc).multiply(x,mc).divide(denominator,mc);
            sum =sum.add(term,mc);
            n++;


        }
        return sum;
    }
}
