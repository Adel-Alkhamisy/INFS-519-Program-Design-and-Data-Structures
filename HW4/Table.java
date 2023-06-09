/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author adel
 */

/**
 * This class implements a HashTable of key/value pairs using Open Hashing with Chaining technique
 * It uses an array of singly Linked Lists to handle collisions.
 */
public class Table {
    // Create an array of linked lists 
    private Node hashTable[];
    private int tableSize=10;
    public Table() {
        this.hashTable = new Node[tableSize];
    }
    /**
     * This is the function that will hash the provided key.
     * @param key
     * @param tableSize
     * @return hash value to the provided key  
     */
    public static int hash(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if (hashVal < 0) //overflow case
        {
            hashVal += tableSize;
        }
        return hashVal;
    }

    /**
     * Inserts a reference to a new key/value pair (node) into the  HashTable. If the provided key already exists, no
     * entry will be created. Otherwise, the new entry is * added to the table.
     *
     * @param key
     * @param value
     * @return True if the new node was inserted successfully.
     * False if the provided key already exists in the table.
     */
    public boolean insert(String key, String value) {
        Node node = new Node(key, value);
        Node head;
        // Get the hash value of the provided key
        int hashValue=hash(key, tableSize);
        if(lookUp(key)==null){
            // check array's index that equal to the hash value
            // if the index is null, then add a reference to the first node in this index.
        if(hashTable[hashValue]==null){
           head=node;
           hashTable[hashValue]=head;
            System.out.println(key+" is inserted successfully in the Hash Table!");
            return true;
        }
        
        else {// Otherwise, traverse the linked List and add the new node at the end of it. 
            Node n = hashTable[hashValue];
            while (n.getNext() != null) {
                n = n.getNext();
            }
            
            n.setNext(node);
            System.out.println(key+" is inserted successfully in the Hash Table!");
            return true;
        }
    }else{
          return false;
        }
        
    }


    /**
     * Looks up the HashTable entry with the provided key.
     *
     * @param key
     * @return The value of the entry with the provided key.
     * Null if no entry with the key can be found.
     */
    public String lookUp(String key) {
        // Get the Hash value of the provided key.
       int hashValue = hash(key, tableSize);
        Node node=hashTable[hashValue];
        // if we hit an empty cell, then The key does not exist in the Hash Table and return null.
        if (node==null) {
            return null;
        } // if the head key is equal to the key, then return its value.
        else if (node.getKey().equals(key)) {
            return node.getValue();
        }
        else{//Otherwise, traverse the linked List
            while (node.getNext() != null) {
                node = node.getNext();
                if(node.getKey().equals(key)){
                    return node.getValue();
                }
            }   
        }
        return null;
    }


    /**
     * Deletes the table entry with the given key.
     *
     * @param key
     * @return True if the entry was successfully deleted. 
     * False if no entry with the given key was found.
     */
    public boolean delete(String key) {
        int hashValue = hash(key, tableSize);
        Node node = hashTable[hashValue];
        // if we hit an empty cell, then The key does not exist in the Hash Table and return null.
        if (node == null) {
            System.out.println(key+" is not found!");
            System.out.println("This cell in the Hash Table is empty!");
            return false;
        } // if the first node's key is equal to the key, then delete it by moving the pointer to the next node in Linked List.
        else if (node.getKey().equals(key)) {
            hashTable[hashValue]=node.getNext();
            System.out.println(key + " has been deleted!");
            return true;
            
        } else {// Otherwise, traverse the linked list and delete the node that contains the key if found.
            while (node.getNext() != null) {
                
                if (node.getNext().getKey().equals(key)) {
                    node.setNext(node.getNext().getNext());
                    System.out.println(key + " has been deleted!");
                    return true;
                }
                node = node.getNext();
            }
            // Otherwise, the key is not found in the Linked List.
            System.out.println(key + " is not found!");
            return false;
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
        int hashValue = hash(key, tableSize);
        Node node=hashTable[hashValue];
            if (node.getKey().equals(key)) {
                node.setValue(newValue);
                System.out.println("The address has been updated successfully!");
                return true;
            }else{
                while (node.getNext() != null) {
                    node = node.getNext();
                    if (node.getKey().equals(key)) {
                        node.setValue(newValue);
                        System.out.println("The address has been updated successfully!");
                        return true;
                
                    }
      
                }
            }
        System.out.println(key + " doesn't exist!");
        return false;   
    }

    /**
     * Saves the table to a text file.
     * This method will ask the user about the file name, then, write the data to it.
     * if the file name doesn't exist, this method will create a new file and save data to it.
     *
     */
    public void save() {
        // File name should include full path and extension
        // For example "C:\Users\Adel\Desktop\test.txt"
        // The path of the file and extension must be written.
        String filename;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the file name: For example: C:\\Users\\Adel\\Desktop\\test.txt ");
        filename = s.nextLine();
        File f = new File(filename);
        // check if the the file exists 
        if (f.exists()) {
            System.out.println("The file is exists");
            // create BufferedWriter and write data to the file. 
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
                // Write data to file
                for (Node current : hashTable) {
                    if(current!=null){
                        writer.write(current.toString());
                        while(current.getNext()!=null){
                            current=current.getNext();
                            writer.write(current.toString());
                        }
                    }
                }
                
                System.out.println("The data has been successfully save to file:" + filename);
                writer.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("The file doesn't exist!");
            try { // if the file doesn't exist, then create a new file exactly as specified in 
                // fileName, and write data to it.
                f.createNewFile();
                System.out.println("The file has been successfully created!");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, false))) {
                    for (Node current : hashTable) {
                        if (current != null) {
                            writer.write(current.toString());
                            while (current.getNext() != null) {
                                current=current.getNext();
                                writer.write(current.toString());
                            }
                        }
                    }
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
     * Displays the content (name, address) of all nodes in all linked lists
     * in the hash table starting from index[0].
     * Also, it will display the hash value of each key.
     * The average probe length will also be displayed.
     *
     * @return The number of nodes in the hash table.
     */
    public int displayAll() {
        double probCount=0;
        double averageProbe;
        int index=0;
        
        int count=0;
        for (Node current : hashTable) {
            if(current==null){
                probCount++;
            }
            else {
                int hashValue=hash(current.getKey(), tableSize);
                System.out.println("HashTable["+index+"] Linked List content----->");
                System.out.print(current.toString());
                System.out.println(current.getKey()+" Hash Value="+ hashValue+"\n");
                probCount+=2;
                count++;
                while (current.getNext() != null) {
                    current = current.getNext();
                    hashValue=hash(current.getKey(), tableSize);
                    System.out.print(current.toString());
                    System.out.println(current.getKey()+" Hash Value="+ hashValue+"\n");
                    probCount++;
                    count++;
                    
                }
            }
            index++;
        }
        averageProbe=probCount/hashTable.length;
        System.out.println("The average probe length="+averageProbe);
        return count;
    }


}
