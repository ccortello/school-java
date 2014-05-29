package examples.week1examples;

public abstract class Shape {
    private String color;
    public double mass;

    public Shape(String c, double m) {
        color = c;
        mass = m;
    }

    @Override
    public String toString() {
        return color + " shape";
    }

    private void setColor(String col) {
        this.color = col;
    }

    public String getColor() {
        return this.color;
    }

    public abstract double getArea();
}
