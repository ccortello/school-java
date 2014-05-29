package examples.week1examples;

public class Rectangle extends Shape {
    double height;
    double width;

    public Rectangle(String c, double m, double h, double w) {
        super(c, m);
        height = h;
        width = w;
    }

    @Override
    public double getArea() {
        return height * width;
    }

}
