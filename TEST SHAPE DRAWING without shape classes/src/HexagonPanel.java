import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonPanel extends JPanel {

    private Point startPoint;
    private Point endPoint;
    private boolean isDrawing = false;

    public HexagonPanel() {
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
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];
            int dx = Math.abs(endPoint.x - startPoint.x);
            int dy = Math.abs(endPoint.y - startPoint.y);
            int r = Math.min(dx, dy);
            int centerX = startPoint.x + dx / 2;
            int centerY = startPoint.y + dy / 2;
            for (int i = 0; i < 6; i++) {
                double angleDeg = 60 * i;
                double angleRad = Math.PI / 180 * angleDeg;
                int x = (int) (centerX + r * Math.cos(angleRad));
                int y = (int) (centerY - r * Math.sin(angleRad));
                xPoints[i] = x;
                yPoints[i] = y;
            }
            if (isDrawing) {
                g.setColor(Color.RED);
                g.drawPolygon(xPoints, yPoints, 6);
            } else {
                g.setColor(Color.RED);
                g.fillPolygon(xPoints, yPoints, 6);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        HexagonPanel panel = new HexagonPanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
