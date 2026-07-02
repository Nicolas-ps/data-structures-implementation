package nicolasps.com.github.trees;

import nicolasps.com.github.exceptions.TreeConstraintViolation;

import java.util.Scanner;

public class BSTMain {
    private static BST bst;
    private static Scanner scanner;

    public static void main(String[] args) {
        bst = new BST();
        scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "1":
                case "inserir":
                    insertValue();
                    break;
                case "2":
                case "exibir":
                    printInOrder();
                    break;
                case "0":
                case "sair":
                case "exit":
                    running = false;
                    System.out.println("Encerrando testes...");
                    break;
                default:
                    System.out.println("❌ Comando inválido.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== TESTE DA ÁRVORE BINÁRIA DE BUSCA (BST) ===");
        System.out.println("1. inserir - Inserir um valor inteiro");
        System.out.println("2. exibir  - Exibir valores em ordem (in-order)");
        System.out.println("0. sair    - Encerrar o teste");
        System.out.print("Digite um comando: ");
    }

    private static void insertValue() {
        try {
            System.out.print("Valor a inserir: ");
            int value = Integer.parseInt(scanner.nextLine().trim());
            bst.insert(value);
            System.out.println("✓ Valor " + value + " inserido com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Valor inválido. Digite um número inteiro.");
        } catch (TreeConstraintViolation e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void printInOrder() {
        System.out.println("1. InOrder - Imprimir a arvore em ordem");
        System.out.println("2. PreOrder  - Exibir valores em pre ordem (in-order)");
        System.out.println("3. PostOrder  - Exibir valores em pos ordem (in-order)");

        int value;
        try {
            System.out.print("Ordem de impressão: ");
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("❌ Erro ao exibir a árvore: " + e.getMessage());
            return;
        }

        switch (value) {
            case 1:
                System.out.print("Em ordem: " + bst.printInOder());
                return;
            case 2:
                System.out.print("Em pre ordem: " + bst.printPreOrder());
                return;
            case 3:
                System.out.print("Em pos ordem: " + bst.printPosOrder());
        }
    }
}