package open.gfl.chipcalc.util;

import org.apache.commons.httpclient.cookie.CookieSpec;

public class Rational {
    private final int denominator;
    private final int numerator;

    public Rational(int i) {
        this.numerator = i;
        this.denominator = 1;
    }

    public Rational(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    private static int gcd(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        return gcd(i2, i % i2);
    }

    private static int lcm(int i, int i2) {
        return (i * i2) / gcd(i, i2);
    }

    private static Rational reduce(int i, int i2) {
        int gcd = gcd(i, i2);
        return new Rational(i / gcd, i2 / gcd);
    }

    public Rational add(int i, int i2) {
        int lcm = lcm(this.denominator, i2);
        return reduce((this.numerator * (lcm / this.denominator)) + (i * (lcm / i2)), lcm);
    }

    public Rational add(int i) {
        return add(i, 1);
    }

    public Rational add(Rational rational) {
        return add(rational.numerator, rational.denominator);
    }

    public Rational mult(int i, int i2) {
        return reduce(this.numerator * i, this.denominator * i2);
    }

    public Rational mult(int i) {
        return mult(i, 1);
    }

    public Rational mult(Rational rational) {
        return mult(rational.numerator, rational.denominator);
    }

    public Rational div(Rational rational) {
        return mult(rational.denominator, rational.numerator);
    }

    public int getIntCeil() {
        int i = this.numerator;
        int i2 = this.denominator;
        int i3 = i % i2;
        int i4 = i / i2;
        return i3 > 0 ? i4 + 1 : i4;
    }

    public double getDouble() {
        return ((double) this.numerator) / ((double) this.denominator);
    }

    public String toString() {
        return this.numerator + CookieSpec.PATH_DELIM + this.denominator;
    }
}
