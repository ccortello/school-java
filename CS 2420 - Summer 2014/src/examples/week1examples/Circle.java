package examples.week1examples;

public class Circle extends Shape {
    public double radius;

    public Circle(String c, double m, double r) {
        super(c, m);
        radius = r;
    }

    @Override
    public String toString() {
        return super.toString() + ", with radius " + radius;

    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
