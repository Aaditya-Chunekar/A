import java.util.Scanner;

class PhoneBook {

    // Node class to represent a contact in the tree
    static class ContactNode {
        String name;
        String phoneNumber;
        ContactNode left, right;

        public ContactNode(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.left = this.right = null;
        }
    }

    private ContactNode root;

    // Insert a new contact
    public void insert(String name, String phoneNumber) {
        root = insertRec(root, name, phoneNumber);
        System.out.println("Contact added successfully.");
    }

    private ContactNode insertRec(ContactNode root, String name, String phoneNumber) {
        if (root == null) {
            return new ContactNode(name, phoneNumber);
        }
        if (name.compareToIgnoreCase(root.name) < 0) {
            root.left = insertRec(root.left, name, phoneNumber);
        } else if (name.compareToIgnoreCase(root.name) > 0) {
            root.right = insertRec(root.right, name, phoneNumber);
        } else {
            System.out.println("Contact with this name already exists.");
        }
        return root;
    }

    // Search for a contact
    public void search(String name) {
        ContactNode result = searchRec(root, name);
        if (result != null) {
            System.out.println("Contact found: " + result.name + " - " + result.phoneNumber);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private ContactNode searchRec(ContactNode root, String name) {
        if (root == null || root.name.equalsIgnoreCase(name)) {
            return root;
        }
        if (name.compareToIgnoreCase(root.name) < 0) {
            return searchRec(root.left, name);
        }
        return searchRec(root.right, name);
    }

    // Update a contact
    public void update(String name, String newPhoneNumber) {
        ContactNode contact = searchRec(root, name);
        if (contact != null) {
            contact.phoneNumber = newPhoneNumber;
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Delete a contact
    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private ContactNode deleteRec(ContactNode root, String name) {
        if (root == null) {
            System.out.println("Contact not found.");
            return root;
        }
        if (name.compareToIgnoreCase(root.name) < 0) {
            root.left = deleteRec(root.left, name);
        } else if (name.compareToIgnoreCase(root.name) > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            // Node with one child or no child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Node with two children: get the in-order successor
            ContactNode successor = findMin(root.right);
            root.name = successor.name;
            root.phoneNumber = successor.phoneNumber;
            root.right = deleteRec(root.right, successor.name);
        }
        return root;
    }

    private ContactNode findMin(ContactNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Display all contacts in alphabetical order
    public void displayAllContacts() {
        System.out.println("Phone Book:");
        if (root == null) {
            System.out.println("No contacts found.");
        } else {
            inorderTraversal(root);
        }
    }

    private void inorderTraversal(ContactNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.name + " - " + root.phoneNumber);
            inorderTraversal(root.right);
        }
    }

    // Main method for interaction
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPhone Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    phoneBook.insert(name, phoneNumber);
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    phoneBook.search(name);
                    break;
                case 3:
                    System.out.print("Enter name to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    phoneNumber = scanner.nextLine();
                    phoneBook.update(name, phoneNumber);
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    phoneBook.delete(name);
                    break;
                case 5:
                    phoneBook.displayAllContacts();
                    break;
                case 6:
                    System.out.println("Exiting phone book. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
