import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    Color stroke_color;
    Color fill_color;
    int stroke_size;//border

    public Rectangle(int x, int y, int width, int height,Color strokeColor,Color fillcolor,int stroke_size) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stroke_color=strokeColor;
        this.fill_color=fillcolor;
        this.stroke_size=stroke_size;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fill_color);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(stroke_color);
        g2d.setStroke( new BasicStroke(stroke_size) );
        g2d.drawRect(x, y, width, height);
       
    }
    


    @Override
    public String getInfo() {
        return "";
    }
}
