package examples.week1examples;

public class Triangle extends Shape {
    public Triangle(String col, double m) {
        //color = col; we know this will not work, why?
        //mass = m;
        super(col, m);
    }

    @Override
    public String toString() {
        return this.getColor() + " triangle";
    }

    @Override
    public double getArea() {
        return 0;
    }
}
