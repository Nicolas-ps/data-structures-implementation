package nicolasps.com.github.trees;

public class TripleNode<T> {
    private T value;
    private TripleNode<T> left;
    private TripleNode<T> right;
    private TripleNode<T> parent;

    public TripleNode(T data) {
        this.value = data;
    }

    public TripleNode<T> getLeft() {
        return left;
    }

    public void setLeft(TripleNode<T> left) {
        this.left = left;
    }

    public TripleNode<T> getRight() {
        return right;
    }

    public void setRight(TripleNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
