
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class RightangletrianglePanel extends JPanel {

    private Point startPoint;
    private Point endPoint;
    private boolean isDrawing = false;
    private Rightangletriangle triangle;

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
                triangle = new Rightangletriangle(startPoint, endPoint);
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
                    triangle = new Rightangletriangle(startPoint, endPoint);
                    repaint();
                }
            }
        });
    }
        

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (triangle != null) {
            triangle.draw(g);
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


