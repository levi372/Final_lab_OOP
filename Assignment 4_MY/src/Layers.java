import java.awt.*;
import java.util.ArrayList;

public class Layers extends Button{

     final ArrayList<Layer> layers = new ArrayList<>(); //isme dalna hai ksi tarah
    ArrayList<Shape> redolist=new ArrayList<>();
    private final Button add;
    private final Button remove;
    private boolean selected;
     int indexof_selected_layer=-1; //index of the selected layer 
     Layer selected_layer; ///the actual selected layer
    Graphics g;

    public Layers() {
        super(600, 200, 200, 600, Color.BLACK, Color.pink); //right panel ka colour
        add = new Button(620,700,60,60,Color.BLACK,Color.GREEN,"Add");
        remove = new Button(700,700,60,60,Color.BLACK,Color.RED,"Remove");
        add_layerbutton();
    }

    public void addshapestolayer(Shape s){

        //for always drawing on top layer
        this.getTopLayer().add(s);

        /* for drawing on selected layer
        if(selected_layer==null){
            this.getTopLayer().add(s);
        }
        else {
            layers.get(index).add(s);
        }
        */
    }

    public void add_layerbutton(){
            if(layers.isEmpty()) {
                Layer first_layer = new Layer(620,630,140,60,"Layer 1");
                layers.add(first_layer);
            }
            else{
                if(layers.get(layers.size()-1).getY()-60<200){
                    System.out.println("No More Layers Can Be Added");
                }
                else {
                    Layer layer = new Layer(620, layers.get(layers.size() - 1).getY() - 60, 140, 60, "Layer " + (layers.size() + 1));
                    layers.add(layer);
                }
            }
    }

    public void remove(Layer layer){
        if(layers.size()==1){ //last layer cannot be removed now
            return;
        }
            int layer_pos = 0;
            layer_pos = layers.indexOf(layer);
            if(layer_pos == layers.size()-1){
                layers.remove(layer);
                selected_layer = null;
                selected = false;
            }
            else {
                for(int i = layer_pos + 1; i < layers.size() ; i++){
                    layers.get(i).setY(layers.get(i).getY()+60);
                }
                layers.remove(layer);
                selected = false;
                selected_layer = null;
                rename();
            }
    }

    public void handleClick(int x,int y){
        SelectLayer(x,y);
        if(add.Clicked(x,y)){
            add_layerbutton();
        }
        if(remove.Clicked(x,y) && !layers.isEmpty() && selected){
            remove(selected_layer);
        }
        else if(remove.Clicked(x,y) && layers.isEmpty()){
            System.out.println("No Layers To Remove");
        }
        else if(remove.Clicked(x,y) && !layers.isEmpty() && !selected){
            System.out.println("Select a Layer to Remove");
        }
    }

    public void paintlayers(Graphics g){
        for (Layer layer : layers) {
            layer.paintshapesinlayers(g);
            
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        add.paint(g);
        remove.paint(g);

         
        for(Layer layer:layers){
            layer.paint(g);
        }
        
    }

    public void SelectLayer(int x, int y){
        for(int i =0; i < layers.size() ; i++){
            Layer L = layers.get(i);
            if(!L.getClicked() && L.Clicked(x,y) && !selected){
                L.Toggle(x,y);
                selected = true;
                indexof_selected_layer = layers.indexOf(L);
                selected_layer = L;
                break;
            }
            if(!L.getClicked() && L.Clicked(x,y) && selected){
                layers.get(indexof_selected_layer).setClicked(false);
                L.Toggle(x,y);
                indexof_selected_layer = layers.indexOf(L);
                selected_layer = L;
                break;
            }
            if(L.getClicked() && L.Clicked(x,y) && selected){
                L.Toggle(x,y);
                selected = false;
                indexof_selected_layer = 0;
                selected_layer = null;
                break;
            }
        }
    }

    public void rename(){
        for(Layer l:layers){
            l.setText("Layer "+(layers.indexOf(l)+1));
        }
    }

    public Layer getTopLayer(){
        return layers.get(layers.size()-1);
    }

    public void Key(char key){
        if(key == 'a'){
            add_layerbutton();
        }
        if(key == 'r' && layers.size() != 1 && !selected){
            remove(getTopLayer());
        }
        if(key == 'r' && layers.isEmpty()){
            System.out.println("No Layers To Remove");
        }
        if(key == 'r' && !layers.isEmpty() && selected){
            remove(selected_layer);
        }
    }
}
