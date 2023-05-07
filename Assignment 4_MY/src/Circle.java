import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape {
    // int x,y; no need
    Point center;
    //Point radiusPoint; no need
    Color stroke_color, fill_color;

    int radius;

    int stroke_size;//border size


    public Circle(Point centre, int radius, Color stroke_color, Color fill_color) {
        this.center = centre;
        this.radius = radius;
        this.stroke_color = stroke_color;
        this.fill_color = fill_color;
    }
   
    
    
    public Circle(Point centre, int radius, Color stroke_color, Color fill_color,int stroke_size) {
        this.center = centre;
        this.radius = radius;
        this.stroke_color = stroke_color;
        this.fill_color = fill_color;
        this.stroke_size=stroke_size;
    }

    @Override
    public void draw(Graphics g) {
    //first fill then draw 
    Graphics2D g2d = (Graphics2D) g;
    
    g2d.setColor(fill_color);
    g2d.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
    g2d.setColor(stroke_color);
    g2d.setStroke( new BasicStroke(stroke_size) );
    g2d.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
 

        /* 
         g.setColor(fill_color);
         g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
         g.setColor(stroke_color);
         g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
        */  
    
    
    

}
    

    @Override
    public String getInfo() {
    return "Circle "+radius+" "+center.getX()+" "+center.getY();
    }

    






    
    
}
