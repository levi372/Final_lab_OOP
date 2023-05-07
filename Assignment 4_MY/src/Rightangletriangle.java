import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rightangletriangle extends Shape {

    private Point startPoint;
    private Point endPoint;
    Color stroke_color;
    Color fill_color;
    int stroke_size;//border

    public Rightangletriangle(Point startPoint, Point endPoint,Color strokeColor,Color fill_color,int stroke_size) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stroke_color=strokeColor;
        this.fill_color=fill_color;
        this.stroke_size=stroke_size;
    }

    public void draw(Graphics g) {

        if (startPoint != null && endPoint != null) {
            Graphics2D g2d = (Graphics2D) g;


            //g.setColor(stroke_color); // lines ka color
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

            g2d.setColor(fill_color);
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.setColor(stroke_color);
            g2d.setStroke( new BasicStroke(stroke_size) );
            g2d.drawPolygon(xPoints, yPoints, 3);
            
        }
    }

    @Override
    public String getInfo() {
        return "";
    }
}
