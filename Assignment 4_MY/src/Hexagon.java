import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Hexagon extends Shape {

    private Point startPoint, endPoint;
    Color stroke_color,fill_color;
    int stroke_size;

    public Hexagon(Point startPoint, Point endPoint,Color strokecolor,Color fillcolor,int strokesize) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stroke_color=strokecolor;
        this.fill_color=fillcolor;
        this.stroke_size=strokesize;
    }

    public void draw(Graphics g) {
        if (startPoint == null || endPoint == null) {
            return;
        }
        int x1 = startPoint.x;
        int y1 = startPoint.y;
        int x2 = endPoint.x;
        int y2 = endPoint.y;
        int xDiff = Math.abs(x2 - x1);
        int yDiff = Math.abs(y2 - y1);
        int radius = (int) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i;
            double angleRad = Math.toRadians(angleDeg);
            int x = (int) (centerX + radius * Math.cos(angleRad));
            int y = (int) (centerY + radius * Math.sin(angleRad));
            xPoints[i] = x;
            yPoints[i] = y;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fill_color);
        g2d.fillPolygon(xPoints, yPoints,6);
        g2d.setColor(stroke_color);
        g2d.setStroke( new BasicStroke(stroke_size) );
        g2d.drawPolygon(xPoints, yPoints,6);



        
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

}