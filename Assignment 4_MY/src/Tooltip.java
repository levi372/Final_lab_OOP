import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tooltip extends Button {
    Button button;
    private static ArrayList<Tooltip> instances = new ArrayList<>();


    private Tooltip(Button button,String text) {
        super(button.getX(),button.getY() + button.getHeight(), 100, 50,Color.BLACK,Color.YELLOW,text);
        this.button = button;
    }

    public static Tooltip getInstance(Button button, String text) {
        Tooltip instance = null;
        for (Tooltip t : instances) {
            if (t.button == button) {
                instance = t;
                break;
            }
        }
        if (instance == null) {
            instance = new Tooltip(button, text);
            instances.add(instance);
        }
        return instance;
    }




    @Override
    public void paint(Graphics g) {
        if(button.getHovered())
        {
            if(this.getX() + this.getLength() > 800)
            {
                this.setX(800 - this.getLength());
                super.paint(g);
            }
            else
            {
                super.paint(g);
            }
        }
    }
}
