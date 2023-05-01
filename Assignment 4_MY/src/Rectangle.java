import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    Color stroke_color;
    Color fill_color;

    public Rectangle(int x, int y, int width, int height,Color strokeColor,Color fillcolor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stroke_color=strokeColor;
        this.fill_color=fillcolor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    

    public void updatePoints(int x1, int y1, int x2, int y2) {
        x = Math.min(x1, x2);
        y = Math.min(y1, y2);
        width = Math.abs(x1 - x2);
        height = Math.abs(y1 - y2);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(stroke_color);
        g.drawRect(x, y, width, height);
        g.setColor(fill_color);
        g.fillRect(x, y, width, height);
    }
    


    @Override
    public String getInfo() {
        return "";
    }
}
