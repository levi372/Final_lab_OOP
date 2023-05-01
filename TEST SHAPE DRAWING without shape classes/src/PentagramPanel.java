import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PentagramPanel extends JPanel {

    private Point startPoint;
    private Point endPoint;
    private boolean isDrawing = false;

    public PentagramPanel() {
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
            int[] xPoints = new int[10];
            int[] yPoints = new int[10];
            int dx = Math.abs(endPoint.x - startPoint.x);
            int dy = Math.abs(endPoint.y - startPoint.y);
            int r = Math.min(dx, dy);
            int centerX = startPoint.x + dx / 2;
            int centerY = startPoint.y + dy / 2;
            for (int i = 0; i < 10; i++) {
                double angleDeg = 36 * i;
                double angleRad = Math.PI / 180 * angleDeg;
                if (i % 2 == 0) {
                    int x = (int) (centerX + r * Math.cos(angleRad));
                    int y = (int) (centerY - r * Math.sin(angleRad));
                    xPoints[i] = x;
                    yPoints[i] = y;
                } else {
                    int x = (int) (centerX + r / 2 * Math.cos(angleRad));
                    int y = (int) (centerY - r / 2 * Math.sin(angleRad));
                    xPoints[i] = x;
                    yPoints[i] = y;
                }
            }
            if (isDrawing) {
                g.drawPolygon(xPoints, yPoints, 10);
            } else {
                g.fillPolygon(xPoints, yPoints, 10);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pentagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        PentagramPanel panel = new PentagramPanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
