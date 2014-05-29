package examples.week1examples;

public class ShapeTester {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // checking out inheritance
        Triangle t = new Triangle("red", 5.0);
        Circle c = new Circle("red", 3.0, 5.0);

        System.out.println(t);
        System.out.println(c);

        // Checking out polymorphism
        boolean flag = true;
        Shape s;

        if (flag)
            s = new Circle("red", 3.0, 5.0);
        else
            s = new Rectangle("green", 2.0, 3.0, 5.0);

        System.out.println(s.toString() + " with an area of " + s.getArea());
    }
}
