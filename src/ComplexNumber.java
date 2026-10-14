public class ComplexNumber {
    double real;
    double imag;

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public ComplexNumber add(ComplexNumber o) {
        return new ComplexNumber(this.real + o.real, this.imag + o.imag);
    }

    public ComplexNumber subtract(ComplexNumber o) {
        return new ComplexNumber(this.real - o.real, this.imag - o.imag);
    }

    public ComplexNumber multiply(ComplexNumber o) {
        return new ComplexNumber(this.real * o.real - this.imag * o.imag,
                this.real * o.imag + this.imag * o.real);
    }

    public ComplexNumber divide(ComplexNumber o) {
        double denom = o.real * o.real + o.imag * o.imag;
        return new ComplexNumber((this.real * o.real + this.imag * o.imag) / denom,
                (this.imag * o.real - this.real * o.imag) / denom);
    }

    @Override
    public String toString() {
        if (imag == 0) return "" + real;
        if (real == 0) return imag + "i";
        if (imag < 0) return real + " - " + (-imag) + "i";
        return real + " + " + imag + "i";
    }
}
