package src.myutils;

public class Stack<T> {
    private Node<T> head;
    private int size;

    public Stack() {
        this.head = null;
        this.size = 0;
    }

    public void push(T e) {
        if(size == 0){
            head = new Node<>(e);
        } else{
            Node<T> newNode = new Node<>(e);
            newNode.setNext(head);
            head = newNode;
        }

        size++;
    }

    public T pop() {
        if(size == 0){
            throw new IndexOutOfBoundsException();
        } else{
            head = head.getNext();
            size--;

            if(size == 0){
                return null;
            }
            return head.getElement();
        }
    }

    public void clear(){
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getElement());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
}
