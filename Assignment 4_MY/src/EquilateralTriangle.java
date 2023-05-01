import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class EquilateralTriangle extends Shape {
    private Point startPoint;
    private Point endPoint;
    private Point trianglePoint;
    int[] xp = new int[3];
    int[] yp = new int[3];
    Color stroke_color;
    Color fill_color;

    public EquilateralTriangle(Point startPoint, Point endPoint,Color stroke_color,Color fill_color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stroke_color=stroke_color;
        this.fill_color=fill_color;
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        double length = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2));
        double angle2 = angle + (2.0 / 3.0) * Math.PI;
        trianglePoint = new Point((int) (endPoint.x + length * Math.cos(angle2)), (int) (endPoint.y + length * Math.sin(angle2)));
        xp[0] = startPoint.x;
        xp[1] = trianglePoint.x;
        xp[2] = endPoint.x;
        yp[0] = startPoint.y;
        yp[1] = trianglePoint.y;
        yp[2] = endPoint.y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g.drawLine(endPoint.x, endPoint.y, trianglePoint.x, trianglePoint.y);
        g.drawLine(trianglePoint.x, trianglePoint.y, startPoint.x, startPoint.y);

        g.setColor(stroke_color);
        g.drawPolygon(xp, yp, 3);
        g.setColor(fill_color);
        g.fillPolygon(xp, yp, 3);
    }

    @Override
    public String getInfo() { //will add this while doing filing
       return "";
    }
}