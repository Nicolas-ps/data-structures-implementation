package nicolasps.com.github.lists;

import nicolasps.com.github.contracts.Listable;
import nicolasps.com.github.exceptions.EmptyListException;
import nicolasps.com.github.exceptions.FullListException;

import java.util.Objects;

public class List<T> implements Listable<T> {
    private int tail;
    private int length;
    private final int size;
    private final Node[] data;
    private Node<T> lastNode;
    private Node<T> firstNode;

    public List(int size) {
        this.data = new Node[size];
        this.size = size;
        this.tail = -1;
    }

    public List() {
        this.data = new Node[10];
        this.size = 10;
        this.tail = -1;
    }

    @Override
    public void append(T data) {
        if (isFull()) {
            throw new FullListException();
        }

        Node<T> node = new Node<>(data);
        if (isEmpty()) {
            this.data[this.length] = node;
            this.length++;
            this.tail++;
            this.lastNode = node;
            this.firstNode = node;
            return;
        }

        this.data[this.length] = node;
        this.tail++;
        this.length++;

        node.setPrevious(this.lastNode);
        this.lastNode.setNext(node);
        this.lastNode = node;
    }

    @Override
    public void update(int index, T data) {

    }

    @Override
    public void insert(int index, T data) {
        if (isFull()) {
            throw new FullListException();
        }
    }

    @Override
    public T remove(int index) throws EmptyListException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        if (! this.indexIsValid(index) | index > this.tail) {
            throw new IndexOutOfBoundsException();
        }

        this.data[index] = null;
        this.length--;

        Node<T> current = this.firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        if (Objects.nonNull(current.getPrevious())) {
            current.getPrevious().setNext(current.getNext());
        }

        if (current.hasNext()) {
            current.getNext().setPrevious(current.getPrevious());
        }

        return current.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return this.length;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        Node<T> current = this.data[0];
        for (int i = 0; i < this.length; i++) {
            stringBuilder.append(current.getData());
            if (! current.hasNext()) {
                break;
            }

            current = current.getNext();
            stringBuilder.append(",");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean indexIsValid(int index) {
        if (index < 0 || index > (this.size - 1)) {
            return false;
        }

        return true;
    }
}
