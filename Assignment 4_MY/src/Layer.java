import java.awt.*;
import java.util.ArrayList;

public class Layer extends Button{ //layer is also a button layers me main pura pura stack daal dunga


    ArrayList<Shape> shapes = new ArrayList<>(); //each layer has a set of shapes

    public Layer(int x, int y, int length, int height, String text) {
        super(x, y, length, height, Color.BLACK, Color.magenta, text);
    }

    public void add(Shape shape)
    {
        shapes.add(shape);
    }


    
    public void paintshapesinlayers(Graphics g){
      for (Shape s : shapes) {
        s.draw(g);
      }
    }

    public Shape getandremovelatestshape(){
      Shape temp=shapes.get(shapes.size()-1);
      shapes.remove(shapes.size()-1);
      return temp;
    }

  

    

    
}
