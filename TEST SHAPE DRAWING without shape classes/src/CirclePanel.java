import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CirclePanel extends JPanel implements MouseListener, MouseMotionListener {
    private Point center;
    private Point radiusPoint;

    CirclePanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (center != null && radiusPoint != null) { //this would be done in circle class
            int radius = (int) Math.sqrt(Math.pow(radiusPoint.x - center.x, 2) + Math.pow(radiusPoint.y - center.y, 2));
            g.setColor(Color.MAGENTA);
            g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
            g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);       
         }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        center = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        radiusPoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        radiusPoint = e.getPoint();
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
        JFrame frame = new JFrame("Circle Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new CirclePanel());
        frame.setVisible(true);
    }
}
