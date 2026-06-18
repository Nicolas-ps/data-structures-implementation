package nicolasps.com.github.forum;

import java.util.List;
import java.util.Scanner;

public class ForumMain {
    private static ForumTree forum;
    private static Scanner scanner;

    public static void main(String[] args) {
        forum = new ForumTree();
        scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "1":
                case "inserir":
                    insertComment();
                    break;
                case "2":
                case "responder":
                    replyToComment();
                    break;
                case "3":
                case "editar":
                    editComment();
                    break;
                case "4":
                case "remover":
                    removeComment();
                    break;
                case "5":
                case "discussao":
                    displayDiscussion();
                    break;
                case "6":
                case "buscar":
                    searchByAuthor();
                    break;
                case "7":
                case "caminho":
                    displayPath();
                    break;
                case "8":
                case "folhas":
                    listLeaves();
                    break;
                case "9":
                case "contar":
                    countComments();
                    break;
                case "10":
                case "arvore":
                    displayTree();
                    break;
                case "0":
                case "sair":
                case "exit":
                    running = false;
                    System.out.println("Encerrando sistema...");
                    break;
                case "ajuda":
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("❌ Comando inválido. Digite 'ajuda' para ver os comandos disponíveis.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE COMENTÁRIOS EM FÓRUM ===");
        System.out.println("1.  inserir    - Inserir comentário principal");
        System.out.println("2.  responder  - Responder a um comentário");
        System.out.println("3.  editar     - Editar comentário");
        System.out.println("4.  remover    - Remover comentário (e todas as respostas)");
        System.out.println("5.  discussao  - Exibir discussão completa");
        System.out.println("6.  buscar     - Buscar comentários por autor");
        System.out.println("7.  caminho    - Exibir caminho até um comentário");
        System.out.println("8.  folhas     - Listar comentários sem respostas");
        System.out.println("9.  contar     - Contar total de comentários");
        System.out.println("10. arvore     - Exibir árvore (visão compacta)");
        System.out.println("0.  sair       - Encerrar o sistema");
        System.out.println("ajuda          - Mostrar ajuda");
        System.out.print("Digite um comando: ");
    }

    private static void printHelp() {
        System.out.println("\n=== AJUDA ===");
        System.out.println("Você pode usar números (0-10) ou nomes de comandos.");
        System.out.println("Exemplos:");
        System.out.println("  1 ou inserir   → Adiciona um comentário principal");
        System.out.println("  2 ou responder → Responde a um comentário pelo ID");
        System.out.println("  7 ou caminho   → Mostra a hierarquia até um comentário");
        System.out.println("  10 ou arvore   → Exibe a estrutura em forma de árvore");
    }

    private static void insertComment() {
        try {
            System.out.print("Autor: ");
            String author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("❌ O autor não pode ser vazio.");
                return;
            }
            System.out.print("Texto do comentário: ");
            String text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("❌ O texto não pode ser vazio.");
                return;
            }
            Comment comment = new Comment(author, text);
            forum.insertComment(comment);
            System.out.println("✓ Comentário #" + comment.getId() + " inserido com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void replyToComment() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        try {
            System.out.print("ID do comentário para responder: ");
            int parentId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Autor da resposta: ");
            String author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("❌ O autor não pode ser vazio.");
                return;
            }
            System.out.print("Texto da resposta: ");
            String text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("❌ O texto não pode ser vazio.");
                return;
            }
            Comment reply = new Comment(author, text);
            if (forum.replyTo(parentId, reply)) {
                System.out.println("✓ Resposta #" + reply.getId() + " adicionada ao comentário #" + parentId + "!");
            } else {
                System.out.println("❌ Comentário #" + parentId + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void editComment() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        try {
            System.out.print("ID do comentário a editar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Novo texto: ");
            String newText = scanner.nextLine().trim();
            if (newText.isEmpty()) {
                System.out.println("❌ O texto não pode ser vazio.");
                return;
            }
            if (forum.editComment(id, newText)) {
                System.out.println("✓ Comentário #" + id + " atualizado com sucesso!");
            } else {
                System.out.println("❌ Comentário #" + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void removeComment() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        try {
            System.out.print("ID do comentário a remover: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Tem certeza? Isso removerá todas as respostas associadas. (s/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("s")) {
                System.out.println("Operação cancelada.");
                return;
            }
            if (forum.removeComment(id)) {
                System.out.println("✓ Comentário #" + id + " e suas respostas foram removidos.");
            } else {
                System.out.println("❌ Comentário #" + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void displayDiscussion() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        System.out.println("\n=== DISCUSSÃO COMPLETA ===");
        System.out.println(forum.displayDiscussion());
    }

    private static void searchByAuthor() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        try {
            System.out.print("Nome do autor (ou parte): ");
            String query = scanner.nextLine().trim();
            if (query.isEmpty()) {
                System.out.println("❌ Digite um nome para buscar.");
                return;
            }
            List<Comment> results = forum.searchByAuthor(query);
            if (results.isEmpty()) {
                System.out.println("Nenhum comentário encontrado para \"" + query + "\".");
            } else {
                System.out.println("\n=== " + results.size() + " resultado(s) para \"" + query + "\" ===");
                for (Comment c : results) {
                    System.out.println(c);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void displayPath() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        try {
            System.out.print("ID do comentário: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            String path = forum.getPath(id);
            if (path == null) {
                System.out.println("❌ Comentário #" + id + " não encontrado.");
            } else {
                System.out.println("\nCaminho: " + path);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. Digite um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void listLeaves() {
        if (forum.isEmpty()) {
            System.out.println("Não há comentários cadastrados.");
            return;
        }
        List<Comment> leaves = forum.getLeaves();
        System.out.println("\n=== COMENTÁRIOS SEM RESPOSTAS (" + leaves.size() + ") ===");
        for (Comment c : leaves) {
            System.out.println(c);
        }
    }

    private static void countComments() {
        int total = forum.countComments();
        System.out.println("📊 Total de comentários na discussão: " + total);
    }

    private static void displayTree() {
        if (forum.isEmpty()) {
            System.out.println("Árvore vazia.");
            return;
        }
        System.out.println("\n=== ÁRVORE DE COMENTÁRIOS ===");
        System.out.println(forum.displayTree());
    }
}
