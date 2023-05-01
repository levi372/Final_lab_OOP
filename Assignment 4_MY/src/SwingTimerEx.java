import java.awt.EventQueue;
import javax.swing.JFrame;

public class SwingTimerEx extends JFrame { //main driver function

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {

        add(new Board()); ///adding jpanel to jframe

        setResizable(false);
        pack();

        setTitle("Java Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }
}