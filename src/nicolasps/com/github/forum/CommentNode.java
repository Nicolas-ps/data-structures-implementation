package nicolasps.com.github.forum;

import java.util.ArrayList;
import java.util.List;

public class CommentNode {
    private final Comment comment;
    private CommentNode parent;
    private final List<CommentNode> children;

    public CommentNode(Comment comment) {
        this.comment = comment;
        this.children = new ArrayList<>();
    }

    public void addChild(CommentNode child) {
        child.parent = this;
        this.children.add(child);
    }

    public boolean removeChild(CommentNode child) {
        return this.children.remove(child);
    }

    public Comment getComment() { return comment; }
    public CommentNode getParent() { return parent; }
    public List<CommentNode> getChildren() { return children; }
    public boolean isLeaf() { return children.isEmpty(); }
}
