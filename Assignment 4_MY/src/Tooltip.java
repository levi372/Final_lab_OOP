import java.awt.Color;
import java.awt.Graphics;

public class Tooltip extends Button {
    Button button;

    public Tooltip(Button button,String text) {
        super(button.getX(),button.getY() + button.getHeight(), 100, 50,Color.BLACK,Color.YELLOW,text);
        this.button = button;
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
