package src.myutils;

public class Queue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(T e) {
        if (first == null) {
            Node<T> newNode = new Node<>(e);
            first = newNode;
            last = newNode;
        } else {
            Node<T> newNode = new Node<>(e);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (first == null) {
            throw new IndexOutOfBoundsException();
        } else {
            if(size > 1){
                first = first.getNext();
                size--;
                return first.getElement();
            } else{
                first = null;
                size--;
                return null;
            }
        }
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        Node<T> current = first;

        while (current != null) {
            System.out.print(current.getElement());
            if (current.getNext() != null) {
                System.out.print(" <- ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
}
