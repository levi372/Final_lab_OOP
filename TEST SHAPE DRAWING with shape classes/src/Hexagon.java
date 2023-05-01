import java.awt.*;

public class Hexagon extends Shape {

    private Point startPoint;
    private Point endPoint;
    private Color color;
    private boolean filled;
    

    public Hexagon(Point startPoint) { //will be addding stoke and fill colours in it
        this.startPoint = startPoint;
        this.color = Color.BLUE;
        this.filled = true;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int[] getXPoints() {
        int[] xPoints = new int[6];
        int dx = Math.abs(endPoint.x - startPoint.x);
        int dy = Math.abs(endPoint.y - startPoint.y);
        int r = Math.min(dx, dy);
        int centerX = startPoint.x + dx / 2;
        int centerY = startPoint.y + dy / 2;
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i;
            double angleRad = Math.PI / 180 * angleDeg;
            int x = (int) (centerX + r * Math.cos(angleRad));
            xPoints[i] = x;
        }
        return xPoints;
    }

    public int[] getYPoints() {
        int[] yPoints = new int[6];
        int dx = Math.abs(endPoint.x - startPoint.x);
        int dy = Math.abs(endPoint.y - startPoint.y);
        int r = Math.min(dx, dy);
        int centerX = startPoint.x + dx / 2;
        int centerY = startPoint.y + dy / 2;
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i;
            double angleRad = Math.PI / 180 * angleDeg;
            int y = (int) (centerY - r * Math.sin(angleRad));
            yPoints[i] = y;
        }
        return yPoints;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = getXPoints();
        int[] yPoints = getYPoints();
        if (filled) {
            g.setColor(color);
            g.fillPolygon(xPoints, yPoints, 6);
        } else {
            g.setColor(color);
            g.drawPolygon(xPoints, yPoints, 6);
        }
    }

    @Override
    public String getInfo() {
        return "";
    }

}
