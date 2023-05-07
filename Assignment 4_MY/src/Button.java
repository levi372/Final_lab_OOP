import java.awt.*;

public class Button {
    private int x;
    private int y;
    private int length;
    private int height;
    private Color stroke_col;
    private Color button_col;
    private boolean clicked;
    private String text;
    private Image dep_img;
    private Image pre_img;
    private Image current_img;
    private boolean img;
    private boolean hovered;

   //constructor with image
    public Button(int x, int y, int length, int height,Image dep_img, Image pre_img) { //isme tooltip daalna prega as no text
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.dep_img = dep_img;
        this.pre_img = pre_img;
        current_img = dep_img;
        img = true;
    }

    // Constructor with text
    public Button(int x, int y, int length, int height, Color stroke_col, Color button_col, String text) { //o tool tip required for this as text available
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.stroke_col = stroke_col;
        this.button_col = button_col;
        this.clicked = false;
        this.text = text;
    }

    // Constructor without text
    public Button(int x, int y, int length, int height, Color stroke_col, Color button_col)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.stroke_col = stroke_col;
        this.button_col = button_col;
        this.text = "";
    }

    // Clicked method
    public boolean Clicked(int inputX, int inputY) {
        if (inputX >= x && inputX <= x + length && inputY >= y && inputY <= y + height) {
            return true;
        }
        return false;
    }
    public void Hovered(int inputX, int inputY)
    {
        hovered = inputX >= x && inputX <= x + length && inputY >= y && inputY <= y + height;
    }

    // Toggle method
    public void Toggle(int inputX, int inputY) {
        if (Clicked(inputX, inputY)) {
            clicked = !clicked;
            if(img){
                changeImage();
            }
        }
    }

    // Paint method
    public void paint(Graphics g) {
            g.setColor(clicked ? Color.RED : stroke_col);
            g.fillRect(x, y, length, height);

            int innerButtonMargin = 3;
            g.setColor(button_col);
            g.fillRect(x + innerButtonMargin, y + innerButtonMargin, length - 2 * innerButtonMargin, height - 2 * innerButtonMargin);

            g.setColor(clicked ? Color.RED : stroke_col);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            int textX = x + (length - textWidth) / 2;
            int textY = y + (height - textHeight) / 2 + fm.getAscent();
            g.drawString(text, textX, textY);
    }

    // Getter for text
    public String getText() {
        return text;
    }

    // Setter for text
    public void setText(String text) {
        this.text = text;
    }

    public boolean getClicked(){
        return clicked;
    }

    public Color getColor(){
        return button_col;
    }

    public void setColor(Color color){
        button_col = color;
    }

    public void setStrokeCol(Color color){
        stroke_col = color;
    }

    public void changeImage(){
        if(clicked){
            current_img = pre_img;
        }
        else{
            current_img = dep_img;
        }
    }

    public Image getImage() {
        return current_img;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setClicked(boolean clicked){
        this.clicked = clicked;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getHeight()
    {
        return height;
    }
    public boolean getHovered()
    {
        return hovered;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getLength()
    {
        return length;
    }
}
