import java.util.Scanner;

class PhoneBook {

    // Node class for AVL Tree
    class Node {
        long phoneNumber;
        String name;
        Node left, right;
        int height;

        Node(long phoneNumber, String name) {
            this.phoneNumber = phoneNumber;
            this.name = name;
            this.height = 1;
        }
    }

    private Node root;

    // Utility to get the height of a node
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Get the balance factor of a node
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Rotate right
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T = x.right;

        x.right = y;
        y.left = T;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotate left
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T = y.left;

        y.left = x;
        x.right = T;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a new contact
    public void insert(long phoneNumber, String name) {
        root = insertNode(root, phoneNumber, name);
    }

    private Node insertNode(Node node, long phoneNumber, String name) {
        if (node == null) {
            return new Node(phoneNumber, name);
        }

        if (phoneNumber < node.phoneNumber) {
            node.left = insertNode(node.left, phoneNumber, name);
        } else if (phoneNumber > node.phoneNumber) {
            node.right = insertNode(node.right, phoneNumber, name);
        } else {
            System.out.println("Duplicate phone number! Cannot add.");
            return node; // No duplicates allowed
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    // Balance the AVL tree
    private Node balance(Node node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Delete a contact
    public void delete(long phoneNumber) {
        root = deleteNode(root, phoneNumber);
    }

    private Node deleteNode(Node node, long phoneNumber) {
        if (node == null) {
            System.out.println("Phone number not found!");
            return node;
        }

        if (phoneNumber < node.phoneNumber) {
            node.left = deleteNode(node.left, phoneNumber);
        } else if (phoneNumber > node.phoneNumber) {
            node.right = deleteNode(node.right, phoneNumber);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                node = temp;
            } else {
                Node temp = minValueNode(node.right);
                node.phoneNumber = temp.phoneNumber;
                node.name = temp.name;
                node.right = deleteNode(node.right, temp.phoneNumber);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    // Get the node with the smallest value
    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Search for a contact
    public void search(long phoneNumber) {
        Node result = searchNode(root, phoneNumber);
        if (result == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found: " + result.name + " - " + result.phoneNumber);
        }
    }

    private Node searchNode(Node node, long phoneNumber) {
        if (node == null || node.phoneNumber == phoneNumber) {
            return node;
        }
        if (phoneNumber < node.phoneNumber) {
            return searchNode(node.left, phoneNumber);
        }
        return searchNode(node.right, phoneNumber);
    }

    // Display all contacts (in-order traversal)
    public void display() {
        if (root == null) {
            System.out.println("Phone book is empty.");
        } else {
            System.out.println("Phone Book:");
            displayInOrder(root);
        }
    }

    private void displayInOrder(Node node) {
        if (node != null) {
            displayInOrder(node.left);
            System.out.println(node.name + " - " + node.phoneNumber);
            displayInOrder(node.right);
        }
    }
}

public class Dummy {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Phone Book Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter phone number: ");
                    long phoneNumber = sc.nextLong();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    phoneBook.insert(phoneNumber, name);
                    break;
                case 2:
                    System.out.print("Enter phone number to delete: ");
                    long deleteNumber = sc.nextLong();
                    phoneBook.delete(deleteNumber);
                    break;
                case 3:
                    System.out.print("Enter phone number to search: ");
                    long searchNumber = sc.nextLong();
                    phoneBook.search(searchNumber);
                    break;
                case 4:
                    phoneBook.display();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
