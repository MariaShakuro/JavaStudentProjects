class TaylorSeries {
    public double taylor_sinh(double x, double E) {
        double sum = x;
        double term = x;
        int n = 1;

        while (Math.abs(term) >=E) {
            term *= x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }
}
