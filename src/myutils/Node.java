package src.myutils;

public class Node<T> {
    private T element;
    private Node<T> next;
    // TODO: Incluir cor;

    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    public T getElement() { return element; }

    public Node<T> getNext() { return next; }

    public void setNext(Node<T> next) { this.next = next; }

}
