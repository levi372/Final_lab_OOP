import java.awt.*;
import java.util.ArrayList;

import javax.tools.Tool;

public class Toolbar extends Button{


    private final Button Edit_color;
    private final Gradiant_Window Gradiant_window;
     final Button Stroke_colorbox; //was private
     final Button fill_Button_colorbox; //was private
    private final Button red;
    private final Button green;
    private final Button blue;
    private final Button custom1;
    private final Button custom2;
    private final Button custom3;

    //PEN BUTTTONS
    Button Strokewidth_heading;
    int stroke_size=5;
    Button strokewidth_small;
    Button strokewidth_medium;
    Button strokewidth_large;
    private final ArrayList<Button> penbuttons=new ArrayList<>();


    private final ArrayList<Button> gradient = new ArrayList<>(); //stores all rgb colors in the window
    private final Button display;
    private final ArrayList<Button> customs = new ArrayList<>(); //the 3 white colors which recieve color from gradiant
    private final Button Add_color;
    private int count = 0;
    private ArrayList<Tooltip> tooltips = new ArrayList<>();


    public Toolbar(){

        super(0,0,0,0, Color.BLACK,Color.GRAY);

        Strokewidth_heading=new Button(330,5,100,30,Color.BLACK,Color.YELLOW,"STROKE WIDTH");
        penbuttons.add(Strokewidth_heading);
        strokewidth_small=new Button(280,40,50,32,Color.BLACK,Color.ORANGE,"SMALL");
        penbuttons.add(strokewidth_small);
        strokewidth_medium=new Button(350,40,60,32,Color.BLACK,Color.ORANGE,"MEDIUM");
        penbuttons.add(strokewidth_medium);
        strokewidth_large=new Button(420,40,50,32,Color.BLACK,Color.ORANGE,"LARGE");
        penbuttons.add(strokewidth_large);




        red = new Button(500, 10, 32, 32, Color.BLACK, Color.RED);


        blue = new Button(540, 10, 32, 32, Color.BLACK, Color.BLUE);


        green = new Button(580, 10, 32, 32, Color.BLACK, Color.GREEN);


        custom1 = new Button(500, 50, 32, 32, Color.BLACK, Color.white);
        customs.add(custom1);


        custom2 = new Button(540, 50, 32, 32, Color.BLACK, Color.white);
        customs.add(custom2);


        custom3 = new Button(580, 50, 32, 32, Color.BLACK, Color.WHITE);
        customs.add(custom3);


        Stroke_colorbox = new Button(645, 20, 64, 64, Color.BLACK, Color.WHITE);
        //tooltips.add(new Tooltip(Stroke_colorbox,"Stroke Color"));
        tooltips.add(Tooltip.getInstance(Stroke_colorbox, "Stroke Color"));


        fill_Button_colorbox = new Button(720, 20, 64, 64, Color.BLACK, Color.WHITE);
        //tooltips.add(new Tooltip(fill_Button_colorbox,"Button Color"));
        tooltips.add(Tooltip.getInstance(fill_Button_colorbox, "Button Color"));



        Edit_color = new Button(700, 100, 75, 50, Color.BLACK, Color.orange, "EDIT Color");

        //singleton
        Gradiant_window = Gradiant_Window.getInstance(100,250,400,400,"Colors gradiant tab"); //this is window not button

        display = new Button(174,580,66,40,Color.BLACK,Color.WHITE);


        Add_color = new Button(364,580,66,40,Color.BLACK,Color.green,"Add Color");
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        for(Button p: penbuttons){ //paints all buttons
            p.paint(g);
        }

        red.paint(g);
        blue.paint(g);
        green.paint(g);
        for(Button b : customs){
            b.paint(g);
        }
        Stroke_colorbox.paint(g);
        fill_Button_colorbox.paint(g);
        Edit_color.paint(g);
        for(Tooltip t:tooltips)
        {
            t.paint(g);
        }
        if(Gradiant_window.getVisible()){
            Gradiant_window.paint(g);
            display.paint(g);
            Add_color.paint(g);
            for(Button b:gradient){
                b.paint(g);
            }
        }
    }

