package nicolasps.com.github.trees;

import java.util.Objects;

public class ABP {
    private TripleNode<Integer> root;

    public ABP(Integer root) {
        this.root = new TripleNode<>(root);
    }

    public void insert(Integer number) {
        TripleNode<Integer> parentNode = this.root;
        insertRecursive(number, parentNode);
    }

    private void insertRecursive(Integer number, TripleNode<Integer> parentNode) {
        TripleNode<Integer> node = new TripleNode<>(number);
        if (node.getValue() < parentNode.getValue()) {
            if (Objects.isNull(parentNode.getLeft())) {
                this.root.setLeft(root);
                return;
            }
        }
    }
}
