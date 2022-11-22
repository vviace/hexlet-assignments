package exercise;

// BEGIN
public class Cottage implements Home{
    private double area;
    private int floorCount;
    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    public Cottage() {

    }
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area + " метров";
    }
    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        if (another.getArea() != area) {
            if (another.getArea() > area) {
                return -1;
            }
            else if (another.getArea() < area) {
                return 1;
            }
        }
        return 0;
    }
}
// END
