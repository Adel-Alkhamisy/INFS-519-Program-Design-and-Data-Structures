/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework2;

/**
 *
 * @author adel
 */
/**
 * This class is a single entry in a binary search tree. It stores a key/value
 * pair of strings and pointers to store * references to the left and right
 * child Nodes in the tree.
 */
public class Node implements Comparable<Node> {

    /* Node key and value*/
    private String key, value;
    /* Child Nodes in the tree */
    private Node left, right;

    /**
     * Creates a new Node. * @param key
     *
     * @param value
     */
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.left=null;
        this.right=null;
    }

    /**
     * @return The Node key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key The Node key
     */
    public void setKey(String key) {
        this.key=key;
    }

    /**
     * @return The Node value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value The Node value
     */
    public void setValue(String value) {
        this.value=value;
    }

    /**
     * @return The left child Node
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * @param left The left child Node
     */
    public void setLeft(Node left) {
        this.left=left;
    }

    /**
     * @return The right child Node
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * @param right The right child Node
     */
    public void setRight(Node right) {
        this.right=right;
    }
     // This is to organize  printing of key and value of a node
    // return the key and value of a node
    @Override
    public String toString() {
        return String.format("%s%n%s%n", this.key, this.value);
    }
    
    /**
     * @param Node that we want to compare its key to the another node
     * @return int value 
     * if the returned value is 0, then First string equal to the second string, in other words it is the same string.
     * if the returned value is negative, then the first string is less than the second string.
     * if the returned value is positive, then the first string is greater than the second string.
     */
    @Override
    public int compareTo(Node that) {
        return this.key.compareTo(that.key);
    }
}
