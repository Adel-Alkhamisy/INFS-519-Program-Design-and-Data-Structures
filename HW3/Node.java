/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3v2;

/**
 *
 * @author Adel
 */
/**
 * This class is a single entry in an AVL tree. It stores a key/value pair of
 * strings, as well as references to the left child, right child, and parent
 * Nodes within the tree.
 */
public class Node implements Comparable<Node> {

    /* Node key and value*/
    private String key, value;
    /* Child Nodes in the tree */
    private Node left, right, parent;
    /* Node balance and height */
    private int balance, height;

    /**
     * Creates a new Node.
     *
     * @param key
     * @param value
     */
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height=0;
        this.balance=0;
        
    }
    public Node(String key, String value, Node parent) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent =parent;
        this.height=0;
        this.balance=0;
    }

    /**
     * @return The Node key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key – Set the Node key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The Node value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value – Set the Node value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return The left child Node
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * @param left – Set the left child Node
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * @return The right child Node
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * @param right – Set the right child Node
     */
    public void setRight(Node right) {
        this.right = right;
    }
    public Node getParent(){
        return this.parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }
    /**
     * @return The balance of this node in the AVL tree. (Left subtree height
     * -Right subtree height)
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * @param balance -Set the balance of this node in the AVL tree (Left
     * subtree height -Right subtree height)
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * @return The height of this node in the AVL tree
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @param height – Set the height of this node in the AVL tree
     */
    public void setHeight(int height) {
        this.height = height;
    }

    // This is to organize  printing of key and value of a node
    // return the key and value of a node
    @Override
    public String toString() {
        return String.format("%s%n%s%n", this.key, this.value);
    }

    /**
     * @param Node that we want to compare its key to the another node
     * @return int value if the returned value is 0, then First string equal to
     * the second string, in other words it is the same string. if the returned
     * value is negative, then the first string is less than the second string.
     * if the returned value is positive, then the first string is greater than
     * the second string.
     */
    @Override
    public int compareTo(Node that) {
        return this.key.compareTo(that.key);
    }

}
