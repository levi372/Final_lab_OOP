import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FreeDrawingPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>(); //in asssignment use linked list instead of this
    private Point lastPoint;
    private Color strokeColor = Color.BLACK;
    private Color fillColor = Color.BLACK;
    private int radius = 10;

    public FreeDrawingPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
        });


        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = e.getPoint();
                Circle circle = new Circle(currentPoint, radius, strokeColor, fillColor);
                shapes.add(circle);
                lastPoint = currentPoint;
                repaint();
            }
        });
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Free Drawing");
        FreeDrawingPanel freeDrawing = new FreeDrawingPanel();
        frame.getContentPane().add(freeDrawing);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }


    
}
