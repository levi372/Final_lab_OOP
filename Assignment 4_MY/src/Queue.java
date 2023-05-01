import java.awt.Graphics;

public class Queue {
    Node first;
    Node last;


    public void enqueue(Shape data){
        if(first==null){
            first=new Node(data);
        }
        else if(last==null){
          last=new Node(data);
        }
        else{
           Node temp=new Node(data);
           last.next=temp;
           last=temp;
        }      
    }

    

    public Shape dequeue(){
        if(first == null) 
        return null;
        else {
            Shape data = first.data;
            first = first.next;
            return data;
        }
    }

    public void showQueue(Graphics g){
        Node temp=first;
        while(temp!=null) {
            temp.data.draw(g);
            temp = temp.next;
        }
    }

    public int qSize() {
        int i = 0;
        Node temp = first;
        while(temp != null) {
            i = i + 1;
            temp = temp.next;
        }
        return i;
    }
}
