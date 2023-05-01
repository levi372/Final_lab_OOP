import java.awt.Color;
import java.awt.Graphics;

public class Pentagram  extends Shape{

    private int x, y, size;
    private Color color;

    public Pentagram(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
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

        g.setColor(Color.RED);
        g.drawPolygon(xPoints, yPoints, 10);
        g.setColor(Color.RED);
        g.fillPolygon(xPoints, yPoints, 10);
    }

    @Override
    public String getInfo() {
        return "";
    }


}
