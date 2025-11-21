public class BinarySearchTree {

    // Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // isEmpty method
    boolean isEmpty() {
        return root == null;
    }

    // Insert method
    Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }
        if (key < node.data)
            node.left = insert(node.left, key);
        else if (key > node.data)
            node.right = insert(node.right, key);

        return node;
    }

    // Wrapper to call insert from main
    void insert(int key) {
        root = insert(root, key);
    }

    // Search method
    boolean search(Node node, int key) {
        if (node == null)
            return false;
        if (node.data == key)
            return true;
        else if (key < node.data)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    // Delete method
    Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.data)
            node.left = delete(node.left, key);
        else if (key > node.data)
            node.right = delete(node.right, key);
        else {
            // Node found
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal (sorted output)
    void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // Main method to test
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("Is tree empty? " + bst.isEmpty());

        // Insert nodes
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal of BST:");
        bst.inorder(bst.root);

        System.out.println("\n\nSearching 40: " +            bst.search(bst.root, 40));
        System.out.println("Searching 90: " + bst.search(bst.root, 90));

        // Delete a node
        bst.root = bst.delete(bst.root, 30);
        System.out.println("\nAfter deleting 30, inorder:");
        bst.inorder(bst.root);

        System.out.println("\nIs tree empty? " + bst.isEmpty());
    }
}
