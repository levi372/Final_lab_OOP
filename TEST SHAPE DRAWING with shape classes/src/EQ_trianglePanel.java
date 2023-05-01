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
    //private Point trianglePoint;
     EquilateralTriangle eqTriangle; // instance of EquilateralTriangle class
     
   // private boolean isTriangleDrawn = false; // flag to check if a triangle is already drawn
    
    MyPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (eqTriangle != null) {
        eqTriangle.draw(g);
    }
}

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        eqTriangle = new EquilateralTriangle(startPoint, endPoint); // create new instance of EquilateralTriangle
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        eqTriangle = new EquilateralTriangle(startPoint, endPoint); // create new instance of EquilateralTriangle
        repaint(); // call repaint to draw the line continuously while dragging
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

