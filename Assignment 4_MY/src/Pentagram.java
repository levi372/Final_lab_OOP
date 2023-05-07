import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pentagram  extends Shape{

    private int x, y, size;
    Color stroke_color;
    Color fill_color;
    int stroke_size;

    public Pentagram(int x, int y, int size, Color st,Color fill,int stroke_size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.stroke_color=st;
        this.fill_color=fill;
        this.stroke_size=stroke_size;

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int[] xPoints = new int[10];
        int[] yPoints = new int[10];
        int dx = size;
        int dy = size;
        int r = Math.min(dx, dy);
        int centerX = x + dx / 2;
        int centerY = y + dy / 2;
        for (int i = 0; i < 10; i++) {
            double angleDeg = 36 * i;
            double angleRad = Math.PI / 180 * angleDeg;
            if (i % 2 == 0) {
                int x = (int) (centerX + r * Math.cos(angleRad));
                int y = (int) (centerY - r * Math.sin(angleRad));
                xPoints[i] = x;
                yPoints[i] = y;
            } else {
                int x = (int) (centerX + r / 2 * Math.cos(angleRad));
                int y = (int) (centerY - r / 2 * Math.sin(angleRad));
                xPoints[i] = x;
                yPoints[i] = y;
            }
        }
        
        
        g2d.setColor(fill_color);
        g2d.fillPolygon(xPoints, yPoints, 10);
        g2d.setColor(stroke_color);
        g2d.setStroke( new BasicStroke(stroke_size) );
        g2d.drawPolygon(xPoints, yPoints, 10);
        
    }

    @Override
    public String getInfo() {
        return "";
    }


}
