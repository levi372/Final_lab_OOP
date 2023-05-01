import java.awt.Graphics;

public class Stack {
    Node top;


    public void push(Shape data){
        if(top==null){
          top=new Node(data);
        }
        else {
         Node newnode=new Node(data);
         newnode.next=top;
         top=newnode;
        }
    }


    public Shape pop(){
        if (top == null) return null;
        else{
        Shape x=top.data;
        top=top.next;
        return x;
        }
    }

    public void drawData(Graphics g) {
        Node temp = top;      
         if(temp!=null) {
         temp.data.draw(g);
         temp = temp.next;
        }
    }

    public void drawAll(Graphics g) {
        Node temp = top;      
         while(temp!=null) {
         temp.data.draw(g);
         temp = temp.next;
        }
    }
    

    public int stackSize() {
        int i = 0;
        Node temp = top;
        while(temp != null) {
            i = i + 1; 
            temp = temp.next;
        }
        return i;
    }

    public String getData() {
        Node temp = top;
        String info = "";      
         while(temp!=null) {
         info += temp.data.getInfo()+"\n";
         temp = temp.next;
        }
        return info;
    }

   

    public void clearStack() {
        while(top != null) {
         top = top.next;
        }
    }


}
