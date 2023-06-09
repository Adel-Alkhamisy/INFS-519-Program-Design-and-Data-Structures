/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3v2;

/**
 *
 * @author adel
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements an AVL tree of key/value pairs *
 */
public class Table {

    /**
     * Root node in the tree
     */
    private Node root;
    
    /**
     * Inserts a new Node into the table. If the provided key already exists, no
     * entry will be created. Otherwise, the new entry is * added to the table.
     *
     * @param key
     * @param value
     * @return True if the new node was inserted successfully.
     *
     * False if the provided key already exists in the table.
     */
    public boolean insert(String key, String value) {
        // Create new node
        Node newNode = new Node(key, value);
        // If the tree is empty, then insert the newNode as the root of BST
        if (root == null) {
            root = newNode;
            return true;
        } 
        else { // Otherwise, call insertNode method, to insert the newNode at the correct
                // place according to BST rules.
                root = insertNode(root, newNode);   
                return true;
            }
        }
 

    /**
     * Inserts a new node into an AVL tree. Note that if the new node key is not
     * unique, the * new node will not be added.
     *
     * @param root Root node of the tree
     * @param newNode Node to add
     * @return Root node of the altered tree
     */
    private Node insertNode(Node root, Node newNode) {
        Node n = root;
        while (true) {
            if (n.getKey().equals(newNode.getKey())) {
                System.out.println("The key is already exists!");
                return root;
            }
            Node parent = n;
            boolean goLeft = n.compareTo(newNode)>0;
            n = goLeft ? n.getLeft() : n.getRight();
            if (n == null) {
                if (goLeft) {
                    parent.setLeft(new Node(newNode.getKey(),newNode.getValue(),parent));
                } else {
                    parent.setRight(new Node(newNode.getKey(),newNode.getValue(),parent));
                }
                rebalance(parent);
                break;
            }
        }
        return this.root;
  
        
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
        if (root == null) {
            return null;
        } // if the root key is equal to the key, then return its value.
        else if (root.getKey() == key) {
            return root.getValue();
        } // Otherwise, call lookUpNode method 
        else {
            // if the lookUpNode method return a node, then this is the node that contain 
            // the value that we are looking for, and then return its value.
            node = lookUpNode(root, key);
            if (node != null) {
                return node.getValue();
            } else { // Otherwise, the key not found.
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
        Node lookingFor = new Node(key, "Test");
        // If key not found
        if (parent == null) {
            return null;
        } else {// if the key is found
            if (lookingFor.compareTo(parent) == 0) {
                System.out.println("The key is found");
                return parent;
            }
        } // if the key that we are looking for is equal to or less than -1, 
        //then recur down the left side of the tree 
        if (lookingFor.compareTo(parent) <= -1) {
            return lookUpNode(parent.getLeft(), key);

        } // if the key that we are looking for is equal to or greater than 1, 
        //then recur down the right side of the tree. 
        else if (lookingFor.compareTo(parent) >= 1) {
            return lookUpNode(parent.getRight(), key);
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
        if (root == null) {
            System.out.println("The AVL tree is empty!");
            return false;
        }
        Node child = root;
        while (child != null) {
            Node node = child;
            child = key.compareTo(node.getKey())>=0 ? node.getRight() : node.getLeft();
            if (key.equals(node.getKey())) {
                System.out.println("The Key is found!");
                root=deleteNode(node,key);
                System.out.println("The Key is successfully deleted!");
                return true;
            }
        }
        System.out.println("The key doesn't exists!");
        return false;
    }

    /**
     * Deletes the node with the provided key from the given tree
     *
     * @param root - The root of the tree containing the node to delete * 
     * @param key - The key of the node to delete
     * @return The root node of the altered tree.
     */
    private Node deleteNode(Node root, String key) {
        //Case 1: node to be deleted has no children, i.e. it is a leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            // if the node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (root.getParent() == null) {
                this.root = null;
            } else {
                Node parent = root.getParent();
                if (parent.getLeft() == root) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                rebalance(parent);
            }
            return this.root;
        }
        if (root.getLeft() != null) {
            Node child = root.getLeft();
            //Get the inorder predecessor
            child=findLargestNode(child);
            //update the key
            root.setKey(child.getKey());
            // update the value (address)
            root.setValue(child.getValue());
            Node temp = deleteNode(child,key);
        } else {
            Node child = root.getRight();
            //Get the inorder predecessor
            child=findLargestNode(child);
            //update the key
            root.setKey(child.getKey());
            // update the value (address)
            root.setValue(child.getValue());
            Node temp = deleteNode(child,key);
        }
        return this.root;
        
    }

    /**
     * Finds the largest node of the provided tree 
     * @param parent - The root of the tree
     *
     * @return The inorder predecessor node in the provided tree
     */
    private Node findLargestNode(Node parent) {
        if (parent.getLeft() != null) {
            while (parent.getRight() != null) {
                parent = parent.getRight();
            }
            return parent;
        } else {
            while (parent.getLeft() != null) {
                parent = parent.getLeft();
            }
            return parent;
        }   
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
        Node oldNode = lookUpNode(root, key);
        // if lookUpNode method return a node, then this is the node that contains the key.
        // print a message to inform the user about oldValue, then update the node value with the newValue.
        if (oldNode != null) {
            System.out.println("Name: " + oldNode.getKey() + "\n Old Address: " + oldNode.getValue());
            oldNode.setValue(newValue);
            System.out.println("\nNew Address: " + oldNode.getValue());
            return true;
        } else {
            System.out.println(key + " doesn't exist!");
            return false;
        }
    }

    /**
     * Saves the table to a text file
     *
     * @param filename Name of the file to contain the table. Name including
     * path and extension
     */
    public void save(String filename) {
        // File name should include full path and extension
        // For example "C:\Users\Adel\Desktop\test.txt"
        File f = new File(filename);
        // check if the the file exists 
        if (f.exists()) {
            System.out.println("The file is exists");
            // create BufferedWriter and call writeNode method that will write data to the file. 
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
                writeNode(writer, root);
                System.out.println("The data has been successfully save to file:" + filename);
                writer.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
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
     * Writes a tree to a file using pre-order traversal 
     * (parent, left, right)
     * @param writer Writer to the file
     * @param node Root node of the tree to write
     * @throws IOException
     */
    private void writeNode(BufferedWriter writer, Node node) throws IOException {
        // if node is null do nothing
        if (node == null) {
        } else {// Otherwise,
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
        if (root == null) {
            return 0;
        } else {// call displayNode method to print all tree data in inOrder.
            return displayNode(root);
        }
    }

    /**
     * Displays all nodes in a (sub)tree using in-order traversal
     * (left, parent, right)
     *
     * @param node The root node of the tree to display
     * @return The number of nodes in the tree
     */
    private int displayNode(Node node) {
        int size = 1;
        // this is the base case
        if (node == null) {
            return 0;
        } else {
            // first, recur on left child and increment the size
            size += displayNode(node.getLeft());
            // then, print node data
            System.out.print(node.toString());
            System.out.printf("key:" + node.getKey() + " height:" + node.getHeight() + " balance:" + node.getBalance() + "\n");
            try {
                System.out.printf(" parent:" + node.getParent().getKey() + "\n");
            } catch (NullPointerException e) {
                System.out.print(" parent of "+node.getKey()+" NullPointerException\n");
                System.out.println(node.getKey()+" is the Root");
            }
            //then, recur on right child and increment the size
            size += displayNode(node.getRight());
        }  // retun the size of the BST.
        return size;
    }
    /**
     * Re-balances an AVL tree at the provided Note that the height and balance
     * factor will also be updated.
     *
     * @param n - Root of the (sub)tree to balance
     * 
     */
    private void rebalance(Node n) {
        updateAVL(n);
        if (n.getBalance() == -2) {
            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight())) {
                n = rotateRight(n);
            } else {
                n = rotateLeftThenRight(n);
            }
        } else if (n.getBalance() == 2) {
            if (height(n.getRight().getRight()) >= height(n.getRight().getLeft())) {
                n = rotateLeft(n);
            } else {
                n = rotateRightThenLeft(n);
            }
        }
        if (n.getParent() != null) {
            rebalance(n.getParent());
        } else {
            root = n;
        }
    }
    
    /**
     * Performs a Right-Right single rotation for an unbalanced AVL tree.
     *
     * @param root - The root of the unbalanced tree 
     * @return The root of the newly balanced tree
     */
    private Node rotateLeft(Node root) {
        Node newRoot = root.getRight();
        newRoot.setParent(root.getParent());
        root.setRight(newRoot.getLeft());
        if (root.getRight() != null) {
            root.getRight().setParent(root);
        }
        newRoot.setLeft(root);
        root.setParent(newRoot);
        if (newRoot.getParent() != null) {
            if (newRoot.getParent().getRight() == root) {
                newRoot.getParent().setRight(newRoot);
            } else {
                newRoot.getParent().setLeft(newRoot);
            }
        }
        updateAVL(root);
        updateAVL(newRoot);
        return newRoot;
    }
    
    /**
     * Performs a left-left single rotation for an unbalanced AVL tree.
     *
     * @param root - The root of the unbalanced tree 
     * @return The root of the newly balanced tree
     */
    private Node rotateRight(Node root) {
        Node newRoot = root.getLeft();
        // newRoot take the parent of the root
        newRoot.setParent(root.getParent());
        root.setLeft(newRoot.getRight());
        if (root.getLeft() != null) {
            root.getLeft().setParent(root);
        }
        newRoot.setRight(root);
        root.setParent(newRoot);
        if (newRoot.getParent() != null) {
            if (newRoot.getParent().getRight() == root) {
                newRoot.getParent().setRight(newRoot);
            } else {
                newRoot.getParent().setLeft(newRoot);
            }
        }
        updateAVL(root);
        updateAVL(newRoot);
        return newRoot;
    }
    
    /**
     * Performs a left-right double rotation for an
     * unbalanced AVL tree.
     * @param root The root of the unbalanced tree 
     * @return The root of the newly balanced tree
     */
    private Node rotateLeftThenRight(Node n) {
        n.setLeft(rotateLeft(n.getLeft()));
        return rotateRight(n);
    }
    
    /**
     * Performs a right-left double rotation for an unbalanced AVL tree.
     *
     * @param root - The root of the unbalanced tree 
     * @return The root of the newly balanced tree
     */
    private Node rotateRightThenLeft(Node n) {
        n.setRight(rotateRight(n.getRight()));
        return rotateLeft(n);
    }
    
    
    /**
     * Updates the height and balance factor of a node. 
     * Note that this method assumes all child nodes have up-to-date height and balance factors.
     */
    private void updateAVL(Node n) {
        if (n != null) {
            n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
            n.setBalance(height(n.getRight()) - height(n.getLeft()));
        }
        }
    
    
// A utility function to get the height of the tree
    private int height(Node n) {
        if (n == null) {
            return -1;
        }
        return n.getHeight();
    }

    }
    
    
