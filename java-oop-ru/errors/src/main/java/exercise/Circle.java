package exercise;

// BEGIN
public class Circle {
    private final Point point;
    private final int radius;
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }
    public int getRadius() {
        return radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius can't be negative");
        }
        return (Math.PI * Math.pow(radius,2));
    }
     

}
// END
