import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

    private Toolbar toolbar;

    private  Button r_tri;
    private  Button eq_tri;
    private  Button circle;
    private  Button rectangle;
    private  Button pentagram;
    private  Button hexagon;
    private Button current_shape;
    private final ArrayList<Button> shapes_buttons = new ArrayList<>(); //STORES ALL THE BUTTONS
    private Layers layers;
    private Layer top;
    private Button back_panel;
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            toolbar.gradientKey(e.getKeyChar());
            fileButton.Key(e.getKeyChar());
            editButton.Key(e.getKeyChar());
            layers.Key(e.getKeyChar());
        }
    }

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {

        //ALL file idit wale buttons
        fileButton = new File_Button(0,0,64,64);
        editButton = new EditButton(64,0,64,64);
        back_panel = new Button(0, 0, 800, 200, Color.BLACK, Color.GRAY);
        toolbar = new Toolbar();
        layers = new Layers();
        top = layers.getTopLayer();
      

       //ALL SHAPES BUTTONS

        ImageIcon c_dep = new ImageIcon("src/Shapes/Circle Dep.png");
        ImageIcon c_pre = new ImageIcon("src/Shapes/Circle Pressed.png");
        circle = new Button(100, 100, 64, 64, c_dep.getImage(), c_pre.getImage());
        shapes_buttons.add(circle); 
        


        ImageIcon eq_dep = new ImageIcon("src/Shapes/EQ Tri Dep.png");
        ImageIcon eq_pre = new ImageIcon("src/Shapes/EQ Tri Pre.png");
        eq_tri = new Button(200, 100, 64, 64, eq_dep.getImage(), eq_pre.getImage());
        shapes_buttons.add(eq_tri);
       

        ImageIcon hex_dep = new ImageIcon("src/Shapes/Hex Dep.png");
        ImageIcon hex_pre = new ImageIcon("src/Shapes/Hex Pre.png");
        hexagon = new Button(300, 100, 64, 64, hex_dep.getImage(), hex_pre.getImage());
        shapes_buttons.add(hexagon);
       


        ImageIcon pent_pre = new ImageIcon("src/Shapes/Pent Pre.png");
        ImageIcon pent_dep = new ImageIcon("src/Shapes/Pentagram Dep.png");
        pentagram = new Button(400, 100, 64, 64, pent_dep.getImage(), pent_pre.getImage());
        shapes_buttons.add(pentagram); 
       


        ImageIcon rect_dep = new ImageIcon("src/Shapes/Rectangle Dep.png");
        ImageIcon rect_pre = new ImageIcon("src/Shapes/Rectangle Pressed.png");
        rectangle = new Button(500, 100, 64, 64, rect_dep.getImage(), rect_pre.getImage());
        shapes_buttons.add(rectangle);
       


        ImageIcon r_dep = new ImageIcon("src/Shapes/Right Tri Dep.png");
        ImageIcon r_pre = new ImageIcon("src/Shapes/Right Tri Pre.png");
        r_tri = new Button(600, 100, 64, 64, r_dep.getImage(), r_pre.getImage());
        shapes_buttons.add(r_tri);
       

    }

    private void initBoard() { //this board is the white one yahi sari drawings hogi  yessssss

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawButton(g);

    }
    
    private void drawNotification(String text, Graphics g)
    {
    }

    private void drawButton(Graphics g) {
        back_panel.paint(g);
        layers.paint(g);
        for(Button b : shapes_buttons){
            g.drawImage(b.getImage(),b.getX(),b.getY(),this); //DRAWS ALLTHE BUTTONS
        }
       
        fileButton.paint(g);
        editButton.paint(g);
        toolbar.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
        top = layers.getTopLayer();
    }
    
    public void IsClicked(int x, int y)
    {
        if(!fileButton.getClicked() && !toolbar.winClicked()){
            editButton.handleClick(x,y);
        }
    	if(!editButton.getClicked() && !toolbar.winClicked()){
            fileButton.handleClick(x,y);
        }
        if(!fileButton.getClicked() && !editButton.getClicked()){
            toolbar.handleClick(x,y);
        }
        if(!fileButton.getClicked() && !editButton.getClicked() && !toolbar.winClicked()){
            layers.handleClick(x,y);
            if(current_shape == null){
                for(Button b : shapes_buttons){
                    b.Toggle(x,y);
                    if (b.getClicked()){
                        current_shape = b;
                        break;
                    }
                }
            }
            else if (current_shape != null && current_shape.getClicked()) {
                current_shape.Toggle(x,y);
            }
            else if (current_shape != null){
                for(Button b : shapes_buttons){
                    b.Toggle(x,y);
                    if (b.getClicked()){
                        current_shape = b;
                        break;
                    }
                }
            }
        }
    }
    

	@Override
	public void mouseClicked(MouseEvent e) { //button clicking yaha se check krlo
		IsClicked(e.getX(), e.getY());

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
        toolbar.handleHover(e.getX(),e.getY());
        for(Button b:shapes_buttons)
        {
            b.Hovered(e.getX(),e.getY());
        }
	}
}