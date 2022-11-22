package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private Integer floor;

    public Flat(double area, double balconyArea, Integer floor) {
        this.area = area + balconyArea;
        this.floor = floor;
    }
    public Flat() {

    }
    public String toString() {
        return "Квартира площадью " + area + " метров на " + floor + " этаже";
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
