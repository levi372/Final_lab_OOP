import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rightangletriangle extends Shape {

    private Point startPoint;
    private Point endPoint;

    public Rightangletriangle(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void draw(Graphics g) {
        if (startPoint != null && endPoint != null) {
            g.setColor(Color.RED); // set the color to blue
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            int dx = Math.abs(endPoint.x - startPoint.x);
            int dy = Math.abs(endPoint.y - startPoint.y);
            int r = (int)Math.sqrt(dx*dx + dy*dy);
            int x = startPoint.x;
            int y = endPoint.y;
            g.drawLine(startPoint.x, startPoint.y, startPoint.x, endPoint.y);
            g.drawLine(startPoint.x, endPoint.y, endPoint.x, endPoint.y);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            // fill the triangle with a light blue color
            int[] xPoints = {startPoint.x, startPoint.x, endPoint.x};
            int[] yPoints = {startPoint.y, endPoint.y, endPoint.y};
            g.setColor(Color.RED);
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public String getInfo() {
        return "";
    }
}