    public void handleClick(int x, int y){
        if(!Gradiant_window.getVisible()){ //if gradiant window not visible

            //setting radius and colours
            if(strokewidth_small.Clicked(x, y)){
                stroke_size=5;
                strokewidth_small.setColor(Color.RED);
                strokewidth_medium.setColor(Color.ORANGE);
                strokewidth_large.setColor(Color.ORANGE);
            }
            if(strokewidth_medium.Clicked(x, y)){
                stroke_size=10;
                strokewidth_small.setColor(Color.ORANGE);
                strokewidth_medium.setColor(Color.RED);
                strokewidth_large.setColor(Color.ORANGE);
            }
            if(strokewidth_large.Clicked(x, y)){
                stroke_size=20;
                strokewidth_small.setColor(Color.ORANGE);
                strokewidth_medium.setColor(Color.ORANGE);
                strokewidth_large.setColor(Color.RED);
            }

            if(!strokewidth_small.Clicked(x, y) && !strokewidth_medium.Clicked(x, y) && !strokewidth_large.Clicked(x, y) ){
                strokewidth_small.setColor(Color.ORANGE);
                strokewidth_medium.setColor(Color.ORANGE);
                strokewidth_large.setColor(Color.ORANGE);
            }



            if(Edit_color.Clicked(x,y)){
                makeGradient();
                Gradiant_window.setVisible(true);
            }

            if(!Stroke_colorbox.getClicked()){
                fill_Button_colorbox.Toggle(x,y);
            }

            if (!fill_Button_colorbox.getClicked()){
                Stroke_colorbox.Toggle(x,y);
            }

            if(Stroke_colorbox.getClicked() && red.Clicked(x,y)){
                Stroke_colorbox.setColor(red.getColor());
            }

            else if(Stroke_colorbox.getClicked() && blue.Clicked(x,y)){
                Stroke_colorbox.setColor(blue.getColor());
            }

            else if(Stroke_colorbox.getClicked() && green.Clicked(x,y)){
                Stroke_colorbox.setColor(green.getColor());
            }

            else if(Stroke_colorbox.getClicked() && custom1.Clicked(x,y)){
                Stroke_colorbox.setColor(custom1.getColor());
            }

            else if(Stroke_colorbox.getClicked() && custom2.Clicked(x,y)){
                Stroke_colorbox.setColor(custom2.getColor());
            }

            else if(Stroke_colorbox.getClicked() && custom3.Clicked(x,y)){
                Stroke_colorbox.setColor(custom3.getColor());
            }

            if(fill_Button_colorbox.getClicked() && red.Clicked(x,y)){
                fill_Button_colorbox.setColor(red.getColor());
            }

            else if(fill_Button_colorbox.getClicked() && blue.Clicked(x,y)){
                fill_Button_colorbox.setColor(blue.getColor());
            }

            else if(fill_Button_colorbox.getClicked() && green.Clicked(x,y)){
                fill_Button_colorbox.setColor(green.getColor());
            }

            else if(fill_Button_colorbox.getClicked() && custom1.Clicked(x,y)){
                fill_Button_colorbox.setColor(custom1.getColor());
            }

            else if(fill_Button_colorbox.getClicked() && custom2.Clicked(x,y)){
                fill_Button_colorbox.setColor(custom2.getColor());
            }

            else if(fill_Button_colorbox.getClicked() && custom3.Clicked(x,y)){
                fill_Button_colorbox.setColor(custom3.getColor());
            }

        }


        else if(Gradiant_window.getVisible()){
            //adds colors to white boxes through color gradiant window
            //note gradiant has lots of buttons
            if(Gradiant_window.Clicked(x,y)){
                Gradiant_window.setVisible(false);
            }
            for(Button b:gradient){
                if(b.Clicked(x,y)){
                    display.setColor(b.getColor()); //since every gradiant button has its own color
                }
            }
            if(Add_color.Clicked(x,y)){ //add color button inside gradiant
                if(count==customs.size()){
                    count=0;
                }
                customs.get(count).setColor(display.getColor());
                count++;
            }
        }
    }


    public void handleHover(int x, int y)
    {
        fill_Button_colorbox.Hovered(x,y);
        Stroke_colorbox.Hovered(x,y);
    }

    public boolean winClicked(){
        return Gradiant_window.getVisible();
    }

    public void makeGradient(){ //gradiant window working
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                Button b = new Button(174+j,300+i,1,1,Color.getHSBColor((256-i)/256.0f,j/256.0f,0.8f),Color.getHSBColor((256-i)/256.0f,j/256.0f,0.8f));
                gradient.add(b);
            }
        }
    }

    public void gradientKey(char key){
        if(key == 'g'){
            makeGradient();
            Gradiant_window.setVisible(true);
        }
    }
}
