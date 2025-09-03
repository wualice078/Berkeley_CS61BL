/** A class that represents a path via pursuit curves. */
public class Path {

    private Point curr;
    private Point next;

    public Path(double x, double y) {
        this.curr = new Point(x, y);
        this.next = new Point(x, y);
    }
    public double getCurrX() {
        return curr.getX();
    }
    public double getCurrY() {
        return curr.getY();
    }
    public double getNextX() {
        return next.getX();
    }
    public double getNextY() {
        return next.getY();
    }
    public Point getCurrentPoint() {
        return curr;
    }
    public void setCurrentPoint(Point point) {
        this.curr.setX(point.getX());
        this.curr.setY(point.getY());
    }

    public void iterate(double dx, double dy) {
        this.curr.setX(this.next.getX());
        this.curr.setY(this.next.getY());
        double nextx = this.curr.getX() + dx;
        double nexty = this.curr.getY() + dy;
        this.next.setX(nextx);
        this.next.setY(nexty);
    }
}
