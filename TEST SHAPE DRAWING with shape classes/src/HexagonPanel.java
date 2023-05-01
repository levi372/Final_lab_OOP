import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonPanel extends JPanel {

    private Hexagon hexagon;

    public HexagonPanel() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                hexagon = new Hexagon(e.getPoint()); //sets start point
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //if (hexagon != null) {
                    hexagon.setEndPoint(e.getPoint());
                    repaint();
                //}
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                hexagon = null; //to be on safe side
                repaint();
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       // if (hexagon != null) {
            hexagon.draw(g);
       // }
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
