package nicolasps.com.github.trees;

import nicolasps.com.github.lists.List;

public class Node<T> {
    private final List<Node<T>> children;
    private final T data;

    public Node() {
        this.children = new List<>();
        this.data = null;
    }

    public Node(T value) {
        this.children = new List<>();
        this.data = value;
    }

    public List<Node<T>> addChild(Node<T> node) {
        children.append(node);
        return this.children;
    }

    public T getData() {
        return data;
    }
}
