package nicolasps.com.github.lists;

import java.util.Scanner;

public class Main {
    private static List<String> list;
    private static Scanner scanner;

    static void main(String[] args) {
        list = new List<>();
        scanner = new Scanner(System.in);
        
        boolean running = true;
        while (running) {
            printMenu();
            String command = scanner.nextLine().trim().toLowerCase();
            
            switch (command) {
                case "1":
                case "append":
                    appendElement();
                    break;
                case "2":
                case "insert":
                    insertElement();
                    break;
                case "3":
                case "update":
                    updateElement();
                    break;
                case "4":
                case "remove":
                    removeElement();
                    break;
                case "5":
                case "size":
                    printSize();
                    break;
                case "6":
                case "is-empty":
                    printIsEmpty();
                    break;
                case "7":
                case "is-full":
                    printIsFull();
                    break;
                case "8":
                    printList();
                    break;
                case "9":
                case "clear":
                    clearList();
                    break;
                case "10":
                case "exit":
                case "quit":
                    running = false;
                    System.out.println("Encerrando shell...");
                    break;
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("❌ Comando inválido. Digite 'help' para ver os comandos disponíveis.");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== SHELL INTERATIVO - TESTE DE LISTA ===");
        System.out.println("1. append    - Adicionar elemento ao final");
        System.out.println("2. insert    - Inserir elemento em posição específica");
        System.out.println("3. update    - Atualizar elemento em posição");
        System.out.println("4. remove    - Remover elemento");
        System.out.println("5. size      - Mostrar tamanho da lista");
        System.out.println("6. is-empty  - Verificar se lista está vazia");
        System.out.println("7. is-full   - Verificar se lista está cheia");
        System.out.println("8. print     - Exibe lista");
        System.out.println("9. clear     - Limpa lista");
        System.out.println("10. quit/exit - sair do programa");
        System.out.println("help         - Mostrar ajuda");
        System.out.print("Digite um comando: ");
    }

    private static void printHelp() {
        System.out.println("\n=== AJUDA ===");
        System.out.println("Você pode usar números (1-9) ou nomes de comandos (append, insert, etc).");
        System.out.println("Exemplos:");
        System.out.println("  1 ou append    → Adiciona um elemento");
        System.out.println("  3 ou update    → Atualiza elemento em posição X");
        System.out.println("  5 ou size      → Mostra o tamanho atual");
    }

    private static void appendElement() {
        try {
            System.out.print("Digite o elemento para adicionar: ");
            String element = scanner.nextLine();
            list.append(element);
            System.out.println("✓ Elemento '" + element + "' adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void insertElement() {
        try {
            System.out.print("Digite o índice para inserção: ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.print("Digite o elemento para inserir: ");
            String element = scanner.nextLine();
            list.insert(index, element);
            System.out.println("✓ Elemento '" + element + "' inserido na posição " + index + "!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Índice deve ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void updateElement() {
        try {
            System.out.print("Digite o índice para atualizar: ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.print("Digite o novo elemento: ");
            String element = scanner.nextLine();
            list.update(index, element);
            System.out.println("✓ Elemento na posição " + index + " atualizado para '" + element + "'!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Índice deve ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void removeElement() {
        try {
            System.out.print("Digite o índice do elemento a remover: ");
            int index = Integer.parseInt(scanner.nextLine());
            String removed = list.remove(index);
            System.out.println("✓ Elemento '" + removed + "' removido da posição " + index + "!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Índice deve ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void printSize() {
        int size = list.size();
        System.out.println("📊 Tamanho da lista: " + size);
    }

    private static void printIsEmpty() {
        boolean empty = list.isEmpty();
        System.out.println(empty ? "✓ Lista está vazia" : "✗ Lista não está vazia");
    }

    private static void printIsFull() {
        boolean full = list.isFull();
        System.out.println(full ? "✗ Lista está cheia" : "✓ Lista não está cheia");
    }

    private static void clearList() {
        list = new List<>();
        System.out.println("✓ Lista foi limpa!");
    }

    private static void printList() {
        System.out.println(list.toString());
    }
}
