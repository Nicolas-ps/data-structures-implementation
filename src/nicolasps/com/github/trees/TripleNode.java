package nicolasps.com.github.trees;

public class TripleNode<T> {
    private final T data;
    private TripleNode<T> left;
    private TripleNode<T> middle;
    private TripleNode<T> right;
    private TripleNode<T> parent;

    public TripleNode(T data) {
        this.data = data;
    }

    public TripleNode<T> getLeft() {
        return left;
    }

    public void setLeft(TripleNode<T> left) {
        this.left = left;
    }

    public TripleNode<T> getMiddle() {
        return middle;
    }

    public void setMiddle(TripleNode<T> middle) {
        this.middle = middle;
    }

    public TripleNode<T> getRight() {
        return right;
    }

    public void setRight(TripleNode<T> right) {
        this.right = right;
    }

    public TripleNode<T> getParent() {
        return parent;
    }

    public void setParent(TripleNode<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }
}
