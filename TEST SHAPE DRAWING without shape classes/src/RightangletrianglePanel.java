import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RightangletrianglePanel extends JPanel {

    private Point startPoint;
    private Point endPoint;
    private boolean isDrawing = false;

    public RightangletrianglePanel() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                isDrawing = true;
            }
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                isDrawing = false;
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (isDrawing) {
                    endPoint = e.getPoint();
                    repaint();
                }
            }

            public void mouseDragged(MouseEvent e) {
              if (isDrawing) {
            endPoint = e.getPoint();
            repaint();
        }
    }
 

        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startPoint != null && endPoint != null) {
            g.setColor(Color.RED); // set the color to blue
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            int dx = Math.abs(endPoint.x - startPoint.x);
            int dy = Math.abs(endPoint.y - startPoint.y);
            int r = (int)Math.sqrt(dx*dx + dy*dy);
            int x = startPoint.x;
            int y = endPoint.y;
            g.drawLine(startPoint.x, startPoint.y, startPoint.x, endPoint.y);
            g.drawLine(startPoint.x, endPoint.y, endPoint.x, endPoint.y);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            // fill the triangle with a light blue color
            int[] xPoints = {startPoint.x, startPoint.x, endPoint.x};
            int[] yPoints = {startPoint.y, endPoint.y, endPoint.y};
            g.setColor(Color.RED);
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }
    
    
    
    
    
    
    
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Right Angle Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        RightangletrianglePanel panel = new RightangletrianglePanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
