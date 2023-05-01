import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RectanglePanel extends JPanel implements MouseListener, MouseMotionListener {
    private Point startPoint;
    private Point endPoint;
    private Rectangle rect;
    //private boolean isDragging;

    RectanglePanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        rect = null;
        //isDragging = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (rect != null) {
            g.setColor(Color.BLUE);
            rect.draw(g);
        }
    }
    
    

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        //isDragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        rect = new Rectangle(x, y, width, height);
        //isDragging = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //if (isDragging) {
            endPoint = e.getPoint();
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            rect = new Rectangle(x, y, width, height);
            repaint();
        //}
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
        JFrame frame = new JFrame("Rectangle Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        RectanglePanel panel = new RectanglePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
