import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Shape {
    // int x,y; no need
    Point center;
    //Point radiusPoint; no need
    Color stroke_color, fill_color;

    int radius;
   
    
    
    public Circle(Point centre, int radius, Color stroke_color, Color fill_color) {
        this.center = centre;
        this.radius = radius;
        this.stroke_color = stroke_color;
        this.fill_color = fill_color;
    }

    @Override
    public void draw(Graphics g) {
            g.setColor(stroke_color);
            g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
            g.setColor(fill_color);
            g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
    }
    

    @Override
    public String getInfo() {
    return "Circle "+radius+" "+center.getX()+" "+center.getY();
    }

    






    
    
}
