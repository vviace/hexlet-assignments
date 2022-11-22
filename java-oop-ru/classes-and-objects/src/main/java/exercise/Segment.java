package exercise;

// BEGIN
public final class Segment {
    private Point dot1;
    private Point dot2;

    public Segment(Point one, Point two) {
        this.dot1 = one;
        this.dot2 = two;
    }
    public Point getBeginPoint() {
        return dot1;
    }
    public Point getEndPoint() {
        return dot2;
    }
    public Point getMidPoint() {
        return new Point((dot1.getX() + dot2.getX()) / 2,
                (dot1.getY() + dot2.getY()) / 2);
    }
}
// END
