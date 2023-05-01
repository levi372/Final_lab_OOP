import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EQ_trianglePanel extends JFrame {
    public EQ_trianglePanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setTitle("MY FRAME");
        this.add(new MyPanel());
        this.setVisible(true);
    }

}

class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
    private Point startPoint;
    private Point endPoint;
    private Point trianglePoint;
    int[] xp=new int[3];
    int[] yp=new int[3];

    MyPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) { //repaint se ye chalega
        super.paintComponent(g);
        if (startPoint != null && endPoint != null && trianglePoint != null) {
            g.setColor(Color.BLACK);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            g.drawLine(endPoint.x, endPoint.y, trianglePoint.x, trianglePoint.y);
            g.drawLine(trianglePoint.x, trianglePoint.y, startPoint.x, startPoint.y);

            g.setColor(Color.CYAN);
            g.drawPolygon(xp, yp, 3);
            g.fillPolygon(xp, yp, 3);
       
            
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        double length = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2));
        double angle2 = angle + (2.0 / 3.0) * Math.PI;
        trianglePoint = new Point((int) (endPoint.x + length * Math.cos(angle2)), (int) (endPoint.y + length * Math.sin(angle2)));
        repaint();
        //this it the end point yaha sare points finalize huve hai so means yaha object banega triangle ka
        xp[0]=startPoint.x;
        xp[1]=trianglePoint.x;
        xp[2]=endPoint.x;
        yp[0]=startPoint.y;
        yp[1]=trianglePoint.y;
        yp[2]=endPoint.y;


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        double length = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2));
        double angle2 = angle + (2.0 / 3.0) * Math.PI;
        trianglePoint = new Point((int) (endPoint.x + length * Math.cos(angle2)), (int) (endPoint.y + length * Math.sin(angle2)));
        repaint();
    }

    // Unused mouse listener and motion listener methods
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}


    public static void main(String[] args) {
       new EQ_trianglePanel();
    }
}
