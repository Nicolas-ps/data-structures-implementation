package nicolasps.com.github.lists;

import java.util.Objects;

public class Node<T> {
    private Node<T> previous;
    private Node<T> next;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node() {}

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return Objects.nonNull(this.getNext());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
