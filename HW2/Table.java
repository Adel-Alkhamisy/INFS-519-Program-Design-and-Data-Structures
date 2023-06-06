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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements a binary search tree of key/value pairs of strings *
 */
public class Table {

    /**
     * Root node in the tree
     */
    private Node root;

    /**
     * Inserts a new Node into the table. If the provided key already exists, no
     * entry will be created. Otherwise, the new entry is added to the table.
     * Serves as a wrapper for insertNode()
     *
     * @param key
     * @param value
     * @return True if the new node was inserted successfully.
     *
     * False if the provided key already exists in the table.
     */
    public boolean insert(String key, String value) {
        // Create new node
        Node newNode= new Node(key, value);
        // If the tree is empty, then insert the newNode as the root of BST
        if(root==null){
            root=newNode;
            return true;
        }
        else{ // Check if the key is already exist.
            // if the key exist, then inform the user that the key is exist, and 
            // don't make entry with that key.
            String valueAssociatedWithKey=lookUp(key);
            if(valueAssociatedWithKey!=null){
            System.out.println(key+" is already exist");
            return false;
            
       }
            else{ // Otherwise, call insertNode method, to insert the newNode at the correct
                  // place according to BST rules.
            root=insertNode(root, newNode);
            return true;
            }
        }
    }

    /**
     * Inserts a new node into a binary search tree. If the new node key is not
     * unique, the new node will not be added. * @param parent Root node of the
     * tree
     *
     * @param newNode Node to add
     * @return Root node of the altered tree
     */
    private Node insertNode(Node parent, Node newNode) {
        // This is the base case to recursive method.
        if(parent==null){
            parent=newNode;
            return parent;
        }
        // Recursively compare newNode with parent node.
        // if the result of compareTo method is <=-1 then,
        // newNode is less than parent, and recur down the tree on the left side until hit
        // the base case, then make new insertion at this place.
        if(newNode.compareTo(parent)<=-1){
           parent.setLeft(insertNode(parent.getLeft(),newNode));
        }
         // Recursively compare newNode with parent node.
        // if the result of compareTo method is >=1 then,
        // NewNode is greater than parent, and recur down the tree on the right side until hit
        // the base case, then make new insertion at this place.
        else if(newNode.compareTo(parent)>=1){
            parent.setRight(insertNode(parent.getRight(),newNode));
        }
        return parent;
    }

    /**
     * Looks up the table entry with the provided key.
     *
     * @param key
     * @return The value of the entry with the provided key. Null if * no entry
     * with the key can be found.
     */
    public String lookUp(String key) {
        Node node;
        // if the tree is empty, then return null.
        if(root==null){
            return null;
        }
        // if the root key is equal to the key, then return its value.
        else if(root.getKey()==key){
           return root.getValue(); 
        }
        // Otherwise, call lookUpNode method 
        else{
            // if the lookUpNode method return a node, then this is the node that contain 
            // the value that we are looking for, and then return its value.
        node=lookUpNode(root, key);
        if(node!=null){
            return node.getValue();
        }
        else{ // Otherwise, the key not found.
            return null;
        }
    }
    }

    /**
     * Looks up the node in the binary search tree. * @param parent Root node of
     * the tree
     *
     * @param key Key of the node to search for
     * @return The Node with the provided key. Null if no entry with the key can
     * be found.
     */
    private Node lookUpNode(Node parent, String key) {
        // Create a new node, with the key that we look for, only for the purpose of 
        // comparing its key with nodes in BST.
        Node lookingFor= new Node(key,"Test");
        // If key not found
        if(parent==null){
            return null;
        }
       
        else{// if the key is found
            if(lookingFor.compareTo(parent)==0){
            System.out.println("The key is found");
                return parent;
        }
        } // if the key that we are looking for is equal to or less than -1, 
          //then recur down the left side of the tree 
        if(lookingFor.compareTo(parent)<=-1){
                return lookUpNode(parent.getLeft(),key); 
        
        }
        // if the key that we are looking for is equal to or greater than 1, 
        //then recur down the right side of the tree. 
        else if(lookingFor.compareTo(parent)>=1){
                return lookUpNode(parent.getRight(),key);
        }
       
        
       return null;
        
    }

    /**
     * Deletes the table entry with the given key.
     *
     * @param key
     * @return True if the entry was successfully deleted. False if * no entry
     * with the given key was found.
     */
    public boolean delete(String key) {
        // if the lookUp method return a value, then we found the key in BST
        // and call deleteNode method to delete it.
        String valueAssociatedWithKey = lookUp(key);
        if (valueAssociatedWithKey != null) {
            root=deleteNode(root, key);
            System.out.println("Entry was successfully deleted");
            return true;
    }else{ // Otherwise, the key doesn't exist in BST.
            System.out.println("No entry with the given key was found");
            return false;
        }
    }

