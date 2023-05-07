import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener , MouseInputListener{

    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 800;
    private final int DELAY = 25;
    private Timer timer;
    private EditButton editButton;
    private File_Button fileButton;

    private Toolbar toolbar;//ye layega colors

   // variables for all buttons
    private  Button Current_SHAPEBUTTON;
    private  Button rightangletriangle_B;
    private  Button eq_tri_B;
    private  Button circle_B;
    private  Button rectangle_B;
    private  Button pentagram_B;
    private  Button hexagon_B;
    private  Button pen_B;
    private final ArrayList<Button> shapes_buttons = new ArrayList<>(); //need to add buttons so that evryytime repaint function called theyare drewed
     Layers layers; //isme  1 layer add hogi
    private Layer toplayer;
    private Grid grid;
    private Button back_panel;
    private final ArrayList<Tooltip> tooltips = new ArrayList<>();

    //VARIABLE FOR SHAPES
    Graphics g;

    //undoredo
    Shape current_shape;


    //CIRCLE
    Point center;
    Point radiusPoint;
    int radius;
    Circle circle_shape;

   
    Point startPoint;
    Point endPoint;

    //EQ_TRIANGLE
    Shape eqTriangle_shape;

    //Hexagon
    Shape hexagon_shape;

    //PENTAGRAM
    Shape pentagram_shape; //uses same start point and end point of eq triangle

    //RECTANGLE
    Shape rectangle_shape;

    //Rightangletriangle
    Shape rightangletriangle_shape;
    
    //FREE DRAWING
     ArrayList<Shape> freedrawingcircles = new ArrayList<>(); //in asssignment use linked list instead of this
    
    
    //STORING Shapes
    //Stack shape_stack=new Stack();
    //ArrayList<Shape> allshapes=new ArrayList<>();


    //private Point lastPoint;
   //will remove this as ye tool bar se ayega
    
    
    //ArrayList<Integer> clickX=new ArrayList<>();
    //ArrayList<Integer> clickY=new ArrayList<>();
    //FileReading fr;
    //FileWrite fw;
    //int clicks=0;
    //Stack st;
    //Queue redo;
    //Shape latest;



 

public Board() { //this board is the white one yahi sari drawings hogi  yessssss

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    
    private void InitializeAssets() {

        //ALL file edit layers grid wale buttons upar wala panel basically
        fileButton = new File_Button(0,0,64,64);
        editButton = new EditButton(64,0,64,64);
        back_panel = new Button(0, 0, 800, 200, Color.BLACK, Color.pink);
        toolbar = new Toolbar();
        layers = new Layers();
        toplayer = layers.getTopLayer();
        grid = Grid.getInstance(); //singleton

       //ALL SHAPES BUTTONS

        ImageIcon c_dep = new ImageIcon("src/Shapes/Circle Dep.png");
        ImageIcon c_pre = new ImageIcon("src/Shapes/Circle Pressed.png");
        circle_B = new Button(100, 100, 64, 64, c_dep.getImage(), c_pre.getImage());
        shapes_buttons.add(circle_B); 
        //tooltips.add( new Tooltip(circle_B,"Circle"));
        tooltips.add(Tooltip.getInstance(circle_B, "CIRCLE"));


        ImageIcon eq_dep = new ImageIcon("src/Shapes/EQ Tri Dep.png");
        ImageIcon eq_pre = new ImageIcon("src/Shapes/EQ Tri Pre.png");
        eq_tri_B = new Button(200, 100, 64, 64, eq_dep.getImage(), eq_pre.getImage());
        shapes_buttons.add(eq_tri_B);
        //tooltips.add(new Tooltip(eq_tri_B,"EQ Tri"));
        tooltips.add(Tooltip.getInstance(eq_tri_B, "EQ Tri"));



        ImageIcon hex_dep = new ImageIcon("src/Shapes/Hex Dep.png");
        ImageIcon hex_pre = new ImageIcon("src/Shapes/Hex Pre.png");
        hexagon_B = new Button(300, 100, 64, 64, hex_dep.getImage(), hex_pre.getImage());
        shapes_buttons.add(hexagon_B);
        //tooltips.add(new Tooltip(hexagon_B, "Hexagon"));
        tooltips.add(Tooltip.getInstance(hexagon_B, "Hexagon"));



        ImageIcon pent_pre = new ImageIcon("src/Shapes/Pent Pre.png");
        ImageIcon pent_dep = new ImageIcon("src/Shapes/Pentagram Dep.png");
        pentagram_B = new Button(400, 100, 64, 64, pent_dep.getImage(), pent_pre.getImage());
        shapes_buttons.add(pentagram_B);
        //tooltips.add(new Tooltip(pentagram_B, "Pentagram"));
        tooltips.add(Tooltip.getInstance(pentagram_B, "Pentagram"));



        ImageIcon rect_dep = new ImageIcon("src/Shapes/Rectangle Dep.png");
        ImageIcon rect_pre = new ImageIcon("src/Shapes/Rectangle Pressed.png");
        rectangle_B = new Button(500, 100, 64, 64, rect_dep.getImage(), rect_pre.getImage());
        shapes_buttons.add(rectangle_B);
        //tooltips.add(new Tooltip(rectangle_B,"Rectangle"));
        tooltips.add(Tooltip.getInstance(rectangle_B, "Rectangle"));



        ImageIcon r_dep = new ImageIcon("src/Shapes/Right Tri Dep.png");
        ImageIcon r_pre = new ImageIcon("src/Shapes/Right Tri Pre.png");
        rightangletriangle_B = new Button(600, 100, 64, 64, r_dep.getImage(), r_pre.getImage());
        shapes_buttons.add(rightangletriangle_B);
        //tooltips.add(new Tooltip(rightangletriangle_B,"Right Tri"));
        tooltips.add(Tooltip.getInstance(rightangletriangle_B, "Right Tri"));


        ImageIcon pen_dep = new ImageIcon("src/Shapes/Pen Dep.png");
        ImageIcon pen_pre = new ImageIcon("src/Shapes/Pen Pre.png");
        pen_B = new Button(10, 100, 64, 64, pen_dep.getImage(), pen_pre.getImage());
        shapes_buttons.add(pen_B);
        //tooltips.add(new Tooltip(pen_B,"Free Drawing Pen"));
        tooltips.add(Tooltip.getInstance(pen_B, "Free Drawing Pen"));



    }


   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

         drawButton(g); //draws all buttons

        layers.paintlayers(g);       


   //freedrawing
         for(int i=0;i<freedrawingcircles.size();i++){
            freedrawingcircles.get(i).draw(g);
         }

        
         /* 
         //CIRCLE
          if(Current_SHAPEBUTTON==circle_B){

               if (center != null && radiusPoint != null) { //this would be done in circle class
                   if (circle_shape != null) { // if circle object exists, draw it
                       circle_shape.draw(g); //will be adding shapes later to layers
                    }
                }
         }

         //eq_triangle
         if(Current_SHAPEBUTTON==eq_tri_B){
            if (eqTriangle_shape != null) {
                eqTriangle_shape.draw(g);
            }
         }

         //HEXAGON
         if(Current_SHAPEBUTTON==hexagon_B){
            if (hexagon_shape != null) {
                hexagon_shape.draw(g);
            }
            if (startPoint != null && endPoint != null) { //this done for smoothness
                    new Hexagon(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size).draw(g);
                }
         }


         //PENTAGRAM
         if(Current_SHAPEBUTTON==pentagram_B){
            if (startPoint != null && endPoint != null && pentagram_shape!=null) {
                pentagram_shape.draw(g);
            }
         }

         //RECTANGLE
         if(Current_SHAPEBUTTON==rectangle_B){
            if (rectangle_shape != null) {
                rectangle_shape.draw(g);
            }
         }

         //rightangletriangle
         if(Current_SHAPEBUTTON==rightangletriangle_B){
            if (rightangletriangle_shape != null) {
                rightangletriangle_shape.draw(g);
            }
         }

         */



         

         





        


}
    

    
      /* 
    private void setUpDrawingGraphics(){
        g=getGraphics();
        shape_stack.drawData(g);
    }
    */

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            toolbar.gradientKey(e.getKeyChar());//ye sirf color gradiant show krega 'g'
            fileButton.Key(e.getKeyChar());
            editButton.Key(e.getKeyChar()); //yaha se bhe undo redo tak jana hai
            layers.Key(e.getKeyChar());
        }
    }


    private void drawButton(Graphics g) {
        back_panel.paint(g);
        layers.paint(g);
        for(Button b : shapes_buttons){
            g.drawImage(b.getImage(),b.getX(),b.getY(),this); //DRAWS ALLTHE BUTTONS
        }
       grid.paint(g); //DRAWS GRIDS
        fileButton.paint(g);
        editButton.paint(g);
        toolbar.paint(g);
        for(Tooltip t:tooltips)
        {
            t.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
        toplayer = layers.getTopLayer();

    }

    @Override
	public void mouseClicked(MouseEvent e) { //button clicking yaha se check krlo
		IsClicked(e.getX(), e.getY()); //this sets current shape button and also handles toolbar and file menu clicks
        
	}
    
    public void IsClicked(int x, int y){
        if(!fileButton.getClicked() && !toolbar.winClicked()){ //undo redo here
         editButton.handleClick(x,y); //edit button clicked
        
           
        }
    	if(!editButton.getClicked() && !toolbar.winClicked()){
            fileButton.handleClick(x,y); //file button clicked
        }
        if(!fileButton.getClicked() && !editButton.getClicked()){
            toolbar.handleClick(x,y);
        }
        if(!fileButton.getClicked() && !editButton.getClicked() && !toolbar.winClicked()){
            grid.handleClick(x,y);
            layers.handleClick(x,y);
            if(Current_SHAPEBUTTON == null){
                for(Button b : shapes_buttons){
                    b.Toggle(x,y);
                    if (b.getClicked()){
                        Current_SHAPEBUTTON = b;
                        break;
                    }
                }
            }
            else if (Current_SHAPEBUTTON != null && Current_SHAPEBUTTON.getClicked()) {
                Current_SHAPEBUTTON.Toggle(x,y);
            }
            else if (Current_SHAPEBUTTON != null){
                for(Button b : shapes_buttons){
                    b.Toggle(x,y);
                    if (b.getClicked()){
                        Current_SHAPEBUTTON = b;
                        break;
                    }
                }
            }
        }
    }

	@Override
	public void mousePressed(MouseEvent e) {
 
        //CIRCLE
        if(Current_SHAPEBUTTON==circle_B){
        center=e.getPoint();
        circle_shape = new Circle(center, 0, toolbar.Stroke_colorbox.getColor(), toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
    }

        //EQ_triangle
        if(Current_SHAPEBUTTON==eq_tri_B){
            startPoint = e.getPoint();
        }

        //HEXAGON
        if(Current_SHAPEBUTTON==hexagon_B){
            startPoint=e.getPoint();
             repaint();
        }

        //PENTAGRAM
        if(Current_SHAPEBUTTON==pentagram_B){
           startPoint=e.getPoint();
        }

        //RECTANGLE
        if(Current_SHAPEBUTTON==rectangle_B){
            startPoint = e.getPoint();
        }

        //rightangletriangle
        if(Current_SHAPEBUTTON==rightangletriangle_B){
            startPoint = e.getPoint();
        }

        //FREE drawing
        if(Current_SHAPEBUTTON==pen_B){
         endPoint=e.getPoint();
        }



 
        /* UNDO/REDO 
        if(SwingUtilities.isRightMouseButton(e)){
		
			//undo function isme button milana hai 
			if(shapes.stackSize() != 0)
			undo_redo.push(shapes.pop());
			System.out.println("stack size: "+shapes.stackSize());
			System.out.println("rightpresed: qsize: "+undo_redo.stackSize());
			g.clearRect(0, 0, 600, 600);
			g.drawString("1: Circle 2: Rectangle 3: Triangle", 340, 590);
			shapes.drawAll(g);
        
		}

		if(SwingUtilities.isMiddleMouseButton(e)) {	
			
			if(undo_redo.stackSize() != 0) {
                //redo function isme bhe button milana hai
			shapes.push(undo_redo.pop());
			setUpDrawingGraphics();
			System.out.println("stack size: "+shapes.stackSize());
			System.out.println("middle presed: qsize: "+undo_redo.stackSize());
            }

        }
        */


	}

	@Override
	public void mouseReleased(MouseEvent e) { 
        //stack also added here

        //yaha pe layers addition bhe hoga
        //undo redo bhe hoskta ig


       //CIRCLE
        if(Current_SHAPEBUTTON==circle_B){

           radiusPoint=e.getPoint();
           int radius = (int) Math.sqrt(Math.pow(radiusPoint.x - center.x, 2) + Math.pow(radiusPoint.y - center.y, 2));
           circle_shape = new Circle(center, radius, toolbar.Stroke_colorbox.getColor(), toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size); // create new Circle object
           //allshapes.add(circle_shape); 
           layers.addshapestolayer(circle_shape);
           repaint();
	    }

        //eq_triangle
        if(Current_SHAPEBUTTON==eq_tri_B){
            endPoint = e.getPoint();
            eqTriangle_shape = new EquilateralTriangle(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size); // create new instance of EquilateralTriangle
            //allshapes.add(eqTriangle_shape);
            layers.addshapestolayer(eqTriangle_shape);
            repaint();
        }

        //HEXAGON
        if(Current_SHAPEBUTTON==hexagon_B){
                endPoint = e.getPoint();
                hexagon_shape =new Hexagon(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
                //allshapes.add(hexagon_shape);
                layers.addshapestolayer(hexagon_shape);
                startPoint = null;
                endPoint = null;
                repaint();
        }

        //PENTAGRAM
        if(Current_SHAPEBUTTON==pentagram_B){
            endPoint = e.getPoint();
            int size=Math.min( Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y));
            pentagram_shape = new Pentagram(startPoint.x, startPoint.y,size, toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            //allshapes.add(pentagram_shape);
            layers.addshapestolayer(pentagram_shape);
            repaint();
        }

        //RECTANGLE
        if(Current_SHAPEBUTTON==rectangle_B){
            endPoint = e.getPoint();
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            rectangle_shape = new Rectangle(x, y, width, height,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            //allshapes.add(rectangle_shape);
            layers.addshapestolayer(rectangle_shape);
            repaint();
        }

        //rightangletriangle
        if(Current_SHAPEBUTTON==rightangletriangle_B){
            endPoint = e.getPoint();
            rightangletriangle_shape = new Rightangletriangle(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            //allshapes.add(rightangletriangle_shape);
            layers.addshapestolayer(rightangletriangle_shape);
            repaint();
        }


    }    

    @Override
	public void mouseDragged(MouseEvent e) {

        //CIRCLE
        if(Current_SHAPEBUTTON==circle_B){

         Point radiusPoint = e.getPoint();
         int radius = (int) Math.sqrt(Math.pow(radiusPoint.x - circle_shape.center.x, 2) + Math.pow(radiusPoint.y - circle_shape.center.y, 2));
         circle_shape.radius = radius;
         repaint();
        }  

        //EQ_triangle
        if(Current_SHAPEBUTTON==eq_tri_B){
            endPoint = e.getPoint();
            eqTriangle_shape = new EquilateralTriangle(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size); // create new instance of EquilateralTriangle
            repaint();
        }

        //HEXAGON
        if(Current_SHAPEBUTTON==hexagon_B){
            endPoint=e.getPoint();
            repaint();
        }

        //PENTAGRAM
        if(Current_SHAPEBUTTON==pentagram_B){
            endPoint = e.getPoint();
            int size=Math.min( Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y));
            pentagram_shape = new Pentagram(startPoint.x, startPoint.y,size,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            repaint();
        }

        //RECTANGLE
        if(Current_SHAPEBUTTON==rectangle_B){
            endPoint = e.getPoint();
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            rectangle_shape = new Rectangle(x, y, width, height,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            repaint();
        }

        //RIGHTANGLETRIANGLE
        if(Current_SHAPEBUTTON==rightangletriangle_B){
            endPoint = e.getPoint();
            rightangletriangle_shape = new Rightangletriangle(startPoint, endPoint,toolbar.Stroke_colorbox.getColor(),toolbar.fill_Button_colorbox.getColor(),toolbar.stroke_size);
            repaint();
        }

        //Free_drawing
        if(Current_SHAPEBUTTON==pen_B){
            Point currentpoint=e.getPoint();
            Circle circle = new Circle(currentpoint, toolbar.stroke_size, toolbar.Stroke_colorbox.getColor(), toolbar.fill_Button_colorbox.getColor());
            freedrawingcircles.add(circle);
            endPoint = currentpoint;
            repaint();
        }



    }

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

    @Override
	public void mouseMoved(MouseEvent e) {
        toolbar.handleHover(e.getX(),e.getY());
        for(Button b:shapes_buttons)
        {
            b.Hovered(e.getX(),e.getY());
        }


        if(Current_SHAPEBUTTON==rightangletriangle_B){
            endPoint = e.getPoint();
            repaint();
        }

	}



}