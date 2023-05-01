import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PentagramPanel extends JPanel {

    private Point startPoint;
    private Point endPoint;
    //private boolean isDrawing = false;
    private Pentagram pentagram;

    public PentagramPanel() {
        setBackground(Color.WHITE);
        pentagram = new Pentagram(0, 0, 50, Color.RED);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
               // isDrawing = true;
            }


            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                //isDrawing = false;
                pentagram = new Pentagram(startPoint.x, startPoint.y, Math.min(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y)), Color.RED);
                repaint();
            }
        });


        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
              //  if (isDrawing) { not neccessary to implmeent this cuz i will be doing buttons
                    endPoint = e.getPoint();
                    pentagram = new Pentagram(startPoint.x, startPoint.y, Math.min(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y)), Color.RED);
                    repaint();
              //  }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startPoint != null && endPoint != null) {
            pentagram.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pentagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        PentagramPanel panel = new PentagramPanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