    /**
     * Deletes the node with the provided key from the given tree
     *
     * @param parent The root of the tree containing the node to delete * @param
     * key The key of the node to delete
     * @return The root node of the altered tree.
     */
    private Node deleteNode(Node parent, String key) {
        // this is  Base Case, If the tree is empty.
        if (parent == null)
            return parent;
        
        // Recursively compare key with parent node's key.
        // if the result of compareTo method is <=-1 then,
        // key is less than parent's key, so recur down the tree on the left side
        if (key.compareTo(parent.getKey()) <= -1) {
            parent.setLeft(deleteNode(parent.getLeft(), key));
        }
        // Recursively compare key with parent node's key.
        // if the result of compareTo method is >=1 then,
        // key is greater than parent's key, so recur down the tree on the right side
        else if(key.compareTo(parent.getKey())>=1){
            parent.setRight(deleteNode(parent.getRight(), key));
        }
        
        // if key is same as root's key, then This is the node
        // to be deleted
        else{ // Node with no child or only one child
            if(parent.getLeft()==null){
                return parent.getRight();
            }
            else if(parent.getRight()==null){
                return parent.getLeft();
            }
            //Get the inOrder successor, if the node has two childen
            // which is the smallest node in the right subtree
            parent.setKey(findSuccessorNode(parent.getRight()).getKey());
            
            //Delete inorder successor
            parent.setRight(deleteNode(parent.getRight(), parent.getKey()));
        }
        return parent;
    }

    /**
     * Finds the inOrder successor node in the right Sub-tree
     *
     * @param parent The root of the tree
     * @return inOrder successor node in the right Sub-tree
     */
    private Node findSuccessorNode(Node parent) {
        while(parent.getLeft()!=null){
           parent=parent.getLeft();
        }
        return parent;
    }
    /*
    *Update the value associated with key
    *if key found, update its value, and return true
    *Otherwise, return false
    *
    *@param key is the key of the node, newValue is the value that will replace old value.
    *@return true if the key was found and the oldvalue replaced by newValue, otherwise return false.
    */
    public boolean update(String key, String newValue) {
        // utilize lookUpNode method to return the node that contains the key
        Node oldNode=lookUpNode(root,key);
        // if lookUpNode method return a node, then this is the node that contains the key.
        // print a message to inform the user about oldValue, then update the node value with the newValue.
        if(oldNode!=null){
           System.out.println("Name: "+oldNode.getKey()+"\n Old Address: "+oldNode.getValue());
           oldNode.setValue(newValue);
            System.out.println("\nNew Address: "+oldNode.getValue());
            return true;
        }else{
            System.out.println(key+ " doesn't exist!");
            return false;
        }
    }

    /**
     * Saves the table to a text file
     *
     * @param filename  Name of the file to contain the table.
     * Name including path and extension
     */
    public void save(String filename) {
        // File name should include full path and extension
        // For example "C:\Users\Adel\Desktop\test.txt"
        File f=new File(filename);
        // check if the the file exists 
        if(f.exists()){
            System.out.println("The file is exists");
            // create BufferedWriter and call writeNode method that will write data to the file. 
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
                writeNode(writer, root);
                System.out.println("The data has been successfully save to file:"+filename);
                writer.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }else{
            try { // if the file doesn't exist, then create new file exactly as specified in 
                  // fileName, and write data to it.
                f.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, false))) {
                    writeNode(writer, root);
                    System.out.println("The data has been successfully save to file:" + f);
                     writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
       
    }

    /**
     * Writes a tree to a file using pre-order traversal * (parent, left, right)
     *
     * @param writer Writer to the file
     * @param node Root node of the tree to write
     * @throws IOException
     */
    private void writeNode(BufferedWriter writer, Node node) throws IOException {
        // if node is null do nothing
        if(node==null){
        }else{// Otherwise,
            // first wite the node data to file
            writer.write(node.toString());
            // then, recur on left child
            writeNode(writer, node.getLeft());
            // then, recur on right child
            writeNode(writer, node.getRight());
        }
    }

    /**
     * Displays all nodes in the table.
     *
     * @return The number of nodes in the table.
     */
    public int displayAll() {
        // if the tree is empty
        if(root==null){
            return 0;
        }
        else{// call displayNode method to print all tree data in inOrder.
          return displayNode(root);
        }
    }

    /**
     * Displays all nodes in a (sub)tree using in-order traversal * (left,
     * parent, right)
     *
     * @param node The root node of the tree to display
     * @return The number of nodes in the tree
     */
    private int displayNode(Node node) {
        int size=1;
        // this is the base case
        if(node==null){
            return 0;
        }else{
        // first, recur on left child and increment the size
        size+=displayNode(node.getLeft());
        // then, print node data
        System.out.print(node.toString());
        //then, recur on right child and increment the size
        size+=displayNode(node.getRight());
    }  // retun the size of the BST.
        return size;
    }
}
