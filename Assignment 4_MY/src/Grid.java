import java.awt.*;

public class Grid extends Button {
    private int count;
    private static Grid instance;

    private Grid() {
        super(128, 0, 64, 64, Color.BLACK, Color.GREEN, "Grid");
    }

     //singleton design pattern for grid
      public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public void handleClick(int x, int y){
        if(super.Clicked(x,y)){
            if(count == 64){
                count = 0;
            }
            else if(count == 0){
                count += 2;
            }
            else{
                count *= 2;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (count != 0) {
            int y = 200;
            int x = 0;
            for (int i = 0; i < 600/count ; i++) {
                g.drawLine(0, y + count, 600, y + count);
                y += count;
                g.drawLine(x+count,200,x+count,800);
                x += count;
            }
        }
    }
}
