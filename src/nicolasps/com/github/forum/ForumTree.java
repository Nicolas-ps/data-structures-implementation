package nicolasps.com.github.forum;

import java.util.ArrayList;
import java.util.List;

public class ForumTree {
    private final List<CommentNode> roots;

    public ForumTree() {
        this.roots = new ArrayList<>();
    }

    // Op 1: Inserir comentário principal
    public void insertComment(Comment comment) {
        roots.add(new CommentNode(comment));
    }

    // Op 2: Responder a um comentário existente
    public boolean replyTo(int parentId, Comment reply) {
        CommentNode parent = findById(parentId);
        if (parent == null) return false;
        parent.addChild(new CommentNode(reply));
        return true;
    }

    // Op 3: Editar comentário
    public boolean editComment(int id, String newText) {
        CommentNode node = findById(id);
        if (node == null) return false;
        node.getComment().setText(newText);
        return true;
    }

    // Op 4: Remover comentário e todas as respostas associadas
    public boolean removeComment(int id) {
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i).getComment().getId() == id) {
                roots.remove(i);
                return true;
            }
        }
        CommentNode target = findById(id);
        if (target == null) return false;
        target.getParent().removeChild(target);
        return true;
    }

    // Op 5: Exibir toda a discussão em formato hierárquico (detalhado)
    public String displayDiscussion() {
        if (roots.isEmpty()) return "(discussão vazia)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roots.size(); i++) {
            appendDetailed(roots.get(i), "", i == roots.size() - 1, sb);
        }
        return sb.toString();
    }

    private void appendDetailed(CommentNode node, String prefix, boolean isLast, StringBuilder sb) {
        String connector = isLast ? "└── " : "├── ";
        sb.append(prefix).append(connector).append(node.getComment()).append("\n");
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        List<CommentNode> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            appendDetailed(children.get(i), childPrefix, i == children.size() - 1, sb);
        }
    }

    // Op 6: Buscar comentário por autor
    public List<Comment> searchByAuthor(String author) {
        List<Comment> results = new ArrayList<>();
        String query = author.toLowerCase();
        for (CommentNode root : roots) {
            searchByAuthorRecursive(root, query, results);
        }
        return results;
    }

    private void searchByAuthorRecursive(CommentNode node, String query, List<Comment> results) {
        if (node.getComment().getAuthor().toLowerCase().contains(query)) {
            results.add(node.getComment());
        }
        for (CommentNode child : node.getChildren()) {
            searchByAuthorRecursive(child, query, results);
        }
    }

    // Op 7: Exibir o caminho completo até um comentário
    public String getPath(int id) {
        CommentNode node = findById(id);
        if (node == null) return null;
        List<String> path = new ArrayList<>();
        CommentNode current = node;
        while (current != null) {
            path.add(0, current.getComment().toShortString());
            current = current.getParent();
        }
        return String.join(" > ", path);
    }

    // Op 8: Listar todos os comentários sem respostas (folhas da árvore)
    public List<Comment> getLeaves() {
        List<Comment> leaves = new ArrayList<>();
        for (CommentNode root : roots) {
            getLeavesRecursive(root, leaves);
        }
        return leaves;
    }

    private void getLeavesRecursive(CommentNode node, List<Comment> leaves) {
        if (node.isLeaf()) {
            leaves.add(node.getComment());
            return;
        }
        for (CommentNode child : node.getChildren()) {
            getLeavesRecursive(child, leaves);
        }
    }

    // Op 9: Contabilizar a quantidade total de comentários de uma discussão
    public int countComments() {
        int total = 0;
        for (CommentNode root : roots) {
            total += countRecursive(root);
        }
        return total;
    }

    private int countRecursive(CommentNode node) {
        int count = 1;
        for (CommentNode child : node.getChildren()) {
            count += countRecursive(child);
        }
        return count;
    }

    // Op 10: Exibir a árvore (visão compacta)
    public String displayTree() {
        if (roots.isEmpty()) return "(árvore vazia)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roots.size(); i++) {
            appendCompact(roots.get(i), "", i == roots.size() - 1, sb);
        }
        return sb.toString();
    }

    private void appendCompact(CommentNode node, String prefix, boolean isLast, StringBuilder sb) {
        String connector = isLast ? "└── " : "├── ";
        sb.append(prefix).append(connector).append(node.getComment().toShortString()).append("\n");
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        List<CommentNode> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            appendCompact(children.get(i), childPrefix, i == children.size() - 1, sb);
        }
    }

    public boolean isEmpty() {
        return roots.isEmpty();
    }

    private CommentNode findById(int id) {
        for (CommentNode root : roots) {
            CommentNode found = findByIdRecursive(root, id);
            if (found != null) return found;
        }
        return null;
    }

    private CommentNode findByIdRecursive(CommentNode node, int id) {
        if (node.getComment().getId() == id) return node;
        for (CommentNode child : node.getChildren()) {
            CommentNode found = findByIdRecursive(child, id);
            if (found != null) return found;
        }
        return null;
    }
}
