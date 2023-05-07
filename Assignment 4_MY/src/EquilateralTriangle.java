import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class EquilateralTriangle extends Shape {
    private Point startPoint;
    private Point endPoint;
    private Point trianglePoint;
    int[] xp = new int[3];
    int[] yp = new int[3];
    Color stroke_color;
    Color fill_color;
    int stroke_size;//border size

    public EquilateralTriangle(Point startPoint, Point endPoint,Color stroke_color,Color fill_color,int stroke_size) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stroke_color=stroke_color;
        this.fill_color=fill_color;
        this.stroke_size=stroke_size;
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
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.drawLine(endPoint.x, endPoint.y, trianglePoint.x, trianglePoint.y);
        g2d.drawLine(trianglePoint.x, trianglePoint.y, startPoint.x, startPoint.y);
        
        
        g2d.setColor(fill_color);
        g2d.fillPolygon(xp, yp, 3);
        g2d.setColor(stroke_color);
        g2d.setStroke(new BasicStroke(stroke_size));
        g2d.drawPolygon(xp, yp, 3);
        
    }

    @Override
    public String getInfo() { //will add this while doing filing
       return "";
    }
}