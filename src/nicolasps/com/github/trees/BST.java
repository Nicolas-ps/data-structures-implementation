package nicolasps.com.github.trees;

import nicolasps.com.github.exceptions.TreeConstraintViolation;

import java.util.Objects;

public class BST {
    private BinaryNode<Integer> root;

    public BST(Integer root) {
        this.root = new BinaryNode<>(root);
    }

    public BST() {}

    public void insert(Integer number) {
        BinaryNode<Integer> newNode = new BinaryNode<>(number);
        if (Objects.isNull(this.root)) {
            this.root = newNode;
            return;
        }

        BinaryNode<Integer> current = this.root;
        BinaryNode<Integer> parentNode = null;
        while (Objects.nonNull(current)) {
            parentNode = current;
            if (current.getValue().equals(newNode.getValue())) {
                throw new TreeConstraintViolation("This value already exists in the tree");
            }

            if (newNode.getValue() < current.getValue()) {
                current = current.getLeft();
                continue;
            }

            if (newNode.getValue() > current.getValue()) {
                current = current.getRight();
            }
        }

        newNode.setParent(parentNode);
        if (newNode.getValue() < parentNode.getValue()) {
            parentNode.setLeft(newNode);
            return;
        }

        parentNode.setRight(newNode);
    }

    public String printInOder() {
        return this.printInOrderRecursive(this.root);
    }

    public String printInOrderRecursive(BinaryNode<Integer> binaryNode) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.printInOrderRecursive(binaryNode.getLeft()));
        stringBuilder.append(binaryNode.getValue());
        stringBuilder.append(this.printInOrderRecursive(binaryNode.getRight()));

        return stringBuilder.toString();
    }
}
