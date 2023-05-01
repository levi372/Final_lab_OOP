import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FreeDrawing extends JFrame {
    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();
    private ArrayList<Point> currentLine = new ArrayList<>();
    
    public FreeDrawing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setTitle("Free Drawing");
        this.add(new DrawingPanel());
        this.setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        public DrawingPanel() {
            this.setBackground(Color.WHITE);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentLine.add(e.getPoint());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    lines.add(currentLine);
                    currentLine = new ArrayList<>();
                }
            });
            this.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    currentLine.add(e.getPoint());
                    repaint();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (ArrayList<Point> line : lines) {
                Point startPoint = line.get(0);
                for (int i = 1; i < line.size(); i++) {
                    Point endPoint = line.get(i);
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    startPoint = endPoint;
                }
            }
            for (int i = 1; i < currentLine.size(); i++) {
                Point startPoint = currentLine.get(i - 1);
                Point endPoint = currentLine.get(i);
                g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
        }
    }

    public static void main(String[] args) {
        new FreeDrawing();
    }
}
