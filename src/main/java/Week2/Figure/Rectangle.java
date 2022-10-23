package Week2.Figure;

public class Rectangle implements Figure {
    double length;
    double width;

    public Rectangle(int length, int width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

