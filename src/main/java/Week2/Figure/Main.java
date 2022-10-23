package Week2.Figure;

public class Main {
    public static void main(String[] args) {
        Figure circle = new Circle(10);
        Figure rec = new Rectangle(10, 15);
        Figure square = new Square(10);

        System.out.println("Area of circle is:" + circle.area());
        System.out.println("Area of rectangle is:" + rec.area());
        System.out.println("Area of square is:" + square.area());

    }
}
