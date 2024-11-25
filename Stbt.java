class ThreadedBinaryTree {

    // Node class for the threaded binary tree
    static class Node {
        int key;
        Node left, right;
        boolean isThreaded; // True if the right pointer is a thread

        public Node(int key) {
            this.key = key;
            this.left = this.right = null;
            this.isThreaded = false;
        }
    }

    private Node root;

    // Insert a node into the binary tree (standard BST insertion)
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Convert the binary tree into a threaded binary tree
    public void createThreads() {
        Node[] prev = new Node[1]; // Array to hold previous node reference
        createThreadsRec(root, prev);
    }

    private void createThreadsRec(Node node, Node[] prev) {
        if (node == null) return;

        // Recur to the left subtree
        createThreadsRec(node.left, prev);

        // If the previous node exists and its right pointer is null, thread it
        if (prev[0] != null && prev[0].right == null) {
            prev[0].right = node;
            prev[0].isThreaded = true;
        }

        // Update the previous node
        prev[0] = node;

        // Recur to the right subtree
        if (!node.isThreaded) {
            createThreadsRec(node.right, prev);
        }
    }

    // Inorder traversal using threads
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Node current = findLeftmost(root);

        System.out.println("Inorder traversal:");
        while (current != null) {
            System.out.print(current.key + " ");

            // If the current node is threaded, follow the thread
            if (current.isThreaded) {
                current = current.right;
            } else {
                // Otherwise, move to the leftmost node of the right subtree
                current = findLeftmost(current.right);
            }
        }
        System.out.println();
    }

    // Helper method to find the leftmost node of a subtree
    private Node findLeftmost(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Main method to demonstrate the threaded binary tree
    public static void main(String[] args) {
        ThreadedBinaryTree tbt = new ThreadedBinaryTree();

        // Insert nodes into the tree
        tbt.insert(50);
        tbt.insert(30);
        tbt.insert(70);
        tbt.insert(20);
        tbt.insert(40);
        tbt.insert(60);
        tbt.insert(80);

        // Create threads for the tree
        tbt.createThreads();

        // Perform an inorder traversal using threads
        tbt.inorderTraversal();
    }
}
